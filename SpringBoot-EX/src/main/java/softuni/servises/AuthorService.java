package softuni.servises;

import softuni.entities.Author;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws Exception;
    Author getRandomAuthor();
}
