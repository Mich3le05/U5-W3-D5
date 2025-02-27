package it.epicode.gestione_eventi.eventi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    Optional<Evento> findByLuogoAndData(String luogo, LocalDate data);
}
