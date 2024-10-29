package vish.dima.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vish.dima.crud.models.Book;
import vish.dima.crud.models.Person;
import vish.dima.crud.repositories.BooksRepository;
import vish.dima.crud.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public Optional<Book> show(String name) {
        return booksRepository.findByAuthor(name);
    }

    public Optional<Person> getPersonForId(int id) {
        return booksRepository.findById(id).map(Book::getOwner);
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);

    }

    public List<Book> searchByTitle(String query) {
        return booksRepository.findByTitleStartingWith(query);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(Book bookTo, int id) {
        Book book = booksRepository.findById(id).get();

        book.setId(id);
        book.setOwner(book.getOwner());
        booksRepository.save(bookTo);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }


    @Transactional
    public void setNullOwner(int id) {
        booksRepository.findById(id).ifPresent(book -> {
            book.setOwner(null);
            book.setTakenAt(null);
        });
    }

    @Transactional
    public void setNewOwner(int id, Person person) {

        booksRepository.findById(id).ifPresent(
                book1 -> {
                    book1.setOwner(person);
                });

    }
}
