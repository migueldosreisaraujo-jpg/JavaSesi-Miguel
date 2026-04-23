package com.escola.api;


import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno salvar(Aluno aluno) {
        // Validação simples
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        return repository.save(aluno);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // ✅ Método útil para o frontend
    public boolean existePorId(Long id) {
        return repository.existsById(id);
    }
}



// Projeto Old
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AlunoService {
//
//    private final AlunoRepository repository;
//
//    public AlunoService(AlunoRepository repository) {
//        this.repository = repository;
//    }
//
//    public List<Aluno> listarTodos() {
//        return repository.findAll();
//    }
//
//    public Aluno salvar(Aluno aluno) {
//        return repository.save(aluno);
//    }
//
//    public void deletar(Long id) {
//        repository.deleteById(id);
//    }
//
//    public Aluno buscarPorId(Long id) {
//        return repository.findById(id).orElse(null);
//    }
//}