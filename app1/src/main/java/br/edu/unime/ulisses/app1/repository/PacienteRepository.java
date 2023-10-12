package br.edu.unime.ulisses.app1.repository;

import br.edu.unime.ulisses.app1.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PacienteRepository extends MongoRepository<Paciente, String> {

Paciente findFirstByNomeAndSobrenome(String nome, String sobrenome);

}
