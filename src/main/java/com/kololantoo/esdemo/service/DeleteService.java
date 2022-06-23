package com.kololantoo.esdemo.service;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.repository.MyEsDemoRepository;
import com.kololantoo.esdemo.utils.EsUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkProcessor;
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
    @Autowired
    private EsUtil esUtil;

    public void deleteByTemplate(MyEsDemo demo) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.delete(demo);
    }

    public void deleteBatchByTemplate(List<MyEsDemo> list) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        for (MyEsDemo demo : list) {
            template.delete(demo);
        }
    }

    public void queryDeleteByTemplate() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.matchQuery("id",1));
        template.delete(builder.build(), MyEsDemo.class);
    }

    public void deleteByRepository(MyEsDemo demo) {
        repository.deleteById(demo.getId());
    }

    public void deleteBatchByRepository(List<MyEsDemo> list) {
        List<Long> idList = list.stream().map(MyEsDemo::getId).collect(Collectors.toList());
        repository.deleteAllById(idList);
    }

    public void deleteByRestClient(MyEsDemo demo) {
        String indexName = demo.getClass().getAnnotation(Document.class).indexName();
        DeleteRequest deleteRequest = new DeleteRequest(indexName, String.valueOf(demo.getId()));
        try {
            client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteBatchByRestClient(List<MyEsDemo> list) {
        BulkProcessor bulkProcessor = esUtil.createBulkProcessor();
        List<DeleteRequest> deleteRequestList=new ArrayList<>();
        list.forEach(e->{
            DeleteRequest deleteRequest = new DeleteRequest("demo", String.valueOf(e.getId()));
            deleteRequestList.add(deleteRequest);
        });
        deleteRequestList.forEach(bulkProcessor::add);
    }

    public void queryDeleteByRestClient() {
        DeleteByQueryRequest request = new DeleteByQueryRequest("sub_bank1031");
        request.setQuery(QueryBuilders.matchQuery("id",1));
        try {
            client.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
