package projet1.to_do_list.Models.Taches;



import jakarta.persistence.*;
import projet1.to_do_list.Models.User.User;

import java.util.Date;
import java.util.UUID;

@Table(name = "tacheTable")
@Entity
public class Taches {
    @Id
    @Column(name = "tacheId")
    private UUID idTaches;
    @Column(name = "titre")
    private String titre;
    @Column(name = "description")
    private String description;
    @Column(name = "echeance")
    @Temporal(TemporalType.DATE)
    private Date echeance;
    @Column
    private Statut statut;
    @ManyToOne
    @JoinColumn(name = "user_matricule")
    private User user;

    public Taches(UUID idTaches, String titre, String description, Date echeance, Statut statut) {
        this.idTaches = idTaches;
        this.titre = titre;
        this.description = description;
        this.echeance = echeance;
        this.statut = statut;
    }

    public Taches() {
        this.idTaches= UUID.randomUUID();
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public UUID getIdTaches() {
        return idTaches;
    }

    public void setIdTaches(UUID idTaches) {
        this.idTaches = idTaches;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }
}
