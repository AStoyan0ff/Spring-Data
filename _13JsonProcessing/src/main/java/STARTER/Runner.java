package STARTER;

import STARTER.entities.Category;
import STARTER.entities.Product;
import STARTER.entities.User;
import STARTER.repositories.CategoryRepository;
import STARTER.repositories.ProductRepository;
import STARTER.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
@Order(1)
public class Runner implements CommandLineRunner {

    private static final String CATEGORIES_FILE = "/input/categories.json";
    private static final String PRODUCTS_FILE = "/input/products.json";
    private static final String USERS_FILE = "/input/users.json";

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Random random = new Random();

    public Runner(CategoryRepository categoryRepository,
                  ProductRepository productRepository,
                  UserRepository userRepository) {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        addCategories();
        addUsers();
        addProducts();
    }

    private void addCategories() throws IOException{

        // Read categories from filestream
        InputStream buff = getClass().getResourceAsStream(CATEGORIES_FILE);
        Category[] categories = new ObjectMapper().readValue(buff, Category[].class);

        categoryRepository.saveAll(Arrays.asList(categories));
    }

    private void addUsers() throws IOException{

        InputStream buff = getClass().getResourceAsStream(USERS_FILE);
        User[] users = new ObjectMapper().readValue(buff, User[].class);

        userRepository.saveAll(Arrays.asList(users));
    }

    private void addProducts() throws IOException{

        // Get all users
        List<User> usersList = userRepository.findAll();
        // Get all categories
        List<Category> categoriesList = categoryRepository.findAll();

        InputStream buff = getClass().getResourceAsStream(PRODUCTS_FILE);
        Product[] products = new ObjectMapper().readValue(buff, Product[].class);

        for (Product product : products) {
            // set random seller
            int seller = random.nextInt(usersList.size());
            int buyer = random.nextInt(usersList.size());

            // Seller and Buyer are different people
            while (seller == buyer) {
                buyer = random.nextInt(usersList.size());
            }

            product.setSeller(usersList.get(seller));

            // Set random products without a buyer
            if (random.nextInt(10) > 3) product.setBuyer(usersList.get(buyer));

            Collections.shuffle(categoriesList);

            int categoriesSize = random.nextInt(3) + 1;
            product.setCategories(new HashSet<>(categoriesList.subList(0, categoriesSize)));
        }

        productRepository.saveAll(Arrays.asList(products));
    }
}
