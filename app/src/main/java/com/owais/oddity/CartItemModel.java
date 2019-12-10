package com.owais.oddity;

import java.util.List;

public class CartItemModel {
    private List<String> name;
    private List<String> price;
    private List<Integer> quantity;
    private String name2;
    private String price2;
    private int quantity2;

    public CartItemModel(String name2, String price2, int quantity2) {
        this.name2 = name2;
        this.price2 = price2;
        this.quantity2 = quantity2;
    }

    public CartItemModel(String name2) {
        this.name2 = name2;
    }

    public CartItemModel(List<String> name, List<String> price, List<Integer> quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public int getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(int quantity2) {
        this.quantity2 = quantity2;
    }



    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public int getSize(){
        return name.size();
    }
}
