package dev.rayyan.drums.Repositories;

import dev.rayyan.drums.Models.item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface itemRepository extends MongoRepository<item, String> {
    Optional<item> findByName(String name);
}
