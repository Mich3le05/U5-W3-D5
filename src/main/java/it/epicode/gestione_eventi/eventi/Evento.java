package it.epicode.gestione_eventi.eventi;


import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.gestione_eventi.partecipazione.Partecipazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "evento_seq")
    @SequenceGenerator(name = "evento_seq", sequenceName = "evento_seq", allocationSize = 1)
    private Integer id;

    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private Integer numPostiDisponibili;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Partecipazione> partecipazioni;
}
