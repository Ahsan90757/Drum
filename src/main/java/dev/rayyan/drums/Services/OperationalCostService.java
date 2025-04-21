package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.Category;
import dev.rayyan.drums.Models.OperationalCost;
import dev.rayyan.drums.Repositories.CategoryRepository;
import dev.rayyan.drums.Repositories.OperationalCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OperationalCostService {

    @Autowired
    private OperationalCostRepository operationalCostRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<OperationalCost> getAllOperationalCosts() {
        return operationalCostRepository.findAll();
    }

    public Optional<OperationalCost> getOperationalCostById(String id) {
        return operationalCostRepository.findById(id);
    }

    public OperationalCost addOperationalCost(OperationalCost operationalCost) {
        return operationalCostRepository.save(operationalCost);
    }

    public void deleteOperationalCost(String id) {
        operationalCostRepository.deleteById(id);
    }
    public OperationalCost saveOperationalCost(OperationalCost request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));

        OperationalCost cost = new OperationalCost(null, category, request.getAmount(), LocalDateTime.now());
        return operationalCostRepository.save(cost);
    }
}