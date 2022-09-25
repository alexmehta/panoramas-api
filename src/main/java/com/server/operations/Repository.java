package com.server.operations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Repository extends JpaRepository<Item, Long> {
    @Query("select a from Item a")
    List<Item> fetchAll();
}
