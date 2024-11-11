package barisla.example.prova.services.models;

import lombok.*;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreaUtente {
    private String nome;
    private String cognome;
    private String cellulare;
    private String email;
    private Integer eta;
}
