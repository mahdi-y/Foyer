package com.project.tp_foyer.repository;

import com.project.tp_foyer.model.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    List<Universite> findByNomUniversite(String nomUniversite);
}