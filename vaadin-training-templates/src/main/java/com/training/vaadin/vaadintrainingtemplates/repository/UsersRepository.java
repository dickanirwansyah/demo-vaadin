package com.training.vaadin.vaadintrainingtemplates.repository;

import com.training.vaadin.vaadintrainingtemplates.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
