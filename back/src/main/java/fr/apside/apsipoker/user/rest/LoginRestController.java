package fr.apside.apsipoker.user.rest;

import fr.apside.apsipoker.common.Constant;
import fr.apside.apsipoker.security.MyUserDetailsService;
import fr.apside.apsipoker.security.jwt.JwtTokenUtil;
import fr.apside.apsipoker.user.model.PokerUser;
import fr.apside.apsipoker.user.rest.dto.JwtDto;
import fr.apside.apsipoker.user.rest.dto.LoginDto;
import fr.apside.apsipoker.user.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.REST_URL)
public class LoginRestController {

    private final LoginService loginService;

    private final MyUserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    public LoginRestController(LoginService loginService,
                               MyUserDetailsService userDetailsService,
                               AuthenticationManager authenticationManager,
                               JwtTokenUtil jwtTokenUtil) {
        this.loginService = loginService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public JwtDto login(@RequestBody LoginDto dto) throws Exception {
        authenticate(dto.getUsername(), dto.getPassword());
        final PokerUser userDetails = userDetailsService.loadUserByUsername(dto.getUsername());

        return new JwtDto(jwtTokenUtil.generateToken(userDetails));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody LoginDto dto) {
        this.loginService.register(dto.getUsername(), dto.getPassword());
    }
}
