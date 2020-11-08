package fr.apside.apsipoker.user.rest.dto;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.model.UserRole;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
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
}
