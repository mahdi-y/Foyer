package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Foyer;
import com.project.tp_foyer.model.Universite;
import com.project.tp_foyer.repository.FoyerRepository;
import com.project.tp_foyer.repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoyerServiceImpl implements IFoyerService{

    @Autowired
    private FoyerRepository foyerRepository;

    @Autowired
    private UniversiteRepository universiteRepository;

    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer getFoyerById(Long id) {
        return foyerRepository.findById(id).orElse(null);
    }

    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public Foyer updateFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void deleteFoyer(Long id) {
        foyerRepository.deleteById(id);
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Universite not found!"));

        Foyer savedFoyer = foyerRepository.save(foyer);
        universite.setFoyer(savedFoyer);
        universiteRepository.save(universite);

        return savedFoyer;
    }
}
