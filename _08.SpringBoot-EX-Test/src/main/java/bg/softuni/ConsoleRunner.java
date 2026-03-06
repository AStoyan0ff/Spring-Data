package bg.softuni;

import bg.softuni.DTO.*;
import bg.softuni.entities.Author;
import bg.softuni.entities.Book;
import bg.softuni.entities.Category;
import bg.softuni.enums.AgeRestriction;
import bg.softuni.enums.EditionType;
import bg.softuni.services.AuthorService;
import bg.softuni.services.BookService;
import bg.softuni.services.CategoryService;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public ConsoleRunner(CategoryService categoryService, AuthorService authorService, BookService bookService) {

        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        // 1. Read seed files

        // Categories
        List<Category> categoriesList = findAllCategories();

        // Authors
        List<Author> authorList = findAllAuthors();

        // Books
        findAllBooks(authorList, categoriesList);

        // 2. Repositories & Queries

        // First Query
        List<Book> books = bookService.findReleaseAfter(2000);

        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        // Second Query
        List<Author> authors = authorService.findAuthorsBefore(1990);

        for (Author author : authors) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }

        // Third Query
        List<AuthorSummaryDTO> orders = authorService.getSummary();

        for (AuthorSummaryDTO author : orders) {

            System.out.printf("%s %s - %s books%n",
                author.getFirstName(),
                author.getLastName(),
                author.getBooksCount());
        }

        // Fourth Query
        List<Book> booksByAuthor = bookService.findByAuthor("George", "Powell");

        for (Book book : booksByAuthor) {
            System.out.printf("%s (%s) - %s%n", book.getTitle(), book.getReleaseDate(), book.getCopies());
        }
    }

    private List<Category> findAllCategories() throws IOException {

        List<String> categoryData = readFiles("categories.txt");
        List<Category> categoriesList = new ArrayList<>();

        for (String line : categoryData) {
            CategoryDTO category = new CategoryDTO(line);

            Category currCategory = categoryService.createCategoty(category);
            categoriesList.add(currCategory);
        }
        return categoriesList;
    }

    private List<Author> findAllAuthors() throws IOException {

        List<String> authorsData = readFiles("authors.txt");
        List<Author> authorList = new ArrayList<>();


        for (String line : authorsData) {

            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            AuthorDTO author = new AuthorDTO(firstName, lastName);
            Author currAuthor =  authorService.createAuthor(author);
            authorList.add(currAuthor);
        }
        return authorList;
    }

    private void findAllBooks(List<Author> authorList, List<Category> categoriesList) throws IOException {
        List<String> booksData = readFiles("books.txt");

        for (String line : booksData) {

            String[] data = line.split("\\s+");

            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

            Long copies = Long.parseLong(data[2]);
            BigDecimal price = new BigDecimal(data[3]);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            String title = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));

            int randomAuthorIndex = ThreadLocalRandom.current().nextInt(0, authorList.size());
            Author author = authorList.get(randomAuthorIndex);

            //  Random categories

            int randomCategoriesIndex = ThreadLocalRandom.current().nextInt(0, 8);
            List<Category> relevantCategories = new ArrayList<>();

            for (int i = 0; i < randomCategoriesIndex; i++) {

                int count  = ThreadLocalRandom.current().nextInt(0, categoriesList.size());
                relevantCategories.add(categoriesList.get(count));
            }

            BookDTO input = new BookDTO(title, copies, price, editionType, releaseDate, ageRestriction);
            BookRelationsDTO relationsDTO = new BookRelationsDTO(relevantCategories, author);

            bookService.createBook(input, relationsDTO);
        }
    }

    private List<String> readFiles(String path) throws IOException {

        ClassPathResource resource = new ClassPathResource(path);

        try (
            InputStream inputStream = resource.getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(inputStreamReader);

            return buffer.lines().toList();
        }
    }
}
