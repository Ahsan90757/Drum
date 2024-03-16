package dev.rayyan.drums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class transaction {

    @Id
    private ObjectId id;
    private String customerId;
    private String date;
    private String paymentMethod;
    private double amountReceived;
    @DocumentReference
    private List<transactionItem> transactionItemIds;

}
