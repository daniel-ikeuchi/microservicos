package br.com.microservicos.book.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.microservicos.book.model.Book;
import br.com.microservicos.book.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private Environment environment;

	@Autowired
	private BookRepository repository;

	public Book findBook(Long id) {
		Optional<Book> book = repository.findById(id);

		if (!book.isPresent()) {
			throw new RuntimeException("Book not found!");
		}

		String port = environment.getProperty("local.server.port");
		book.get().setEnvironment(port);

		return book.get();
	}
}
