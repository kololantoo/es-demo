package com.kololantoo.esdemo.service;

import com.alibaba.fastjson2.JSON;
import com.kololantoo.esdemo.model.MyEsDemo;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description
 */
@Service
public class UpdateService {

    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private RestHighLevelClient client;

    /**
     * 使用Template的save方法进行更新，该方法会覆盖会将id相等数据的所有字段均替换为新传入对象的字段值，null也会覆盖，若id不匹配，则新增
     * @param demo
     */
    public void updateByTemplateSave(MyEsDemo demo) {
        template.save(demo);
    }

    /**
     * 使用Template进行update，注意withDocAsUpsert属性
     * @param demo
     */
    public void updateByTemplate(MyEsDemo demo) {
        Document document = Document.create();
        document.fromJson(JSON.toJSONString(demo));
        UpdateQuery query = UpdateQuery
                .builder(String.valueOf(demo.getId()))
                .withDocument(document)
                // 文档不存在时插入
                .withDocAsUpsert(true)
                .build();
        template.update(query, IndexCoordinates.of("demo"));
    }

    /**
     * 使用Template进行批量更新
     * @param list
     */
    public void updateBatchByTemplate(List<MyEsDemo> list) {
        List<UpdateQuery> updateQueryList = new ArrayList<>();
        for (MyEsDemo demo : list) {
            // todo 验证是否可以不传入id
            Document document = Document.create();
            document.fromJson(JSON.toJSONString(demo));
            UpdateQuery query = UpdateQuery
                    .builder(String.valueOf(demo.getId()))
                    .withDocument(document)
                    // 文档不存在时插入
                    .withDocAsUpsert(true)
                    .build();
            updateQueryList.add(query);
        }
        template.bulkUpdate(updateQueryList,MyEsDemo.class);
    }

    /**
     * 使用Template进行条件查询更新，该方法将是的查询到的对象中，Document中指定的字段都被更新为对应值
     */
    public void queryUpdateByTemplate() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("id",1));
        Document document = Document.create();
        document.put("normalText1","使用esTemplate进行条件查询更新");
        // 条件更新，将其normalText1字段进行更新
        UpdateQuery query = UpdateQuery
                .builder(queryBuilder.build())
                .withDocument(document)
                .build();
        template.updateByQuery(query,IndexCoordinates.of("demo"));
    }


    /**
     * 使用RestClient进行更新
     * @param demo
     * @throws IOException
     */
    public void updateByRestClient(MyEsDemo demo) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("demo", String.valueOf(demo.getId()));
        Document document = Document.create();
        document.fromJson(JSON.toJSONString(demo));
        updateRequest.doc(document);
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    /**
     * 使用RestClient进行批量更新
     * @param list
     * @throws IOException
     */
    public void batchUpdateByRestClient(List<MyEsDemo> list) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        list.forEach(e->{
            //获取id
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index("demo");
            // true，表明如果文档不存在，则新更新的文档内容作为新的内容插入文档，这个和scriptedUpsert的区别是：更新文档的两种不同方式，有的使用doc方法更新有的使用脚本更新
            updateRequest.docAsUpsert(true);
            //更新的id
            updateRequest.id(String.valueOf(e.getId()));
            //更新的数据
            Document document = Document.create();
            document.fromJson(JSON.toJSONString(e));
            updateRequest.doc(document);
            bulkRequest.add(updateRequest);
        });
        client.bulk(bulkRequest,RequestOptions.DEFAULT);
    }

    /**
     * 使用RestClient条件查询更新
     * 将使用QueryBuilder中的条件进行查询，并将Document中指定的字段都被更新为对应值
     * @throws IOException
     */
    public void queryUpdateByRestClient() throws IOException {
        //查询条件如果是and关系使用must 如何是or关系使用should
        BoolQueryBuilder boolQueryBuilder =  QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("id","1"))
                .should(QueryBuilders.rangeQuery("doubleVal").gt(1d));
        Document document = Document.create();
        document.put("normalText1","使用restClient进行条件查询更新");
        UpdateByQueryRequest request = getUpdateRequst("demo", boolQueryBuilder, document);
        client.updateByQuery(request,RequestOptions.DEFAULT);
    }

    /**
     * 构建UpdateByQueryRequest
     * @param index
     * @param query
     * @param document
     * @return
     */
    public UpdateByQueryRequest getUpdateRequst(String index, QueryBuilder query, Document document) {
        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest(index);
        //设置分片并行
        updateByQueryRequest.setSlices(2);
        //设置版本冲突时继续执行
        updateByQueryRequest.setConflicts("proceed");
        //设置更新完成后刷新索引 ps很重要如果不加可能数据不会实时刷新
        updateByQueryRequest.setRefresh(true);
        updateByQueryRequest.setQuery(query);
        StringBuilder script = new StringBuilder();
        Set<String> keys = document.keySet();
        for (String key : keys) {
            String appendValue = "";
            Object value = document.get(key);
            if (value instanceof Number) {
                appendValue = value.toString();
            } else if (value instanceof String) {
                appendValue = "'" + value.toString() + "'";
            } else if (value instanceof List){
                appendValue = JSON.toJSONString(value);
            } else {
                appendValue = value.toString();
            }
            script.append("ctx._source.").append(key).append("=").append(appendValue).append(";");
        }
        updateByQueryRequest.setScript(new Script(script.toString()));
        return updateByQueryRequest;
    }
}
