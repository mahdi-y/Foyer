package com.project.tp_foyer.repository;

import com.project.tp_foyer.model.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
//    List<Foyer> findByUniversityId(Long universiteId); // Add this line to support the query
}
