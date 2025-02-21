package it.epicode.gestione_eventi.utenti;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteLoginRequest {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;

    public UtenteLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
