package bg.softuni.services;

import bg.softuni.DTO.CategoryDTO;
import bg.softuni.entities.Category;

public interface CategoryService {

    Category createCategoty(CategoryDTO input);
}
