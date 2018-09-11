package com.spring.app.demovaadins.backend.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "createdAt")
    private Date createdAt;

    private Boolean colleague;

    public Product(){

    }

    public Product(String name, int stock, int price, Date createdAt){
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }

    public Boolean getColleague(){
        return colleague;
    }

    public void setColleague(Boolean colleague){
        this.colleague = colleague;
    }


}
