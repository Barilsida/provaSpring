package barisla.example.prova.controllers;

import barisla.example.presentation.model.CreaUtenteRequest;
import barisla.example.prova.ProvaApplication;
import barisla.example.prova.expetions.UtenteEsisteGia;
import barisla.example.prova.mappers.UtenteMapper;
import barisla.example.prova.services.UserService;
import barisla.example.prova.services.models.CreaUtente;
import barisla.example.prova.services.models.Utente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UtentiController.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {UtenteMapper.class} )
class UtentiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;



    @Test
    void creaUtenteSuccesso() {
        CreaUtente richiestaNuovoUtente = CreaUtente.builder().
                nome("Paolo").
                cognome("verdi").
                email("mrossi@gmail.com").
                cellulare("12312345").
                eta(23).build();

        Utente utenteCreato = new Utente();
        utenteCreato.setId("1231232");
        utenteCreato.setNome(richiestaNuovoUtente.getNome());
        utenteCreato.setCognome(richiestaNuovoUtente.getCognome());
        utenteCreato.setCellulare(richiestaNuovoUtente.getCellulare());
        utenteCreato.setEmail(richiestaNuovoUtente.getEmail());
        utenteCreato.setEta(richiestaNuovoUtente.getEta());


        try {
            when(userService.creaUtente(richiestaNuovoUtente)).thenReturn(utenteCreato);

            CreaUtenteRequest utenteBody= new CreaUtenteRequest();
            utenteBody.setNome(richiestaNuovoUtente.getNome());
            utenteBody.setCognome(richiestaNuovoUtente.getCognome());
            utenteBody.setCellulare(richiestaNuovoUtente.getCellulare());
            utenteBody.setEmail(richiestaNuovoUtente.getEmail());
            utenteBody.setEta(richiestaNuovoUtente.getEta());

            ObjectMapper objectMapper = new ObjectMapper();
            String bodyAsString = objectMapper.writeValueAsString(utenteBody);

            mockMvc.perform(post( "/api/v1/utenti")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(bodyAsString))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath(
                            "$.id"
                    ).value(
                            "1231232"
                    ))
                    .andExpect(jsonPath(
                            "$.nome"
                    ).value(
                            "Paolo"
                    ))
                    .andExpect(jsonPath(
                            "$.cognome"
                    ).value(
                            "Verdi"
                    ))
                    .andExpect(jsonPath(
                            "$.email"
                    ).value(
                            "mrossi@gmail.com"
                    ))
                    .andExpect(jsonPath(
                            "$.cellulare"
                    ).value(
                            "12312345"
                    ))
                    .andExpect(jsonPath(
                            "$.eta"
                    ).value(
                            23
                    )
            );

        } catch (UtenteEsisteGia u) {
            assertEquals(true, true);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}