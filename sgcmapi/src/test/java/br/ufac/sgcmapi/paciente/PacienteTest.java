package br.ufac.sgcmapi.paciente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteTest {
    
    private Paciente paciente;

    @BeforeEach
    void setUp(){
        paciente = new Paciente();
    }

    @Test
    void TestPacienteGettersSetters(){
        var id = 2L;
        var nome = "Paciente Teste";
        var email = "paciente.teste@email.com";
        var telefone = "68 4002-8922";
        var dataNascimento = LocalDate.of(1990, 10, 20);
        var grupoSanguineo = EGrupoSanguineo.A_POSITIVO;
        var sexo = ESexo.F;
        var cep = "69901-234";
        var endereco = "Rua Teste, 123";
        var cidade = "Rio Branco";
        var estado = "AC";
    
        paciente.setId(id);
        paciente.setNome(nome);
        paciente.setEmail(email);
        paciente.setTelefone(telefone);
        paciente.setDataNascimento(dataNascimento);
        paciente.setGrupoSanguineo(grupoSanguineo);
        paciente.setSexo(sexo);
        paciente.setCep(cep);
        paciente.setEndereco(endereco);
        paciente.setCidade(cidade);
        paciente.setEstado(estado);

        assertEquals(2L, paciente.getId());
        assertEquals(nome, paciente.getNome());
        assertEquals(email, paciente.getEmail());
        assertEquals(telefone, paciente.getTelefone());
        assertEquals(dataNascimento, paciente.getDataNascimento());
        assertEquals(grupoSanguineo, paciente.getGrupoSanguineo());
        assertEquals(sexo, paciente.getSexo());
        assertEquals(cep, paciente.getCep());
        assertEquals(endereco, paciente.getEndereco());
        assertEquals(cidade, paciente.getCidade());
        assertEquals(estado, paciente.getEstado());
    }
}