package br.ufac.sgcmapi.profissional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.ufac.sgcmapi.especialidade.Especialidade;
import br.ufac.sgcmapi.unidade.Unidade;

public class ProfissionalTeste {
    
    private Profissional profissional;

    @BeforeEach
    void setUp(){
        profissional = new Profissional();
    }

    @Test
    void TestProfissionalGettersSetters(){
        var id = 2L;
        var nome = "Profissional Teste";
        var registroConselho = "123456789";
        var especialidade = new Especialidade();
        var unidade = new Unidade();
        var telefone = "68 4002-8922";
        var email = "profissional@email.com";
    
        profissional.setId(id);
        profissional.setNome(nome);
        profissional.setRegistroConselho(registroConselho);
        profissional.setEspecialidade(especialidade);
        profissional.setUnidade(unidade);
        profissional.setTelefone(telefone);
        profissional.setEmail(email);

        assertEquals(2L, profissional.getId());
        assertEquals(nome, profissional.getNome());
        assertEquals(registroConselho, profissional.getRegistroConselho());
        assertEquals(especialidade, profissional.getEspecialidade());
        assertEquals(unidade, profissional.getUnidade());
        assertEquals(telefone, profissional.getTelefone());
        assertEquals(email, profissional.getEmail());
    }
}