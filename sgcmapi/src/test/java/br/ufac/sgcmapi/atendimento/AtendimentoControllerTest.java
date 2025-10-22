package br.ufac.sgcmapi.atendimento;

import static org.mockito.ArgumentMatchers.any;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AtendimentoController.class)
public class AtendimentoControllerTest {
    @MockitoBean
    private AtendimentoService servico;

    @Autowired
    private MockMvc mockMvc;

    private Atendimento atendimento;
    private String conteudoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        atendimento = new Atendimento();
        atendimento.setId(1L);

        conteudoJson = new ObjectMapper().writeValueAsString(atendimento);
    }

    @Test
    void testAtendimentoInserir() throws Exception {
        Mockito.when(servico.salvar(any(Atendimento.class)))
            .thenReturn(atendimento);
        mockMvc.perform(MockMvcRequestBuilders.post("/atendimento/inserir")
            .contentType(MediaType.APPLICATION_JSON)
            .content(conteudoJson))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }
}
