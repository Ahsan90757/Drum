package dev.rayyan.drums.Controllers;
import dev.rayyan.drums.Models.transactionAccount;
import dev.rayyan.drums.Services.transactionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/transactionAccounts")
public class transactionAccountController {

    @Autowired
    private transactionAccountService transactionAccountServiceObj;
    @GetMapping
    public ResponseEntity<List<transactionAccount>> getAllTransactionAccounts(){
        return new ResponseEntity<List<transactionAccount>>(transactionAccountServiceObj.getAllTransactionAccounts(), HttpStatus.OK);
    }



}