package com.example.ServiceClient.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voiture {


    private Long Id;
    private String marque;
    private String matricule;
    private String model;
    private Long id_client;

}
