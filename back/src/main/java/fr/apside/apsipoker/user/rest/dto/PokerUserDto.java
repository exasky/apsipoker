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

    private String email;
    private String firstName;
    private String lastName;

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
        dto.setEmail(bo.getEmail());
        dto.setFirstName(bo.getFirstName());
        dto.setLastName(bo.getLastName());
        dto.setAgency(bo.getAgency());

        return dto;
    }

    public static List<PokerUser> toBo(List<PokerUserDto> bos) {
        return Objects.isNull(bos)
                ? new ArrayList<>()
                : bos.stream().map(PokerUserDto::toBo).collect(Collectors.toList());
    }

    public static PokerUser toBo(PokerUserDto dto) {
        PokerUser bo = new PokerUser(dto.id);

        bo.setRole(dto.role);
        bo.setUsername(dto.username);
        bo.setEmail(dto.email);
        bo.setFirstName(dto.firstName);
        bo.setLastName(dto.lastName);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // endregion
}
