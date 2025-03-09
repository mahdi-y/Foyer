package com.project.tp_foyer.repository;

import com.project.tp_foyer.model.Chambre;
import com.project.tp_foyer.model.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    @Query("SELECT c FROM Chambre c WHERE c.typeC = :typeC AND c.bloc.foyer.universite.nomUniversite = :nomUniversite " +
            "AND c.idChambre NOT IN (SELECT r.chambre.idChambre FROM Reservation r WHERE r.anneeUniversitaire = CURRENT_DATE)")
    List<Chambre> findUnreservedRoomsByUniversityAndType(@Param("nomUniversite") String nomUniversite, @Param("typeC") TypeChambre typeC);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findRoomsByBlockAndTypeJPQL(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);

    List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);

}