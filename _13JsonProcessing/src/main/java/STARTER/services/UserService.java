package STARTER.services;

import STARTER.DTOs.SellerViewDTO;
import STARTER.DTOs.SoldProductViewDTO;
import STARTER.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Query 2 - Successfully Sold Products
    public String findUsersSold() throws JsonProcessingException {
        var users = userRepository.getUsersByProductsSold().stream()
            .map(user -> new SellerViewDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getProductsSold().stream()
                    .filter(p -> p.getBuyer() != null)
                    .map(product -> new SoldProductViewDTO(
                        product.getName(),
                        product.getPrice(),
                        product.getBuyer().getFirstName(),
                        product.getBuyer().getLastName()))
                    .toList()
            )).toList();

        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(users);
    }
}
