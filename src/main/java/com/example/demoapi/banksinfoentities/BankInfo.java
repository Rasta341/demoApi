package com.example.demoapi.banksinfoentities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BANKS")
public class BankInfo {
    @SequenceGenerator(
            name = "SequenceGenerator",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SequenceGenerator")
    private Long id;
    private String name;
    private String adress;
    private String phones;
    private  String workHours;


    public BankInfo(){}

    public BankInfo(String name, String adress, String phones, String workHours) {
        this.name = name;
        this.adress = adress;
        this.phones = phones;
        this.workHours = workHours;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }
    @Override
    public String toString() {
        return "Bank [name: " + name + ", adress: " + adress +
                ", phones: " + phones + ", work hours: " + workHours;
    }


}
