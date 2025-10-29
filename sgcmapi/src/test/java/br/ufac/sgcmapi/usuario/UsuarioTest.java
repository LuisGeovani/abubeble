package br.ufac.sgcmapi.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
    
    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
    }

    @Test
    void TestUsuarioGettersSetters(){
        var id = 2L;
        var nomeCompleto = "User Teste";
        var nomeUsuario = "usuario123";
        var senha = "senhaTeste";
        var ativo = true;
        var papel = EPapel.ADMIN;
    
        usuario.setId(id);
        usuario.setNomeCompleto(nomeCompleto);
        usuario.setNomeUsuario(nomeUsuario);
        usuario.setSenha(senha);
        usuario.setAtivo(ativo);
        usuario.setPapel(papel);

        assertEquals(2L, usuario.getId());
        assertEquals(nomeCompleto, usuario.getNomeCompleto());
        assertEquals(nomeUsuario, usuario.getNomeUsuario());
        assertEquals(senha, usuario.getSenha());
        assertTrue(usuario.isAtivo());
        assertEquals(papel, usuario.getPapel());
    }
}