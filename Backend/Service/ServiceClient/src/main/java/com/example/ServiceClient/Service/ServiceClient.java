package com.example.ServiceClient.Service;

import com.example.ServiceClient.Model.Client;
import com.example.ServiceClient.Model.Reservation;
import com.example.ServiceClient.Model.Voiture;

import java.util.List;

public interface ServiceClient {

    String AjouterClient(String nom,String prenom,Integer age);
    String ModifierClient(Long id,String nom,String prenom,Integer age);
    String SupprimerClient(Long id);
    List<Client> tousLesClient();
    Client clientParId(Long id);
    Reservation AllouerVoiture(Long client, Long voiture);


}
