package fr.apside.apsipoker.user.service;

import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.model.UserRole;
import fr.apside.apsipoker.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PokerUser register(String username, String password) {
        PokerUser s = new PokerUser();

        s.setUsername(username);
        s.setRole(UserRole.ROLE_ADMIN);
        s.setPassword(encorePassword(password));

        return this.userRepository.save(s);
    }

    public String encorePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
