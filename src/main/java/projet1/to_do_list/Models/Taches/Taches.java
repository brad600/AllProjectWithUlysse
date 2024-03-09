package projet1.to_do_list.Models.Taches;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import projet1.to_do_list.Models.User.User;

import java.io.Serializable;
import java.util.Date;

@Table(name = "tacheTable")
@Data
@Entity
public class Taches implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private int code;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "echeance")
    @Temporal(TemporalType.DATE)
    private Date echeance;

    @Column
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name="associatedUser", referencedColumnName="id")
    private User associatedUser;

    public Taches() {
    }

    public Taches(int id, int code, String titre, String description, Date echeance, Statut statut) {
        this.id = id;
        this.code = code;
        this.titre = titre;
        this.description = description;
        this.echeance = echeance;
        this.statut = statut;
//        this.associatedUser = associatedUser;
    }
}
