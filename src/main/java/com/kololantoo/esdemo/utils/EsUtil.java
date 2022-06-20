package com.kololantoo.esdemo.utils;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhengyang
 * @date 2022/6/20
 * @description
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Resource))
public class EsUtil {

    @Resource
    private final RestHighLevelClient restHighLevelClient;
    @Resource
    private final ElasticsearchRestTemplate template;

    // 基于ElasticsearchRestTemplate的增删改查
    public<T> void add(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
        template.save(t, IndexCoordinates.of(indexName));
    }

    public<T> void delete(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
        template.delete(t,IndexCoordinates.of(indexName));
    }

    public<T> void update(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
    }

    public<T> void search(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();

    }
}
