package softuni.servises;

import org.springframework.stereotype.Service;
import softuni.constants.GlobalConstants;
import softuni.entities.Author;
import softuni.entities.Book;
import softuni.entities.Category;
import softuni.enums.AgeRestriction;
import softuni.enums.EditionType;
import softuni.repositories.BookRepository;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorService authorService,
                           CategoryService categoryService) {

        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws Exception {

        if (bookRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(
                GlobalConstants.RESOURCE_PATH +
                    GlobalConstants.BOOKS_FILE_NAME))

            .forEach(row -> {

                String[] data = row.split("\\s+");

                EditionType editionType =
                    EditionType.values()[
                        Integer.parseInt(data[0])];

                LocalDate releaseDate =
                    LocalDate.parse(
                        data[1],
                        DateTimeFormatter.ofPattern("d/M/yyyy"));

                int copies = Integer.parseInt(data[2]);

                BigDecimal price = new BigDecimal(data[3]);

                AgeRestriction ageRestriction =
                    AgeRestriction.values()[
                        Integer.parseInt(data[4])];

                String title =
                    Arrays
                        .stream(data)
                        .skip(5)
                        .collect(Collectors.joining(" "));

                Author author =
                    authorService.getRandomAuthor();

                Set<Category> categories =
                    categoryService.getRandomCategories();

                Book book =
                    new Book(
                        title,
                        editionType,
                        price,
                        releaseDate,
                        ageRestriction,
                        author,
                        categories,
                        copies);

                bookRepository.save(book);
            });
    }

    @Override
    public List<Book> findBooksAfter2000() {

        return bookRepository.findAllByReleaseDateAfter(
            LocalDate.of(2000,1,1));
    }
}
