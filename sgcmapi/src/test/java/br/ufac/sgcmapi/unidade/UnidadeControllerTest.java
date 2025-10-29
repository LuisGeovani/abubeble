package br.ufac.sgcmapi.unidade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.List;

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

@WebMvcTest(UnidadeController.class)
public class UnidadeControllerTest {

    @MockitoBean
    private UnidadeService servico;

    @Autowired
    private MockMvc mockMvc;

    private Unidade unidade;
    private List<Unidade> unidadesLista;
    private String conteudoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        unidade = new Unidade();
        unidade.setId(1L);

        unidadesLista = List.of(unidade); 

        conteudoJson = new ObjectMapper().writeValueAsString(unidade);
    }

    @Test
    void testUnidadeInserir() throws Exception {
        Mockito.when(servico.salvar(any(Unidade.class)))
               .thenReturn(unidade);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/unidade/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void testUnidadeConsultar() throws Exception {
        Mockito.when(servico.consultar("teste")).thenReturn(unidadesLista);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/unidade/consultar")
                .param("termoBusca", "teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    void testUnidadeAtualizar() throws Exception {
        Mockito.when(servico.salvar(any(Unidade.class))).thenReturn(unidade);

        mockMvc.perform(MockMvcRequestBuilders.put("/config/unidade/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUnidadeRemover() throws Exception {
        doNothing().when(servico).remover(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/unidade/remover/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}