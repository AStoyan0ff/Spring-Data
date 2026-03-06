package bg.softuni.services;

import bg.softuni.DTO.CategoryDTO;
import bg.softuni.entities.Category;
import bg.softuni.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategoty(CategoryDTO input) {

        Category category = new Category();
        category.setName(input.getName());

        return categoryRepository.save(category);
    }
}
