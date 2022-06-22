package com.kololantoo.esdemo.service;

import com.alibaba.fastjson2.JSON;
import com.kololantoo.esdemo.model.MyEsDemo;
import com.kololantoo.esdemo.utils.EsUtil;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhengyang
 * @date 2022/6/21
 * @description
 */
@Service
public class UpdateService {

    @Autowired
    private EsUtil esUtil;

    /**
     * 使用null覆盖旧字段
     */
    public void updateWithNull() {
        MyEsDemo demo = new MyEsDemo();
        demo.setId(1L);
//        74113657
        demo.setNormalText1("test"+"74113657");
//        demo.setNormalText1(null);
        List<MyEsDemo> list = new ArrayList<>();
        list.add(demo);
        saveOrUpdate(list);
    }

    private void saveOrUpdate(List<MyEsDemo> list) {
        BulkProcessor bulkProcessor = esUtil.createBulkProcessor();
        List<UpdateRequest> updateRequests=new ArrayList<>();
        list.forEach(e->{
            //获取id
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index("demo");
            // true，表明如果文档不存在，则新更新的文档内容作为新的内容插入文档，这个和scriptedUpsert的区别是：更新文档的两种不同方式，有的使用doc方法更新有的使用脚本更新
            updateRequest.docAsUpsert(true);
            //更新的id
            updateRequest.id(String.valueOf(e.getId()));
            //更新的数据
            String json = JSON.toJSONString(e);
            Map<String,Object> map = JSON.parseObject(json);
            updateRequest.doc(map);
            updateRequests.add(updateRequest);
        });
        updateRequests.forEach(bulkProcessor::add);
    }
}
