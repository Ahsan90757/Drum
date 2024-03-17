package dev.rayyan.drums.Repositories;
import dev.rayyan.drums.Models.transaction;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transactionRepository extends MongoRepository<transaction , ObjectId> {
}
