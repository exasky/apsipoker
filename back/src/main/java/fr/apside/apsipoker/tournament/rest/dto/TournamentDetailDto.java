package fr.apside.apsipoker.tournament.rest.dto;

import fr.apside.apsipoker.tournament.model.Tournament;
import lombok.Data;

import java.util.Date;
import java.util.List;

import static fr.apside.apsipoker.common.Utils.mapList;

@Data
public class TournamentDetailDto {
    private Long id;
    private String championshipName;
    private Date date;
    private List<TournamentPlayerDetailDto> participants;

    public static TournamentDetailDto toDto(Tournament bo) {
        TournamentDetailDto dto = new TournamentDetailDto();

        dto.setId(bo.getId());
        dto.setChampionshipName(bo.getChampionship().getName());
        dto.setDate(bo.getDate());
        dto.setParticipants(mapList(bo.getParticipants(), TournamentPlayerDetailDto::toDto));

        return dto;
    }

    public static Tournament toBo(TournamentDetailDto dto) {
        Tournament bo = new Tournament(dto.id);

        bo.setDate(dto.date);
        bo.setParticipants(mapList(dto.getParticipants(), TournamentPlayerDetailDto::toBo));

        return bo;
    }
}
