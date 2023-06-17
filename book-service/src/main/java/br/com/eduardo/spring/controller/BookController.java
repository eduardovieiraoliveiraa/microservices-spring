package br.com.eduardo.spring.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.eduardo.spring.model.Book;
import br.com.eduardo.spring.proxy.CambioProxy;
import br.com.eduardo.spring.repositopry.BookRepository;
import br.com.eduardo.spring.response.Cambio;
/**
 * 
 * @author Eduardo Oliveira
 * @date 17/06/2023
 */
@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CambioProxy cambioProxy;
	
	@GetMapping("{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		
		Optional<Book> book = bookRepository.findById(id);
		
		if(book.isEmpty()) throw new RuntimeException("Book not found");
		Book bookManeged = book.get();

		Cambio cambio = cambioProxy.getCambio(bookManeged.getPrice(), "USD", currency);
		String porta = environment.getProperty("local.server.port");
		
		bookManeged.setEnviroment("Book port : " + porta + " Cambio Port " + cambio.getEnvironment());
		bookManeged.setPrice(cambio.getConvertedValue());
		
		return bookManeged;
	}
	
//	@GetMapping("{id}/{currency}") JEITO ERRADO
//	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
//		
//		Optional<Book> book = bookRepository.findById(id);
//		
//		if(book.isEmpty()) throw new RuntimeException("Book not found");
//		Book bookManeged = book.get();
//		
//		HashMap<String, String> params = new HashMap<>();
//		params.put("amount", bookManeged.getPrice().toString());
//		params.put("from","USD");
//		params.put("to", currency);
//		
//		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
//		var cambio = response.getBody();
//		
//		String porta = environment.getProperty("local.server.port");
//		
//		bookManeged.setEnviroment(porta);
//		bookManeged.setPrice(cambio.getConvertedValue());
//		
//		return bookManeged;
//	}

}
