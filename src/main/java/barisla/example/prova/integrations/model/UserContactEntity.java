package barisla.example.prova.integrations.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UtenteContatti")
public class UserContactEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    String id;
    String tipo;
    String value;

}
