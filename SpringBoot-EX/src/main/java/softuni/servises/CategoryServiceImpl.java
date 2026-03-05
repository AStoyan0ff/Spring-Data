package softuni.servises;

import org.springframework.stereotype.Service;
import softuni.constants.GlobalConstants;
import softuni.entities.Category;
import softuni.repositories.CategoryRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final Random random = new Random();

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws Exception {

        if (categoryRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(
            GlobalConstants.RESOURCE_PATH +
            GlobalConstants.CATEGORIES_FILE_NAME))

            .forEach(name -> {
                Category category = new Category(name);
                categoryRepository.save(category);
            });
    }

    @Override
    public Set<Category> getRandomCategories() {

        long count = categoryRepository.count();
        int randomCount = random.nextInt(3) + 1;

        Set<Category> categories = new HashSet<>();

        for (int pos = 0; pos < randomCount; pos++) {
            long id = random.nextLong(count) + 1;

            categories.add(categoryRepository.findById((int) id).orElse(null));

        }

        return categories;
    }
}
