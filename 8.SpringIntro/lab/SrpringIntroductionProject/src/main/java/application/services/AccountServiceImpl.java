package application.services;

import application.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import application.repositories.AccountRepository;

import java.math.BigDecimal;
@Service
@Primary
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
       if(!this.accountRepository.exists(id)){
           System.out.printf("Account %d does not exists%n",id);
       }else {
           Account account=this.accountRepository.findOne(id);
           if(account.getBalance().compareTo(money)<0&&account.getUser()!=null){
               System.out.println("Insufficient money");
           }else {
               account.setBalance(account.getBalance().subtract(money));
               this.accountRepository.save(account);
           }
       }

    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        if(!this.accountRepository.exists(id)){
            System.out.printf("Account %d does not exists%n",id);
        }else {
            Account account=this.accountRepository.findOne(id);
            if(money.compareTo(BigDecimal.ZERO)<0){
                System.out.println("Money cannot be negative");
            }else {
                account.setBalance(account.getBalance().add(money));
                this.accountRepository.save(account);
            }
        }

    }
}
