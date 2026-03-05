package softuni.servises;

import softuni.entities.Category;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws Exception;

    Set<Category> getRandomCategories();
}
