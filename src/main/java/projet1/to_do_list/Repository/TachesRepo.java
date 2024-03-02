package projet1.to_do_list.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet1.to_do_list.Models.Taches.Taches;

import java.util.UUID;

public interface TachesRepo extends JpaRepository<Taches, UUID> {
}
