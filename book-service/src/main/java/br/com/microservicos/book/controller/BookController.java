package br.com.microservicos.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservicos.book.model.Book;
import br.com.microservicos.book.service.BookService;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable Long id,
			@PathVariable String currency) {
		return service.findBook(id, currency);
	}
	
}
