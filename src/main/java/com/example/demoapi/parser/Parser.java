package com.example.demoapi.parser;

import com.example.demoapi.banksinfoentities.BankInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser {
    private static String url = "https://kovalut.ru/bank-vtb/";
    public static BankInfo getBankInfo(String city) throws IOException {
        BankInfo bank = new BankInfo();
        url = url + city;
        Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table").get(2);
        Elements rows = table.select("tr");

        for(int j = 0; j < table.childrenSize(); j++) {
            for (int i = 0; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                bank.setName(String.valueOf(rows.get(0).text()));
                bank.setAdress(String.valueOf(rows.get(1).text()));
                bank.setPhones(String.valueOf(rows.get(2).text()));
                bank.setWorkHours(String.valueOf(rows.get(3).text()));
            }
        }
        return bank;
    }
}


