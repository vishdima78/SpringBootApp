package vish.dima.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vish.dima.crud.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleStartingWith(String title);

    Optional<Book> findByAuthor(String author);
//
//    List<Book> findByTitle(String title);
//
//    Optional<Person> findByOwnerId(int id);
}
