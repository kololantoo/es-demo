package com.kololantoo.esdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author zhengyang
 * @date 2022/6/20
 * @description
 */
@Document(indexName = "demo")
@Setting(replicas = 2,shards = 2)
@Data
public class MyEsDemo {

    /**
     * 测试使用id查询的相关语句
     */
    @Id
    @Field(type = FieldType.Long)
    private Long id;

    /**
     * 简单的text类型
     */
    @Field(type = FieldType.Text)
    private String normalText1;

    /**
     * 简单的text类型
     */
    @Field(type = FieldType.Text)
    private String normalText2;

    /**
     * 将normalText1和normalText2组合起来
     */
    @Field(type = FieldType.Text)
    private String combineText;

    /**
     * 简单的keyword类型
     */
    @Field(type = FieldType.Keyword)
    private String normalKeyword;

    /**
     * 既建索引时用 ik_max_word 尽可能多的分词，而搜索时用 ik_smart 尽可能提高匹配准度，让用户的搜索尽可能的准确
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String analyzeText;

    /**
     * 指定日期格式
     */
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private Date date;

    /**
     * Boolean类型的值
     */
    @Field(type = FieldType.Boolean)
    private Boolean boolVal;

    /**
     * Double 类型的值
     */
    @Field(type = FieldType.Double)
    private Double doubleVal;

    /**
     * List 类型的值
     */
    @Field(type = FieldType.Text)
    private List<String> list;
}
