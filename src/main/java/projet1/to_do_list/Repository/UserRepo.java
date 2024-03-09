package projet1.to_do_list.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet1.to_do_list.Models.User.User;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUserMatricule(UUID userMatricule);
    boolean existsByUserMatricule(UUID userMatricule);
    void deleteByUserMatricule(UUID userMatricule);
}
