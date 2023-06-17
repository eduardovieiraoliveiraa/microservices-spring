package br.com.eduardo.spring.repositopry;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eduardo.spring.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
