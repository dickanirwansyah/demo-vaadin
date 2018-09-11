package com.spring.app.demovaadins.vaadin.ui;

import com.spring.app.demovaadins.backend.entity.Product;

import java.io.Serializable;

public class ProductModifiedEvent implements Serializable{

    private final Product product;

    public ProductModifiedEvent(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return product;
    }
}
