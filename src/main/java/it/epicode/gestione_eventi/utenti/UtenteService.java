package it.epicode.gestione_eventi.utenti;

import it.epicode.gestione_eventi.eventi.Evento;
import it.epicode.gestione_eventi.eventi.EventoService;
import it.epicode.gestione_eventi.exceptions.ExceptionHandlerClass;
import it.epicode.gestione_eventi.partecipazione.Partecipazione;
import it.epicode.gestione_eventi.partecipazione.PartecipazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private PartecipazioneRepository partecipazioneRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Utente> getUtenteById(Integer id) {
        return utenteRepository.findById(id);
    }

    public Optional<Utente> getUtenteByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Page<Utente> getAllUtenti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return utenteRepository.findAll(pageable);
    }

    public String saveUtente(UtenteRequest utenteDto) {
        if (getUtenteByEmail(utenteDto.getEmail()).isEmpty()) {
            Utente utente = new Utente();
            utente.setNome(utenteDto.getNome());
            utente.setCognome(utenteDto.getCognome());
            utente.setEmail(utenteDto.getEmail());
            utente.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
            utente.setRuoloUtente(utenteDto.getRuoloUtente());
            utenteRepository.save(utente);

            return "Utente con id "+utente.getId()+" creato con successo";
        } else {
            throw new ExceptionHandlerClass.BadRequestException("L'email dell'utente "+utenteDto.getEmail()+" è già presente");
        }
    }

    public Partecipazione partecipaEvento(Integer idEvento, Integer idUtente) {
        Optional<Utente> utenteOptional = getUtenteById(idUtente);
        if (utenteOptional.isPresent()) {
            Optional<Evento> eventoOptional = eventoService.getEventoById(idEvento);
            if (eventoOptional.isPresent()) {
                Evento evento = eventoOptional.get();
                if (evento.getPartecipazioni().size() < evento.getNumPostiDisponibili()) {
                    Optional<Partecipazione> partecipazioneOptional = partecipazioneRepository.findByUtenteAndEvento(utenteOptional.get(), evento);
                    if (partecipazioneOptional.isPresent()) {
                        throw new ExceptionHandlerClass.PartecipazionePresenteException("Stai già partecipando a questo evento");
                    } else {
                        Partecipazione partecipazione = new Partecipazione();
                        partecipazione.setUtente(utenteOptional.get());
                        partecipazione.setEvento(evento);
                        return partecipazioneRepository.save(partecipazione);
                    }
                } else {
                    throw new ExceptionHandlerClass.EventoPienoException("Non ci sono posti disponibili per questo evento");
                }
            } else {
                throw new ExceptionHandlerClass.EventoNonTrovatoException("Evento con id "+idEvento+" non trovato");
            }
        } else {
            throw new ExceptionHandlerClass.UtenteNonTrovatoException("Utente con id "+idUtente+" non trovato");
        }
    }
}
