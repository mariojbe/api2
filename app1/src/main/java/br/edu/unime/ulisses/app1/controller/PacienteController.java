package br.edu.unime.ulisses.app1.controller;
import br.edu.unime.ulisses.app1.entity.Paciente;
import br.edu.unime.ulisses.app1.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> obterTodos() {
        return ResponseEntity.ok().body(pacienteService.obterTodos());
    }

    @GetMapping("/{nome}/{sobrenome}")
    public ResponseEntity<?> obterPeloNome(@PathVariable String nome, @PathVariable String sobrenome) {
        try {
            Paciente paciente = pacienteService.obterPeloNomeESobrenome(nome, sobrenome);

            return ResponseEntity.ok().body(paciente);
        } catch (Exception e){
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody @Valid Paciente paciente, BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            List<String> erros = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.toArray());
        }
        pacienteService.inserir(paciente);

        return ResponseEntity.created(null).body(paciente);
    }

@PutMapping("/{nome}/{sobrenome}")
public ResponseEntity<?> atualizar(
                            @PathVariable String nome,
                            @PathVariable String sobrenome,
                            @RequestBody Paciente paciente
){
    try {
        Paciente pacienteAtualizado = pacienteService.atualizar(nome, sobrenome, paciente);

        return ResponseEntity.ok().body(pacienteAtualizado);
    } catch (Exception e){
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }
}

    @DeleteMapping("/{nome}/{sobrenome}")
    public ResponseEntity<?> excluir(
            @PathVariable String nome,
            @PathVariable String sobrenome
    ){
        try {
             pacienteService.deletar(nome, sobrenome);

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

}
