package dev.rayyan.drums.Repositories;
import dev.rayyan.drums.Models.transaction;

import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface transactionRepository extends MongoRepository<transaction , ObjectId> {
    List<transaction> findByCustomerNumber(String customerNumber);
    Optional<transaction> findTopByOrderByTransactionNumberDesc();
    //List<transaction> findTopNByOrderByDateDesc(int numberOfTransactions);
    @Query("{ 'transactionItems.itemName': ?0 }")
    List<transaction> findTopNByItemNameOrderByIdDesc(String itemName, Pageable pageable);


}

