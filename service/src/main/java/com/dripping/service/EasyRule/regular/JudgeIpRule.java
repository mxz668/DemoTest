package com.dripping.service.EasyRule.regular;

import org.jeasy.rules.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Rule(name = "判断IP", description = "判断IP")
public class JudgeIpRule {

    @Condition //条件判断注解：如果return true， 执行Action
    public boolean check(@Fact("content") String log){
        String regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        return true;
    }

    @Action //执行方法注解
    public void action(@Fact("content") String content){
        // "\\d+\\.\\d+\\.\\d+\\.\\d+" 只能找到第一个
        String regex = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        Pattern r = Pattern.compile(regex);
        Matcher matcher = r.matcher(content);
        while (matcher.find()) {
            String ip = matcher.group();
            System.out.println("ip: " + ip);
        }
        System.out.println(content);
    }

    @Priority //优先级注解：return 数值越小，优先级越高
    public int getPriority(){
        return 1;
    }

}