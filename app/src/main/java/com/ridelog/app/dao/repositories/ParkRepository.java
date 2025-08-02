package com.ridelog.app.dao.repositories;


import com.ridelog.app.dao.model.Park;
import com.ridelog.app.ui.models.ParkDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

    @Query("SELECT new Park (p.id, p.name, p.location, p.description) " +
            "FROM Park p")
    Page<ParkDTO> findAllParquesDTO(Pageable pageable);

    @Query("SELECT p FROM Park p WHERE p.name ILIKE %:name% OR p.location ILIKE %:location%")
    Page<Park> findByNameOrLocationContainingIgnoreCase(@Param("name") String name,
                                                          @Param("location") String location,
                                                          Pageable pageable);

    @Query("SELECT p FROM Park p WHERE p.id IN :parkIds")
    List<Park> findAllByIdIn(@Param("parkIds") List<Long> parkIds);

    @Query("SELECT p FROM Park p LEFT JOIN FETCH p.imagenes WHERE p.id = :id")
    Park findByIdWithImages(@Param("id") Long id);

    @Query("SELECT AVG(v.rating) FROM Review v JOIN v.attraction a WHERE a.park.id = :parqueId")
    Double getAverageRatingByParqueId(@Param("parqueId") Long parqueId);

}
