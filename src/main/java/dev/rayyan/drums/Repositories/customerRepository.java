package dev.rayyan.drums.Repositories;

import dev.rayyan.drums.Models.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface customerRepository extends MongoRepository<customer, String> {
    Optional<customer> findByCustomerName(String customerName);

    void deleteByCustomerName(String customerName);
}
