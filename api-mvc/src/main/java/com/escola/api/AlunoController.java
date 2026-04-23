package com.escola.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "*", maxAge = 3600) // Backup para desenvolvimento
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        Aluno salvo = service.salvar(aluno);
        return ResponseEntity.status(201).body(salvo); // HTTP 201 Created
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id) {
        Aluno aluno = service.buscarPorId(id);

        if (aluno == null) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }

        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
            @PathVariable Long id,
            @RequestBody Aluno novoAluno) {

        Aluno alunoExistente = service.buscarPorId(id);

        if (alunoExistente == null) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }

        alunoExistente.setNome(novoAluno.getNome());
        alunoExistente.setEmail(novoAluno.getEmail());
        alunoExistente.setTelefone(novoAluno.getTelefone());

        Aluno atualizado = service.salvar(alunoExistente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build(); // HTTP 404
        }
        service.deletar(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}


















//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
////@CrossOrigin(origins = "*")
//@RequestMapping("/alunos")
//public class AlunoController {
//
//    private final AlunoService service;
//
//    public AlunoController(AlunoService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<Aluno> listar() {
//        return service.listarTodos();
//    }
//
//    @PostMapping
//    public Aluno criar(@RequestBody Aluno aluno) {
//        return service.salvar(aluno);
//    }
//
//    @GetMapping("/{id}")
//    public Aluno buscar(@PathVariable Long id) {
//        return service.buscarPorId(id);
//    }
//
//    @PutMapping("/{id}")
//    public Aluno atualizar(
//            @PathVariable Long id,
//            @RequestBody Aluno novoAluno) {
//
//        Aluno aluno = service.buscarPorId(id);
//
//        if (aluno == null) {
//            return null;
//        }
//
//        aluno.setNome(novoAluno.getNome());
//        aluno.setEmail(novoAluno.getEmail());
//        aluno.setTelefone(novoAluno.getTelefone());
//
//        return service.salvar(aluno);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deletar(@PathVariable Long id) {
//        service.deletar(id);
//    }
//}