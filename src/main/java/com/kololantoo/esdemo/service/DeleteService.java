package com.kololantoo.esdemo.service;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.repository.MyEsDemoRepository;
import com.kololantoo.esdemo.utils.EsUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description
 */
@Service
@Slf4j
public class DeleteService {


    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private MyEsDemoRepository repository;
    @Autowired
    private RestHighLevelClient client;

    /**
     * 使用Template删除
     * @param demo
     */
    public void deleteByTemplate(MyEsDemo demo) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.delete(demo);
    }

    /**
     * 使用Template批量删除
     * @param list
     */
    public void deleteBatchByTemplate(List<MyEsDemo> list) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        for (MyEsDemo demo : list) {
            template.delete(demo);
        }
    }

    /**
     * 使用Template根据查询条件删除
     */
    public void queryDeleteByTemplate() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.matchQuery("id",1));
        template.delete(builder.build(), MyEsDemo.class);
    }

    /**
     * 使用Repository删除
     * @param demo
     */
    public void deleteByRepository(MyEsDemo demo) {
        repository.deleteById(demo.getId());
    }

    /**
     * 使用Repository批量删除
     * @param list
     */
    public void deleteBatchByRepository(List<MyEsDemo> list) {
        List<Long> idList = list.stream().map(MyEsDemo::getId).collect(Collectors.toList());
        repository.deleteAllById(idList);
    }

    /**
     * 使用RestClient删除
     * @param demo
     * @throws IOException
     */
    public void deleteByRestClient(MyEsDemo demo) throws IOException {
        String indexName = demo.getClass().getAnnotation(Document.class).indexName();
        DeleteRequest deleteRequest = new DeleteRequest(indexName, String.valueOf(demo.getId()));
        client.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * 使用RestClient批量删除
     * @param list
     * @throws IOException
     */
    public void deleteBatchByRestClient(List<MyEsDemo> list) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        list.forEach(e->{
            DeleteRequest deleteRequest = new DeleteRequest("demo", String.valueOf(e.getId()));
            bulkRequest.add(deleteRequest);
        });
        client.bulk(bulkRequest,RequestOptions.DEFAULT);
    }

    /**
     * 使用RestClient条件查询删除
     * @throws IOException
     */
    public void queryDeleteByRestClient() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest("sub_bank1031");
        request.setQuery(QueryBuilders.matchQuery("id",1));
        client.deleteByQuery(request, RequestOptions.DEFAULT);
    }

}
