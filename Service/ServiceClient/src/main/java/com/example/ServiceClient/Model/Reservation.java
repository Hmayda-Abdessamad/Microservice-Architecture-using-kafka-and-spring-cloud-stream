package com.example.ServiceClient.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {

    private Long id_client;
    private Long id_voiture;
}
