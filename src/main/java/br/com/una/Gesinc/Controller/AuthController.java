package br.com.una.Gesinc.Controller;

import br.com.una.Gesinc.Config.Security.TokenService;
import br.com.una.Gesinc.Domain.Users;
import br.com.una.Gesinc.Dto.LoginDto;
import br.com.una.Gesinc.Form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginForm loginForm) {
        var authToken = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        var authentication = manager.authenticate(authToken);

        var tokenJwt = tokenService.generateToken((Users) authentication.getPrincipal());
        return ResponseEntity.ok(new LoginDto(tokenJwt));
    }

}
