package projet1.to_do_list.Models.User;

import jakarta.persistence.*;
import projet1.to_do_list.Models.Taches.Taches;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "UserTable")
@Entity
public class User {
    @Id
    @Column(name = "UserMatricule")
    private UUID matricule;
    @Column(name = "UserNom")
    private String nom;
    @Column(name = "UserPrenom")
    private String prenom;
    @Column(name = "UserAge")
    private int age;
    @Column(name = "date_echeance")
    @Temporal(TemporalType.DATE)
    private Date echeance;
    @OneToMany(mappedBy = "user")
    private List<Taches> listTaches;

    public User() {
        this.matricule = UUID.randomUUID();
    }

    public User(UUID matricule, String nom, String prenom, int age, Date echeance) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.echeance = echeance;
    }

    public UUID getMatricule() {
        return matricule;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }
}
