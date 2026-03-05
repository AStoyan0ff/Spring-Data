package softuni.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import softuni.servises.AuthorService;
import softuni.servises.BookService;
import softuni.servises.CategoryService;

@Component
@Order(2)
public class ConsoleRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    public ConsoleRunner(
        AuthorService authorService,
        CategoryService categoryService,
        BookService bookService) {

        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

        seedDatabase();

        bookService.findBooksAfter2000()
            .forEach(b -> System.out.println(b.getTitle()));
    }

    private void seedDatabase() throws Exception {

        authorService.seedAuthors();
        categoryService.seedCategories();
        bookService.seedBooks();
    }
}
