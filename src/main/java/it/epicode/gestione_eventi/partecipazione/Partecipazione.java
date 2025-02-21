package it.epicode.gestione_eventi.partecipazione;

import it.epicode.gestione_eventi.eventi.Evento;
import it.epicode.gestione_eventi.utenti.Utente;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "partecipazioni")
public class Partecipazione {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="utente_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name="evento_id")
    private Evento evento;
}
