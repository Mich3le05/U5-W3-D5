package it.epicode.gestione_eventi.utenti;


import it.epicode.gestione_eventi.partecipazione.Partecipazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Page<Utente> getAllUtenti(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "15") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.getAllUtenti(page, size, sortBy);
    }

    @PostMapping("/{id}/partecipa/{evento}")
    public Partecipazione partecipaEvento(@PathVariable Integer id, @PathVariable Integer evento) {
        return utenteService.partecipaEvento(evento, id);
    }
}
