package nam.gor.machineryleasing.services;

import nam.gor.machineryleasing.models.entities.Machinery;
import nam.gor.machineryleasing.models.enums.MachineryStatus;
import nam.gor.machineryleasing.dao.MachineryDao;
import nam.gor.machineryleasing.models.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MachineryService {
    private final MachineryDao dao;

    public List<Machinery> getMachines() {
        return dao.findAll();
    }

    public Machinery getMachinery(String id) {
        return dao.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "The machinery with the id '%s'" +
                                        " was not found",
                                id));
    }

    @Transactional
    public Machinery createMachinery(Machinery m) {
        var machinery_id = UUID.randomUUID().toString();
        m.setId(machinery_id);
        m.setStatus(MachineryStatus.ACTIVE);
        m.getPhotos().forEach(p -> p.setMachinery_id(machinery_id));
        m.getBuying().setMachinery_id(machinery_id);
        return dao.save(m);
    }

    @Transactional
    public void updateMachinery(String id,
                                Machinery newM) {
        Machinery existingM = getMachinery(id);
        existingM.setName(newM.getName());
        existingM.setBrand(newM.getBrand());
        existingM.setStatus(newM.getStatus());
        existingM.setType(newM.getType());
        existingM.setColor(newM.getColor());
        existingM.setChassis(newM.getChassis());
        existingM.setMileage(newM.getMileage());
        existingM.setReleaseYear(newM.getReleaseYear());
        newM
                .getBuying()
                .setMachinery_id(existingM.getId());
        existingM.setBuying(newM.getBuying());
        newM
                .getPhotos()
                .forEach(p -> p.setMachinery_id(existingM.getId()));
        existingM.getPhotos().clear();
        existingM
                .getPhotos()
                .addAll(newM.getPhotos());
        dao.save(existingM);
    }
}
