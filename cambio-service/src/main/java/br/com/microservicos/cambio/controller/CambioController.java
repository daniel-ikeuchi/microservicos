package br.com.microservicos.cambio.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservicos.cambio.model.Cambio;
import br.com.microservicos.cambio.service.CambioService;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	@Autowired
	private CambioService service;

	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable BigDecimal amount,
			@PathVariable String from,
			@PathVariable String to) {
		return service.getCambio(amount, from, to);
	}
	
}
