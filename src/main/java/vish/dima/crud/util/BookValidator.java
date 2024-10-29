package vish.dima.crud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vish.dima.crud.models.Book;
import vish.dima.crud.services.BookService;

@Component
public class BookValidator implements Validator {
//    private final BookDAO bookDAO;
    private final BookService bookService;

    @Autowired
    public BookValidator( BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book=(Book) target;
        if (bookService.show(book.getTitle()).isPresent()){
            errors.rejectValue("title","", "This title is already taken");
        }
    }
}
