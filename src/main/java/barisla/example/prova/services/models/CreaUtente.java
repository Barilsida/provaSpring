package barisla.example.prova.services.models;

import lombok.*;

import java.util.ArrayList;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreaUtente {
    private String nome;
    private String cognome;
    private String email;
    private Integer eta;
   // private ArrayList<ContattoUtente> contatti;

}
