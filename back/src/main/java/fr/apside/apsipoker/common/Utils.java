package fr.apside.apsipoker.common;

import fr.apside.apsipoker.user.model.PokerUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static PokerUser getCurrentUser() {
        return (PokerUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
