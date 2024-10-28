package print.print.Event;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import print.print.Association.AssociationEntity;
import print.print.Auth.Model.AppUser;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private LocalDateTime date;
    private Integer duration; // in minutes or hours, based on your requirements
    private Integer capacity;

    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<AppUser> participants;
    @ManyToOne
    @JoinColumn(name = "association_id")
    private AssociationEntity association;

    public EventEntity(String name, String address, LocalDateTime date, Integer duration, Integer capacity) {
        this.name = name;
        this.address = address;
        this.date = date;
        this.duration = duration;
        this.capacity = capacity;
    }
}
