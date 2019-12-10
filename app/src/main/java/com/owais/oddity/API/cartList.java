package com.owais.oddity.API;

public class cartList {
    String product_id;
    String quantity;

    public cartList(String product_id, String quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public cartList(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
