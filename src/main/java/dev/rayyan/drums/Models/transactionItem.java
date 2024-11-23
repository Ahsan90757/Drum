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
    private float quantity;
    private float unitPrice;

    public float getTotalPrice() {
        return quantity * unitPrice;
    }
}