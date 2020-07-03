package com.dripping.service.EasyRule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * 描述：既不被6也不被8整除
 *
 * @Author: mabiao
 * @Date: 2020/7/3 15:50
 * @Version 1.0
 */
@Rule(name = "既不被6也不被8整除", description = "打印number自己")
public class OtherCheckRule {

    @Condition
    public boolean check(@Fact("number") int number){
        return number % 6 != 0 || number % 8 != 0;
    }

    @Action
    public void action(@Fact("number") int number){
        System.out.println(number);
    }

    @Priority
    public int getPriority(){
        return 3;
    }

}
