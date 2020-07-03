package com.dripping.service.EasyRule;

import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.composite.UnitRuleGroup;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;

import java.io.FileReader;

/**
 * 描述：同时被6和8整除，组合校验
 *
 * @Author: mabiao
 * @Date: 2020/7/3 15:48
 * @Version 1.0
 */
@Rule(name = "被6和8同时整除",  description = "组合规则")
public class SixEightUnitGroupRule extends UnitRuleGroup {

    public SixEightUnitGroupRule(Object... rules) {
        for (Object rule : rules) {
            addRule(rule);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

}
