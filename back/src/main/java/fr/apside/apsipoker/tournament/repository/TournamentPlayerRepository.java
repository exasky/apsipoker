package fr.apside.apsipoker.tournament.repository;

import fr.apside.apsipoker.tournament.model.TournamentPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentPlayerRepository extends JpaRepository<TournamentPlayer, Long> {
}
