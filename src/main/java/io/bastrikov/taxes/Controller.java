package io.bastrikov.taxes;

import io.bastrikov.taxes.models.TaxRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Value("${message}")
    private String message;

    @GetMapping("/hello")
    public String test(){
        return message;
    }

    @PostMapping("/calculate")
    public double calculateTax(@RequestBody TaxRequestEntity request) {
        double locationMultiplier = calculateLocationMultiplier(request.getAddress().getCountry());
        double priceMultiplier = calculatePriceMultiplier(request.getTotalPrice());
        double baseTax = 20;
        double result = baseTax * locationMultiplier * priceMultiplier;
        result = Math.floor(result * 100) / 100;
        return result;

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
