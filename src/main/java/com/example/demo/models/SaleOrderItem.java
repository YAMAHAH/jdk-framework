package com.example.demo.models;


import javax.persistence.*;


@Entity
public class SaleOrderItem {
    @Id
    @GeneratedValue
    private Integer Id;
    private Double total;
    private Double totalRemail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salesOrder_id")
    private SalesOrder salesOrder;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="itemProduct_id")
    private Good itemProduct;

    public SaleOrderItem(){

    }

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

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Good getItemProduct() {
        return itemProduct;
    }

    public void setItemProduct(Good itemProduct) {
        this.itemProduct = itemProduct;
    }
}
