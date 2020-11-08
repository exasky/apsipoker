package fr.apside.apsipoker.championship.repository;

import fr.apside.apsipoker.championship.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {
    @Query(value = "SELECT DISTINCT c FROM Championship c " +
            "JOIN c.participants participant " +
            "WHERE participant.id = :pokerUserId")
    List<Championship> getAllForUser(Long pokerUserId);
}
