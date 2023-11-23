package br.edu.unime.api2.entity;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Pacientes")
@Entity
public class Paciente {

    @Id
    private String id;

    @NotBlank(message = "O nome não deve estar em branco")
    @Size(min = 3, max = 100, message = "o nome deve ter entre 3 e 100 digitos!")
    private String nome;

    @NotBlank(message = "O sobrenome não deve estar em branco")
    @Size(min = 3, max = 100, message = "o nome deve ter entre 3 e 100 digitos!")
    private String sobrenome;

    @CPF(message = "Número de Cadastro de Pessoa Física Brasileiro (CPF) inválido")
    @Indexed(unique = true)
    @NotBlank(message = "O CPF não deve estar em branco")
    @NotNull
    private String cpf;

    @NotNull
    @Min(value = 1, message = "Informe uma idade maior que 0!")
    private int idade;

    @Pattern(regexp = "^[M|F|m|f]{1}$", message = "Favor informar 'M', 'm', 'F' ou 'f' no campo sexo")
    private String sexo;

    private String contato;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String municipio;
    private String estado;

}
