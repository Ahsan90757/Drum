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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<item>> getItemById(@PathVariable String id) {
        return new ResponseEntity<Optional<item>>(itemServiceObj.getItemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<item> createItem(@RequestBody item item){
        return new ResponseEntity<item>(itemServiceObj.createItem(item),HttpStatus.CREATED);
    }
}
