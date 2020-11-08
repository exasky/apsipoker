package fr.apside.apsipoker.user.rest.dto;

import fr.apside.apsipoker.user.model.PokerUser;
import lombok.Data;

@Data
public class PokerUserUpdatePasswordDto {
    private Long id;
    private String password;

    public static PokerUser toBo(PokerUserUpdatePasswordDto dto) {
        PokerUser bo = new PokerUser(dto.id);

        bo.setPassword(dto.password);

        return bo;
    }
}
