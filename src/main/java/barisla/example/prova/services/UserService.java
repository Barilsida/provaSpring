package barisla.example.prova.services;


import barisla.example.prova.expetions.UtenteEsisteGia;
import barisla.example.prova.expetions.UtenteNonTrovato;
import barisla.example.prova.integrations.model.UtenteEntity;
import barisla.example.prova.integrations.model.UtentiDAO;
import barisla.example.prova.mappers.UtenteMapper;
import barisla.example.prova.services.models.CreaUtente;
import barisla.example.prova.services.models.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UtentiDAO utentiRepository;

    @Autowired
    UtenteMapper utenteMapper;

    private HashMap<String, Utente> utenti = new HashMap<>();

    public Utente creaUtente(CreaUtente utente) throws UtenteEsisteGia {
        if(utentiRepository.findByEmail(utente.getEmail()).size()>0){
            throw new UtenteEsisteGia(utente.getEmail());
        }

        UtenteEntity utenteDAO = utenteMapper.transform(utente);
        utentiRepository.save(utenteDAO);

        return utenteMapper.transform(utenteDAO);
    }

    public Utente getUtenteById(String id) throws UtenteNonTrovato {
       Optional<UtenteEntity> utenti = utentiRepository.findById(UUID.fromString(id));
        if (!utenti.isPresent()){
            throw new UtenteNonTrovato(id);
        }

        return utenteMapper.transform(utenti.get());

    }

    public List<Utente> getUtentiByCognome (String cognome) {

        return utenteMapper.transformListUtenteDAO(utentiRepository.findByCognomeIgnoreCase(cognome));
    }
    public  List<Utente> getUtenteByEmail (String email) {
        return utenteMapper.transformListUtenteDAO(utentiRepository.cercaPerEmail(email));
    }

    public List<Utente> getAll () {
        return utenteMapper.transformListUtenteDAO(utentiRepository.findAll());
    }

    public Utente deleteUtenteByID (String id) throws UtenteNonTrovato {
        Optional<UtenteEntity> utenteDAOOptional = utentiRepository.findById(UUID.fromString(id));

        if(!utentiRepository.findById(UUID.fromString(id)).isPresent()){
            throw new UtenteNonTrovato(id);
        }

        utentiRepository.deleteById(UUID.fromString(id));

        return utenteMapper.transform(utenteDAOOptional.get());
    }
}
