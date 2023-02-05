package nam.gor.machineryleasing.dao;

import nam.gor.machineryleasing.models.entities.Machinery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineryDao extends JpaRepository<Machinery, String> {
}
