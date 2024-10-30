package barisla.example.prova.services.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@Builder
public class CreaUtente {
    private String nome;
    private String cognome;
    private String cellulare;
    private String email;
    private Integer eta;
}
