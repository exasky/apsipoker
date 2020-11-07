package fr.apside.apsipoker.security;

import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.repository.PokerUserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PokerUserRepository pokerUserRepository;

    public MyUserDetailsService(PokerUserRepository pokerUserRepository) {
        this.pokerUserRepository = pokerUserRepository;
    }

    @Override
    public PokerUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.pokerUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
