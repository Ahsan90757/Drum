package dev.rayyan.drums.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class transactionItem {

    private String itemName;
    private double quantity;
    private double unitPrice;
    private String itemId;


    public double getTotalPrice() {
        return quantity * unitPrice;
    }
}
