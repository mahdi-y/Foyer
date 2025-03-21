package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Foyer;
import com.project.tp_foyer.model.Universite;
import com.project.tp_foyer.repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.project.tp_foyer.repository.FoyerRepository;

@Service
public class UniversiteServiceImpl implements IUniversiteService {

    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private FoyerRepository foyerRepository;

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }



    @Override
    public Universite getUniversiteById(Long id) {
        return universiteRepository.findById(id).orElse(null);
    }

    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite updateUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public void deleteUniversite(Long id) {
        universiteRepository.deleteById(id);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer not found!"));

        Universite universite = (Universite) universiteRepository.findByNomUniversite(nomUniversite);

        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Universite not found!"));

        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }
}