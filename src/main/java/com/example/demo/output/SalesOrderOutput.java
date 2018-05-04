package com.example.demo.output;

import java.sql.Date;
import java.util.List;

public class SalesOrderOutput {

    private Integer id;
    private String SaleOrderNo;
    private Date SaleDate;

    private List<SalesOrderItemOutput> SaleOrderItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleOrderNo() {
        return SaleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        SaleOrderNo = saleOrderNo;
    }

    public Date getSaleDate() {
        return SaleDate;
    }

    public void setSaleDate(Date saleDate) {
        SaleDate = saleDate;
    }

    public List<SalesOrderItemOutput> getSaleOrderItems() {
        return SaleOrderItems;
    }

    public void setSaleOrderItems(List<SalesOrderItemOutput> saleOrderItems) {
        SaleOrderItems = saleOrderItems;
    }
}
