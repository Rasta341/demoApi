package com.example.demoapi.service;

import com.example.demoapi.banksinfoentities.BankInfo;
import com.example.demoapi.bankinforepositories.BankInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BankInfoService {
    @Autowired
    private BankInfoRepository bankInfoRepository;
    @Transactional
    public void bankInfoServiceRepository() {
        Optional<BankInfo> bankInfoOptional = bankInfoRepository.findById(127L);
    }
}
