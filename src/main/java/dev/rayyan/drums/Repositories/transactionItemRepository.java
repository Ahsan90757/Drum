package dev.rayyan.drums.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import dev.rayyan.drums.Models.transactionItem;

@Repository
public interface transactionItemRepository extends MongoRepository<transactionItem, ObjectId> {

//    List<transactionItem> findtransactionItemByitemName(String itemName);
}
