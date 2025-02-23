package dev.rayyan.drums.Controllers;

import dev.rayyan.drums.Models.OperationalCost;
import dev.rayyan.drums.Services.OperationalCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operational-costs")
public class OperationalCostController {

    @Autowired
    private OperationalCostService operationalCostService;

    @GetMapping
    public List<OperationalCost> getAllOperationalCosts() {
        return operationalCostService.getAllOperationalCosts();
    }

    @GetMapping("/{id}")
    public Optional<OperationalCost> getOperationalCostById(@PathVariable String id) {
        return operationalCostService.getOperationalCostById(id);
    }

    @PostMapping
    public OperationalCost addOperationalCost(@RequestBody OperationalCost operationalCost) {
        return operationalCostService.addOperationalCost(operationalCost);
    }

    @DeleteMapping("/{id}")
    public void deleteOperationalCost(@PathVariable String id) {
        operationalCostService.deleteOperationalCost(id);
    }
}
