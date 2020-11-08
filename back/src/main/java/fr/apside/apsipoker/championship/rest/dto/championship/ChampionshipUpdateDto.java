package fr.apside.apsipoker.championship.rest.dto.championship;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.rest.dto.tournament.TournamentForChampionshipUpdateDto;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static fr.apside.apsipoker.common.Utils.mapList;

@Data
public class ChampionshipUpdateDto {
    private String name;
    private Date startDate;
    private Date endDate;
    private List<PokerUserDto> participants = new ArrayList<>();

    @Valid
    private List<TournamentForChampionshipUpdateDto> tournaments = new ArrayList<>();

    public static Championship toBo(ChampionshipUpdateDto dto) {
        Championship bo = new Championship();

        bo.setName(dto.name);
        bo.setStartDate(dto.startDate);
        bo.setEndDate(dto.endDate);
        bo.setParticipants(mapList(dto.participants, PokerUserDto::toBo));
        bo.setTournaments(mapList(dto.tournaments, TournamentForChampionshipUpdateDto::toBo));

        return bo;
    }
}
