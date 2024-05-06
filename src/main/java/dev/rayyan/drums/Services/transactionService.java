package dev.rayyan.drums.Services;
import dev.rayyan.drums.Repositories.itemRepository;
import dev.rayyan.drums.Repositories.transactionRepository;
import dev.rayyan.drums.Models.transaction;
import dev.rayyan.drums.Models.transactionItem;
import dev.rayyan.drums.Models.item;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.io.ObjectInput;
import java.util.List;
import java.util.Optional;

@Service
public class transactionService {
    @Autowired
    itemRepository itemRepositoryObj;
    @Autowired
    transactionRepository transactionRepositoryObj;


    public List<transaction> allTransactions(){
        return transactionRepositoryObj.findAll();
    }

    public Optional<transaction> transactionById(ObjectId id){
        return transactionRepositoryObj.findById(id);
    }

    public transaction createTransaction(transaction transaction) {
        for (transactionItem transactionItem : transaction.getTransactionItems()) {
            String itemName = transactionItem.getItemName();
            double quantity = transactionItem.getQuantity();

            Optional<item> optionalItem = itemRepositoryObj.findByName(itemName);

            if (optionalItem.isPresent()) {
                item existingItem = optionalItem.get();
                double remainingQuantity = existingItem.getRemainingQuantity();
                if ("buying".equalsIgnoreCase(transaction.getTransactionType())) {
                    existingItem.setRemainingQuantity(remainingQuantity + quantity);
                } else {
                    if (remainingQuantity >= quantity) {
                        existingItem.setRemainingQuantity(remainingQuantity - quantity);
                    }
                }
                itemRepositoryObj.save(existingItem);
            }
        }
        return transactionRepositoryObj.save(transaction);
    }

    public List<transaction> transactionsByCustomerNumber(String customerNumber) {
        return transactionRepositoryObj.findByCustomerNumber(customerNumber);
    }
}
