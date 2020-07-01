package com.dripping.service.elastic;

import com.alibaba.fastjson.JSONArray;

/**
 * 描述：EsSQL 响应对象
 *
 * @Author: mabiao
 * @Date: 2020/7/1 15:27
 * @Version 1.0
 */
public class EsResult {

    public JSONArray array;


    public JSONArray getArray() {
        return array;
    }

    public void setArray(JSONArray array) {
        this.array = array;
    }
}
