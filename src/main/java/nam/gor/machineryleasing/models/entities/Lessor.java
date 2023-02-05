package nam.gor.machineryleasing.models.entities;

import nam.gor.machineryleasing.models.enums.LessorStatus;
import nam.gor.machineryleasing.models.annotations.AfterPlatformLaunch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lessors")
public class Lessor {
    @Id
    private String id;

    @NotBlank(message = "this field is mandatory")
    private String name;

    @NotBlank(message = "this field is mandatory")
    @Email(message = "this field must be a valid email")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "this field is mandatory")
    private LessorStatus status;

    @NotNull(message = "this field is mandatory")
    @AfterPlatformLaunch(message = "the date must be after 2022")
    private LocalDate joinDate;

    public boolean isActive() {
        return status == LessorStatus.ACTIVE;
    }
}
