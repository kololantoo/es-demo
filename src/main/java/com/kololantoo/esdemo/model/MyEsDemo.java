package com.kololantoo.esdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * @author zhengyang
 * @date 2022/6/20
 * @description
 */
@Document(indexName = "demo")
@Setting(replicas = 2)
@Data
public class MyEsDemo {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Text)
    private String sex;

    @Field(type = FieldType.Long)
    private Long code;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String key;
}
