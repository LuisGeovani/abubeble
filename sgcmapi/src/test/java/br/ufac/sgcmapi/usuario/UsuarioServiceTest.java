package br.ufac.sgcmapi.usuario;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService servico;

    private Usuario usuario;
    private List<Usuario> usuariosLista;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setId(1L);

        usuariosLista = List.of(usuario);
    }

    @Test
    void testUsuarioConsultar(){
        Mockito.when(repo.consultar((String) null))
               .thenReturn(usuariosLista);
        var resultado = servico.consultar((String) null);
        assertEquals(usuariosLista, resultado);
    }

    @Test
    void testUsuarioConsultarPorId() {
        Mockito.when(repo.findById(1L)).thenReturn(Optional.of(usuario));
        var resultado = servico.consultar(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testUsuarioSalvar() {
        Mockito.when(repo.save(usuario)).thenReturn(usuario);
        var resultado = servico.salvar(usuario);
        assertEquals(usuario, resultado);
    }

    @Test
    void testUsuarioRemover() {
        doNothing().when(repo).deleteById(1L);
        servico.remover(1L);
        Mockito.verify(repo, Mockito.times(1)).deleteById(1L);
    }
}