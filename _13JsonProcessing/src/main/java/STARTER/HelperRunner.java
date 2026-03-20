package STARTER;

import STARTER.services.ProductService;
import STARTER.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Order(2)
public class HelperRunner implements CommandLineRunner {

    private final ProductService productService;
    private final UserService userService;

    public HelperRunner(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        productsInRange();
        usersProductSold();

    }

    // Query 1 - Products in a range
    private void productsInRange() throws IOException {
        BigDecimal minPrice = new BigDecimal(500);
        BigDecimal maxPrice = new BigDecimal(1000);

        var products = productService.getAllProducts(minPrice, maxPrice);
        System.out.println(products);

        // Save to file
        Files.writeString(Path.of("src/main/resources/output/products-in-range.json") ,products);
    }

    // Query 2 - Successfully Sold Products
    private void usersProductSold() throws IOException {

        var users = userService.findUsersSold();
        System.out.println(users);

        Files.writeString(Path.of("src/main/resources/output/users-product-sold.json") ,users);
    }
}
