package com.liuxin.ladder.easyrules;

import java.util.List;

/**
 * @description:
 * @author: liuxin79
 * @date: 2020-10-30 15:01
 */
public class JsonRule {

    private String name;

    private String description;

    private String condition;

    private Integer priority;

    private List<String> actions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}
