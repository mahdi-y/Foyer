package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Chambre;
import com.project.tp_foyer.model.Etudiant;
import com.project.tp_foyer.model.Reservation;
import com.project.tp_foyer.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tp_foyer.repository.ChambreRepository;
import com.project.tp_foyer.repository.EtudiantRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(String id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite) {
        return reservationRepository.findReservationsByYearAndUniversity(anneeUniversitaire, nomUniversite);
    }

    @Override
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre not found!"));

        if (chambre.getReservations().size() >= chambre.getTypeC().getCapacity()) {
            throw new RuntimeException("Chambre capacity exceeded!");
        }

        Etudiant etudiant = etudiantRepository.findById(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Etudiant not found!"));

        Reservation reservation = new Reservation();
        reservation.setIdReservation(generateReservationId(chambre));
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);
        reservation.setChambre(chambre);
        reservation.setEtudiant(etudiant);

        return reservationRepository.save(reservation);
    }

    private String generateReservationId(Chambre chambre) {
        return chambre.getNumeroChambre() + "-" + chambre.getBloc().getNomBloc() + "-" + new SimpleDateFormat("yyyy").format(new Date());
    }
}