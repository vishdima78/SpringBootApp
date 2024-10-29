package vish.dima.crud.dao;

import org.springframework.stereotype.Component;


@Component
public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    public Optional<Book> show(String name) {
//        return jdbcTemplate.query("select * from Book WHERE author=?", new Object[]{name},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
//    }
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }
//
//
//    public Optional<Person> getBooksForId(int id) {
//
//        return jdbcTemplate.query("select person.*from book join person  on book.person_id = person.id" +
//                                " where book.id=?",
//                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//
//
//    }
//
//    public void deleteBook(int id) {
//        jdbcTemplate.update("update book set person_id=NULL where id=?", id);
//    }
//
//    public void addBook(int id, Person selectedPerson) {
//        jdbcTemplate.update("update book set person_id=? where id=?", selectedPerson.getId(), id);
//    }
//
//    public void save(Book book) {
//
//        jdbcTemplate.update("INSERT INTO Book(title,author,year) VALUES (?,?,?)",
//                book.getTitle(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book updatedBook) {
//        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
//                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE  id=?", id);
//
//    }


}