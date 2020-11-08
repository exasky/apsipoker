package fr.apside.apsipoker.championship.rest.dto.tournament.player;

import fr.apside.apsipoker.tournament.model.TournamentPlayer;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

@Data
public class TournamentPlayerForChampionshipUpdateDto {
    private Long id;
    private PokerUserDto player;
    private Byte position;
    private Float points;

    public static TournamentPlayer toBo(TournamentPlayerForChampionshipUpdateDto dto) {
        TournamentPlayer tournamentPlayer = new TournamentPlayer(dto.id);

        tournamentPlayer.setPosition(dto.position);
        tournamentPlayer.setPlayer(PokerUserDto.toBo(dto.player));
        tournamentPlayer.setPoints(dto.points);

        return tournamentPlayer;
    }

    public static TournamentPlayerForChampionshipUpdateDto toDto(TournamentPlayer bo) {
        TournamentPlayerForChampionshipUpdateDto dto = new TournamentPlayerForChampionshipUpdateDto();

        dto.setId(bo.getId());
        dto.setPosition(bo.getPosition());
        dto.setPlayer(PokerUserDto.toDto(bo.getPlayer()));
        dto.setPoints(bo.getPoints());

        return dto;
    }

}
