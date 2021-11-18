package jfv.marah.springbootcursus.repositories;

import jfv.marah.springbootcursus.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
