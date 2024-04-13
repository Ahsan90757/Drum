package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.item;
import dev.rayyan.drums.Repositories.itemRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class itemService {

    @Autowired
    private itemRepository itemRepositoryObj;

    public List<item> getAllItems() {
        return itemRepositoryObj.findAll();
    }

    public Optional<item> getItemById(String id) {
        return itemRepositoryObj.findById(id);
    }
    public Optional<item> getItemByName(String name) {
        return itemRepositoryObj.findByName(name);
    }

    public item createItem(item item){
        return itemRepositoryObj.insert(item);
    }
}
