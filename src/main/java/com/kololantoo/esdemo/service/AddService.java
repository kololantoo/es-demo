package com.kololantoo.esdemo.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.repository.MyEsDemoRepository;
import com.kololantoo.esdemo.utils.SnowflakeIdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
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
// 使用lombok此注解之后，Autowired 的对象必须是final或者是@NotNull
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddService {

    @Autowired
    private final ElasticsearchRestTemplate template;
    @Autowired
    private final MyEsDemoRepository repository;
    @Autowired
    private final RestHighLevelClient client;
    @Autowired
    private final SnowflakeIdUtil snowflakeIdUtil;

    /**
     * 使用ElasticsearchRestTemplate新增
     * @param demo
     */
    public void addByTemplate(MyEsDemo demo) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.save(demo);
    }

    /**
     * 使用ElasticsearchRestTemplate批量新增
     * @param list
     */
    public void addBatchByTemplate(List<MyEsDemo> list) {
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.save(list);
    }

    /**
     * 使用Repository新增
     * @param demo
     */
    public void addByRepository(MyEsDemo demo) {
        repository.save(demo);
    }

    /**
     * 使用Repository批量新增
     * @param list
     */
    public void addBatchByRepository(List<MyEsDemo> list) {
        repository.saveAll(list);
    }

    /**
     * 使用RestClient新增
     * @param demo
     */
    public void addByRestClient(MyEsDemo demo) {
        String indexName = demo.getClass().getAnnotation(Document.class).indexName();
        IndexRequest indexRequest = new IndexRequest(indexName);

        indexRequest.id(String.valueOf(snowflakeIdUtil.nextId()));
        indexRequest.source(JSON.toJSONString(demo), XContentType.JSON);

        try {
            client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("保存失败");
        }
    }

    /**
     * 使用RestClient批量新增
     * @param list
     */
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
