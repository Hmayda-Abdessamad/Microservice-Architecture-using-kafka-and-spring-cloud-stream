package com.microservices.voiture.Controller;

import com.microservices.voiture.Models.Client;
import com.microservices.voiture.Models.Voiture;
import com.microservices.voiture.Repository.VoitureRepository;
import com.microservices.voiture.Service.ClientService;
import com.microservices.voiture.Service.VoitureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/voitures")
public class VoitureController {

    @Autowired
    private ClientService clientService; // Inject the Feign Client

    @Autowired
    private VoitureServiceImpl voitureService;



    @PostMapping("/add")
    public ResponseEntity<Object> ajouterUneVoiture(
            @RequestParam String marque,
            @RequestParam String matricule,
            @RequestParam String model
    ) {
        voitureService.ajouterVoiture(marque,matricule,model);
        String response = "Car added successfully";

        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", response,
                "marque", marque,
                "matricule", matricule,
                "model", model
        ));
    }

    @PutMapping("/edit")
    public ResponseEntity<String> modifierUneVoiture(@RequestParam Long id,@RequestParam  String marque,@RequestParam String matricule, @RequestParam String model){
        String response= voitureService.modifierVoiture(id,marque,matricule,model);
        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<Object> supprimerUneVoiture(@RequestParam Long id){
        String success = voitureService.supprimerVoiture(id);

        return ResponseEntity.ok().body("{\"message\": \"Car deleted successfully\"}");

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

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Voiture>> VoitureParClient(@PathVariable long id){
        List<Voiture> response= voitureService.voitureParClientId(id);
        return ResponseEntity.ok(response);
    }



}