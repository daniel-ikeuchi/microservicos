package br.com.microservicos.cambio.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.microservicos.cambio.model.Cambio;
import br.com.microservicos.cambio.repository.CambioRepository;

@Service
public class CambioService {

	@Autowired
	private Environment environment;

	@Autowired
	private CambioRepository repository;

	public Cambio getCambio(BigDecimal amount, String from, String to) {
		Cambio cambio = repository.findByFromAndTo(from, to);

		if (cambio == null) {
			throw new RuntimeException("Unsupported Currency!");
		}

		String port = environment.getProperty("local.server.port");
		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal convertedValue = amount.multiply(conversionFactor);
		
		cambio.setConvertedValue(convertedValue);
		cambio.setEnvironment(port);
		
		return cambio;
	}
}
