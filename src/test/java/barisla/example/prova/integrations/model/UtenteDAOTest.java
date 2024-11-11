package barisla.example.prova.integrations.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UtenteDAOTest {
    UtenteDAO utenteDAO = new UtenteDAO();
    @Test
    void getId() {
        UUID id = new UUID(123121212L, 123);
        utenteDAO.setId(id);

        assertEquals(utenteDAO.getId(), id);
    }

    @Test
    void getNome() {
        utenteDAO.setNome("Mario");

        assertEquals(utenteDAO.getNome(),"Mario");
    }

    @Test
    void getCognome() {
        utenteDAO.setCognome("Rossi");

        assertEquals(utenteDAO.getCognome(),"Rossi");
    }

    @Test
    void getEmail() {
        utenteDAO.setEmail("mrossi@gmail.com");

        assertEquals(utenteDAO.getEmail(),"mrossi@gmail.com");
    }

    @Test
    void getCellulare() {
        utenteDAO.setCellulare("12312345");

        assertEquals(utenteDAO.getCellulare(),"12312345");
    }

    @Test
    void getEta() {
        utenteDAO.setEta(23);

        assertEquals(utenteDAO.getEta(),23);
    }

    @Test
    void setId() {
        UUID id = new UUID(123121212L, 123);
        utenteDAO.setId(id);

        assertEquals(utenteDAO.getId(), id);
    }

    @Test
    void setNome() {
        utenteDAO.setNome("Mario");

        assertEquals(utenteDAO.getNome(),"Mario");
    }

    @Test
    void setCognome() {
        utenteDAO.setCognome("Rossi");

        assertEquals(utenteDAO.getCognome(),"Rossi");
    }

    @Test
    void setEmail() {
        utenteDAO.setEmail("mrossi@gmail.com");

        assertEquals(utenteDAO.getEmail(),"mrossi@gmail.com");
    }

    @Test
    void setCellulare() {
        utenteDAO.setCellulare("12312345");

        assertEquals(utenteDAO.getCellulare(),"12312345");
    }

    @Test
    void setEta() {
        utenteDAO.setEta(23);

        assertEquals(utenteDAO.getEta(),23);
    }
}