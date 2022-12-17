package br.com.microservicos.book.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservicos.book.model.Book;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable Long id,
			@PathVariable String currency) {
		
		return new Book(1L, "Daniel Ikeuchi", "Titulo do Livro", new Date(), BigDecimal.TEN, currency, "8100");
	}
	
}
