package com.microservices.voiture.Service;

import com.microservices.voiture.Models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="service-client")
public interface ClientService {

     @GetMapping(path="/clients/{id}")
     Client clientById(@PathVariable Long id);


}
