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
//            String itemId = transactionItem.getItemId();
            String itemName = transactionItem.getItemName();
            System.out.println("_________________Name:____________________"+itemName);
            double quantity = transactionItem.getQuantity();
            System.out.println("_________________Quantity:____________________"+quantity);

            // Retrieve the item from the item repository
            Optional<item> optionalItem = itemRepositoryObj.findByName(itemName);

            if (optionalItem.isPresent()) {
                System.out.println("_____________Found_____________");
                item item = optionalItem.get();
                double remainingQuantity = item.getRemainingQuantity();
                System.out.println("_________________remainingQuantity:____________________"+remainingQuantity);
                // Check if there is sufficient quantity available
                if ("buying".equalsIgnoreCase(transaction.getType())) {
                    // For buying transaction, add quantity to item's remaining quantity
                    System.out.println("_________________HERE________________");
                    item.setRemainingQuantity(remainingQuantity+quantity);
                    itemRepositoryObj.save(item);
                } else {
                    // For selling transaction, deduct quantity from item's remaining quantity
                    if (remainingQuantity >= quantity) {
                        item.setRemainingQuantity(remainingQuantity - quantity);
                        itemRepositoryObj.save(item);
                    } else {
                        // Handle insufficient quantity error
                        // You can throw an exception or handle it as needed
                    }
                }
            } else {
                // Handle item not found error
                //throw new ItemNotFoundException("Item not found with ID: " + itemId);
            }
        }

        return transactionRepositoryObj.save(transaction);
    }
    public List<transaction> transactionsByCustomerNumber(String customerNumber) {
        return transactionRepositoryObj.findByCustomerNumber(customerNumber);
    }

}
