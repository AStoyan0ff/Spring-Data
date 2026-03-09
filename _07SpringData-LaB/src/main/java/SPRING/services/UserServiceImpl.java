package SPRING.services;

import SPRING.entities.User;
import SPRING.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {

        Optional<User> isExists = userRepository.findByUsername(user.getUsername());

        if(isExists.isEmpty()) {
            System.out.println("User already saved");
            return;
        }
        userRepository.save(user);
    }
}
