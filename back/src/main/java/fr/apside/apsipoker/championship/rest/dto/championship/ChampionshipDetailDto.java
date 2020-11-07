package fr.apside.apsipoker.championship.rest.dto.championship;

import fr.apside.apsipoker.championship.model.Championship;
import fr.apside.apsipoker.championship.rest.dto.tournament.TournamentForChampionshipUpdateDto;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class ChampionshipDetailDto {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private List<PokerUserDto> participants = new ArrayList<>();
    private List<TournamentForChampionshipUpdateDto> tournaments = new ArrayList<>();

    public static List<ChampionshipDetailDto> toDto(List<Championship> bos) {
        return Objects.isNull(bos)
                ? new ArrayList<>()
                : bos.stream().map(ChampionshipDetailDto::toDto).collect(Collectors.toList());
    }

    public static ChampionshipDetailDto toDto(Championship bo) {
        ChampionshipDetailDto dto = new ChampionshipDetailDto();

        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setStartDate(bo.getStartDate());
        dto.setEndDate(bo.getEndDate());
        dto.setParticipants(PokerUserDto.toDto(bo.getParticipants()));
        dto.setTournaments(TournamentForChampionshipUpdateDto.toDto(bo.getTournaments()));

        return dto;
    }
}
