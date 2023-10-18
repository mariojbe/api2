package br.edu.unime.api2.repository;

import br.edu.unime.api2.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PacienteRepository extends MongoRepository<Paciente, String> {

Paciente findFirstByNomeAndSobrenome(String nome, String sobrenome);

}
