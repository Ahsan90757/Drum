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
    @GetMapping("/name/{customerName}")
    public ResponseEntity<Optional<customer>> getCustomerByName(@PathVariable String customerName) {
        return new ResponseEntity<Optional<customer>>(customerServiceObj.getCustomerByName(customerName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<customer> createCustomer(@RequestBody customer customer){
        return new ResponseEntity<customer>(customerServiceObj.createCustomer(customer),HttpStatus.OK);
    }

    @DeleteMapping("/{customerName}")
    public ResponseEntity<String> deleteCustomerByName(@PathVariable String customerName) {
        try {
            // Attempt to delete the item by name
            customerServiceObj.deleteCustomerByName(customerName);
            return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle any errors that occur during deletion
            return new ResponseEntity<>("Failed to delete item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
