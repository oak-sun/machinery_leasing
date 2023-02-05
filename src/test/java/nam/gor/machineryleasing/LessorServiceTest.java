package nam.gor.machineryleasing;

import nam.gor.machineryleasing.models.entities.Lessor;
import nam.gor.machineryleasing.dao.LessorDao;
import nam.gor.machineryleasing.services.LessorService;
import nam.gor.machineryleasing.models.enums.LessorStatus;
import nam.gor.machineryleasing.models.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessorServiceTest {

    @InjectMocks
    private LessorService service;

    @Mock
    private LessorDao dao;

    @Test
    void getLessors() {
        Lessor lessor1 = new Lessor(
                "id-1",
                "Oak Sun",
                "oaksun@test.com",
                LessorStatus.ACTIVE,
                null);
        Lessor lessor2 = new Lessor(
                "id-2", "Duck DeDuct",
                "duck@test.com",
                LessorStatus.ACTIVE,
                LocalDate.now());
        List<Lessor> list = List.of(lessor1, lessor2);
        when(dao.findAll()).thenReturn(list);
        List<Lessor> actual = service.getLessors();
        assertEquals(list, actual);
    }

    @Test
    void getLessor() {
        when(dao.findById("id-1"))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> service.getLessor("id-1"));
        Lessor expected = new Lessor(
                "id-1",
                "Oak Sun",
                "oaksun@test.com",
                LessorStatus.ACTIVE,
                null);
        when(dao.findById("id-1"))
                .thenReturn(Optional.of(expected));
        Lessor actual = service.getLessor("id-1");
        assertEquals(expected, actual);
    }

    @Test
    void createLessor() {
        Lessor lessor = new Lessor(
                null,
                "Oak Sun",
                "oaksun@test.com",
                null,
                LocalDate.now());
        Lessor actual = service.createLessor(lessor);
        assertEquals(LessorStatus.ACTIVE, actual.getStatus());
        assertEquals(36, actual.getId().length());
    }

    @Test
    void updateLessor() {
        Lessor existing = new Lessor(
                "id-1",
                "OakSun",
                "oaksun@test.com",
                null,
                LocalDate.now());
        when(dao.findById("id-1"))
                .thenReturn(Optional.of(existing));
        Lessor newLessor = new Lessor(
                "id-1",
                "Raphael",
                "raphael@test.com",
                LessorStatus.ACTIVE, LocalDate.now());
        service.updateSeller("id-1", newLessor);
//        verify(dao).save(newLessor);
    }
}