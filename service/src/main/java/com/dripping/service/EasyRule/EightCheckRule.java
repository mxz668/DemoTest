package com.dripping.service.EasyRule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * 描述：8或8的倍数校验
 *
 * @Author: mabiao
 * @Date: 2020/7/3 15:41
 * @Version 1.0
 */
@Rule(name = "被8整除", description = "number如果被8整除，打印：number 被8整除")
public class EightCheckRule {

    @Condition //条件判断注解：如果return true， 执行Action
    public boolean check(@Fact("number") int number){
        return number % 8 == 0;
    }

    @Action //执行方法注解
    public void action(@Fact("number") int number){
        System.out.println(number + " 被8整除");
    }

    @Priority //优先级注解：return 数值越小，优先级越高
    public int getPriority(){
        return 1;
    }
}
