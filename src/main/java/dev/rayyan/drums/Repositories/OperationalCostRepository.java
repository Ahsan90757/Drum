package dev.rayyan.drums.Repositories;

import dev.rayyan.drums.Models.OperationalCost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationalCostRepository extends MongoRepository<OperationalCost, String> {
    List<OperationalCost> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<OperationalCost> findByDate(LocalDateTime date);
}
