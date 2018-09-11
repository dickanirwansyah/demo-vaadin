package com.app.demovaadincrud.service;

import com.app.demovaadincrud.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person create(Person person);

    Optional<Person> getId(Long id);

    List<Person> list();

    void delete(Long id);

    void delete(Person person);

    Person update(Person person);

}
