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
    UtentiDAO utentiRepository;

    @BeforeEach
     void setUp() {
        UtenteEntity utenteEntity = new UtenteEntity();
        utenteEntity.setNome("Giacomo");
        utenteEntity.setCognome("Bianchi");
        utenteEntity.setEmail("gbianchi@gmail.com");
        utenteEntity.setEta(30);
        utentiRepository.save(utenteEntity);
    }

    @AfterEach
    void cleanUp() {
        utentiRepository.deleteAll();
    }

    @Test
    void cercaPerEmailSuccesso() {
        List<UtenteEntity> utenti = utentiRepository.cercaPerEmail("gbianchi@gmail.com");

        assertEquals(1, utenti.size());
        assertEquals("gbianchi@gmail.com", utenti.get(0).getEmail());
    }

    @Test
    void cercaPerEmailFailure() {
        List<UtenteEntity> utenti = utentiRepository.cercaPerEmail("grossi@gmail.com");

        assertEquals(0, utenti.size());
    }
}