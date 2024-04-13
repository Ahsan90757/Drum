package dev.rayyan.drums.Controllers;

import dev.rayyan.drums.Services.transactionService;
import dev.rayyan.drums.Models.transaction;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class transactionController {

    @Autowired
    private transactionService transactionServiceObj;


    @GetMapping("")
    public ResponseEntity<List<transaction>> getAllTransactions(){
        return new ResponseEntity<List<transaction>>(transactionServiceObj.allTransactions(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<transaction>> getTransactionById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<transaction>>(transactionServiceObj.transactionById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<transaction> createTransaction(@RequestBody transaction transaction) {
        transaction createdTransaction = transactionServiceObj.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/customer/{customerNumber}")
    @ResponseBody
    public List<transaction> getTransactionsByCustomerId(@PathVariable String customerNumber) {
        return transactionServiceObj.transactionsByCustomerNumber(customerNumber);
    }

}
