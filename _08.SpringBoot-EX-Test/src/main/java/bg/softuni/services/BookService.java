package bg.softuni.services;

import bg.softuni.DTO.BookDTO;
import bg.softuni.DTO.BookRelationsDTO;
import bg.softuni.entities.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDTO input, BookRelationsDTO relationsDTO);

    List<Book> findReleaseAfter(int year);
    List<Book> findByAuthor(String authorFirstName, String authorLastName);

}
