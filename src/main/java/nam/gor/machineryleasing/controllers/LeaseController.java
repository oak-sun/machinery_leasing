package nam.gor.machineryleasing.controllers;

import nam.gor.machineryleasing.models.entities.Lease;
import nam.gor.machineryleasing.models.entities.LeaseNote;
import nam.gor.machineryleasing.services.LeaseService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LeaseController {
    private final LeaseService service;

    @GetMapping("/leases")
    public List<Lease> getLeases() {
        return service.getLeases();
    }

    @GetMapping("/leases/{id}")
    public Lease getLease(@PathVariable("id") String lease_id) {
        return service.getLease(lease_id);
    }

    @PostMapping("/leases")
    public Lease registerLease(@RequestBody @Validated LeaseNote leaseNote) {
        return service.registerNewLease(leaseNote);
    }
}
