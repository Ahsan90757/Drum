package dev.rayyan.drums.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "operationalCosts")
public class OperationalCost {
    @Id
    private ObjectId id;
    private String category;
    private String subcategory;
    private double amount;
    private LocalDateTime date; // Full date and time
}
