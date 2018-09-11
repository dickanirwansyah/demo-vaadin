package com.app.demovaadincrud.repository;

import com.app.demovaadincrud.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
}
