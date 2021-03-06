package com.dripping.EasyRule;

import com.dripping.service.EasyRule.*;
import com.dripping.service.EasyRule.regular.JudgeIpRule;
import com.dripping.service.EasyRule.regular.RegularRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;
import org.junit.Test;

import java.io.FileReader;

/**
 * 描述：
 */
public class RuleExcutor {

    @Test
    public void excuteRule(){
        /**
         * 创建规则执行引擎
         * 注意: skipOnFirstAppliedRule意思是，只要匹配到第一条规则就跳过后面规则匹配
         */
        RulesEngineParameters parameters = new
                RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        //创建规则
        Rules rules = new Rules();
        rules.register(new EightCheckRule());
        rules.register(new SixCheckRule());
        rules.register(new SixEightUnitGroupRule(new EightCheckRule(), new SixCheckRule()));
        rules.register(new OtherCheckRule());
        Facts facts = new Facts();
        for (int i=1 ; i<=50 ; i++){
            //规则因素，对应的name，要和规则里面的@Fact 一致
            facts.put("number", i);
            //执行规则
            rulesEngine.fire(rules, facts);
            System.out.println();
        }
    }

    @Test
    public void weatherRule(){
        try {
            MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
            Rules rules = ruleFactory.createRules(new FileReader("D:\\WorkSpace\\DemoTest\\service\\src\\main\\resources\\weather-rule.yml"));

            Facts facts = new Facts();
            facts.put("weather", "sun");

            RulesEngine rulesEngine = new DefaultRulesEngine();
            rulesEngine.fire(rules, facts);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void regularRule(){
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        rules.register(new RegularRule());
        Facts facts = new Facts();
        facts.put("content","是正则");
        rulesEngine.fire(rules,facts);
    }

    @Test
    public void judgeIp(){
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        rules.register(new JudgeIpRule());
        Facts facts = new Facts();
        facts.put("content","!254.254.254.254 127.0.0.1localhost192.168.2.1localhost");
        rulesEngine.fire(rules,facts);
    }
}
