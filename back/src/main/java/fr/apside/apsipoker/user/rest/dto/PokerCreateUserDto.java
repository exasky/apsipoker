package fr.apside.apsipoker.user.rest.dto;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.model.UserRole;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PokerCreateUserDto {
    @NotNull(message = Constant.Errors.USER.NAME_EMPTY)
    @NotEmpty(message = Constant.Errors.USER.NAME_EMPTY)
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    @NotNull(message = Constant.Errors.USER.ROLE_EMPTY)
    private UserRole role;

    @NotNull(message = Constant.Errors.USER.PASSWORD_EMPTY)
    @NotEmpty(message = Constant.Errors.USER.PASSWORD_EMPTY)
    private String password;

    @NotNull(message = Constant.Errors.USER.AGENCY_EMPTY)
    @NotEmpty(message = Constant.Errors.USER.AGENCY_EMPTY)
    private String agency;


    public static PokerCreateUserDto toDto(PokerUser bo) {
        PokerCreateUserDto dto = new PokerCreateUserDto();

        dto.setRole(bo.getRole());
        dto.setUsername(bo.getUsername());
        dto.setEmail(bo.getEmail());
        dto.setFirstName(bo.getFirstName());
        dto.setLastName(bo.getLastName());
        dto.setAgency(bo.getAgency());

        return dto;
    }

    public static PokerUser toBo(PokerCreateUserDto dto) {
        PokerUser bo = new PokerUser();

        bo.setUsername(dto.username);
        bo.setPassword(dto.password);
        bo.setRole(dto.role);
        bo.setEmail(dto.email);
        bo.setFirstName(dto.firstName);
        bo.setLastName(dto.lastName);
        bo.setAgency(dto.agency);

        return bo;
    }

    // region Getters & Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
