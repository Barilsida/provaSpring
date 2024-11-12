package barisla.example.prova.mappers;

import barisla.example.prova.ProvaApplication;
import barisla.example.prova.integrations.model.UtenteEntity;
import barisla.example.prova.services.models.CreaUtente;
import barisla.example.prova.services.models.Utente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {ProvaApplication.class})
class UtenteMapperTest {
    @Autowired
    private UtenteMapper utenteMapper;

    @Test
    void transformUUIDtoIdString() {
        UUID uuid = new UUID(123123, 123123);
        UtenteEntity utenteEntity = new UtenteEntity();
        utenteEntity.setId(uuid);
        Utente utente = utenteMapper.transform(utenteEntity);

        assertEquals(utente.getId(), utenteEntity.getId().toString());
    }

    @Test
    void transformIgnoreId() {
        CreaUtente creaUtente = new CreaUtente("Mario","Rossi","1231234","mrossi@gmail.com",43);
        UtenteEntity utenteEntity = utenteMapper.transform(creaUtente);

        assertNull(utenteEntity.getId());
    }

    @Test
    void UIDtoIdString() {
        UUID uuid = new UUID(123123, 123123);

        assertInstanceOf(String.class, utenteMapper.uuidToString(uuid));
    }

}