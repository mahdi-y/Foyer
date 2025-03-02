package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Universite;
import java.util.List;

public interface IUniversiteService {
    List<Universite> getAllUniversites();
    Universite getUniversiteById(Long id);
    Universite addUniversite(Universite universite);
    Universite updateUniversite(Universite universite);
    void deleteUniversite(Long id);
}