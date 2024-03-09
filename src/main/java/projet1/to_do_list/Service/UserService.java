package projet1.to_do_list.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet1.to_do_list.Models.Taches.Taches;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Repository.TachesRepo;
import projet1.to_do_list.Repository.UserRepo;

import java.util.*;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final TachesRepo tachesRepo;

    @Autowired
    public UserService(UserRepo userRepo, TachesRepo tachesRepo) {
        this.userRepo = userRepo;
        this.tachesRepo = tachesRepo;
    }

    @Transactional
    public User createUser(User user) {
        return userRepo.save(user);
    }


    public User updateUser(UUID userMatricule, User user) {
        User existingUser = userRepo.findByUserMatricule(userMatricule)
                .orElseThrow(() -> new RuntimeException("Utilisateur avec le matricule " + userMatricule + " non trouvé."));

        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setAge(user.getAge());

        return userRepo.save(existingUser);

    }

    @Transactional
    public String deleteUserByMatricule(UUID userMatricule) {
        if (!userRepo.existsByUserMatricule(userMatricule)) {
            return "Utilisateur avec le matricule " + userMatricule + " non trouvé.";
        }
        userRepo.deleteByUserMatricule(userMatricule);
        return "Utilisateur supprimé avec succès";
    }

    public User findUserByMatricule(UUID userMatricule) {
        return userRepo.findByUserMatricule(userMatricule).orElse(null);
    }


    public Set<Taches> getAllTacheOfThisUser(UUID userMatricule) {
        Optional<User> userOptional = userRepo.findByUserMatricule(userMatricule);
        if (userOptional.isPresent()) {
            return userOptional.get().getListTaches();
        }

        return Collections.emptySet();
    }
}
