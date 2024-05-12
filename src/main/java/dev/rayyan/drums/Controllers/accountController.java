package dev.rayyan.drums.Controllers;


import dev.rayyan.drums.Models.customer;
import dev.rayyan.drums.Services.accountService;
import dev.rayyan.drums.Models.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/accounts")
public class accountController
{
    @Autowired
    private accountService accountServiceObj;

    @GetMapping
    public ResponseEntity<List<account>> getAllAccounts(){
        return new ResponseEntity<List<account>>(accountServiceObj.getAllAccounts(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<account> createAccount(@RequestBody account account){
        return new ResponseEntity<account>(accountServiceObj.createAccount(account),HttpStatus.OK);
    }
    @GetMapping("/name/{accountName}")
    public ResponseEntity<Optional<account>> getAccountByName(@PathVariable String accountName) {
        return new ResponseEntity<Optional<account>>(accountServiceObj.getAccountByName(accountName), HttpStatus.OK);
    }

}
