package br.ufac.sgcmapi.unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnidadeTest {
    
    private Unidade unidade;

    @BeforeEach
    void setUp(){
        unidade = new Unidade();
    }

    @Test
    void TestUnidadeGettersSetters(){
        var id = 2L;
        var nome = "Unidade Teste";
        var endereco = "Rua Teste, 456";
    
        unidade.setId(id);
        unidade.setNome(nome);
        unidade.setEndereco(endereco);

        assertEquals(2L, unidade.getId());
        assertEquals(nome, unidade.getNome());
        assertEquals(endereco, unidade.getEndereco());
    }
}