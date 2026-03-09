package SPRING.services;

import SPRING.entities.Account;
import SPRING.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, long id) {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            System.out.println("Account not found");
            return;
        }

        Account acc = account.get();

        if (acc.getBalance().compareTo(money) < 0) {
            System.out.println("Not enough money");
            return;
        }

        acc.setBalance(acc.getBalance().subtract(money));
        accountRepository.save(acc);
    }

    @Override
    public void transferMoney(BigDecimal money, long id) {

        if (money.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Money must be positive");
            return;
        }

        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            System.out.println("Account not found");
            return;
        }

        Account acc = account.get();

        acc.setBalance(acc.getBalance().add(money));
        accountRepository.save(acc);
    }
}
