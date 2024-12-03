package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.customer;
import dev.rayyan.drums.Repositories.customerRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.SystemEventListener;
import java.util.List;
import java.util.Optional;

@Service
public class customerService {
    @Autowired
    private customerRepository customerRepositoryObj;

    public List<customer> getAllCustomers(){return  customerRepositoryObj.findAll();}

    public Optional<customer> getCustomerById(String id){return customerRepositoryObj.findById(id);}

    public customer createCustomer (customer customer){ return customerRepositoryObj.save(customer);}

    public void deleteCustomerByName(String customerName) {
        customerRepositoryObj.deleteByCustomerName(customerName);
    }
    public Optional<customer> getCustomerByName(String customerName) {
        return customerRepositoryObj.findByCustomerName(customerName);
    }
    public Optional<customer> getCustomerByNumber(String customerNumber) {
        return customerRepositoryObj.findByCustomerNumber(customerNumber);
    }

    public customer updateCustomer(String customerNumber, customer updatedCustomer) {
        customer existingCustomer = customerRepositoryObj.findByCustomerNumber(customerNumber).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setCustomerNumber(updatedCustomer.getCustomerNumber());
            System.out.println( existingCustomer.getCustomerNumber());
            existingCustomer.setCustomerName(updatedCustomer.getCustomerName());
            existingCustomer.setBalance(updatedCustomer.getBalance());
            existingCustomer.setReminder(updatedCustomer.getReminder());
            customerRepositoryObj.save(existingCustomer);
        }
        return existingCustomer;
    }

    public void deleteCustomer(String customerNumber) {
        customer existingCustomer = customerRepositoryObj.findByCustomerNumber(customerNumber).orElse(null);
        System.out.println(existingCustomer);

        if (existingCustomer != null) {
            customerRepositoryObj.delete(existingCustomer);
        }
    }
}
