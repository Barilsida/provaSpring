package barisla.example.prova.services.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreaUtenteTest {
    String nomeTest = "nomeTest";
    String cognomeTest = "cognomeTest";
    String emailTest = "emailTest";
    int etaTest = 23;
    CreaUtente utente = new CreaUtente(nomeTest, cognomeTest, emailTest, etaTest);

    @Test
    void getNome() {
        utente.setNome(nomeTest);

        assertEquals(utente.getNome(),nomeTest);
    }

    @Test
    void getCognome() {
        utente.setCognome(cognomeTest);

        assertEquals(utente.getCognome(),cognomeTest);
    }

    @Test
    void getEmail() {
        utente.setEmail(emailTest);

        assertEquals(utente.getEmail(),emailTest);
    }

    @Test
    void getEta() {
        utente.setEta(etaTest);

        assertEquals(utente.getEta(),etaTest);
    }

    @Test
    void setNome() {
        utente.setNome(nomeTest);

        assertEquals(utente.getNome(),nomeTest);
    }

    @Test
    void setCognome() {
        utente.setCognome(cognomeTest);

        assertEquals(utente.getCognome(),cognomeTest);
    }

    @Test
    void setEmail() {
        utente.setEmail(emailTest);

        assertEquals(utente.getEmail(),emailTest);
    }

    @Test
    void setEta() {
        utente.setEmail(emailTest);

        assertEquals(utente.getEmail(),emailTest);
    }

    @Test
    void builder() {
        utente.builder();
    }
}