package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.account;
import dev.rayyan.drums.Models.customer;
import dev.rayyan.drums.Repositories.accountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class accountService {
    @Autowired
    private accountRepository accountRepositoryObj;
    public List<account> getAllAccounts(){return  accountRepositoryObj.findAll();}
    public account createAccount(account account){ return accountRepositoryObj.save(account);}
    public Optional<account> getAccountByName(String accountName) {
        return accountRepositoryObj.findByAccountName(accountName);
    }
}
