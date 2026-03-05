package softuni.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import softuni.servises.AuthorService;
import softuni.servises.BookService;
import softuni.servises.CategoryService;

@Component
@Order(1)
public class SeedRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    public SeedRunner(
        AuthorService authorService,
        BookService bookService,
        CategoryService categoryService) {

            this.authorService = authorService;
            this.bookService = bookService;
            this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        authorService.seedAuthors();
        categoryService.seedCategories();
        bookService.seedBooks();
    }
}
