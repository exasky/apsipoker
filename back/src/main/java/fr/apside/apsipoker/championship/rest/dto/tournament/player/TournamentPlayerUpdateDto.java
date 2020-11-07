package fr.apside.apsipoker.championship.rest.dto.tournament.player;

import lombok.Data;

@Data
public class TournamentPlayerUpdateDto {
    private Long id;
    private Long playerId;
    private Byte position;
}
