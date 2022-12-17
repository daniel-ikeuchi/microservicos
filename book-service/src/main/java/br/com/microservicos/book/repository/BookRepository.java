package br.com.microservicos.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.microservicos.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
