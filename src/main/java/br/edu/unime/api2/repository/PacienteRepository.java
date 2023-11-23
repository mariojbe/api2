package br.edu.unime.api2.repository;

import br.edu.unime.api2.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

public interface PacienteRepository extends MongoRepository<Paciente, String> {

    public Optional<Paciente> findById(String id);

    public Paciente findFirstByNomeAndSobrenome(String nome, String sobrenome);

    public Optional<Paciente> findByCpf(String cpf);

    List<Paciente> findByEstado(String estado);

}
