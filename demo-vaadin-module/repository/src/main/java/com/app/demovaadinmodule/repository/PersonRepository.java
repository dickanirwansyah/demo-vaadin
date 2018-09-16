package com.app.demovaadinmodule.repository;

import com.app.demovaadinmodule.model.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllBy(Pageable pageable);
    List<Person> findByFirstnameLikeIgnoreCase(String firstnameFilter);
    List<Person> findByFirstnameLikeIgnoreCase(String firstnameFilter, Pageable pageable);
    long countByFirstnameLikeIgnoreCase(String firstnameFilter);
}
