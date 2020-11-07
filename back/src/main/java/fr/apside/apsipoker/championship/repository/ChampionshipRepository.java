package fr.apside.apsipoker.championship.repository;

import fr.apside.apsipoker.championship.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
}
