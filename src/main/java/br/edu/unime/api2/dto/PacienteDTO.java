package br.edu.unime.api2.dto;

import br.edu.unime.api2.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    @NotBlank
    @Size(min = 3, max = 100, message = "o nome deve ter entre 3 e 100 digitos!")
    private String nome;
    private String sobrenome;

    @CPF
    @Indexed(unique = true)
    private String cpf;

    @NotNull
    @Min(value = 1, message = "Informe uma idade maior que 0!")
    private int idade;

    private String sexo;
    private String contato;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String municipio;
    private String estado;

}