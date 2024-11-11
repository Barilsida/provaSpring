package barisla.example.prova.integrations.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Entity
@Table(name = "Utenti")
public class UtenteDAO {
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
