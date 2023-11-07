package br.edu.unime.api2.service;

import br.edu.unime.api2.entity.Paciente;
import br.edu.unime.api2.repository.PacienteRepository;
import br.edu.unime.api2.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> obterTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente obterPeloNomeESobrenome(String nome, String sobrenome) throws Exception {
        Paciente paciente = pacienteRepository.findFirstByNomeAndSobrenome(nome, sobrenome);

        if (paciente == null) {
            throw new Exception("Paciente não encontrado!");
        }

        return paciente;
    }

    public List<Paciente> findByEstado(String estado) {
        try {
            List<Paciente> registroVacinacao = pacienteRepository.findByEstado(estado);
        } catch (Exception e) {
            throw new ClassCastException("Registro Não Lacalizado!");
        }
        return pacienteRepository.findByEstado(estado);
    }


    public Paciente inserir(Paciente paciente) {
        pacienteRepository.insert(paciente);
        return paciente;
    }

    public Paciente atualizarPorId(String id, Paciente novosDadosDoPaciente) {
        Optional<Paciente> paciente = findById(id);

        if (paciente.isPresent()) {
            Paciente novoPaciente = paciente.get();
            novoPaciente.setNome(novosDadosDoPaciente.getNome());
            novoPaciente.setSobrenome(novosDadosDoPaciente.getSobrenome());
            novoPaciente.setCpf(novosDadosDoPaciente.getCpf());
            novoPaciente.setIdade(novosDadosDoPaciente.getIdade());
            novoPaciente.setSexo(novosDadosDoPaciente.getSexo());
            novoPaciente.setContato(novosDadosDoPaciente.getContato());
            novoPaciente.setLogradouro(novosDadosDoPaciente.getLogradouro());
            novoPaciente.setNumero(novosDadosDoPaciente.getNumero());
            novoPaciente.setCep(novosDadosDoPaciente.getCep());
            novoPaciente.setContato(novosDadosDoPaciente.getContato());
            novoPaciente.setMunicipio(novosDadosDoPaciente.getMunicipio());
            novoPaciente.setEstado(novosDadosDoPaciente.getEstado());
            pacienteRepository.save(novoPaciente);
            return novoPaciente;
        }
        return null;

    }

    public Paciente atualizar(String nome, String sobrenome, Paciente novoPaciente) throws Exception {
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

    public void remove(String id) {
        Optional<Paciente> paciente = findById(id);

        paciente.ifPresent(value -> pacienteRepository.delete(value));
    }

    public Optional<Paciente> findById(String id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        Paciente entity = paciente.orElseThrow(() -> new EntityNotFoundException("Paciente Não Lacalizado!"));

        return Optional.ofNullable(entity);
    }

}


