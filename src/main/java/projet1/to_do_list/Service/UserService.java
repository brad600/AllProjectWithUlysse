package projet1.to_do_list.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Repository.UserRepo;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }
    public User updateUser(UUID usermatricule, User user){
       Optional <User> IsUserExist = userRepo.findAllById(usermatricule);
        if (IsUserExist.isPresent()){
            User updateUser = IsUserExist.get();
            updateUser.setNom(user.getNom());
            updateUser.setPrenom(user.getPrenom());
            updateUser.setAge(user.getAge());
            updateUser.setEcheance(user.getEcheance());
            return userRepo.save(updateUser);
        }
        else
            throw new EntityNotFoundException("Utilisateur avec le matricule" +usermatricule+ "inexistant");
    }
    public void deleteUserByMatricule(UUID usermatricule) {
        userRepo.deleteById(usermatricule);
    }

    public User findUserByMatricule(UUID usermatricule) {
        return userRepo.findAllById(usermatricule).orElseThrow();
    }
}
