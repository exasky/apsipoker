package fr.apside.apsipoker.tournament.model;

import fr.apside.apsipoker.user.model.PokerUser;

import javax.persistence.*;

@Entity
@Table(name = "tournament_player")
public class TournamentPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private PokerUser player;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column
    private Byte position;

    @Column
    private Float points;

    public TournamentPlayer() {
    }

    public TournamentPlayer(Long id) {
        this.id = id;
    }

    // region Getters & Setters

    public Long getId() {
        return id;
    }

    public PokerUser getPlayer() {
        return player;
    }

    public void setPlayer(PokerUser player) {
        this.player = player;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Byte getPosition() {
        return position;
    }

    public void setPosition(Byte position) {
        this.position = position;
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    // endregion
}
