package com.kololantoo.esdemo.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
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

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description
 */
@Service
@Slf4j
public class AddService {

    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private MyEsDemoRepository repository;
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private SnowflakeIdUtil snowflakeIdUtil;

    public void addByTemplate(MyEsDemo demo) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.save(demo);
    }

    public void addBatchByTemplate(List<MyEsDemo> list) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.save(list);
    }

    public void addByRepository(MyEsDemo demo) {
        repository.save(demo);
    }

    public void addBatchByRepository(List<MyEsDemo> list) {
        repository.saveAll(list);
    }

    public void addByRestClient(MyEsDemo demo) {
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

    public void addBatchByRestClient(List<MyEsDemo> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String indexName = list.get(0).getClass().getAnnotation(Document.class).indexName();

        BulkRequest request = new BulkRequest();
        for (MyEsDemo demo : list) {
            request.add(new IndexRequest(indexName)
                    .id(String.valueOf(snowflakeIdUtil.nextId()))
                    .source(JSONObject.toJSONString(demo), XContentType.JSON)
            );
        }

        try {
            client.bulk(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
