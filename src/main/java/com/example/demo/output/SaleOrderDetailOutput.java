package com.example.demo.output;

public class SaleOrderDetailOutput {
    private Integer soid;
    private String sono;
    private Integer Id;
    private Double total;
    private Double totalRemail;
    private Integer goid;
    private String gono;
    private String goName;
    private String goDescription;
    private String unit;

    public Integer getSoid() {
        return soid;
    }

    public void setSoid(Integer soId) {
        this.soid = soId;
    }

    public String getSono() {
        return sono;
    }

    public void setSono(String sono) {
        this.sono = sono;
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

    public Integer getGoid() {
        return goid;
    }

    public void setGoid(Integer goid) {
        this.goid = goid;
    }

    public String getGono() {
        return gono;
    }

    public void setGono(String gono) {
        this.gono = gono;
    }

    public String getGoName() {
        return goName;
    }

    public void setGoName(String goName) {
        this.goName = goName;
    }

    public String getGoDescription() {
        return goDescription;
    }

    public void setGoDescription(String goDescription) {
        this.goDescription = goDescription;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
