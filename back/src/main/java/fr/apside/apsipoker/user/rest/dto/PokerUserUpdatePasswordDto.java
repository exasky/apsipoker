package fr.apside.apsipoker.user.rest.dto;

import fr.apside.apsipoker.user.model.PokerUser;

public class PokerUserUpdatePasswordDto {
    private Long id;
    private String password;

    public static PokerUser toBo(PokerUserUpdatePasswordDto dto) {
        PokerUser bo = new PokerUser(dto.id);

        bo.setPassword(dto.password);

        return bo;
    }

    // region Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // endregion
}
