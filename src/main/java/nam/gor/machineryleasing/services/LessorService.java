package nam.gor.machineryleasing.services;

import nam.gor.machineryleasing.dao.LessorDao;
import nam.gor.machineryleasing.models.entities.Lessor;
import nam.gor.machineryleasing.models.enums.LessorStatus;
import nam.gor.machineryleasing.models.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LessorService {
    private final LessorDao dao;

    public List<Lessor> getLessors() {
        return dao.findAll();
    }

    public Lessor getLessor(String id) {
        return dao
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Lessor with id '%s' was not found.",
                                id));
    }

    public Lessor createLessor(Lessor lessor) {
        lessor.setId(UUID.randomUUID().toString());
        lessor.setStatus(LessorStatus.ACTIVE);
        dao.save(lessor);
        return lessor;
    }

    public void updateSeller(String id,
                             Lessor lessor) {
        var existingL = getLessor(id);
        existingL.setName(lessor.getName());
        existingL.setEmail(lessor.getEmail());
        existingL.setStatus(lessor.getStatus());
        existingL.setJoinDate(lessor.getJoinDate());
        dao.save(existingL);
    }
}
