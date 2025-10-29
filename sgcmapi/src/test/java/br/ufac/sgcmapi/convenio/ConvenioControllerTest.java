package br.ufac.sgcmapi.convenio;

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

@WebMvcTest(ConvenioController.class)
public class ConvenioControllerTest {

    @MockitoBean
    private ConvenioService servico;

    @Autowired
    private MockMvc mockMvc;

    private Convenio convenio;
    private List<Convenio> conveniosLista;
    private String conteudoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        convenio = new Convenio();
        convenio.setId(1L);

        conveniosLista = List.of(convenio); 

        conteudoJson = new ObjectMapper().writeValueAsString(convenio);
    }

    @Test
    void testConvenioInserir() throws Exception {
        Mockito.when(servico.salvar(any(Convenio.class)))
               .thenReturn(convenio);

        mockMvc.perform(MockMvcRequestBuilders.post("/convenio/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void testConsultarPorTermoBusca() throws Exception {
        Mockito.when(servico.consultar("teste")).thenReturn(conveniosLista);

        mockMvc.perform(MockMvcRequestBuilders.get("/convenio/consultar")
                .param("termoBusca", "teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    void testConvenioAtualizar() throws Exception {
        Mockito.when(servico.salvar(any(Convenio.class))).thenReturn(convenio);

        mockMvc.perform(MockMvcRequestBuilders.put("/convenio/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testConvenioRemover() throws Exception {
        doNothing().when(servico).remover(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/convenio/remover/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}