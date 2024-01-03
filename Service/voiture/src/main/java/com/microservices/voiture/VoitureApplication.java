package com.microservices.voiture;

import com.microservices.voiture.Models.Client;
import com.microservices.voiture.Models.Voiture;
import com.microservices.voiture.Repository.VoitureRepository;
import com.microservices.voiture.Service.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
@EnableFeignClients

public class VoitureApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoitureApplication.class, args);
	}






}
