package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Foyer;

import java.util.List;
import java.util.Optional;

public interface IFoyerService {
    List<Foyer> getAllFoyers();
    Foyer getFoyerById(Long id);
    Foyer addFoyer(Foyer foyer);
    Foyer updateFoyer(Foyer foyer);
    void deleteFoyer(Long id);
}
