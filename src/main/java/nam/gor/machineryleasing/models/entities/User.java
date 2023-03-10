package nam.gor.machineryleasing.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    String id;
    String name;
    String email;

    @JsonIgnore
    String password;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null ||
                Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        final User that = (User) o;
        return id != null &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
