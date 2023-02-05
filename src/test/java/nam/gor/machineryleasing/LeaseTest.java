package nam.gor.machineryleasing;

import nam.gor.machineryleasing.models.entities.Buying;
import nam.gor.machineryleasing.models.entities.Machinery;
import nam.gor.machineryleasing.models.entities.Lease;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LeaseTest {

    @Test
    void calculateProfit() {
        int leasePrice = 4000000;
        int buyingPrice = 3800000;
        Buying buying = new Buying();
        buying.setPrice(buyingPrice);
        Machinery machinery = new Machinery();
        machinery.setBuying(buying);
        Lease lease = new Lease();
        lease.setMachinery(machinery);
        lease.setPrice(leasePrice);
        assertEquals(
                leasePrice - buyingPrice,
                lease.calculateProfit());
    }
}