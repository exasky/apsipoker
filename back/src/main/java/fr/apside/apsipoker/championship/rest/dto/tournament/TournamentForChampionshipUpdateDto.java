package fr.apside.apsipoker.championship.rest.dto.tournament;

import fr.apside.apsipoker.championship.model.Tournament;
import fr.apside.apsipoker.championship.rest.dto.tournament.player.TournamentPlayerForChampionshipUpdateDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class TournamentForChampionshipUpdateDto {
    private Long id;
    private Date date;
    private List<TournamentPlayerForChampionshipUpdateDto> participants;

    public static List<Tournament> toBo(List<TournamentForChampionshipUpdateDto> dtos) {
        return Objects.isNull(dtos)
                ? new ArrayList<>()
                : dtos.stream().map(TournamentForChampionshipUpdateDto::toBo).collect(Collectors.toList());
    }

    public static Tournament toBo(TournamentForChampionshipUpdateDto dto) {
        Tournament tournament = new Tournament(dto.id);

        tournament.setDate(dto.date);
        tournament.setParticipants(TournamentPlayerForChampionshipUpdateDto.toBo(dto.participants));

        return tournament;
    }

    public static List<TournamentForChampionshipUpdateDto> toDto(List<Tournament> bos) {
        return Objects.isNull(bos)
                ? new ArrayList<>()
                : bos.stream().map(TournamentForChampionshipUpdateDto::toDto).collect(Collectors.toList());
    }

    public static TournamentForChampionshipUpdateDto toDto(Tournament bo) {
        TournamentForChampionshipUpdateDto dto = new TournamentForChampionshipUpdateDto();

        dto.setId(bo.getId());
        dto.setDate(bo.getDate());
        dto.setParticipants(TournamentPlayerForChampionshipUpdateDto.toDto(bo.getParticipants()));

        return dto;
    }
}
