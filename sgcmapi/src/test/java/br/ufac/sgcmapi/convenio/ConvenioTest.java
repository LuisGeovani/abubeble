package br.ufac.sgcmapi.convenio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConvenioTest {
    private Convenio convenio;
    
    @BeforeEach
    void setUp(){
        convenio = new Convenio();
    }

    // @Test
    // void TestConvenioId(){
    //     var id = 2L;
    //     convenio.setId(id);
    //     assertEquals(2L, convenio.getId());
    // }
    
    // @Test
    // void TestConvenioNome(){
    //     var nome = "Teste";
    //     convenio.setNome(nome);
    //     assertEquals("Teste", convenio.getNome());
    // }

    // @Test
    // void TestConvenioRazaoSocial(){
    //     var razaoSocial = "Unimed";
    //     convenio.setRazaoSocial(razaoSocial);
    //     assertEquals("Unimed", convenio.getRazaoSocial());
    // }

    // @Test
    // void TestConvenioCNPJ(){

    // }

    @Test
    void TestConvenioGettersSetters(){
        var id = 2L;
        var nome = "Convenio";
        var razaoSocial = "Convenio Razão";
        var cnpj = "123.456.789-10";
        var representante = "Convenio Representante";
        var email = "Teste@Email.com";
        var telefone = "(68) 4002-8922";
        var ativo = true;
    
        convenio.setId(id);
        convenio.setNome(nome);
        convenio.setRazaoSocial(razaoSocial);
        convenio.setCnpj(cnpj);
        convenio.setRepresentante(representante);
        convenio.setEmail(email);
        convenio.setTelefone(telefone);
        convenio.setAtivo(ativo);

        assertEquals(2l, convenio.getId());
        assertEquals("Convenio", convenio.getNome());
        assertEquals("Convenio Razão", convenio.getRazaoSocial());
        assertEquals("123.456.789-10", convenio.getCnpj());
        assertEquals("Convenio Representante", convenio.getRepresentante());
        assertEquals("Teste@Email.com", convenio.getEmail());
        assertEquals("(68) 4002-8922", convenio.getTelefone());
        assertEquals(true, convenio.isAtivo());
    }
}
