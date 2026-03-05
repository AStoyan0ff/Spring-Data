package softuni.servises;

import org.springframework.stereotype.Service;
import softuni.entities.User;
import softuni.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUsersByEmailProvider(String provider) {

        List<User> users =
            userRepository.findByEmailEndingWith(provider);

        if(users.isEmpty()){

            System.out.println(
                "No users found with email domain " + provider);

            return;
        }

        users.forEach(u ->
            System.out.println(
                u.getUsername() + " " + u.getEmail()));
    }

    @Override
    public void removeInactiveUsers(String date) {

        LocalDateTime dateTime = LocalDateTime.parse(date);

        List<User> users =
            userRepository.findByLastTimeLoggedInBefore(dateTime);

        users.forEach(u -> u.setDeleted(true));

        userRepository.saveAll(users);

        System.out.println(users.size() + " users marked as deleted");
    }
}
