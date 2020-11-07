package fr.apside.apsipoker.championship.rest;

import fr.apside.apsipoker.championship.service.TournamentService;
import fr.apside.apsipoker.common.Constant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.REST_URL + "/tournament")
public class TournamentRestController {
    private final TournamentService service;

    public TournamentRestController(TournamentService service) {
        this.service = service;
    }

    public void create() {

    }
}
