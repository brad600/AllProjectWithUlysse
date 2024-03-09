package projet1.to_do_list.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projet1.to_do_list.Models.Taches.Statut;
import projet1.to_do_list.Models.Taches.Taches;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Repository.TachesRepo;
import projet1.to_do_list.Repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TachesService {
    private final TachesRepo tachesRepo;
    private final UserRepo userRepo;

    @Autowired
    public TachesService(TachesRepo tachesRepo, UserRepo userRepo) {
        this.tachesRepo = tachesRepo;
        this.userRepo = userRepo;
    }

    public Taches updateTaches(int id, Taches taches) {
        Optional<Taches> IsTaskExist = tachesRepo.findById(id);
        if (IsTaskExist.isPresent()) {
            Taches updateTask = IsTaskExist.get();
            updateTask.setCode(taches.getCode());
            updateTask.setTitre(taches.getTitre());
            updateTask.setDescription(taches.getDescription());
            updateTask.setEcheance(taches.getEcheance());
            updateTask.setStatut(taches.getStatut());
            tachesRepo.save(updateTask);
            return tachesRepo.save(updateTask);
        } else
            throw new RuntimeException("Taches avec l'id " + id + " est inexistant");
    }

    @Transactional
    public String deleteTacheById(int id) {

        if (!tachesRepo.existsById(id)) {
            return "Aucune tache existante avec cet id";
        }
        tachesRepo.deleteById(id);
        return "Suppression effectuée avec succès";

    }

    public Taches findTaskByMatricule(int id) {

        return tachesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucune tache trouvée avec cet id"));
    }

    public List<Taches> getAllTaches() {
        return tachesRepo.findAll();
    }

    public Long getNumberOfTaches() {
        return tachesRepo.count();
    }


    public List<Taches> getTachesByStatut(Statut statut) {
        List<Taches> result = new ArrayList<>();
        tachesRepo.findAll().forEach(taches -> {
            if (Objects.equals(taches.getStatut(), statut)) {
                result.add(taches);
            }
        });

        return result;
    }

    public String associatedTaskToUserById(int id, int taches) {
        Optional<User> userOptional = userRepo.findById(id);
        Optional<Taches> tacheOptional = tachesRepo.findById(taches);

        if (userOptional.isPresent()) {
            if (tacheOptional.isPresent()) {
                Taches taches1 = tacheOptional.orElseThrow();
                taches1.setAssociatedUser(userOptional.get());

                tachesRepo.save(taches1);

                return "Tache associé avec succès";
            } else {
                return "La tache associé n'existe pas";
            }
        } else {
            return "L'utilisateur associé n'existe pas";
        }
    }

    public String createTache(Taches taches) {
        boolean testExistence = tachesRepo.existsById(taches.getCode());
        if (testExistence) {
            return "Cette tache existe déjà";
        } else {

            tachesRepo.save(taches);

            return "Tache ajoutée avec succès";
        }
    }
}

