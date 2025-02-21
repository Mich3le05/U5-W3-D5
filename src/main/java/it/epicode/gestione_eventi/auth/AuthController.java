package it.epicode.gestione_eventi.auth;

import it.epicode.gestione_eventi.exceptions.ExceptionHandlerClass;
import it.epicode.gestione_eventi.utenti.UtenteLoginRequest;
import it.epicode.gestione_eventi.utenti.UtenteRequest;
import it.epicode.gestione_eventi.utenti.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ExceptionHandlerClass.BadRequestException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).reduce("", (s, s2) -> s + s2));
        }

        return utenteService.saveUtente(utenteRequest);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated UtenteLoginRequest utenteLoginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ExceptionHandlerClass.BadRequestException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).reduce("", (s, s2) -> s + s2));
        }

        return authService.authenticateUtenteAndCreateToken(utenteLoginRequest);
    }
}
