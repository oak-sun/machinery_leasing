package nam.gor.machineryleasing.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import nam.gor.machineryleasing.models.enums.MachineryStatus;
import nam.gor.machineryleasing.models.enums.MachineryType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "machines")
public class Machinery {
    @Id
    private String id;

    @NotBlank(message = "this field is mandatory")
    private String name;

    @NotBlank(message = "this field is mandatory")
    private String brand;

    @NotBlank(message = "this field is mandatory")
    private String color;

    @NotNull(message = "this field is mandatory")
    @Enumerated(EnumType.STRING)
    private MachineryStatus status;

    @NotNull(message = "this field is mandatory")
    @Enumerated(EnumType.STRING)
    private MachineryType type;

    @NotBlank(message = "this field is mandatory")
    private String chassis;

    @Positive(message = "the field must be positive")
    private int mileage;

    private int releaseYear;

    @NotNull(message = "this field is mandatory")
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(
            name = "machinery_id",
            referencedColumnName = "id")
    private Buying buying;

    @NotNull(message = "this field is mandatory")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "machinery_id",
            referencedColumnName = "id")
    @ToString.Exclude
    private Set<Photo> photos;

    public boolean isLeased() {
        return status == MachineryStatus.LEASED;
    }

    public int buyingPrice() {
        return buying.getPrice();
    }
}
