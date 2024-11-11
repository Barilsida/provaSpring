package barisla.example.prova.integrations.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UtentiRepositoryTest {
    @Autowired
    UtentiRepository utentiRepository;

    @BeforeEach
     void setUp() {
        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.setCellulare("1111111");
        utenteDAO.setNome("Giacomo");
        utenteDAO.setCognome("Bianchi");
        utenteDAO.setEmail("gbianchi@gmail.com");
        utenteDAO.setEta(30);
        utentiRepository.save(utenteDAO);
    }

    @AfterEach
    void cleanUp() {
        utentiRepository.deleteAll();
    }

    @Test
    void cercaPerEmailSuccesso() {
        List<UtenteDAO> utenti = utentiRepository.cercaPerEmail("gbianchi@gmail.com");

        assertEquals(1, utenti.size());
        assertEquals("gbianchi@gmail.com", utenti.get(0).getEmail());
    }

    @Test
    void cercaPerEmailFailure() {
        List<UtenteDAO> utenti = utentiRepository.cercaPerEmail("grossi@gmail.com");

        assertEquals(0, utenti.size());
    }
}