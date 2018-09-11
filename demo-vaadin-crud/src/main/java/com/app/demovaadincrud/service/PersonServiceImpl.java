package com.app.demovaadincrud.service;

import com.app.demovaadincrud.entity.Person;
import com.app.demovaadincrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> getId(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> list() {
        return personRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Person update(Person person) {
        if (person.getId()!=null){
            return personRepository.save(person);
        }
        return person;
    }
}
