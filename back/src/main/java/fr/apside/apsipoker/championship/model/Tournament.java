package fr.apside.apsipoker.championship.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Date date;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TournamentPlayer> participants = new ArrayList<>();

    public Tournament() {}

    public Tournament(Long id) {
        this.id = id;
    }

    // region Getters & Setters

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public List<TournamentPlayer> getParticipants() {
        return participants;
    }

    public void setParticipants(List<TournamentPlayer> participants) {
        this.participants.clear();
        this.participants.addAll(participants);
    }

    // endregion
}
