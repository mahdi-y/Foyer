package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Chambre;
import java.util.List;

public interface IChambreService {
    List<Chambre> getAllChambres();
    Chambre getChambreById(Long id);
    Chambre addChambre(Chambre chambre);
    Chambre updateChambre(Chambre chambre);
    void deleteChambre(Long id);
}