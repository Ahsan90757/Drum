package dev.rayyan.drums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactionItems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class transactionItem {

    @Id
    private ObjectId id;
    private String itemName;
    private double quantity;
    private double unitPrice;

    public double getTotalPrice(){
        return quantity*unitPrice;
    }

}