package dev.rayyan.drums.Repositories;

import dev.rayyan.drums.Models.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepository extends MongoRepository<customer, String> {

}