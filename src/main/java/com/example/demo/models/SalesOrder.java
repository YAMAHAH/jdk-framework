package com.example.demo.models;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class SalesOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private String SaleOrderNo;
    private Date SaleDate;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "salesOrder")
    private List<SaleOrderItem> SaleOrderItems;

    public SalesOrder(){
    }

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

    public List<SaleOrderItem> getSaleOrderItems() {
        return SaleOrderItems;
    }

    public void setSaleOrderItems(List<SaleOrderItem> saleOrderItems) {
        SaleOrderItems = saleOrderItems;
    }
}
