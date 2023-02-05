package nam.gor.machineryleasing.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "leases")
public class Lease {
    @Id
    private String id;

    private LocalDate date;
    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(
            name = "id",
            referencedColumnName = "lessee_id")
    private Lessee lessee;

    @ManyToOne
    @JoinColumn(name = "lessor_id",
            referencedColumnName = "id")
    private Lessor lessor;

    @ManyToOne
    @JoinColumn(
            name = "machinery_id",
            referencedColumnName = "id")
    private Machinery machinery;

    @JsonProperty("profit")
    public int calculateProfit() {
        return price - machinery.buyingPrice();
    }
}
