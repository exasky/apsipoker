package fr.apside.apsipoker.user.rest;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.user.rest.dto.PokerCreateUserDto;
import fr.apside.apsipoker.user.rest.dto.PokerUserDto;
import fr.apside.apsipoker.user.rest.dto.PokerUserUpdatePasswordDto;
import fr.apside.apsipoker.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constant.REST_URL + "/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<PokerUserDto> getAll() {
        return PokerUserDto.toDto(this.userService.getAll());
    }

    @GetMapping("/{id}")
    public PokerUserDto getById(@PathVariable Long id) {
        return PokerUserDto.toDto(this.userService.getById(id));
    }

    @PostMapping
    public PokerUserDto create(@Valid @RequestBody PokerCreateUserDto dto) {
        return PokerUserDto.toDto(this.userService.create(PokerCreateUserDto.toBo(dto)));
    }

    @PutMapping("/{id}")
    public PokerUserDto update(@PathVariable Long id, @Valid @RequestBody PokerUserDto dto) {
        return PokerUserDto.toDto(this.userService.update(id, PokerUserDto.toBo(dto)));
    }

    @PutMapping("/update-password/{id}")
    public PokerUserDto updatePassword(@PathVariable Long id, @RequestBody PokerUserUpdatePasswordDto dto) {
        return PokerUserDto.toDto(this.userService.updatePassword(id, PokerUserUpdatePasswordDto.toBo(dto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
