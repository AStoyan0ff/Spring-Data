package SPRING;

import SPRING.entities.Account;
import SPRING.entities.User;
import SPRING.services.AccountService;
import SPRING.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final AccountService accountService;
    private final UserService userService;

    public Runner(AccountService accountService, UserService userService) {

        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("user", 20);
        Account account = new Account();

        user.setAccounts(List.of(account));
        account.setUser(user);

        userService.saveUser(user);

        accountService.withdrawMoney(BigDecimal.TEN, 1);
        accountService.transferMoney(BigDecimal.TEN, 1);
    }
}
