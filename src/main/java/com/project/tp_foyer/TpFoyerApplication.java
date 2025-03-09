package com.project.tp_foyer;

import com.project.tp_foyer.model.*;
import com.project.tp_foyer.repository.*;
import com.project.tp_foyer.service.IChambreService;
import com.project.tp_foyer.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class TpFoyerApplication implements CommandLineRunner {

    @Autowired
    private UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;
    @Autowired
    private BlocRepository blocRepository;
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private IChambreService chambreService;
    @Autowired
    private IReservationService reservationService;

    public static void main(String[] args) {
        SpringApplication.run(TpFoyerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Universite universite = new Universite();
        universite.setNomUniversite("Université Centrale");
        universite.setAdresse("Tunis");
        universiteRepository.save(universite);

        Foyer foyer = new Foyer();
        foyer.setNomFoyer("Foyer Principal");
        foyer.setCapaciteFoyer(100L);
        foyer.setUniversite(universite);
        foyerRepository.save(foyer);

        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");
        bloc.setCapaciteBloc(30L);
        bloc.setFoyer(foyer);
        blocRepository.save(bloc);

        Chambre chambre1 = new Chambre();
        chambre1.setNumeroChambre(101L);
        chambre1.setTypeC(TypeChambre.SIMPLE);
        chambre1.setBloc(bloc);
        chambreRepository.save(chambre1);

        Chambre chambre2 = new Chambre();
        chambre2.setNumeroChambre(102L);
        chambre2.setTypeC(TypeChambre.DOUBLE);
        chambre2.setBloc(bloc);
        chambreRepository.save(chambre2);

        Etudiant etudiant = new Etudiant();
        etudiant.setNomEt("Zalila");
        etudiant.setPrenomEt("Mahdi");
        etudiant.setCin(12345678L);
        etudiant.setEcole("Université Centrale"); // Must match Université name
        etudiant.setDateNaissance(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
        etudiantRepository.save(etudiant);

        Reservation reservation = new Reservation();
        reservation.setIdReservation("RES-1");
        reservation.setAnneeUniversitaire(new SimpleDateFormat("yyyy-MM-dd").parse("2024-09-01"));
        reservation.setEstValide(true);
        reservation.setChambre(chambre2);
        reservation.setEtudiant(etudiant);
        reservationRepository.save(reservation);

        System.out.println("\n--- TESTING METHODS ---\n");

        // 1. Chambres non réservées par type et université
        List<Chambre> freeChambres = chambreService
                .getChambresNonReserveParNomUniversiteEtTypeChambre("Université Centrale", TypeChambre.SIMPLE);
        System.out.println("Chambres NON réservées (type: INDIVIDUEL): " + freeChambres.size());
        freeChambres.forEach(c -> System.out.println("  - Chambre #" + c.getNumeroChambre()));

        // 2. Réservations par année universitaire et nom université
        Date year = new SimpleDateFormat("yyyy-MM-dd").parse("2024-09-01");
        List<Reservation> reservations = reservationService
                .getReservationParAnneeUniversitaireEtNomUniversite(year, "Université Centrale");
        System.out.println("\nRéservations pour 2024: " + reservations.size());
        reservations.forEach(r -> System.out.println("  - " + r.getIdReservation() + ", Chambre #" + r.getChambre().getNumeroChambre()));

        // 3. Chambres d’un bloc selon un type
        List<Chambre> chambresParBloc = chambreService.getChambresParBlocEtType(bloc.getIdBloc(), TypeChambre.SIMPLE);
        System.out.println("\nChambres du bloc par type (INDIVIDUEL): " + chambresParBloc.size());
        chambresParBloc.forEach(c -> System.out.println("  - Chambre #" + c.getNumeroChambre()));
    }
}
