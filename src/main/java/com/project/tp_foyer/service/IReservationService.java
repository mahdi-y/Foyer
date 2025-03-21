package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservations();
    Reservation getReservationById(String id);
    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(String id);
    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite);
    Reservation ajouterReservation(long idChambre, long cinEtudiant);
}