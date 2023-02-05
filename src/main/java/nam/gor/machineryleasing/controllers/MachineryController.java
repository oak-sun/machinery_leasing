package nam.gor.machineryleasing.controllers;

import nam.gor.machineryleasing.models.entities.Machinery;
import nam.gor.machineryleasing.services.MachineryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MachineryController {
    private final MachineryService service;

    @GetMapping("/machines")
    public List<Machinery> getMachines() {
        return service.getMachines();
    }

    @GetMapping("/machines/{id}")
    public Machinery getMachinery(@PathVariable("id") String machinery_id) {
        return service.getMachinery(machinery_id);
    }

    @PostMapping("/machines")
    public Machinery createMachinery(@RequestBody @Validated Machinery machinery) {
        return service.createMachinery(machinery);
    }

    @PutMapping("/machines/{id}")
    public void updateMachinery(@PathVariable("id") String machinery_id,
                                @RequestBody @Validated Machinery machinery) {
        service.updateMachinery(machinery_id, machinery);
    }
}
