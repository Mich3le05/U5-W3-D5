package it.epicode.gestione_eventi.eventi;

import it.epicode.gestione_eventi.exceptions.ExceptionHandlerClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Page<Evento> getAllEventi(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return eventoRepository.findAll(pageable);
    }

    public Optional<Evento> getEventoById(Integer id) {
        return eventoRepository.findById(id);
    }

    public Optional<Evento> getEventoByLuogoAndData(String luogo, LocalDate data) {
        return eventoRepository.findByLuogoAndData(luogo, data);
    }

    public String saveEvento(EventoRequest eventoRequest) {
        Optional<Evento> eventoOptional = getEventoByLuogoAndData(eventoRequest.getLuogo(), eventoRequest.getData());
        if (eventoOptional.isEmpty()) {
            Evento evento = new Evento();
            evento.setData(eventoRequest.getData());
            evento.setDescrizione(eventoRequest.getDescrizione());
            evento.setTitolo(eventoRequest.getTitolo());
            evento.setLuogo(eventoRequest.getLuogo());
            evento.setNumPostiDisponibili(eventoRequest.getNumPostiDisponibili());
            eventoRepository.save(evento);
            return "Evento con id " + evento.getId() + " creato con successo";
        } else {
            throw new ExceptionHandlerClass.EventoPresenteException("Evento presente per giorno e luogo indicati!");
        }
    }

    public Evento updateEvento(Integer id, EventoRequest eventoRequest) {
        Optional<Evento> eventoOptional = getEventoById(id);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            evento.setData(eventoRequest.getData());
            evento.setDescrizione(eventoRequest.getDescrizione());
            evento.setTitolo(eventoRequest.getTitolo());
            evento.setLuogo(eventoRequest.getLuogo());
            evento.setNumPostiDisponibili(eventoRequest.getNumPostiDisponibili());
            return eventoRepository.save(evento);
        } else {
            throw new ExceptionHandlerClass.EventoNonTrovatoException("Evento con id " + id + " non trovato");
        }
    }

    public String deleteEvento(Integer id) {
        Optional<Evento> eventoOptional = getEventoById(id);
        if (eventoOptional.isPresent()) {
            eventoRepository.delete(eventoOptional.get());
            return "Evento con id " + id + " eliminato con successo";
        } else {
            throw new ExceptionHandlerClass.EventoNonTrovatoException("Evento con id " + id + " non trovato");
        }
    }
}
