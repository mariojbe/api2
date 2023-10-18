package br.edu.unime.api2.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pacientes")
public class Paciente {

    @Id
    private String id;

    @NotBlank
    @Size(min = 3, max = 100, message = "o nome deve ter entre 3 e 100 digitos!")
    private String nome;
    private String sobrenome;
    private int idade;
    private String sexo;
    private String cpf;
    private String contato;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String municipio;
    private String estado;



}
