package nam.gor.machineryleasing.services;

import lombok.AllArgsConstructor;
import nam.gor.machineryleasing.dao.LeaseDao;
import nam.gor.machineryleasing.dao.LessorDao;
import nam.gor.machineryleasing.dao.MachineryDao;
import nam.gor.machineryleasing.models.entities.Lease;
import nam.gor.machineryleasing.models.entities.LeaseNote;
import nam.gor.machineryleasing.models.entities.Lessee;
import nam.gor.machineryleasing.models.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LeaseService {
    private final LeaseDao leaseD;
    private final LessorDao lessorD;
    private final MachineryDao machineryD;

    @Transactional
    public Lease registerNewLease(LeaseNote leaseNote) {
        var machinery = machineryD
                .findById(leaseNote.getMachinery_id())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Machinery the id '%s' was not found",
                                leaseNote.getMachinery_id()));
        var lessor = lessorD
                .findById(leaseNote.getLessor_id())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Lessor with id '%s' was not found.",
                                leaseNote.getLessor_id()));
        var lease = new Lease();
        lease.setId(UUID.randomUUID().toString());
        var lessee = new Lessee(
                lease.getId(),
                leaseNote.getLesseeName(),
                leaseNote.getLesseeEmail(),
                leaseNote.getLesseePhone()
        );
        lease.setLessee(lessee);
        lease.setLessor(lessor);
        lease.setMachinery(machinery);
        lease.setPrice(leaseNote.getPrice());
        lease.setDate(leaseNote.getDate());
        leaseD.save(lease);
        return lease;
    }

    public List<Lease> getLeases() {
        return leaseD.findAll();
    }

    public Lease getLease(String id) {
        return leaseD.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Lease with id '%s' was not found.",
                                id));
    }
}
