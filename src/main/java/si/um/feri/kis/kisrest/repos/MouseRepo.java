package si.um.feri.kis.kisrest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.um.feri.kis.kisrest.model.Mouse;

@Repository
public interface MouseRepo extends JpaRepository<Mouse, Long> {
}
