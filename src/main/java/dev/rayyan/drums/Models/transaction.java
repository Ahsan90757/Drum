package dev.rayyan.drums.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import dev.rayyan.drums.Models.transactionItem;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class transaction {

    @Id
    private ObjectId id;
    private String customerNumber;
    private String type;
    private String date;
    private String paymentMethod;
    private double amountReceived;
    private List<transactionItem> transactionItems;

//    public List<transactionItem> getTransactionItems(){
//        return transactionItems;
//    }

    void test(){

    }
}
