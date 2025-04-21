package dev.rayyan.drums.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "operationalCosts")
public class OperationalCost {
    @Id
    private ObjectId id;
    @DBRef
    private Category category;
    private double amount;
    private LocalDateTime date; // Full date and time

    public String getCategoryId() {
        return category.getId();
    }
}
