package com.example.demo.models;


import javax.jdo.annotations.Index;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Good {
    @Id
    @GeneratedValue
    private Integer Id;
    @Index(name = "idx_productNo")
    private String productNo;
    private String ProductName;
    private String productDescirption;
    private String unit;

    public Good(){

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescirption() {
        return productDescirption;
    }

    public void setProductDescirption(String productDescirption) {
        this.productDescirption = productDescirption;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
