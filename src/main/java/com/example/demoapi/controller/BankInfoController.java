package com.example.demoapi.controller;

import com.example.demoapi.bankinforepositories.BankInfoRepository;
import com.example.demoapi.banksinfoentities.BankInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "localhost:8082")
    @RestController
    @RequestMapping("/api")
    public class BankInfoController {
        @Autowired
        private BankInfoRepository bankInfoRepository;

        @GetMapping("/banksInfo")
        public ResponseEntity<List<BankInfo>> getAllBanksInfo(@RequestParam(required = false) String adress) {
            try {
                List<BankInfo> banksInfo = new ArrayList<BankInfo>();
                if (adress == null)
                    banksInfo.addAll(bankInfoRepository.findAll());
                else
                    banksInfo.addAll(bankInfoRepository.findBankInfoByAdressContaining(adress));
                if (banksInfo.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @PostMapping("/banksInfo")
    public ResponseEntity<BankInfo> createBankInfo(@RequestBody BankInfo bankInfo) {
        try {
            boolean bankInfoExists = bankInfoRepository.existsBankInfoByAdress(bankInfo.getAdress());
            if (bankInfoExists) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            BankInfo _bankInfo = bankInfoRepository.save(new BankInfo(bankInfo.getName(), bankInfo.getAdress(), bankInfo.getPhones(), bankInfo.getWorkHours()));
            return new ResponseEntity<>(_bankInfo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
