package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.customer;
import dev.rayyan.drums.Repositories.customerRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class customerService {
    @Autowired
    private customerRepository customerRepositoryObj;

    public List<customer> getAllCustomers(){return  customerRepositoryObj.findAll();}

    public Optional<customer> getCustomerById(String id){return customerRepositoryObj.findById(id);}

    public customer createCustomer (customer customer){ return customerRepositoryObj.save(customer);}

}
