package fr.apside.apsipoker.championship.model;

import fr.apside.apsipoker.user.model.PokerUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "tournament")
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToMany
    private List<PokerUser> participants = new ArrayList<>();

    @OneToMany(mappedBy = "championship", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Tournament> tournaments = new ArrayList<>();

    public Championship() {
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
        this.tournaments = tournaments;
    }

    // endregion
}
