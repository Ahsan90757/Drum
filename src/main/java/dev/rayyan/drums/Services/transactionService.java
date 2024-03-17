package dev.rayyan.drums.Services;
import dev.rayyan.drums.Repositories.transactionRepository;
import dev.rayyan.drums.Models.transaction;

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
    private transactionRepository transactionRepositoryObj;

    public List<transaction> allTransactions(){
        return transactionRepositoryObj.findAll();
    }

    public Optional<transaction> transactionById(ObjectId id){
        return transactionRepositoryObj.findById(id);
    }

}
