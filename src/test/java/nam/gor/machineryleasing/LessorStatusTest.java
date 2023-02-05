package nam.gor.machineryleasing;

import nam.gor.machineryleasing.models.entities.Lessor;
import nam.gor.machineryleasing.models.enums.LessorStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LessorStatusTest {

    @ParameterizedTest
    @EnumSource(LessorStatus.class)
    void isActive(LessorStatus status) {
        Lessor lessor = new Lessor();
        lessor.setStatus(status);
        assertEquals(status.equals(
                LessorStatus.ACTIVE),
                lessor.isActive());
    }
}