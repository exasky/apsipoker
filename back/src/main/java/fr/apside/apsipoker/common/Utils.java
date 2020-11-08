package fr.apside.apsipoker.common;

import fr.apside.apsipoker.user.model.PokerUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {
    public static PokerUser getCurrentUser() {
        return (PokerUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static <IN, OUT> List<OUT> mapList(List<IN> in, Function<? super IN, ? extends OUT> func) {
        return Objects.isNull(in)
                ? new ArrayList<>()
                : in.stream().map(func).collect(Collectors.toList());
    }
}
