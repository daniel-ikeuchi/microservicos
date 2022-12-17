package br.com.microservicos.cambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.microservicos.cambio.model.Cambio;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {

	Cambio findByFromAndTo(String from, String to);
	
}
