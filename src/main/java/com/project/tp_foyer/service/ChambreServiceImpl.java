package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Chambre;
import com.project.tp_foyer.model.TypeChambre;
import com.project.tp_foyer.repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChambreServiceImpl implements IChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre getChambreById(Long id) {
        return chambreRepository.findById(id).orElse(null);
    }

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre updateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        return chambreRepository.findUnreservedRoomsByUniversityAndType(nomUniversite, type);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        List<Chambre> roomsJPQL = chambreRepository.findRoomsByBlockAndTypeJPQL(idBloc, typeC);
        List<Chambre> roomsKeywords = chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC);
        return roomsJPQL.isEmpty() ? roomsKeywords : roomsJPQL;
    }


}