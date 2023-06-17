package br.com.eduardo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eduardo.spring.model.Cambio;
/**
 * 
 * @author Eduardo Oliveira
 * @date 14/06/2023
 */
public interface CambioRepository extends JpaRepository<Cambio, Long>{
	
	Cambio findByFromAndTo(String from, String to);
}
