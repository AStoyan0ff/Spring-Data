package softuni.servises;

public interface UserService {

    void getUsersByEmailProvider(String provider);
    void removeInactiveUsers(String date);
}
