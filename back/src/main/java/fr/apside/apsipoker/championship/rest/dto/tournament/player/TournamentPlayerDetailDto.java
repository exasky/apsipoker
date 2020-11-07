package fr.apside.apsipoker.championship.rest.dto.tournament.player;

import fr.apside.apsipoker.championship.model.TournamentPlayer;
import lombok.Data;

@Data
public class TournamentPlayerDetailDto {
    private Long id;
    private Long playerId;
    private String playerName;
    private Byte position;
    private Float points;

    public static TournamentPlayerDetailDto toDto(TournamentPlayer bo) {
        TournamentPlayerDetailDto dto = new TournamentPlayerDetailDto();

        dto.setId(bo.getId());
        dto.setPlayerId(bo.getPlayer().getId());
        dto.setPlayerName(bo.getPlayer().getUsername());
        dto.setPosition(bo.getPosition());
        dto.setPoints(bo.getPoints());

        return dto;
    }
}
