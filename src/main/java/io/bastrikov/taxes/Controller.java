package io.bastrikov.taxes;

import io.bastrikov.taxes.models.TaxRequestEntity;
import io.bastrikov.taxes.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    TaxService taxService;

    @PostMapping("/calculate")
    public double calculateTax(@RequestBody TaxRequestEntity request) {
        double locationMultiplier = taxService.calculateLocationMultiplier(request.getAddress().getCountry());
        double priceMultiplier = taxService.calculatePriceMultiplier(request.getTotalPrice());
        double baseTax = 20;
        double result = baseTax * locationMultiplier * priceMultiplier;
        result = Math.floor(result * 100) / 100;
        return result;

    }

}
