package com.dripping.service.EasyRule.regular;


import org.jeasy.rules.annotation.*;

import java.util.regex.Pattern;

@Rule(name = "正则", description = "正则")
public class RegularRule {

    @Condition //条件判断注解：如果return true， 执行Action
    public boolean check(@Fact("content") String log){
        String regex = ".*正则.*";
        return Pattern.matches(regex, log);
    }

    @Action //执行方法注解
    public void action(@Fact("content") String content){
        System.out.println(content);
    }

    @Priority //优先级注解：return 数值越小，优先级越高
    public int getPriority(){
        return 1;
    }

}
