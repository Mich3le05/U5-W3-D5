package it.epicode.gestione_eventi.partecipazione;

import it.epicode.gestione_eventi.eventi.Evento;
import it.epicode.gestione_eventi.utenti.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartecipazioneRepository extends JpaRepository<Partecipazione, Integer> {
    Optional<Partecipazione> findByUtenteAndEvento(Utente utente, Evento evento);
}
