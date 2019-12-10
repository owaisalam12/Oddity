package com.owais.oddity;

import java.util.ArrayList;

public class AddCartModel {
    static ArrayList<CartModel> cartlistmodel = new ArrayList<>();

    // Methods for Cart
    public void addCartListModel(CartModel cartModel) {
        this.cartlistmodel.add(0, cartModel);
    }

    public void removeCartListModel(int position) {
        this.cartlistmodel.remove(position);
    }

    public ArrayList<CartModel> getCartListModel() {
        return this.cartlistmodel;
    }
}
