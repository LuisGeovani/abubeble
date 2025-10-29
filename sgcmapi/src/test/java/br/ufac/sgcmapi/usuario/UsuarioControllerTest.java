package br.ufac.sgcmapi.usuario;

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

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @MockitoBean
    private UsuarioService servico;

    @Autowired
    private MockMvc mockMvc;

    private Usuario usuario;
    private List<Usuario> usuariosLista;
    private String conteudoJson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        usuario = new Usuario();
        usuario.setId(1L);

        usuariosLista = List.of(usuario); 

        conteudoJson = new ObjectMapper().writeValueAsString(usuario);
    }

    @Test
    void testUsuarioInserir() throws Exception {
        Mockito.when(servico.salvar(any(Usuario.class)))
               .thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.post("/config/usuario/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(1)));
    }

    @Test
    void testUsuarioConsultar() throws Exception {
        Mockito.when(servico.consultar("teste")).thenReturn(usuariosLista);

        mockMvc.perform(MockMvcRequestBuilders.get("/config/usuario/consultar")
                .param("termoBusca", "teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
    }

    @Test
    void testUsuarioAtualizar() throws Exception {
        Mockito.when(servico.salvar(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.put("/config/usuario/atualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(conteudoJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUsuarioRemover() throws Exception {
        doNothing().when(servico).remover(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/config/usuario/remover/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}