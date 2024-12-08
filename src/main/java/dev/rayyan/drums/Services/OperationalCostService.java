package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.OperationalCost;
import dev.rayyan.drums.Repositories.OperationalCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationalCostService {

    @Autowired
    private OperationalCostRepository operationalCostRepository;

    // Fetch operational costs for a specific date
    public List<OperationalCost> getOperationalCostsByDate(LocalDateTime date) {
        return operationalCostRepository.findByDate(date);
    }

    // Fetch operational costs within a date range
    public List<OperationalCost> getOperationalCostsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return operationalCostRepository.findByDateBetween(startDate, endDate);
    }

    // Save a new operational cost
    public OperationalCost saveOperationalCost(OperationalCost operationalCost) {
        return operationalCostRepository.save(operationalCost);
    }
}
