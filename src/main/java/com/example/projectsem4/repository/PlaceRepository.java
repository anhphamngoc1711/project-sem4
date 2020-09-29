package com.example.projectsem4.repository;


import com.example.projectsem4.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    @Transactional
    @Modifying
    @Query("delete from Place e where e.place_id=:x")
     int deletePlace(@Param("x") int place_id);
}
