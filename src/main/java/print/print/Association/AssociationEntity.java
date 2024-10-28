package print.print.Association;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.print.Auth.Model.AppUser;
import print.print.Event.EventEntity;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class AssociationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    // Liste des membres de l'association
    @ManyToMany
    @JoinTable(
            name = "association_members",
            joinColumns = @JoinColumn(name = "association_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AppUser> membres;

    // Président de l'association (un seul utilisateur)
    @OneToOne
    @JoinColumn(name = "president_id")
    private AppUser president;

    // Liste des événements organisés par l'association
    @OneToMany(mappedBy = "association", cascade = CascadeType.ALL)
    private List<EventEntity> evenements;

    public AssociationEntity(String nom, String description, List<AppUser> membres, AppUser president, List<EventEntity> evenements) {
        this.nom = nom;
        this.description = description;
        this.membres = membres;
        this.president = president;
        this.evenements = evenements;
    }
}
