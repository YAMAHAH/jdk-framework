package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class FilterModel {
    private String property;

    private String operator;

    private Object value;

    private Boolean ignoreCase = true;

    private Boolean not = false;

    private List<FilterModel> childFilters = new ArrayList<>();

    /** 属性 */
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    /** 运算符 */
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    /** 值 */
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /** 是否忽略大小写 */
    public Boolean getIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(Boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public List<FilterModel> getChildFilters() {
        return childFilters;
    }

    public void setChildFilters(List<FilterModel> childFilters) {
        this.childFilters = childFilters;
    }

    public Boolean getNot() {
        return not;
    }

    public void setNot(Boolean not) {
        this.not = not;
    }

}
