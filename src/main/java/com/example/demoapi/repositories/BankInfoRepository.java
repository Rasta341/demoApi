package com.example.demoapi.repositories;

import com.example.demoapi.entities.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
    List<BankInfo> findBankInfoByAdress(String adress);
}
