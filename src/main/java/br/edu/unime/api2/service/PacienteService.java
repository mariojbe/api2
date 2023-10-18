package br.edu.unime.api2.service;

import br.edu.unime.api2.entity.Paciente;
import br.edu.unime.api2.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> obterTodos(){
        return pacienteRepository.findAll();
    }
    public Paciente obterPeloNomeESobrenome(String nome, String sobrenome) throws Exception {
        Paciente paciente = pacienteRepository.findFirstByNomeAndSobrenome(nome, sobrenome);

        if (paciente == null){
            throw new Exception("paciente não encontrado");
        }

        return paciente;
    }
public void inserir(Paciente paciente){
        pacienteRepository.insert(paciente);
}
public Paciente atualizar(String nome, String sobrenome, Paciente novoPaciente) throws Exception{
   Paciente pacienteAntigo = obterPeloNomeESobrenome(nome, sobrenome);

    pacienteAntigo.setNome(novoPaciente.getNome());
    pacienteAntigo.setSobrenome(novoPaciente.getSobrenome());
    pacienteAntigo.setIdade(novoPaciente.getIdade());
    pacienteAntigo.setSexo(novoPaciente.getSexo());
    pacienteAntigo.setCpf(novoPaciente.getCpf());
    pacienteAntigo.setContato(novoPaciente.getContato());
    pacienteAntigo.setLogradouro(novoPaciente.getLogradouro());
    pacienteAntigo.setNumero(novoPaciente.getNumero());
    pacienteAntigo.setBairro(novoPaciente.getBairro());
    pacienteAntigo.setCep(novoPaciente.getCep());
    pacienteAntigo.setMunicipio(novoPaciente.getMunicipio());
    pacienteAntigo.setEstado(novoPaciente.getEstado());

    pacienteRepository.save(pacienteAntigo);

    return pacienteAntigo;
}
public void deletar(String nome, String sobrenome) throws Exception {
    Paciente paciente = obterPeloNomeESobrenome(nome, sobrenome);

    pacienteRepository.delete(paciente);
    }
}


