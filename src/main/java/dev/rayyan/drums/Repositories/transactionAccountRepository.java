package dev.rayyan.drums.Repositories;

import dev.rayyan.drums.Models.transactionAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transactionAccountRepository extends MongoRepository<transactionAccount,String> {
}
