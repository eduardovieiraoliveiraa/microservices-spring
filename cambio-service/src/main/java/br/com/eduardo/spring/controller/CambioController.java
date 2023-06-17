package br.com.eduardo.spring.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eduardo.spring.model.Cambio;
import br.com.eduardo.spring.repository.CambioRepository;

@RestController
@RequestMapping("/cambio-service")
public class CambioController {
	
	@Autowired
	private Environment environment; 
	
	@Autowired
	private CambioRepository cambioRepository; 

	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from, @PathVariable("to") String to) {
		var cambio = cambioRepository.findByFromAndTo(from, to);
		
		if(cambio == null) throw new RuntimeException("Currency unsupported");
		
		var port = environment.getProperty("local.server.port");

		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal convertedValue = conversionFactor.multiply(amount);
		
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.HALF_DOWN));
		cambio.setEnvironment(port);
		
		return cambio;
	}
}
