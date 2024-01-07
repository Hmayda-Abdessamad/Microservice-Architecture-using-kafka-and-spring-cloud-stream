package com.example.ServiceClient.Controller;

import com.example.ServiceClient.Model.Client;
import com.example.ServiceClient.Model.Reservation;
import com.example.ServiceClient.Repository.ClientRepository;
import com.example.ServiceClient.Service.ServiceClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ServiceClientImpl serviceClient;

    @PostMapping("/add")
    ResponseEntity<Object> ajouterUnClient(String nom,String prenom,Integer age){

      String res=serviceClient.AjouterClient(nom,prenom,age);
      return ResponseEntity.ok().body("{\"message\": \"" + res + "\"}");
    }



    @PutMapping("/edit")
    ResponseEntity<String> modifierUnClient(Long id,String nom,String prenom,Integer age){
        String res=serviceClient.ModifierClient(id,nom,prenom,age);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> suprimerUnClient(Long id){
        String res=serviceClient.SupprimerClient(id);
        return    ResponseEntity.ok().body("{\"message\": \"" + res + "\"}");
    }

    @GetMapping
    ResponseEntity<List<Client>> tousLesClients(){
        List<Client> res=serviceClient.tousLesClient();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> ClientParId(@PathVariable Long id){
        Client res=serviceClient.clientParId(id);
        return ResponseEntity.ok(res);
    }


    @PostMapping("/allouer")
    ResponseEntity<Reservation> Allouer(@RequestParam Long id_client,Long id_voiture){
        Reservation res=serviceClient.AllouerVoiture(id_client,id_voiture);
        return ResponseEntity.ok(res);
    }


}
