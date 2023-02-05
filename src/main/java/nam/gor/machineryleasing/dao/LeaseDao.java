package nam.gor.machineryleasing.dao;

import nam.gor.machineryleasing.models.entities.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseDao extends JpaRepository<Lease, String> {
}
