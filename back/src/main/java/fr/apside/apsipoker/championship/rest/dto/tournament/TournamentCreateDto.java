package fr.apside.apsipoker.championship.rest.dto.tournament;

import fr.apside.apsipoker.championship.rest.dto.tournament.player.TournamentPlayerUpdateDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TournamentCreateDto {
    private Long championshipId;
    private Date date;
    private List<TournamentPlayerUpdateDto> participants;
}
