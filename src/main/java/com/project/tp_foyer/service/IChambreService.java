package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Chambre;
import com.project.tp_foyer.model.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> getAllChambres();
    Chambre getChambreById(Long id);
    Chambre addChambre(Chambre chambre);
    Chambre updateChambre(Chambre chambre);
    void deleteChambre(Long id);

    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);

    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);

}