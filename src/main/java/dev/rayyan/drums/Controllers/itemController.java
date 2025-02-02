package dev.rayyan.drums.Controllers;

import dev.rayyan.drums.Models.item;
import dev.rayyan.drums.Services.itemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class itemController {

    @Autowired
    private itemService itemServiceObj;

    @GetMapping
    public ResponseEntity<List<item>> getAllItems() {
        return new ResponseEntity<List<item>>(itemServiceObj.getAllItems(),HttpStatus.OK );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<item>> getItemByName(@PathVariable String name) {
        return new ResponseEntity<Optional<item>>(itemServiceObj.getItemByName(name), HttpStatus.OK);
    }
    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("")
    public ResponseEntity<item> createItem(@RequestBody item item){
        return new ResponseEntity<item>(itemServiceObj.createItem(item),HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteItemByName(@PathVariable String name) {
        try {
            // Attempt to delete the item by name
            itemServiceObj.deleteItemByName(name);
            return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle any errors that occur during deletion
            return new ResponseEntity<>("Failed to delete item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
