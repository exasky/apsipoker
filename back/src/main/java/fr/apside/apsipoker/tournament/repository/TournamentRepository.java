package fr.apside.apsipoker.tournament.repository;

import fr.apside.apsipoker.tournament.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
