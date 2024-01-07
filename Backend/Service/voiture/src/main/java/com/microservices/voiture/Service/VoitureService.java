package com.microservices.voiture.Service;

import com.microservices.voiture.Models.Voiture;

import java.util.List;

public interface VoitureService {

    String ajouterVoiture(String marque,String matricule,String model);
    String modifierVoiture(Long id,String marque,String matricule,String model);

    String supprimerVoiture (Long id);
    List<Voiture> voitures();
    Voiture voitureParId(Long id);

    Voiture allouerUneVoiture(Long id_client,Long id_voiture);

    List<Voiture> voitureParClientId(Long id);

}
