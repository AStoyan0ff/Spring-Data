package STARTER.services;

import STARTER.repositories.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    // Query 1 - Products in a range
    public String getAllProducts(BigDecimal min, BigDecimal max) throws JsonProcessingException {

        var products = productRepository.findProductsByDTO(min, max);
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(products);
    }
}
