package br.edu.unime.api2.service;

import br.edu.unime.api2.entity.Paciente;
import br.edu.unime.api2.repository.PacienteRepository;
import br.edu.unime.api2.service.exceptions.CPFExistenteException;
import br.edu.unime.api2.service.exceptions.CPFNotUpdateException;
import br.edu.unime.api2.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> obterTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findByCpf(String cpf) throws Exception {
        Optional<Paciente> paciente = pacienteRepository.findByCpf(cpf);

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


    public Paciente inserir(Paciente paciente) throws Exception {

        if (pacienteRepository.findByCpf(paciente.getCpf()).isPresent()) {
            throw new CPFExistenteException("O CPF informado já encontra-se cadastrado em nosso sistema!: " + paciente.getCpf());
        }

        pacienteRepository.insert(paciente);
        return paciente;
    }

    public Paciente atualizarPorId(String id, Paciente novosDadosDoPaciente) throws Exception {
        Optional<Paciente> paciente = Optional.ofNullable(findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o ID: " + id)));

        if (paciente.isPresent()) {
            Paciente novoPaciente = paciente.get();

            if (!novoPaciente.getCpf().equals(novosDadosDoPaciente.getCpf())) {
                throw new CPFNotUpdateException("Atenção! A edição do campo CPF não permitida em nosso sistema.");
            }

            novoPaciente.setNome(novosDadosDoPaciente.getNome());
            novoPaciente.setSobrenome(novosDadosDoPaciente.getSobrenome());
            novoPaciente.setCpf(novosDadosDoPaciente.getCpf());
            novoPaciente.setIdade(novosDadosDoPaciente.getIdade());
            if (novosDadosDoPaciente.getSexo() != null) {
                novoPaciente.setSexo(novosDadosDoPaciente.getSexo());
            }
            if (novosDadosDoPaciente.getContato() != null) {
                novoPaciente.setContato(novosDadosDoPaciente.getContato());
            }
            if (novosDadosDoPaciente.getLogradouro() != null) {
                novoPaciente.setLogradouro(novosDadosDoPaciente.getLogradouro());
            }
            if (novosDadosDoPaciente.getNumero() != null) {
                novoPaciente.setNumero(novosDadosDoPaciente.getNumero());
            }
            if (novosDadosDoPaciente.getBairro() != null) {
                novoPaciente.setBairro(novosDadosDoPaciente.getBairro());
            }
            if (novosDadosDoPaciente.getCep() != null) {
                novoPaciente.setCep(novosDadosDoPaciente.getCep());
            }
            if (novosDadosDoPaciente.getMunicipio() != null) {
                novoPaciente.setMunicipio(novosDadosDoPaciente.getMunicipio());
            }
            if (novosDadosDoPaciente.getEstado() != null) {
                novoPaciente.setEstado(novosDadosDoPaciente.getEstado());
            }
            pacienteRepository.save(novoPaciente);
            return novoPaciente;
        }
        return null;
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


