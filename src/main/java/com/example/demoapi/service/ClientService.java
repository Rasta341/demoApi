package com.example.demoapi.service;

import com.example.demoapi.entities.Client;
import com.example.demoapi.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Transactional
    public void ClientServiceRepository() {
        Optional<Client> clientsOptional = clientRepository.findById(127L);
    }

}
