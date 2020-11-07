package fr.apside.apsipoker.championship.rest.dto.tournament.player;

import fr.apside.apsipoker.championship.model.TournamentPlayer;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class TournamentPlayerForChampionshipUpdateDto {
    private Long id;
    private PokerUserDto player;
    private Byte position;

    public static List<TournamentPlayer> toBo(List<TournamentPlayerForChampionshipUpdateDto> dtos) {
        return Objects.isNull(dtos)
                ? new ArrayList<>()
                : dtos.stream().map(TournamentPlayerForChampionshipUpdateDto::toBo).collect(Collectors.toList());
    }

    public static TournamentPlayer toBo(TournamentPlayerForChampionshipUpdateDto dto) {
        TournamentPlayer tournamentPlayer = new TournamentPlayer(dto.id);

        tournamentPlayer.setPosition(dto.position);
        tournamentPlayer.setPlayer(PokerUserDto.toBo(dto.player));

        return tournamentPlayer;
    }

    public static List<TournamentPlayerForChampionshipUpdateDto> toDto(List<TournamentPlayer> bos) {
        return Objects.isNull(bos)
                ? new ArrayList<>()
                : bos.stream().map(TournamentPlayerForChampionshipUpdateDto::toDto).collect(Collectors.toList());
    }

    public static TournamentPlayerForChampionshipUpdateDto toDto(TournamentPlayer bo) {
        TournamentPlayerForChampionshipUpdateDto dto = new TournamentPlayerForChampionshipUpdateDto();

        dto.setId(bo.getId());
        dto.setPosition(bo.getPosition());
        dto.setPlayer(PokerUserDto.toDto(bo.getPlayer()));

        return dto;
    }

}
