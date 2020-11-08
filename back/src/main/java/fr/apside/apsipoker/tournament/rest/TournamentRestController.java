package fr.apside.apsipoker.tournament.rest;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.tournament.rest.dto.TournamentDetailDto;
import fr.apside.apsipoker.tournament.service.TournamentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.REST_URL + "/tournament")
public class TournamentRestController {
    private final TournamentService tournamentService;

    public TournamentRestController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/{id}")
    public TournamentDetailDto getById(@PathVariable Long id) {
        return TournamentDetailDto.toDto(tournamentService.getById(id));
    }

    @PutMapping("/{id}")
    public TournamentDetailDto update(@PathVariable Long id, @RequestBody TournamentDetailDto tournament) {
        return TournamentDetailDto.toDto(tournamentService.updateAndCalculatePoints(id, TournamentDetailDto.toBo(tournament)));
    }
}
