package io.bastrikov.taxes.services;

import org.springframework.stereotype.Service;

@Service
public class TaxService {

    public String hello(){
        return "hello";
    }

    public double calculateLocationMultiplier(String country) {
        double locationMultiplier = 1;
        if (country.equals("US")) locationMultiplier = 1.3;
        else if (country.equals("UK")) locationMultiplier = 1.1;
        else if (country.equals("Canada")) locationMultiplier = 0.9;
        else if (country.equals("Russia")) locationMultiplier = 0.4;
        else if (country.equals("Japan")) locationMultiplier = 0.8;
        return locationMultiplier;
    }
    public double calculatePriceMultiplier(double price) {
        double priceMultiplier = 0.0;
        if (price < 20.0) priceMultiplier = 1;
        else if (price < 40.0) priceMultiplier = 1.1;
        else if (price < 100.0) priceMultiplier = 1.2;
        else priceMultiplier = 1.3;
        return priceMultiplier;
    }
}
