package fr.apside.apsipoker.championship.rest.dto.championship;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ChampionshipCreateDto {
    private String name;
    private Date startDate;
    private Date endDate;
    private List<PokerUserDto> participants = new ArrayList<>();

    public static Championship toBo(ChampionshipCreateDto dto) {
        Championship bo = new Championship();

        bo.setName(dto.name);
        bo.setStartDate(dto.startDate);
        bo.setEndDate(dto.endDate);
        bo.setParticipants(PokerUserDto.toBo(dto.participants));

        return bo;
    }
}
