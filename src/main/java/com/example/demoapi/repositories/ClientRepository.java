package com.example.demoapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demoapi.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
