package fr.apside.apsipoker.tournament.rest.dto;

import fr.apside.apsipoker.tournament.model.TournamentPlayer;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import lombok.Data;

@Data
public class TournamentPlayerDetailDto {
    private Long id;
    private PokerUserDto player;
    private Byte position;
    private Float points;

    public static TournamentPlayerDetailDto toDto(TournamentPlayer bo) {
        TournamentPlayerDetailDto dto = new TournamentPlayerDetailDto();

        dto.setId(bo.getId());
        dto.setPlayer(PokerUserDto.toDto(bo.getPlayer()));
        dto.setPosition(bo.getPosition());
        dto.setPoints(bo.getPoints());

        return dto;
    }

    public static TournamentPlayer toBo(TournamentPlayerDetailDto dto) {
        TournamentPlayer bo = new TournamentPlayer(dto.id);

        bo.setPosition(dto.position);
        bo.setPoints(dto.points);
        bo.setPlayer(new PokerUser(dto.player.getId()));

        return bo;
    }
}
