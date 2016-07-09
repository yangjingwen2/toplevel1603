package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    public static void main(String[] args) {
        Schema schema = new Schema(1,"com.androidxx.yangjw.shoppingcart.dao");
        Entity shoppingCart = schema.addEntity("ShoppingCart");
        shoppingCart.addIdProperty().autoincrement();
        shoppingCart.addStringProperty("productName");
        shoppingCart.addFloatProperty("productPrice");
        shoppingCart.addIntProperty("productNum");

        try {
            new DaoGenerator().generateAll(schema,"../TopLevel1603/shoppingcartdemo/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
