package com.project.tp_foyer.service;

import com.project.tp_foyer.model.Bloc;
import com.project.tp_foyer.model.Chambre;
import com.project.tp_foyer.repository.BlocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.project.tp_foyer.repository.ChambreRepository;

@Service
public class BlocServiceImpl implements IBlocService {

    @Autowired
    private BlocRepository blocRepository;

    @Autowired
    private ChambreRepository chambreRepository;


    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc getBlocById(Long id) {
        return blocRepository.findById(id).orElse(null);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(Long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc not found!"));

        List<Chambre> chambres = chambreRepository.findAllById(numChambre);

        for (Chambre chambre : chambres) {
            chambre.setBloc(bloc);
        }

        chambreRepository.saveAll(chambres);
        return bloc;
    }
}