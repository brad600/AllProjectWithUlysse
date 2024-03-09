package projet1.to_do_list.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import projet1.to_do_list.Models.Taches.Statut;
import projet1.to_do_list.Models.Taches.Taches;
import projet1.to_do_list.Models.User.User;
import projet1.to_do_list.Repository.UserRepo;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//@ExtendWith(SpringExtension.class)

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepo userRepo;
    @InjectMocks
    private UserService userService;


    Taches t1 = new Taches(1,1,"sport","tous les jours", Date.from(Instant.now()), Statut.Créée);
    Taches t2 = new Taches(2,5,"sport2","mercredi", Date.from(Instant.now()), Statut.En_cours);
    Taches t3 = new Taches(3,9,"sport3","mardi", Date.from(Instant.now()), Statut.Terminée);

    private Set<Taches> listTaches = Set.of(t1,t2,t3);


    @Test
    void createUserTest() {
        UUID userMatricule = UUID.fromString("db264560-c7e7-46a6-9cbf-11fa13f716ac");
        User user = new User(1, userMatricule, "HEMA", "lao", 25);

        when(userRepo.save(user)).thenReturn(user);
        User result = userService.createUser(user);

        assertThat(result).isNotNull()
                .isEqualTo(user);

        verify(userRepo).save(user);
    }

    @Test
    void updateUserTest() {
        UUID userMatricule = UUID.fromString("db264560-c7e7-46a6-9cbf-11fa13f716ac");

        String nomInitialUser = "HEMA";
        int ageInitialUser = 25;
        String nomUpdatedUser = "Brad";
        int ageUpdatedUser = 17;
        User initialUser = new User(1, userMatricule, nomInitialUser, "lao", ageInitialUser);
        String lao = "lao";
        User updatedUser = new User(1, userMatricule, nomUpdatedUser, lao, ageUpdatedUser);
        when(userRepo.findByUserMatricule(userMatricule)).thenReturn(Optional.of(initialUser));
        when(userRepo.save(initialUser)).thenReturn(updatedUser);

        User result = userService.updateUser(userMatricule, initialUser);

        assertThat(result.getUserMatricule())
                .isEqualTo(userMatricule);
        assertThat(result.getNom())
                .isEqualTo(nomUpdatedUser);
        verify(userRepo).save(assertArg(o -> assertThat(o)
                        .returns(userMatricule, User::getUserMatricule)
                        .returns(nomInitialUser, User::getNom)
                )
        );
        assertEquals(result, updatedUser);

    }

    @Test
    void deleteUserByMatriculeTest() {
        UUID userMatricule = UUID.fromString("db264560-c7e7-46a6-9cbf-11fa13f716ac");
        User initialUser = new User(1, userMatricule, "Brad", "lao", 10);
        when(userRepo.existsByUserMatricule(initialUser.getUserMatricule())).thenReturn(true);

        String result = userService.deleteUserByMatricule(userMatricule);
        String excepted = "Utilisateur supprimé avec succès";

        assertEquals(result, excepted);
        verify(userRepo, times(1)).deleteByUserMatricule(eq(userMatricule));


    }

    @Test
    void findUserByMatriculeTest() {
        UUID userMatricule = UUID.fromString("db264560-c7e7-46a6-9cbf-11fa13f716ac");
        User initialUser = new User(1, userMatricule, "Brad", "lao", 10);

        when(userRepo.findByUserMatricule(initialUser.getUserMatricule())).thenReturn(Optional.of(initialUser));

        User result = userService.findUserByMatricule(userMatricule);

        assertEquals(initialUser, result);
        verify(userRepo, times(1)).findByUserMatricule(eq(userMatricule));
    }


    @Test
    void getAllTacheOfThisUser() {
        UUID userMatricule = UUID.fromString("db264560-c7e7-46a6-9cbf-11fa13f716ac");
        User initialUser = new User(1, userMatricule, "Brad", "lao", 10);
        when(userRepo.findByUserMatricule(userMatricule)).thenReturn(Optional.of(initialUser));

        Set<Taches> result = userService.getAllTacheOfThisUser(userMatricule);
        Set<Object> excepted = Collections.emptySet();

        assertEquals(excepted,result);
        verify(userRepo,times(1)).findByUserMatricule(userMatricule);


    }
}