package fr.apside.apsipoker.championship.repository;

import fr.apside.apsipoker.championship.model.TournamentPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentPlayerRepository extends JpaRepository<TournamentPlayer, Long> {
}
