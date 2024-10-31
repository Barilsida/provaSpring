package barisla.example.prova.handlers;

import barisla.example.presentation.model.Errore;
import barisla.example.prova.expetions.ResorceNotFound;
import barisla.example.prova.expetions.UtenteEsisteGia;
import barisla.example.prova.expetions.UtenteNonTrovato;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class UtentiErrorHandler {

    @ExceptionHandler(ResorceNotFound.class)
    public ResponseEntity<Object> handleResourceNotFound (ResorceNotFound res) {
        Errore errore = new Errore();
        errore.setErrorCode("Resource con id "+res.getId() +" di tipo "+res.getResourceName()+" non trovata");
        return new ResponseEntity<>(errore, HttpStatusCode.valueOf(404));

    }

    @ExceptionHandler(UtenteEsisteGia.class)
    public ResponseEntity<Object> handleUtenteEsisteGia (UtenteEsisteGia utenteEsisteGia) {
        Errore errore = new Errore();
        errore.setErrorCode("Utente con email "+ utenteEsisteGia.getEmail() +"esiste gia.");
        return  new ResponseEntity<>(errore, HttpStatusCode.valueOf(409));

    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> handleNoResourceFoundException (NoResourceFoundException noResourceFoundException) {
        Errore errore = new Errore();
        errore.setErrorCode("Risorsa non trovata");
        return  new ResponseEntity<>(errore, HttpStatusCode.valueOf(404));
    }
}
