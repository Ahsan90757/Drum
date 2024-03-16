package dev.rayyan.drums;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactionItems")
public class transactionItemController {

    @Autowired
    private transactionItemService transactionItemServiceObj;

    @GetMapping
    public ResponseEntity<List<transactionItem>> getAllTransactionItems(){
        return new ResponseEntity<List<transactionItem>>(transactionItemServiceObj.allTransactionItems(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<transactionItem>> getTransactionItemById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<transactionItem>>(transactionItemServiceObj.transactionItemById(id), HttpStatus.OK);
    }
//    @GetMapping("/name/{itemName}")
//    public ResponseEntity<List<transactionItem>> getTransactionItemById(@PathVariable String itemName){
//        return new ResponseEntity<List<transactionItem>>(transactionItemServiceObj.transactionItemByName(itemName), HttpStatus.OK);
//    }
}
