package projet1.to_do_list.Models.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import projet1.to_do_list.Models.Taches.Taches;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "UserTable")
@Entity
public class User implements Serializable {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Setter
    @Getter
    @Column(name = "userMatricule", unique = true)
    private UUID userMatricule = UUID.randomUUID();


    @Setter
    @Getter
    @Column(name = "UserNom")
    private String nom;


    @Setter
    @Getter
    @Column(name = "UserPrenom")
    private String prenom;


    @Getter
    @Setter
    @Column(name = "UserAge")
    private int age;


    @Getter
    @Setter
    @OneToMany(mappedBy = "associatedUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Taches> listTaches=new HashSet<>();

    public User() {
    }

    public User(int id, UUID userMatricule, String nom, String prenom, int age) {
        this.id = id;
        this.userMatricule = userMatricule;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

}
