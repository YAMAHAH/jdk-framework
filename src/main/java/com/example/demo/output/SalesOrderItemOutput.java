package com.example.demo.output;


public class SalesOrderItemOutput {
    private Integer Id;
    private Double total;
    private Double totalRemail;
    private GoodOutput itemProduct;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalRemail() {
        return totalRemail;
    }

    public void setTotalRemail(Double totalRemail) {
        this.totalRemail = totalRemail;
    }

    public GoodOutput getItemProduct() {
        return itemProduct;
    }

    public void setItemProduct(GoodOutput itemProduct) {
        this.itemProduct = itemProduct;
    }
}
