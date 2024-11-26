package barisla.example.prova.services;

import barisla.example.prova.ProvaApplication;
import barisla.example.prova.expetions.UtenteEsisteGia;
import barisla.example.prova.expetions.UtenteNonTrovato;
import barisla.example.prova.integrations.model.UserContactEntity;
import barisla.example.prova.integrations.model.UtenteEntity;
import barisla.example.prova.integrations.model.UtentiDAO;
import barisla.example.prova.services.models.CreaUtente;
import barisla.example.prova.services.models.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ProvaApplication.class})
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UtentiDAO utentiRepository;

    @Test
    void creaUtente() {
        CreaUtente richiestaNuovoUtente = CreaUtente.builder().
                nome("Paolo").
                cognome("verdi").
                eta(23).build();

        Utente nuovoutenteResponse = null;
        try {
            nuovoutenteResponse = userService.creaUtente(richiestaNuovoUtente);
        } catch (UtenteEsisteGia e) {
           assertEquals(true, false);
        }

        assertEquals(nuovoutenteResponse.getNome(), richiestaNuovoUtente.getNome());
        assertEquals(nuovoutenteResponse.getCognome(), richiestaNuovoUtente.getCognome());
        assertEquals(nuovoutenteResponse.getEmail(), richiestaNuovoUtente.getEmail());
        assertEquals(nuovoutenteResponse.getEta(), richiestaNuovoUtente.getEta());

        assertNotNull(nuovoutenteResponse.getId());
    }

    @Test
    void creaUtenteEccessione() {
        CreaUtente richiestaNuovoUtente = CreaUtente.builder().
                nome("Paolo").
                cognome("verdi").
                email("mrossi@gmail.com").
                eta(23)
                .build();
        creaUtentePerTest();

        UtenteEsisteGia utenteEsisteGiaResponse = assertThrows(UtenteEsisteGia.class,
                () -> { userService.creaUtente(richiestaNuovoUtente);}
        );

        assertEquals(utenteEsisteGiaResponse.getEmail(),richiestaNuovoUtente.getEmail());
    }


    @Test
    void deleteUtenteByID() {
        String id = creaUtentePerTest().getId().toString();
        try {
         Utente utenteCancellato = userService.deleteUtenteByID(id);
         assertEquals(id, utenteCancellato.getId());
        } catch (UtenteNonTrovato u) {
            assertEquals(true, false);
        }
    }

    private UtenteEntity creaUtentePerTest () {
        utentiRepository.deleteAll();
        UtenteEntity utenteTest =  new UtenteEntity();
        utenteTest.setNome("Mario");
        utenteTest.setCognome("Rossi");
        utenteTest.setEmail("mrossi@gmail.com");
        utenteTest.setEta(34);

        UserContactEntity userContactEntity = new UserContactEntity();

        userContactEntity.setValue("1212121212");
        userContactEntity.setTipo("cellulare");
        utenteTest.setContactEntity(new ArrayList<>(Collections.singleton(userContactEntity)));

        return utentiRepository.save(utenteTest);
    }
}