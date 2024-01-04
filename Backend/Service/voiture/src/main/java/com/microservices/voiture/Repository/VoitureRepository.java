package com.microservices.voiture.Repository;

import com.microservices.voiture.Models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {


    @Query("SELECT v FROM Voiture v WHERE v.id_client = :clientId")
    List<Voiture> findByClientId(Long clientId);
}
