package nam.gor.machineryleasing.models.entities;

import nam.gor.machineryleasing.models.annotations.AfterPlatformLaunch;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Value
public class LeaseNote {
    @NotBlank(message = "the field is mandatory")
    String machinery_id;

    @NotBlank(message = "the field is mandatory")
    String lessor_id;

    @NotBlank(message = "the field is mandatory")
    String lesseeName;

    @NotBlank(message = "the field is mandatory")
    @Email(message = "the field must be a valid email")
    String lesseeEmail;

    String lesseePhone;

    @Positive(message = "the field must be a positive integer")
    int price;

    @NotNull(message = "this field is mandatory")
    @AfterPlatformLaunch(message = "the date must be after 2022")
    LocalDate date;
}
