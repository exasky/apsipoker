package fr.apside.apsipoker.user.rest;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.user.rest.dto.PokerCreateUserDto;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import fr.apside.apsipoker.user.rest.dto.PokerUserUpdatePasswordDto;
import fr.apside.apsipoker.user.service.PokerUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static fr.apside.apsipoker.common.Utils.mapList;

@RestController
@RequestMapping(Constant.REST_URL + "/user")
public class UserRestController {

    private final PokerUserService pokerUserService;

    public UserRestController(PokerUserService pokerUserService) {
        this.pokerUserService = pokerUserService;
    }

    @GetMapping
    public List<PokerUserDto> getAll() {
        return mapList(pokerUserService.getAll(), PokerUserDto::toDto);
    }

    @GetMapping("/{id}")
    public PokerUserDto getById(@PathVariable Long id) {
        return PokerUserDto.toDto(this.pokerUserService.getById(id));
    }

    @PostMapping
    public PokerUserDto create(@Valid @RequestBody PokerCreateUserDto dto) {
        return PokerUserDto.toDto(this.pokerUserService.create(PokerCreateUserDto.toBo(dto)));
    }

    @PutMapping("/{id}")
    public PokerUserDto update(@PathVariable Long id, @Valid @RequestBody PokerUserDto dto) {
        return PokerUserDto.toDto(this.pokerUserService.update(id, PokerUserDto.toBo(dto)));
    }

    @PutMapping("/update-password/{id}")
    public PokerUserDto updatePassword(@PathVariable Long id, @RequestBody PokerUserUpdatePasswordDto dto) {
        return PokerUserDto.toDto(this.pokerUserService.updatePassword(id, PokerUserUpdatePasswordDto.toBo(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.pokerUserService.delete(id);
    }
}
