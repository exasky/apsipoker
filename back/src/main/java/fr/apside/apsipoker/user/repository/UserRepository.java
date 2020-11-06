package fr.apside.apsipoker.user.repository;

import fr.apside.apsipoker.user.model.PokerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<PokerUser, Long> {
    Optional<PokerUser> findByUsername(String username);
}
