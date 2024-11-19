package barisla.example.prova.integrationTest;

import barisla.example.presentation.model.CreaUtenteRequest;
import barisla.example.prova.ProvaApplication;
import barisla.example.prova.integrations.model.UtenteEntity;
import barisla.example.prova.integrations.model.UtentiDAO;
import barisla.example.prova.services.models.CreaUtente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {ProvaApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:IT/applications.properties")
public class UtentiControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UtentiDAO utentiRepository;

    @Test
    public void creaUtenteSuccesso() throws Exception {
        CreaUtente richiestaNuovoUtenteService = CreaUtente.builder().
                nome("Mario").
                cognome("Rossi").
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
                        "Mario"
                ))
                .andExpect(jsonPath(
                        "$.cognome"
                ).value(
                        "Rossi"
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
                        ));
    }

    @Test
    public void creaUtenteFailureUtenteEsisteGia() throws Exception {
        creaUtentePerTest();
        CreaUtente richiestaNuovoUtenteService = CreaUtente.builder().
                nome("Paolo").
                cognome("Verdi").
                email("pverdi@gmail.com").
                cellulare("12121212").
                eta(34).build();

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
                .andExpect(status().isConflict());

    }

    @Test
    public void deleteUtenteSuccesso() throws Exception {
        creaUtentePerTest();
        UtenteEntity utenteEntity = new UtenteEntity();
        utenteEntity.setId(creaUtentePerTest().getId());
        utenteEntity.setNome(creaUtentePerTest().getNome());
        utenteEntity.setCognome(creaUtentePerTest().getCognome());
        utenteEntity.setCellulare(creaUtentePerTest().getCellulare());
        utenteEntity.setEmail(creaUtentePerTest().getEmail());
        utenteEntity.setEta(creaUtentePerTest().getEta());

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAsString = objectMapper.writeValueAsString(utenteEntity);

        mockMvc.perform(delete("/api/v1/utenti/{id}", creaUtentePerTest().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyAsString))
                .andExpect(status().isOk())
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
                        "Verdi"
                ))
                .andExpect(jsonPath(
                        "$.email"
                ).value(
                        "pverdi@gmail.com"
                ))
                .andExpect(jsonPath(
                        "$.cellulare"
                ).value(
                        "12121212"
                ))
                .andExpect(jsonPath(
                                "$.eta"
                        ).value(
                                34
                        )
                );

    }

    @Test
    public void deleteUtenteFailure() throws Exception {
        UUID uuid = new UUID(12312312,12312312);
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAsString = objectMapper.writeValueAsString(uuid);

        mockMvc.perform(delete("/api/v1/utenti/{id}", uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyAsString))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(
                        "$.errorCode"
                ).exists());
    }

    @Test
    public void getUtenteByIdSuccess() throws Exception {
        creaUtentePerTest();
        UtenteEntity utenteEntity = new UtenteEntity();
        utenteEntity.setId(creaUtentePerTest().getId());
        utenteEntity.setNome(creaUtentePerTest().getNome());
        utenteEntity.setCognome(creaUtentePerTest().getCognome());
        utenteEntity.setCellulare(creaUtentePerTest().getCellulare());
        utenteEntity.setEmail(creaUtentePerTest().getEmail());
        utenteEntity.setEta(creaUtentePerTest().getEta());

        ObjectMapper objectMapper = new ObjectMapper();
        String bodyAsString = objectMapper.writeValueAsString(utenteEntity);
        mockMvc.perform(get("/api/v1/utenti/{id}", creaUtentePerTest().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyAsString))
                .andExpect(status().isOk())
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
                        "Verdi"
                ))
                .andExpect(jsonPath(
                        "$.email"
                ).value(
                        "pverdi@gmail.com"
                ))
                .andExpect(jsonPath(
                        "$.cellulare"
                ).value(
                        "12121212"
                ))
                .andExpect(jsonPath(
                                "$.eta"
                        ).value(
                                34
                        )
                );
    }

    @Test
    public void getUtenteByIdFailure() throws Exception {
       mockMvc.perform(get("/api/v1/utenti{id}", "2213213"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(
                        "$.errorCode"
                ).exists());
    }

    @Test
    public void getUtentiByCognomeSuccess() throws Exception {
        creaUtentePerTest();
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/utenti")
                        .param("offset", "1")
                        .param("limit", "1")
                        .param("cognome", "Verdi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.utenti").isNotEmpty())
                .andReturn();

        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    public void getUtentiByEmailSuccess() throws Exception {
        creaUtentePerTest();
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/utenti")
                        .param("offset", "1")
                        .param("limit", "1")
                        .param("mail", "pverdi@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.utenti").isNotEmpty())
                .andReturn();

        assertEquals("application/json", mvcResult.getResponse().getContentType());

    }

    private UtenteEntity creaUtentePerTest () {
        utentiRepository.deleteAll();
        UtenteEntity utenteTest =  new UtenteEntity();
        UUID uuid = new UUID(12312312,12312312);
        utenteTest.setId(uuid);
        utenteTest.setNome("Paolo");
        utenteTest.setCognome("Verdi");
        utenteTest.setEmail("pverdi@gmail.com");
        utenteTest.setCellulare("12121212");
        utenteTest.setEta(34);

        return utentiRepository.save(utenteTest);
    }

}
