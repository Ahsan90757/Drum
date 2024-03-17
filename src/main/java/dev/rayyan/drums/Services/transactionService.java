package dev.rayyan.drums.Services;
import dev.rayyan.drums.Repositories.itemRepository;
import dev.rayyan.drums.Repositories.transactionRepository;
import dev.rayyan.drums.Models.transaction;
import dev.rayyan.drums.Models.transactionItem;
import dev.rayyan.drums.Models.item;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("_________________HERE____________________");
        for (transactionItem transactionItem : transaction.getTransactionItems()) {
            String itemId = transactionItem.getItemId();
            System.out.println("_________________ID:____________________"+itemId);
            double quantity = transactionItem.getQuantity();
            System.out.println("_________________Quantity:____________________"+quantity);

            // Retrieve the item from the item repository
            Optional<item> optionalItem = itemRepositoryObj.findById(itemId);
            if (optionalItem.isPresent()) {
                item item = optionalItem.get();
                double remainingQuantity = item.getRemainingQuantity();
                System.out.println("_________________remainingQuantity:____________________"+remainingQuantity);
                // Check if there is sufficient quantity available
                if (remainingQuantity >= quantity) {
                    // Update the remaining quantity
                    item.setRemainingQuantity(remainingQuantity - quantity);
                    itemRepositoryObj.save(item);
                } else {
                    // Handle insufficient quantity error
                    //throw new InsufficientQuantityException("Insufficient quantity available for item: " + item.getName());
                }
            } else {
                // Handle item not found error
                //throw new ItemNotFoundException("Item not found with ID: " + itemId);
            }
        }

        return transactionRepositoryObj.save(transaction);
    }
}
