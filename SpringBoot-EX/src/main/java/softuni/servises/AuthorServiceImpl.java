package softuni.servises;

import org.springframework.stereotype.Service;
import softuni.constants.GlobalConstants;
import softuni.entities.Author;
import softuni.repositories.AuthorRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final Random random = new Random();

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws Exception {

        if (authorRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(
                GlobalConstants.RESOURCE_PATH +
                GlobalConstants.AUTHORS_FILE_NAME))

            .forEach(row -> {
                String[] data = row.split("\\s+");

                Author author = new Author(data[0], data[1]);
                authorRepository.save(author);
            });
    }

    @Override
    public Author getRandomAuthor() {

        long count = authorRepository.count();
        long id = random.nextLong(count) + 1;

        return authorRepository.findById((int) id).orElse(null);
    }
}
