package com.kololantoo.esdemo.service;

import com.kololantoo.esdemo.model.MyEsDemo;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        list.add("hello1");
        list.add("world1");
        demo.setList(list);
        template.save(demo);

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
        list2.add("hello2");
        list2.add("world2");
        demo2.setList(list2);
        template.save(demo2);

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
        list3.add("hello3");
        list3.add("world3");
        demo3.setList(list3);
        template.save(demo3);

        MyEsDemo demo4 = new MyEsDemo();
        demo4.setId(4L);
        demo4.setNormalText1("10086");
        demo4.setNormalText2("这是一个没用的测试数据");
        demo4.setCombineText("10086"+"_"+"这是一个没用的测试数据");
        demo4.setNormalKeyword("10086");
        demo4.setAnalyzeText("这是一个没用的测试数据");
        demo4.setDate(new Date(2023));
        demo4.setBoolVal(true);
        demo4.setDoubleVal(-2d);
        List<String> list4 = new ArrayList<>();
        list4.add("hello1");
        list4.add("world2");
        demo4.setList(list4);
        template.save(demo4);
    }

    /**
     * 清除所有数据
     */
    public void truncate() {
        template.delete(new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build(),MyEsDemo.class);
    }
}
