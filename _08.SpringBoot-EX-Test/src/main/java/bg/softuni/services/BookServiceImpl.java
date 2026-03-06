package bg.softuni.services;

import bg.softuni.DTO.BookDTO;
import bg.softuni.DTO.BookRelationsDTO;
import bg.softuni.entities.Book;
import bg.softuni.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(BookDTO input, BookRelationsDTO relationsDTO) {

        Book book = new Book();

        book.setTitle(input.getTitle());
        book.setCopies(input.getCopies());
        book.setPrice(input.getPrice());
        book.setReleaseDate(input.getReleaseDate());
        book.setEditionType(input.getEditionType());
        book.setAgeRestriction(input.getAgeRestriction());

        book.setAuthor(relationsDTO.getAuthor());
        book.setCategories(Set.copyOf(relationsDTO.getCategories()));

        return bookRepository.save(book);
    }

    @Override
    public List<Book> findReleaseAfter(int year) {

        LocalDate date =  LocalDate.of(year, 1, 1);
        return bookRepository.findAllByReleaseDateGreaterThanEqual(date);
    }

    @Override
    public List<Book> findByAuthor(String authorFirstName, String authorLastName) {

        return bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(authorFirstName, authorLastName);
    }

}
