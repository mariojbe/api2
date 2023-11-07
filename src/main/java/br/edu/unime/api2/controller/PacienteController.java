package br.edu.unime.api2.controller;

import br.edu.unime.api2.controller.exceptions.StandardError;
import br.edu.unime.api2.entity.Paciente;
import br.edu.unime.api2.service.PacienteService;
import br.edu.unime.api2.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Instant;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> obterTodos() {
        return ResponseEntity.ok().body(pacienteService.obterTodos());
    }

    @GetMapping("/obter/{id}")
    public ResponseEntity<Paciente> obterVacinaPorId(@PathVariable String id) {
        Optional<Paciente> paciente = pacienteService.findById(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(paciente.get());
    }

    @GetMapping("/obter/uf/{estado}")
    public List<Paciente> obterPorEstadoPaciente(@PathVariable String estado) {
        List<Paciente> paciente = pacienteService.findByEstado(estado);

        if (paciente.isEmpty()) {
            return (List<Paciente>) ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(paciente).getBody();
    }

    @GetMapping("/{nome}/{sobrenome}")
    public ResponseEntity<?> obterPeloNome(@PathVariable String nome, @PathVariable String sobrenome) {
        try {
            Paciente paciente = pacienteService.obterPeloNomeESobrenome(nome, sobrenome);

            return ResponseEntity.ok().body(paciente);
        } catch (Exception e) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Paciente> inserir(@RequestBody @Valid Paciente paciente) {
        if (pacienteService.inserir(paciente) == null) {
            return ResponseEntity.badRequest().body(paciente);
        }
        return ResponseEntity.created(null).body(paciente);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Paciente> atualizarPorId(@RequestBody Paciente novosDadosDoPaciente,
                                                   @PathVariable String id) {
        Optional<Paciente> paciente = pacienteService.findById(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Paciente responseVacina = pacienteService.atualizarPorId(id, novosDadosDoPaciente);
        return ResponseEntity.ok().body(responseVacina);
    }

    @PutMapping("/{nome}/{sobrenome}")
    public ResponseEntity<?> atualizar(@PathVariable String nome, @PathVariable String sobrenome,
                                       @RequestBody Paciente paciente) {
        try {
            Paciente pacienteAtualizado = pacienteService.atualizar(nome, sobrenome, paciente);

            return ResponseEntity.ok().body(pacienteAtualizado);
        } catch (Exception e) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @DeleteMapping("/excluir/{nome}/{sobrenome}")
    public ResponseEntity<?> excluir(@PathVariable String nome, @PathVariable String sobrenome) {
        try {
            pacienteService.deletar(nome, sobrenome);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Paciente> remover(@PathVariable String id) {
        Optional<Paciente> paciente = pacienteService.findById(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        pacienteService.remove(id);

        return ResponseEntity.ok().body(null);
    }

}
