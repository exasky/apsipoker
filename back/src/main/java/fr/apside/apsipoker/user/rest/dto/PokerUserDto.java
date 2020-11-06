package fr.apside.apsipoker.user.rest.dto;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.model.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PokerUserDto {
    private Long id;

    @NotNull(message = Constant.Errors.USER.NAME_EMPTY)
    @NotEmpty(message = Constant.Errors.USER.NAME_EMPTY)
    private String username;

    @NotNull(message = Constant.Errors.USER.ROLE_EMPTY)
    private UserRole role;

    @NotNull(message = Constant.Errors.USER.AGENCY_EMPTY)
    private String agency;

    public static List<PokerUserDto> toDto(List<PokerUser> bos) {
        return Objects.isNull(bos)
                ? new ArrayList<>()
                : bos.stream().map(PokerUserDto::toDto).collect(Collectors.toList());
    }

    public static PokerUserDto toDto(PokerUser bo) {
        PokerUserDto dto = new PokerUserDto();

        dto.setId(bo.getId());
        dto.setRole(bo.getRole());
        dto.setUsername(bo.getUsername());
        dto.setAgency(bo.getAgency());

        return dto;
    }

    public static PokerUser toBo(PokerUserDto dto) {
        PokerUser bo = new PokerUser();

        bo.setRole(dto.role);
        bo.setUsername(dto.username);
        bo.setAgency(dto.agency);

        return bo;
    }

    // region Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
    // endregion
}
