package nam.gor.machineryleasing.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "lessees")
public class Lessee {
    @JsonIgnore
    @Id
    private String lessee_id;

    private String name;
    private String email;
    private String phone;
}
