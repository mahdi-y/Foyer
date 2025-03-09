package com.project.tp_foyer.repository;

import com.project.tp_foyer.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    @Query("SELECT r FROM Reservation r WHERE r.anneeUniversitaire = :anneeUniversitaire " +
            "AND r.etudiant.ecole IN (SELECT u.nomUniversite FROM Universite u WHERE u.nomUniversite = :nomUniversite)")
    List<Reservation> findReservationsByYearAndUniversity(@Param("anneeUniversitaire") Date anneeUniversitaire, @Param("nomUniversite") String nomUniversite);

}