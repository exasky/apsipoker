package fr.apside.apsipoker.dashboard.rest;

import fr.apside.apsipoker.championship.service.ChampionshipService;
import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.dashboard.rest.dto.ChampionshipsForUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static fr.apside.apsipoker.common.Utils.mapList;

@RestController
@RequestMapping(Constant.REST_URL + "/dashboard")
public class DashboardRestController {
    private final ChampionshipService championshipService;

    public DashboardRestController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @GetMapping
    public List<ChampionshipsForUserDto> geDashboardForUser() {
        return mapList(championshipService.getAllForCurrentUser(), ChampionshipsForUserDto::toDto);
    }
}
