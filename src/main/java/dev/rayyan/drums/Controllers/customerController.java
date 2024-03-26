package dev.rayyan.drums.Controllers;

import dev.rayyan.drums.Services.customerService;
import dev.rayyan.drums.Models.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customers")
public class customerController
{
    @Autowired
    private customerService customerServiceObj;

    @GetMapping
    public ResponseEntity<List<customer>> getAllCustomers(){
        return new ResponseEntity<List<customer>>(customerServiceObj.getAllCustomers(),HttpStatus.OK);
    }
    @GetMapping("/{Id}")
    public ResponseEntity<Optional<customer>> getCustomerById(@PathVariable String id){
        return new ResponseEntity<Optional<customer>>(customerServiceObj.getCustomerById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<customer> createCustomer(@RequestBody customer customer){
        return new ResponseEntity<customer>(customerServiceObj.createCustomer(customer),HttpStatus.OK);
    }
}
