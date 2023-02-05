package nam.gor.machineryleasing.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {

    @JsonIgnore
    private String machinery_id;
    private String description;
    // Using @Id here because that's a JPA requirement
    @Id
    @NotBlank(message = "this field is mandatory")
    private String url;
}
