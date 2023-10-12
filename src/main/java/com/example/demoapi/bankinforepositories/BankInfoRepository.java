package com.example.demoapi.bankinforepositories;

import com.example.demoapi.banksinfoentities.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {
    List<BankInfo> findBankInfoByNameContaining(String name);
    boolean existsBankInfoByName(String name);
}
