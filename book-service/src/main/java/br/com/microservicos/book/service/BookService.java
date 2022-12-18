package br.com.microservicos.book.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.microservicos.book.model.Book;
import br.com.microservicos.book.repository.BookRepository;
import br.com.microservicos.book.response.Cambio;

@Service
public class BookService {

	@Autowired
	private Environment environment;

	@Autowired
	private BookRepository repository;

	public Book findBook(Long id, String currency) {
		Optional<Book> book = repository.findById(id);

		if (!book.isPresent()) {
			throw new RuntimeException("Book not found!");
		}
		
		Map<String, String> params = new HashMap<>();
		params.put("amount", book.get().getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		
		var cambio = new RestTemplate().getForEntity("http://localhost:8001/cambio-service/"
				+ "{amount}/{from}/{to}", Cambio.class, params);
		BigDecimal convertedValue = cambio.getBody().getConvertedValue();

		String port = environment.getProperty("local.server.port");
		book.get().setPrice(convertedValue.setScale(2, RoundingMode.HALF_EVEN));
		book.get().setEnvironment(port);

		return book.get();
	}
}
