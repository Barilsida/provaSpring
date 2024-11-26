package barisla.example.prova.integrations.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UtenteEntityTest {
    UtenteEntity utenteEntity = new UtenteEntity();
    @Test
    void getId() {
        UUID id = new UUID(123121212L, 123);
        utenteEntity.setId(id);

        assertEquals(utenteEntity.getId(), id);
    }

    @Test
    void getNome() {
        utenteEntity.setNome("Mario");

        assertEquals(utenteEntity.getNome(),"Mario");
    }

    @Test
    void getCognome() {
        utenteEntity.setCognome("Rossi");

        assertEquals(utenteEntity.getCognome(),"Rossi");
    }

    @Test
    void getEmail() {
        utenteEntity.setEmail("mrossi@gmail.com");

        assertEquals(utenteEntity.getEmail(),"mrossi@gmail.com");
    }

    @Test
    void getEta() {
        utenteEntity.setEta(23);

        assertEquals(utenteEntity.getEta(),23);
    }

    @Test
    void setId() {
        UUID id = new UUID(123121212L, 123);
        utenteEntity.setId(id);

        assertEquals(utenteEntity.getId(), id);
    }

    @Test
    void setNome() {
        utenteEntity.setNome("Mario");

        assertEquals(utenteEntity.getNome(),"Mario");
    }

    @Test
    void setCognome() {
        utenteEntity.setCognome("Rossi");

        assertEquals(utenteEntity.getCognome(),"Rossi");
    }

    @Test
    void setEmail() {
        utenteEntity.setEmail("mrossi@gmail.com");

        assertEquals(utenteEntity.getEmail(),"mrossi@gmail.com");
    }


    @Test
    void setEta() {
        utenteEntity.setEta(23);

        assertEquals(utenteEntity.getEta(),23);
    }
}