package dev.rayyan.drums.Services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import dev.rayyan.drums.Repositories.transactionItemRepository;
import dev.rayyan.drums.Models.transactionItem;

@Service
public class transactionItemService {
    @Autowired
    private transactionItemRepository transactionItemRepositoryObj;
    public List<transactionItem> allTransactionItems(){
        return transactionItemRepositoryObj.findAll();
    }

    public Optional<transactionItem> transactionItemById(ObjectId id){
        return transactionItemRepositoryObj.findById(id);
    }

//    public List<transactionItem> transactionItemByName(String itemName){
//        return transactionItemRepositoryObj.findtransactionItemByitemName(itemName);
//    }
}
