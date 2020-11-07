package fr.apside.apsipoker.championship.repository;

import fr.apside.apsipoker.championship.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
