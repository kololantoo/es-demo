package com.kololantoo.esdemo.service;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.utils.EsUtil;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description 用于初始化示例索引在es中的数据
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitService {

    @Autowired
    private final ElasticsearchRestTemplate template;
    @Autowired
    EsUtil esUtil;

    /**
     * 初始化基础数据
     */
    public void init() {
        MyEsDemo demo = new MyEsDemo();
        demo.setId(1L);
        demo.setNormalText1("74113657");
        demo.setNormalText2("农银匠心·天天利固收增强");
        demo.setCombineText("74113657"+"_"+"农银匠心·天天利固收增强");
        demo.setNormalKeyword("74113657");
        demo.setAnalyzeText("农银匠心·天天利固收增强");
        demo.setDate(new Date(2020));
        demo.setBoolVal(false);
        demo.setDoubleVal(3.14);
        List<String> list = new ArrayList<>();
        list.add("hello");
        demo.setList(list);
        esUtil.add(demo);

        MyEsDemo demo2 = new MyEsDemo();
        demo2.setId(2L);
        demo2.setNormalText1("74137296");
        demo2.setNormalText2("苏银理财恒源季开放3号A类份额");
        demo2.setCombineText("74137296"+"_"+"苏银理财恒源季开放3号A类份额");
        demo2.setNormalKeyword("74137296");
        demo2.setAnalyzeText("苏银理财恒源季开放3号A类份额");
        demo2.setDate(new Date(2021));
        demo2.setBoolVal(true);
        demo2.setDoubleVal(4.33);
        List<String> list2 = new ArrayList<>();
        list2.add("world");
        demo2.setList(list2);
        esUtil.add(demo2);

        MyEsDemo demo3 = new MyEsDemo();
        demo3.setId(3L);
        demo3.setNormalText1("74118749");
        demo3.setNormalText2("华夏理财龙盈权益类G款1号三个月定开FOF型");
        demo3.setCombineText("74118749"+"_"+"华夏理财龙盈权益类G款1号三个月定开FOF型");
        demo3.setNormalKeyword("74118749");
        demo3.setAnalyzeText("华夏理财龙盈权益类G款1号三个月定开FOF型");
        demo3.setDate(new Date(2022));
        demo3.setBoolVal(true);
        demo3.setDoubleVal(10.1);
        List<String> list3 = new ArrayList<>();
        list3.add("here");
        demo3.setList(list3);
        esUtil.add(demo3);
    }

    /**
     * 清除所有数据
     */
    public void truncate() {
        template.delete(new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build(),MyEsDemo.class);
    }
}
