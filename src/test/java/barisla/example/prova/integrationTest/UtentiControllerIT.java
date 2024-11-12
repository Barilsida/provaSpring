package barisla.example.prova.integrationTest;

import barisla.example.presentation.model.CreaUtenteRequest;
import barisla.example.prova.ProvaApplication;
import barisla.example.prova.services.models.CreaUtente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {ProvaApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:IT/applications.properties")
public class UtentiControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void creaUtenteSuccess() throws Exception {
        CreaUtente richiestaNuovoUtenteService = CreaUtente.builder().
                nome("Paolo").
                cognome("verdi").
                email("mrossi@gmail.com").
                cellulare("12312345").
                eta(23).build();

        CreaUtenteRequest richiestaNuovoUtenteController = new CreaUtenteRequest();
        richiestaNuovoUtenteController.setNome(richiestaNuovoUtenteService.getNome());
        richiestaNuovoUtenteController.setCognome(richiestaNuovoUtenteService.getCognome());
        richiestaNuovoUtenteController.setCellulare(richiestaNuovoUtenteService.getCellulare());
        richiestaNuovoUtenteController.setEmail(richiestaNuovoUtenteService.getEmail());
        richiestaNuovoUtenteController.setEta(richiestaNuovoUtenteService.getEta());

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAsString = objectMapper.writeValueAsString(richiestaNuovoUtenteController);

        mockMvc.perform(post("/api/v1/utenti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyAsString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(
                        "$.id"
                ).exists())
                .andExpect(jsonPath(
                        "$.nome"
                ).value(
                        "Paolo"
                ))
                .andExpect(jsonPath(
                        "$.cognome"
                ).value(
                        "verdi"
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

    }


    @Test
    public void creaUtenteFailureUtenteEsisteGia() throws Exception {
        CreaUtente richiestaNuovoUtenteService = CreaUtente.builder().
                nome("Paolo").
                cognome("verdi").
                email("mrossi@gmail.com").
                cellulare("12312345").
                eta(23).build();

        CreaUtenteRequest richiestaNuovoUtenteController = new CreaUtenteRequest();
        richiestaNuovoUtenteController.setNome(richiestaNuovoUtenteService.getNome());
        richiestaNuovoUtenteController.setCognome(richiestaNuovoUtenteService.getCognome());
        richiestaNuovoUtenteController.setCellulare(richiestaNuovoUtenteService.getCellulare());
        richiestaNuovoUtenteController.setEmail(richiestaNuovoUtenteService.getEmail());
        richiestaNuovoUtenteController.setEta(richiestaNuovoUtenteService.getEta());

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAsString = objectMapper.writeValueAsString(richiestaNuovoUtenteController);

        mockMvc.perform(post("/api/v1/utenti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyAsString))
                .andExpect(status().isConflict())
                .andExpect(jsonPath(
                        "$.errorCode"
                ).exists());

    }


}
