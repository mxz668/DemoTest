package com.dripping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @Author: mabiao
 * @Date: 2020/7/1 15:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/es")
public class EsController {


//
//    @PostMapping("/search")
//    public EsResult search(@RequestBody String sql){
//        String[] hosts = new String[]{"10.108.3.115:9300"};
//        ElasticSearchSqlService searchSqlService = new ElasticSearchSqlService();
//        return searchSqlService.search(sql,hosts);
//    }
}
