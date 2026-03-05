package softuni.servises;

import softuni.entities.Book;
import java.util.List;

public interface BookService {

    void seedBooks() throws Exception;
    List<Book> findBooksAfter2000();
}
