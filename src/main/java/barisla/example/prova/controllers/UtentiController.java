package barisla.example.prova.controllers;

import barisla.example.presentation.api.UtentiApi;
import barisla.example.prova.integrations.model.UtentiRepository;
import barisla.example.prova.mappers.UtenteMapper;
import barisla.example.prova.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import barisla.example.presentation.model.CreaUtenteRequest;
import barisla.example.presentation.model.Utente;
import barisla.example.presentation.model.Utenti;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1")
public class UtentiController implements UtentiApi {
    @Autowired
    UserService userService;
    @Autowired
    UtenteMapper utenteMapper;


    public UtentiController() {
      System.out.println("UtentiController started");
    }
    @SneakyThrows
    @Override
    public ResponseEntity<Utente> creaUtente(CreaUtenteRequest creaUtenteRequest) {
        // TODO Auto-generated method stub
        barisla.example.prova.services.models.Utente utenteService = userService.creaUtente(utenteMapper.transform(creaUtenteRequest));
        Utente utente = utenteMapper.transform(utenteService);

        return new ResponseEntity<>(utente, HttpStatusCode.valueOf(201));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Utente> deleteUtente(String id) {
        barisla.example.prova.services.models.Utente deletedUtente = userService.deleteUtenteByID(id);
        Utente utentePM = utenteMapper.transform(deletedUtente);
        return new ResponseEntity<>(utentePM, HttpStatusCode.valueOf(200));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Utente> getUtenteById(String id) {

            barisla.example.prova.services.models.Utente utenteService = userService.getUtenteById(id);
            Utente utente = utenteMapper.transform(utenteService);
            return new ResponseEntity<>(utente, HttpStatusCode.valueOf(200));

    }

    @Override
    public ResponseEntity<Utenti> getUtenti(Integer offset, Integer limmit, String cognome, String email) {
        List<barisla.example.prova.services.models.Utente> utenti;
        List<Utente> utentiMapper;
        Utenti allUtenti = new Utenti();
        utenti = userService.getAll();
        utentiMapper = utenteMapper.transformListUtenteService(utenti);

        if(cognome!= null){
            utenti = userService.getUtentiByCognome(cognome);
            utentiMapper = utenteMapper.transformListUtenteService(utenti);
        } else if (email!= null) {
            utenti = userService.getUtenteByEmail(email);
            utentiMapper = utenteMapper.transformListUtenteService(utenti);
        }

        allUtenti.setUtenti(utentiMapper);
        allUtenti.setPageNumber(0);
        return new ResponseEntity<>(allUtenti, HttpStatusCode.valueOf(200));
    }

}

