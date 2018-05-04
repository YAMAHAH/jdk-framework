package com.example.demo.queryBuilder;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private Integer ord;
    private Integer level;
    private Integer nodeId;
    private Double singleTotal;
    private Double total;
    private Double mrpTotal;
    private Double needTotal;
    private Double cost;
    private Double price;
    @JsonIgnore
    private TreeNode parent;

    private List<TreeNode> childs = new ArrayList<>();
    private Boolean isVirtualNode = false;
    private Object tag;

    public Boolean getIsVirtualNode() {
        return isVirtualNode;
    }

    public void setIsVirtualNode(Boolean virtualNode) {
        isVirtualNode = virtualNode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Double getSingleTotal() {
        return singleTotal;
    }

    public void setSingleTotal(Double singleTotal) {
        this.singleTotal = singleTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getMrpTotal() {
        return mrpTotal;
    }

    public void setMrpTotal(Double mrpTotal) {
        this.mrpTotal = mrpTotal;
    }

    public Double getNeedTotal() {
        return needTotal;
    }

    public void setNeedTotal(Double needTotal) {
        this.needTotal = needTotal;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeNode> childs) {
        this.childs = childs;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }
}
