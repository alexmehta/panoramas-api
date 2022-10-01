package com.server.tour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourRepo extends JpaRepository<Tour,Long> {

    @Query("select a from Tour a")
    List<Tour> fetchAll();
}
