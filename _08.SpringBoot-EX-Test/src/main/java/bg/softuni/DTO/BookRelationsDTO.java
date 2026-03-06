package bg.softuni.DTO;

import bg.softuni.entities.Author;
import bg.softuni.entities.Category;

import java.util.List;

public class BookRelationsDTO {

    private final List<Category> categories;
    private final Author author;

    public BookRelationsDTO(List<Category> categories, Author author) {
        this.categories = categories;
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Author getAuthor() {
        return author;
    }
}
