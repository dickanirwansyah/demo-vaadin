package com.training.vaadin.vaadintrainingtemplates.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@Entity
@Table(name = "users")
public class Users {

    @Id
    private String id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String createdBy;

    @Column
    private String createdAd;
}
