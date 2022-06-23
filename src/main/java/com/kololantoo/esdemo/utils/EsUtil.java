package com.kololantoo.esdemo.utils;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.RefreshPolicy;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengyang
 * @date 2022/6/20
 * @description
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EsUtil {
    @Autowired
    private final ElasticsearchRestTemplate template;

    // 基于ElasticsearchRestTemplate的增删改查
    public<T> void add(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.save(t, IndexCoordinates.of(indexName));
    }

    public<T> void delete(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
        template.setRefreshPolicy(RefreshPolicy.IMMEDIATE);
        template.delete(t,IndexCoordinates.of(indexName));
    }

    public<T> void update(T t) {
        String indexName = t.getClass().getAnnotation(Document.class).indexName();
    }

    public<T> void search(T t) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 使用QueryBuilders创建查询条件
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "张三");
        queryBuilder.withQuery(matchQuery);
    }

    public <T> List<T> getHitResult(NativeSearchQueryBuilder queryBuilder, Class<T> c) {
        List<T> result = new ArrayList<>();
        SearchHits<T> search = template.search(queryBuilder.build(),c);
        if (search.getTotalHits()>0) {
            List<T> hitResult = search.stream().map(SearchHit::getContent).toList();
            result.addAll(hitResult);
        }
        return result;
    }
}
