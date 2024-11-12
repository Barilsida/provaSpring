package barisla.example.prova.integrations.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name = "Utenti")
public class UtenteEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name = "id")
    UUID id;
    String nome;
    String cognome;
    String email;
    String cellulare;
    int eta;

}