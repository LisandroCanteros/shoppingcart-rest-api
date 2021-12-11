package com.informatorio.shoppingcart.dto;

public class CartOperation {
    private Integer amount;
    private Long prodId;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }
}
