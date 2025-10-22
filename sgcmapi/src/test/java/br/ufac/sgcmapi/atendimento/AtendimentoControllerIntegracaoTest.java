package br.ufac.sgcmapi.atendimento;

import java.nio.file.Files;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class AtendimentoControllerIntegracaoTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAtendimentoInserir() throws Exception {
        var arquivoJson = new ClassPathResource("json/atendimentoInserir.json").getFile();
        var conteudoJson = Files.readString(arquivoJson.toPath());

        mockMvc.perform(MockMvcRequestBuilders.post("/atendimento/inserir")
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(6)));
    }
}
