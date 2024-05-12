package dev.rayyan.drums.Repositories;

import dev.rayyan.drums.Models.account;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface accountRepository extends MongoRepository<account, String> {
    Optional<account> findByAccountName(String accountName);

}
