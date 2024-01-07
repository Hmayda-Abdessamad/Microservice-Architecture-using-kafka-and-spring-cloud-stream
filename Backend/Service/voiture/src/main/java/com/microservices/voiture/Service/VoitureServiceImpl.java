package com.microservices.voiture.Service;


import com.microservices.voiture.Models.Reservation;
import com.microservices.voiture.Models.Voiture;
import com.microservices.voiture.Repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class VoitureServiceImpl implements VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;


    @Bean
    private Consumer<Reservation> ReservationConsumer(){
        return (input)->{
          this.allouerUneVoiture(input.getId_client(), input.getId_voiture());
        };
    }
    @Override
    public String ajouterVoiture(String marque, String matricule, String model) {

        Voiture voiture=new Voiture();
        voiture.setMarque(marque);
        voiture.setMatricule(matricule);
        voiture.setModel(model);
        String response="";
        try {
            voitureRepository.save(voiture);
            response="voiture inséré ";
            return response;

        }catch (Exception e){
            System.out.println("err lors de l 'insertion de la voiture , voici l'exception "+ e);
            response="voiture n'est pas inséré";
            return response;
        }


    }

    @Override
    public String modifierVoiture(Long id,String marque, String matricule, String model) {

        Voiture voiture=voitureRepository.findById(id).orElseThrow(()->new RuntimeException("voiture n'existe pas"));
        voiture.setMarque(marque);
        voiture.setMatricule(matricule);
        voiture.setModel(model);
        voitureRepository.save(voiture);
        return "voiture modifiée";
    }

    @Override
    public String supprimerVoiture(Long id) {
        voitureRepository.deleteById(id);
        return "voiture supprimer";
    }

    @Override
    public List<Voiture> voitures() {
        return voitureRepository.findAll();
    }

    @Override
    public Voiture voitureParId(Long id) {
        return voitureRepository.findById(id).orElseThrow(()->new RuntimeException("voiture  n'existe pas"));
    }

    @Override
    public Voiture allouerUneVoiture(Long id_client, Long id_voiture) {
     Voiture voiture=this.voitureParId(id_voiture);
     voiture.setId_client(id_client);
     return voitureRepository.save(voiture);
    }

    @Override
    public List<Voiture> voitureParClientId(Long id) {
        return voitureRepository.findByClientId(id);
    }


}
