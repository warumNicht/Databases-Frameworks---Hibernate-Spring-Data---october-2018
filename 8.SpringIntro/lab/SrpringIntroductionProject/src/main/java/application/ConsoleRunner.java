package application;

import application.models.Account;
import application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import application.services.AccountServiceImpl;
import application.services.UserServiceImpl;

import java.math.BigDecimal;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }




    @Override
    public void run(String... strings) throws Exception {
        User user=new User("Jov40",40);

        Account account=new Account();
        account.setBalance(new BigDecimal(100));
        user.getAccounts().add(account);
        account.setUser(user);
        this.userService.registerUser(user);

        this.accountService.withdrawMoney(new BigDecimal(25),1L);
        this.accountService.transferMoney(new BigDecimal(500),1L);
    }
}
