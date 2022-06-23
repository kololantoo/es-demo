package com.kololantoo.esdemo.utils;

import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhengyang
 * @date 2022/5/30
 * @description es分页查询结果对象
 */
public class EsPageContent<T> {

    private SearchScrollHits<T> hit;

    private ElasticsearchRestTemplate template;

    private String indexName;

    private Class<T> c;

    List<String> scrollIds = new ArrayList<>();

    /**
     * 是否有数据
     * @return
     */
    public boolean hasNext() {
        return this.hit.hasSearchHits();
    }

    /**
     * 获取下一个scroll中的数据
     * @return
     */
    public List<T> getNext() {
        String scrollId = this.hit.getScrollId();
        this.scrollIds.add(scrollId);
        List<SearchHit<T>> data = this.hit.getSearchHits();
        List<T> result = data.stream().map(SearchHit::getContent).collect(Collectors.toList());
        // 后续查询
        this.hit = template.searchScrollContinue(scrollId, 60 * 1000, this.c,IndexCoordinates.of(this.indexName));
        return result;
    }

    /**
     * 清除scroll游标
     */
    public void clearScrollIds() {
        this.template.searchScrollClear(scrollIds);
    }

    public void setHit(SearchScrollHits<T> hit) {
        this.hit = hit;
    }

    public void setTemplate(ElasticsearchRestTemplate template) {
        this.template = template;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setC(Class<T> c) {
        this.c = c;
    }
}
