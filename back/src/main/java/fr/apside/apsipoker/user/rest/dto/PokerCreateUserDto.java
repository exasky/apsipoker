package fr.apside.apsipoker.user.rest.dto;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.model.UserRole;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
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
}
