package com.app.demovaadinmodule.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "person", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "address", nullable = false)
    private String address;

    private Boolean active;

    public Person(){}

    public Person(String firstname, String lastname, String email, Date birthday, String address){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthday = birthday;
        this.address = address;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public Boolean getActive(){
        return active;
    }

    public void setActive(Boolean active){
        this.active = active;
    }
}
