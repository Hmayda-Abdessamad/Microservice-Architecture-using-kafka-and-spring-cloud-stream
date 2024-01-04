package com.example.ServiceClient.Service;

import com.example.ServiceClient.Model.Client;
import com.example.ServiceClient.Model.Reservation;
import com.example.ServiceClient.Model.Voiture;
import com.example.ServiceClient.Repository.ClientRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service

public class ServiceClientImpl implements ServiceClient{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private StreamBridge streamBridge;


    @Override
    public Reservation AllouerVoiture(Long client, Long voiture) {
        Reservation reservation=new Reservation(client, voiture);
        streamBridge.send("Reservation",reservation);
        return reservation;
    }
    @Override
    public String AjouterClient(String nom, String prenom, Integer age) {
        String response;
        try{
            Client client =new Client();
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setAge(age);
            clientRepository.save(client);
            response="client ajouté";
        }catch (Exception e){
            System.out.println("client n est pas ajouté  vue l exception suivante  "+e );
            response="clinet n'est pas ajouté";
        }
        return response;
    }

    @Override
    public String ModifierClient(Long id, String nom, String prenom, Integer age) {
        Client client=clientRepository.findById(id).orElseThrow(()->new RuntimeException("client n existe pas pour le modifier "));
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setAge(age);
        clientRepository.save(client);
        return "client modifé";
    }

    @Override
    public String SupprimerClient(Long id) {
        clientRepository.deleteById(id);
        return "client supprimer";
    }

    @Override
    public List<Client> tousLesClient() {
        List<Client> clients=clientRepository.findAll();
        return clients;
    }

    @Override
    public Client clientParId(Long id) {
        return clientRepository.findById(id).orElseThrow(()->new RuntimeException("client n'existe pas"));
    }




}
