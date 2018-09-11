package com.spring.app.demovaadins.backend.repository;

import com.spring.app.demovaadins.backend.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{


    List<Product> findAllBy(Pageable pageable);
    List<Product> findByNameLikeIgnoreCase(String nameFilter);
    List<Product> findByNameLikeIgnoreCase(String nameFilter, Pageable pageable);
    long countByNameLikeIgnoreCase(String nameFilter);

}
