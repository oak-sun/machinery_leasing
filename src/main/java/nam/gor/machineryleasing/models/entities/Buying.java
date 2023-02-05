package nam.gor.machineryleasing.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import nam.gor.machineryleasing.models.enums.BuyingSource;
import nam.gor.machineryleasing.models.annotations.AfterPlatformLaunch;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buying")
public class Buying {

    @Id
    @JsonIgnore
    private String machinery_id;

    @NotNull(message = "this field is mandatory")
    @AfterPlatformLaunch(message = "the date must be after 2022")
    private LocalDate date;

    @Positive(message = "the field must be positive")
    private int price;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "the field is mandatory")
    private BuyingSource source;
}
