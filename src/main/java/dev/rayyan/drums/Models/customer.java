package dev.rayyan.drums.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class customer {

    @Id
    private ObjectId id;
    String customerName;
    @Indexed(unique = true)
    String customerNumber;
    Date lastTransaction;
    String reminder;
    float balance;
}
