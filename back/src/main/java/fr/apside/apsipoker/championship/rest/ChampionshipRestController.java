package fr.apside.apsipoker.championship.rest;

import fr.apside.apsipoker.championship.rest.dto.championship.ChampionshipCreateDto;
import fr.apside.apsipoker.championship.rest.dto.championship.ChampionshipDetailDto;
import fr.apside.apsipoker.championship.service.ChampionshipService;
import fr.apside.apsipoker.common.Constant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.REST_URL + "/championship")
public class ChampionshipRestController {

    private final ChampionshipService service;

    public ChampionshipRestController(ChampionshipService service) {
        this.service = service;
    }

    @GetMapping
    public List<ChampionshipDetailDto> getAll() {
        return ChampionshipDetailDto.toDto(service.getAll());
    }

    @GetMapping("/{id}")
    public ChampionshipDetailDto getById(@PathVariable Long id) {
        return ChampionshipDetailDto.toDto(service.getById(id));
    }

    @PostMapping
    public ChampionshipDetailDto create(@RequestBody ChampionshipCreateDto dto) {
        return ChampionshipDetailDto.toDto(service.create(ChampionshipCreateDto.toBo(dto)));
    }
}