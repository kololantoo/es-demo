package com.kololantoo.esdemo.service;

import com.alibaba.fastjson2.JSON;
import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.repository.MyEsDemoRepository;
import com.kololantoo.esdemo.utils.SnowflakeIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
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
    private SnowflakeIdUtil snowflakeIdUtil;

    public void deleteByTemplate(MyEsDemo demo) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.delete(demo);
    }

    public void deleteBatchByTemplate(List<MyEsDemo> list) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        for (MyEsDemo demo : list) {
            repository.deleteById(demo.getId());

        }
    }

    public void queryDeleteByTemplate() {
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
        IndexRequest indexRequest = new IndexRequest(indexName);

        indexRequest.id(String.valueOf(snowflakeIdUtil.nextId()));
        indexRequest.source(JSON.toJSONString(demo), XContentType.JSON);

        try {
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("保存失败");
        }
    }

    public void deleteBatchByRestClient(List<MyEsDemo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String indexName = list.get(0).getClass().getAnnotation(Document.class).indexName();

        BulkRequest request = new BulkRequest();
        for (MyEsDemo demo : list) {
        }

        try {
            client.bulk(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
