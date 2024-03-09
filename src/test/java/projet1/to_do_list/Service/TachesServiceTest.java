package projet1.to_do_list.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import projet1.to_do_list.Models.Taches.Statut;
import projet1.to_do_list.Models.Taches.Taches;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Repository.TachesRepo;
import projet1.to_do_list.Repository.UserRepo;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TachesServiceTest {
    @Mock
    TachesRepo tachesRepo;
    @Mock
    UserRepo userRepo;
    @InjectMocks
    private TachesService tachesService;

    @Test
    void createTacheTest() {
        int code = 1;
        Taches taches = new Taches(1, code, "Faire du sport", "saut à la corde & gyaku", Date.from(Instant.now()), Statut.Créée);
        when(tachesRepo.existsById(taches.getCode())).thenReturn(false);

        String result = tachesService.createTache(taches);
        String excepted = "Tache ajoutée avec succès";

        verify(tachesRepo, times(1)).existsById(eq(code));
        assertEquals(result, excepted);
    }

    @Test
    void updateTachesTest() {
        int id = 1;
        Taches taches = new Taches(id, 1, "Faire du sport", "saut à la corde & gyaku", Date.from(Instant.now()), Statut.Créée);
        Taches tachesUpdated = new Taches(id, 2, "Sport", "karate", Date.from(Instant.now()), Statut.En_cours);
        when(tachesRepo.findById(taches.getId())).thenReturn(Optional.of(taches));
        when(tachesRepo.save(taches)).thenReturn(tachesUpdated);

        Taches result = tachesService.updateTaches(taches.getId(), taches);

        assertThat(result.getCode())
                .isEqualTo(tachesUpdated.getCode());

    }


    @Test
    void findTaskByMatriculeTest() {
        int id = 1;
        Taches taches = new Taches(id, 1, "Faire du sport", "saut à la corde & gyaku", Date.from(Instant.now()), Statut.Créée);

        when(tachesRepo.findById(id)).thenReturn(Optional.of(taches));

        Taches result = tachesService.findTaskByMatricule(id);

        assertThat(result).isEqualTo(taches);
        assertEquals(result, taches);
        verify(tachesRepo, times(1)).findById(id);
    }

    @Test
    void deleteTacheById() {
        int id = 1;
        Taches taches = new Taches(id, 1, "Faire du sport", "saut à la corde & gyaku", Date.from(Instant.now()), Statut.Créée);
        when(tachesRepo.existsById(taches.getId())).thenReturn(true);

        String result = tachesService.deleteTacheById(id);
        String excepted = "Suppression effectuée avec succès";

        verify(tachesRepo, times(1)).deleteById(id);
        assertEquals(excepted, result);
    }

    @Test
    void getAllTaches() {
        Taches t1 = new Taches(1,1,"sport","tous les jours", Date.from(Instant.now()), Statut.Créée);
        Taches t2 = new Taches(2,5,"sport2","mercredi", Date.from(Instant.now()), Statut.En_cours);
        Taches t3 = new Taches(3,9,"sport3","mardi", Date.from(Instant.now()), Statut.Terminée);
         List<Taches> listTaches = List.of(t1,t2,t3);
        when(tachesRepo.findAll()).thenReturn(listTaches);

        List<Taches> result = tachesService.getAllTaches();

        assertThat(result)
                .hasSize(3)
                .isNotEmpty()
                .contains(t1,t2,t3)
                .isEqualTo(listTaches);
        verify(tachesRepo,times(1)).findAll();
    }

    @Test
    void getNumberOfTaches() {
        Taches t1 = new Taches(1,1,"sport","tous les jours", Date.from(Instant.now()), Statut.Créée);
        Taches t2 = new Taches(2,5,"sport2","mercredi", Date.from(Instant.now()), Statut.En_cours);
        Taches t3 = new Taches(3,9,"sport3","mardi", Date.from(Instant.now()), Statut.Terminée);
        List<Taches> listTaches = List.of(t1,t2,t3);
        when(tachesRepo.count()).thenReturn(listTaches.stream().count());

        Long result = tachesService.getNumberOfTaches();

        assertThat(result)
                .isNotNull()
                .isEqualTo(3);
        verify(tachesRepo,times(1)).count();
    }

    @Test
    void getTachesByStatut() {
        Statut statut1 = Statut.Créée;
        Taches t1 = new Taches(1,1,"sport","tous les jours", Date.from(Instant.now()), statut1);
        Taches t2 = new Taches(2,5,"sport2","mercredi", Date.from(Instant.now()), Statut.En_cours);
        Taches t3 = new Taches(3,9,"sport3","mardi", Date.from(Instant.now()), Statut.Terminée);
        List<Taches> listTaches = List.of(t1,t2,t3);
        when(tachesRepo.findAll()).thenReturn(listTaches);

        List<Taches> result = tachesService.getTachesByStatut(statut1);

        assertThat(result)
                .hasSize(1)
                .contains(t1);
        verify(tachesRepo,times(1)).findAll();



    }

    @Test
    void associatedTaskToUserById() {
        Taches t1 = new Taches(1,1,"sport","tous les jours", Date.from(Instant.now()), Statut.Créée);
        Taches t2 = new Taches(2,5,"sport2","mercredi", Date.from(Instant.now()), Statut.En_cours);
        Taches t3 = new Taches(3,9,"sport3","mardi", Date.from(Instant.now()), Statut.Terminée);
        UUID userMatricule = UUID.fromString("db264560-c7e7-46a6-9cbf-11fa13f716ac");
        User user = new User(1, userMatricule, "HEMA", "lao", 25);

        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        when(tachesRepo.findById(t1.getId())).thenReturn(Optional.of(t1));
        when(tachesRepo.save(t1)).thenReturn(t1);

        String result = tachesService.associatedTaskToUserById(user.getId(), t1.getId());
        String excepted = "Tache associé avec succès";
        assertEquals(excepted, result);
        verify(userRepo,times(1)).findById(1);
        verify(tachesRepo,times(1)).findById(1);
    }

}