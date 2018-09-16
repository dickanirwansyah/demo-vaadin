package com.app.demovaadinmodule.vaadin;

import com.app.demovaadinmodule.model.Person;

import java.io.Serializable;

public class PersonModifiedEvent implements Serializable {

    private final Person person;

    public PersonModifiedEvent(Person person){
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }
}
