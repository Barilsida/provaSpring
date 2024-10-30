package barisla.example.prova.expetions;

public class UtenteNonTrovato extends ResorceNotFound {
    public UtenteNonTrovato(String id) {
        super(id, "utente");
    }
}
