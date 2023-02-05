package nam.gor.machineryleasing;

import nam.gor.machineryleasing.models.entities.Lessor;
import nam.gor.machineryleasing.models.enums.LessorStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LessorTest {

    @Test
    void isActive() {
        Lessor s = new Lessor();
        s.setStatus(LessorStatus.ACTIVE);
        assertTrue(s.isActive());
        s.setStatus(LessorStatus.INACTIVE);
        assertFalse(s.isActive());
    }
}