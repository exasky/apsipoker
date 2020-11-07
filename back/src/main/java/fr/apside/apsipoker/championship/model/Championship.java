package fr.apside.apsipoker.championship.model;

import fr.apside.apsipoker.user.model.PokerUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "championship")
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToMany
    @JoinTable(name = "championship_participants",
            joinColumns = @JoinColumn(name = "championship_id", table = "poker_user"),
            inverseJoinColumns = @JoinColumn(name = "poker_user_id"))
    private final List<PokerUser> participants = new ArrayList<>();

    @OneToMany(mappedBy = "championship", orphanRemoval = true, cascade = CascadeType.ALL)
    private final List<Tournament> tournaments = new ArrayList<>();

    public Championship() {
    }

    public Championship(Long id) {
        this.id = id;
    }

    public Championship(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // region Getters & Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<PokerUser> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PokerUser> participants) {
        this.participants.clear();
        this.participants.addAll(participants);
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments.clear();
        this.tournaments.addAll(tournaments);
    }

    // endregion
}
