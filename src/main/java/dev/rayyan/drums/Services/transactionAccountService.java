package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.transactionAccount;
import dev.rayyan.drums.Services.transactionAccountService;
import dev.rayyan.drums.Models.customer;
import dev.rayyan.drums.Repositories.transactionAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class transactionAccountService {
    @Autowired
    private transactionAccountRepository transactionAccountRepositoryObj;
    public List<transactionAccount> getAllTransactionAccounts(){return  transactionAccountRepositoryObj.findAll();}
    public transactionAccount createTransactionAccount(transactionAccount transactionAccount){ return transactionAccountRepositoryObj.save(transactionAccount);}
}