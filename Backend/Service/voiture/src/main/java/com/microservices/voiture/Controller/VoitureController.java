package com.microservices.voiture.Controller;

import com.microservices.voiture.Models.Client;
import com.microservices.voiture.Models.Voiture;
import com.microservices.voiture.Repository.VoitureRepository;
import com.microservices.voiture.Service.ClientService;
import com.microservices.voiture.Service.VoitureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private ClientService clientService; // Inject the Feign Client

    @Autowired
    private VoitureServiceImpl voitureService;



    @PostMapping("/add")
    public ResponseEntity<String> ajouterUneVoiture(@RequestParam  String marque,@RequestParam String matricule, @RequestParam String model){
            String response= voitureService.ajouterVoiture(marque,matricule,model);
            return ResponseEntity.ok(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> modifierUneVoiture(@RequestParam Long id,@RequestParam  String marque,@RequestParam String matricule, @RequestParam String model){
        String response= voitureService.modifierVoiture(id,marque,matricule,model);
        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<String> supprimerUneVoiture(@RequestParam Long id){
        String response= voitureService.supprimerVoiture(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Voiture>> Voitures(){
        List<Voiture> response= voitureService.voitures();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voiture> VoitureParID(@PathVariable long id){
        Voiture response= voitureService.voitureParId(id);
        return ResponseEntity.ok(response);
    }



}