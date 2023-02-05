package nam.gor.machineryleasing;

import nam.gor.machineryleasing.models.entities.Buying;
import nam.gor.machineryleasing.models.entities.Machinery;
import nam.gor.machineryleasing.models.enums.MachineryStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MachineryTest {

    @ParameterizedTest
    @EnumSource(MachineryStatus.class)
    void isLeased(MachineryStatus status) {
        Machinery machinery = new Machinery();
        machinery.setStatus(status);
        assertEquals(status.equals(MachineryStatus.LEASED),
                machinery.isLeased());
    }

    @Test
    void buyingPrice() {
        Buying buying = new Buying();
        buying.setPrice(1000);
        Machinery machinery = new Machinery();
        machinery.setBuying(buying);
        assertEquals(1000, machinery.buyingPrice());
    }
}