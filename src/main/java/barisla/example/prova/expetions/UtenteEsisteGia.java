package barisla.example.prova.expetions;

import lombok.Getter;

@Getter
public class UtenteEsisteGia extends Exception {
    private String email;

    public UtenteEsisteGia (String email) {
        super();
        this.email= email;
    }
}
