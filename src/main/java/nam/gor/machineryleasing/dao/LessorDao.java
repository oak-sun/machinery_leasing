package nam.gor.machineryleasing.dao;

import nam.gor.machineryleasing.models.entities.Lessor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessorDao extends JpaRepository<Lessor, String> {
}
