package io.bastrikov.taxes;

import io.bastrikov.taxes.services.TaxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class TaxesApplicationTests {

	TaxService taxService = new TaxService();


	public static Stream<Arguments> getInputsForTaxTesting(){
		return Stream.of(
				Arguments.of("US", 1.3),
				Arguments.of("UK", 1.1),
				Arguments.of("Russia", 0.4),
				Arguments.of("Japan", 0.8),
				Arguments.of("Canada", 0.9)
		);
	}
	@ParameterizedTest
	@MethodSource("getInputsForTaxTesting")
	public void whenCountryPassed_ThenGetTaxForThisCountry(String country, Double expectedTax){
		Assertions.assertEquals(expectedTax, taxService.calculateLocationMultiplier(country));
	}

	public static List<Arguments> dataFor_WhenPricePassed_thenGetTaxMultiplier(){
		return List.of(
				Arguments.of(0.0, 1.0),
				Arguments.of(30.0, 1.1),
				Arguments.of(80.0, 1.2),
				Arguments.of(100.0, 1.3),
				Arguments.of(200, 1.3)
		);
	}

	@ParameterizedTest
	@MethodSource("dataFor_WhenPricePassed_thenGetTaxMultiplier")
	public void WhenPricePassed_thenGetTaxMultiplier(double totalPrice, double expectedTaxMultiplier) {
		double actualTaxMultiplier = taxService.calculatePriceMultiplier(totalPrice);
		Assertions.assertEquals(expectedTaxMultiplier, actualTaxMultiplier);
	}

}
