package it.epicode.gestione_eventi.auth;

import it.epicode.gestione_eventi.exceptions.ExceptionHandlerClass;
import it.epicode.gestione_eventi.utenti.Utente;
import it.epicode.gestione_eventi.utenti.UtenteLoginRequest;
import it.epicode.gestione_eventi.utenti.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUtenteAndCreateToken(UtenteLoginRequest utenteLoginRequest) {
        Optional<Utente> utenteOptional = utenteService.getUtenteByEmail(utenteLoginRequest.getEmail());

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();

            if (passwordEncoder.matches(utenteLoginRequest.getPassword(), utente.getPassword())) {
                return jwtTool.createToken(utente);
            } else {
                throw new ExceptionHandlerClass.NonAutorizzatoException("Password errata. Fai di nuovo il login!");
            }
        } else {
            throw new ExceptionHandlerClass.NonAutorizzatoException("Utente non presente. Fai di nuovo il login!");
        }
    }
}
