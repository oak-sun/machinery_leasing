package nam.gor.machineryleasing.controllers;

import nam.gor.machineryleasing.models.entities.Lessor;
import nam.gor.machineryleasing.services.LessorService;
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
public class LessorController {
    private final LessorService service;

    @GetMapping("/lessors")
    public List<Lessor> getLessors() {
        return service.getLessors();
    }

    @GetMapping("/lessors/{id}")
    public Lessor getLessor(@PathVariable("id") String lessor_id) {
        return service.getLessor(lessor_id);
    }

    @PostMapping("/lessors")
    public Lessor createLessor(@RequestBody @Validated Lessor lessor) {
        return service.createLessor(lessor);
    }

    @PutMapping("/lessors/{id}")
    public void updateLessor(@PathVariable("id") String lessor_id,
                             @RequestBody @Validated Lessor lessor) {
        service.updateSeller(lessor_id, lessor);
    }
}
