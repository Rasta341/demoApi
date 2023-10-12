package com.example.demoapi.controller;

import com.example.demoapi.bankinforepositories.BankInfoRepository;
import com.example.demoapi.banksinfoentities.BankInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8082")
    @RestController
    @RequestMapping("/api")
    public class BankInfoController {
        @Autowired
        private BankInfoRepository bankInfoRepository;

        @GetMapping("/banksInfo")
        public ResponseEntity<List<BankInfo>> getAllBanksInfo(@RequestParam(required = false) String name) {
            try {
                List<BankInfo> banksInfo = new ArrayList<BankInfo>();
                if (name == null)
                    banksInfo.addAll(bankInfoRepository.findAll());
                else
                    banksInfo.addAll(bankInfoRepository.findBankInfoByNameContaining(name));
                if (banksInfo.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else return new ResponseEntity<>(banksInfo, HttpStatus.CREATED);
            } catch (Exception e) {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @PostMapping("/banksInfo")
    public ResponseEntity<BankInfo> createBankInfo(@RequestBody BankInfo bankInfo) {
        try {
            boolean bankInfoExists = bankInfoRepository.existsBankInfoByName(bankInfo.getName());
            if (bankInfoExists) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }

            BankInfo _bankInfo = bankInfoRepository.save(new BankInfo(bankInfo.getName(), bankInfo.getAdress(), bankInfo.getPhones(), bankInfo.getWorkHours()));
            return new ResponseEntity<>(_bankInfo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/banksInfo/{id}")
    public ResponseEntity<BankInfo> getBankInfoById(@PathVariable("id") Long id) {
        Optional<BankInfo> bankInfoData = bankInfoRepository.findById(id);

        if (bankInfoData.isPresent()) {
            return new ResponseEntity<>(bankInfoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/banksInfo/{id}")
    public ResponseEntity<BankInfo> updateBankInfo(@PathVariable("id") Long id,
                                           @RequestBody BankInfo bankInfo) {
        Optional<BankInfo> bankInfoData = bankInfoRepository.findById(id);

        if (bankInfoData.isPresent()) {
            BankInfo _bankInfo = bankInfoData.get();
            if (bankInfo.getName() != null) {
                _bankInfo.setName(bankInfo.getName());
            }
            if(bankInfo.getAdress() != null) {
                _bankInfo.setAdress(bankInfo.getAdress());
            }
            if(bankInfo.getPhones() != null) {
                _bankInfo.setPhones(bankInfo.getPhones());
            }
            if(bankInfo.getWorkHours() != null){
                _bankInfo.setWorkHours(bankInfo.getWorkHours());
            }
            return  new ResponseEntity<>(bankInfoRepository.save(_bankInfo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/banksInfo/{id}")
    public ResponseEntity<HttpStatus> deleteBankInfo(@PathVariable(value = "id") Long id) {
        try {
            bankInfoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/banksInfo")
    public ResponseEntity<HttpStatus> deleteAllBanksInfo() {
        try {
            bankInfoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
