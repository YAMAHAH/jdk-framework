package com.example.demo.output;

public class GoodOutput {
    private Integer Id;
    private String productNo;
    private String ProductName;
    private String productDescirption;
    private String unit;

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
