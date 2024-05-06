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
    private int quantity;
    private int unitPrice;

    public int getTotalPrice() {
        return quantity * unitPrice;
    }
}