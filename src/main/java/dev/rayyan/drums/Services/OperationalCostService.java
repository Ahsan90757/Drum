package dev.rayyan.drums.Services;

import dev.rayyan.drums.Models.OperationalCost;
import dev.rayyan.drums.Repositories.OperationalCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OperationalCostService {

    @Autowired
    private OperationalCostRepository operationalCostRepository;

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
}
