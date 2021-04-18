package com.edu.javeriana.farmaceutica.repositories;

import com.edu.javeriana.farmaceutica.entities.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
