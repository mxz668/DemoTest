package com.dripping.controller;

import com.dripping.service.dingTalk.DingTalkUtil;
import com.dripping.service.elastic.ElasticSearchSqlService;
import com.dripping.service.elastic.EsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：钉钉
 * @Author: mabiao
 */
@RestController
@RequestMapping("/ding")
public class DingTalkController {

    @PostMapping("/send")
    public void search(@RequestBody String content){
        try {
            DingTalkUtil dingtalkUtil = new DingTalkUtil("", "", 843446571L);
            String s = dingtalkUtil.getSimplelist(1L);
            System.out.println("------------");
            System.out.println(s);
            String byMobile = dingtalkUtil.getByMobiles("");
            System.out.println(byMobile);
            String test = dingtalkUtil.sendJobNews(byMobile, "{\"msgType\":\"text\",\"content\":"+content+"}");
            System.out.println(test);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
