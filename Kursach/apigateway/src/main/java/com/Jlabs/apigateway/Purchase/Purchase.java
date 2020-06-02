package com.Jlabs.ApiGateway.Purchase;

public class Purchase {
    private Integer id;
    private Integer customerId;
    private Float total;
    private Integer clothesId;

    Purchase() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getClothesId() {
        return clothesId;
    }

    public void setClothesId(Integer clothesId) { this.clothesId = clothesId; }

}