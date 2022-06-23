package com.kololantoo.esdemo.service;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.repository.MyEsDemoRepository;
import com.kololantoo.esdemo.utils.EsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SearchService {

    @Autowired
    private final ElasticsearchRestTemplate template;
    @Autowired
    private final MyEsDemoRepository repository;
    @Autowired
    private final RestHighLevelClient client;
    @Autowired
    private final EsUtil esUtil;

    /**
     * 使用Template进行条件搜索
     * @return
     */
    List<MyEsDemo> searchByTemplate() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 条件筛选，使用QueryBuilders符合需求的查询条件
        builder.withQuery(QueryBuilders.matchQuery("id",1));
        builder.withQuery(QueryBuilders.rangeQuery("doubleVal").gt(1d));
        builder.withQuery(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("boolVal",true)));
        // 排序
        builder.withSorts(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        // 分页
        Pageable pageable = PageRequest.of(1, 5);
        builder.withPageable(pageable);
        // 查询结果
        return esUtil.getHitResult(builder, MyEsDemo.class);
    }

    /**
     * 使用Repository进行条件搜索
     * @return
     */
    List<MyEsDemo> searchByRepository(Long id) {
        return repository.findAllByIdEquals(id);
    }

    /**
     * 使用RestClient进行条件搜索
     * @return
     */
    List<MyEsDemo> searchByRestClient() throws IOException {
        SearchScrollRequest request = new SearchScrollRequest();
        client.scroll(request, RequestOptions.DEFAULT);
        return null;
    }
}
