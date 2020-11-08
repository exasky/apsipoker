package fr.apside.apsipoker.championship.rest.dto.tournament;

import fr.apside.apsipoker.championship.model.Tournament;
import fr.apside.apsipoker.championship.rest.dto.tournament.player.TournamentPlayerForChampionshipUpdateDto;
import fr.apside.apsipoker.common.Constant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static fr.apside.apsipoker.common.Utils.mapList;

@Data
public class TournamentForChampionshipUpdateDto {
    private Long id;

    @NotNull(message = Constant.Errors.TOURNAMENT.DATE_EMPTY)
    private Date date;

    private List<TournamentPlayerForChampionshipUpdateDto> participants;

    public static Tournament toBo(TournamentForChampionshipUpdateDto dto) {
        Tournament tournament = new Tournament(dto.id);

        tournament.setDate(dto.date);
        tournament.setParticipants(mapList(dto.participants, TournamentPlayerForChampionshipUpdateDto::toBo));

        return tournament;
    }

    public static TournamentForChampionshipUpdateDto toDto(Tournament bo) {
        TournamentForChampionshipUpdateDto dto = new TournamentForChampionshipUpdateDto();

        dto.setId(bo.getId());
        dto.setDate(bo.getDate());
        dto.setParticipants(mapList(bo.getParticipants(), TournamentPlayerForChampionshipUpdateDto::toDto));

        return dto;
    }
}
