package fr.apside.apsipoker.championship.rest.dto.championship;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.rest.dto.tournament.TournamentForChampionshipUpdateDto;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static fr.apside.apsipoker.common.Utils.mapList;

@Data
public class ChampionshipDetailDto {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<PokerUserDto> participants = new ArrayList<>();
    private List<TournamentForChampionshipUpdateDto> tournaments = new ArrayList<>();

    public static ChampionshipDetailDto toDto(Championship bo) {
        ChampionshipDetailDto dto = new ChampionshipDetailDto();

        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setStartDate(bo.getStartDate());
        dto.setEndDate(bo.getEndDate());
        dto.setParticipants(mapList(bo.getParticipants(), PokerUserDto::toDto));
        dto.setTournaments(mapList(bo.getTournaments(), TournamentForChampionshipUpdateDto::toDto));

        return dto;
    }
}
