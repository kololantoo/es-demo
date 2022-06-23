package com.kololantoo.esdemo.utils;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Component;

/**
 * @author zhengyang
 * @date 2022/6/23
 * @description
 */
@Component
public class EsPageUtil {

    private ElasticsearchRestTemplate template;

    /**
     * 获取该对象索引的一个滚动分页对象
     * @param query
     * @param c
     * @param pageSize
     * @return
     * @param <T>
     */
    public <T> EsPageContent<T> getPageData(NativeSearchQuery query, Class<T> c, int pageSize) {
        // 设置每页数据量
        String indexName = c.getAnnotation(Document.class).indexName();
        query.setMaxResults(pageSize);
        SearchScrollHits<T> searchHits = template.searchScrollStart(60 * 1000, query, c, IndexCoordinates.of(indexName));
        EsPageContent<T> content = new EsPageContent<>();
        content.setIndexName(indexName);
        content.setC(c);
        content.setHit(searchHits);
        content.setTemplate(template);
        return content;
    }

}
