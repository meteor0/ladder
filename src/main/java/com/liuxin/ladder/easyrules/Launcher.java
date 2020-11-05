package com.liuxin.ladder.easyrules;

import com.alibaba.fastjson.JSONArray;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;
import org.mvel2.ParserContext;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: liuxin79
 * @date: 2020-10-29 15:06
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        ParserContext context = new ParserContext();
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new JsonRuleDefinitionReader(), context);
        context.addImport("Launcher", Launcher.class);

        //指定规则 name=刘新 执行Launcher.consoleLog(output)方法
        JsonRule jsonRule = new JsonRule();
        jsonRule.setName("myRules");
        jsonRule.setDescription("测试rule");
        jsonRule.setCondition("name.equals(\"刘新\")");
        jsonRule.setPriority(3);
        List<String> actionList = new ArrayList<>();
        actionList.add("Launcher.consoleLog(output)");
        jsonRule.setActions(actionList);
        List<JsonRule> jsonRuleList = new ArrayList<>();
        jsonRuleList.add(jsonRule);
        String json = JSONArray.toJSONString(jsonRuleList);


        Reader reader = new StringReader(json);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        //参数
        Facts facts = new Facts();
        facts.put("name", "刘新");
        facts.put("output", "1234");
        //创建规则
        Rule rule = ruleFactory.createRule(reader);
        //注册规则
        Rules rules = new Rules();
        rules.register(rule);
        //执行规则
        rulesEngine.fire(rules, facts);
        final Map<Rule, Boolean> check = rulesEngine.check(rules, facts);
    }


    public static void consoleLog(String output) throws Exception {
        System.out.println(output);
        throw new Exception("123");
    }
}
