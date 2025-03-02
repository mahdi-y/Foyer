package com.project.tp_foyer.controller;

import com.project.tp_foyer.model.Foyer;
import com.project.tp_foyer.service.FoyerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foyers")
public class FoyerController {

    private FoyerServiceImpl foyerService;

    @PostMapping("/addFoyer")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @GetMapping
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    @DeleteMapping("/deleteFoyer/{id}")
    public void deleteFoyer(@PathVariable long id){
        foyerService.deleteFoyer(id);
    }

//    @GetMapping("/universite/{universiteId}")
//    public List<Foyer> getFoyersByUniversityId(@PathVariable Long universiteId) {
//        return foyerService.getFoyersByUniversityId(universiteId);
//    }
}
