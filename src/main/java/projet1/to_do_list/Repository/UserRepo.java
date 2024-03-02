package projet1.to_do_list.Repository;
import  projet1.to_do_list.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import projet1.to_do_list.Models.User.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {


    Optional<User> findAllById(UUID usermatricule);
}
