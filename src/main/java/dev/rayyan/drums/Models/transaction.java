package dev.rayyan.drums.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import dev.rayyan.drums.Models.transactionItem;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class transaction {

    @Id
    private ObjectId id;
    private int transactionNumber;
    private String customerNumber;
    private String transactionType;
    private Date date;
    private String paymentMethod;
    private String receivedBy;
    private float amountReceived;
    private String Description;
    private String Reminder;

    private int totalAmount;
    //    private int remainingAmount;
    private List<transactionItem> transactionItems;
    private List<transactionAccount> transactionAccounts;

    public int getTransactionNumber(transaction transaction) {
        return  transactionNumber;
    }


//    private List<account> payments;c

//    public List<transactionItem> getTransactionItems(){
//        return transactionItems;
//    }


}