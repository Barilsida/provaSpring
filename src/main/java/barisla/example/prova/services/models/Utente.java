package barisla.example.prova.services.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utente {
    private String id;
    private String nome;
    private String cognome;
    private String email;
    private Integer eta;
    private ArrayList<ContattoUtente> contattiUtente;
}
