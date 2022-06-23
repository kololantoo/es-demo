package com.kololantoo.esdemo.controller;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.service.SearchService;
import com.kololantoo.esdemo.utils.EsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhengyang
 * @date 2022/6/22
 * @description
 */
@RestController
@RequestMapping("search")
@Api(tags = "查询")
public class SearchController {

    @Autowired
    private SearchService service;
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Autowired
    private EsUtil esUtil;

    @PostMapping("terms")
    @ApiOperation("terms查询")
    public List<MyEsDemo> terms(@RequestBody List<String> list) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 使用termsQuery时，只要list字段包含有传入字段中的任意一个字段就会被查找到，是一个or的关系
        builder.withQuery(QueryBuilders.termsQuery("list",list));
        return esUtil.getHitResult(builder, MyEsDemo.class);
    }

    @PostMapping("term")
    @ApiOperation("term查询")
    public List<MyEsDemo> term(@RequestBody List<String> list) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        // 通过bool添加形成组合条件，形成 and 的关系
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        for (String term : list) {
            boolQuery.must(QueryBuilders.termQuery("list",term));
        }
        builder.withQuery(boolQuery);
        return esUtil.getHitResult(builder, MyEsDemo.class);
    }
}
