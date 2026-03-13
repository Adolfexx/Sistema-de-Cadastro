package com.biopark.alunosapp.RepositorioAluno;

import com.biopark.alunosapp.ModelAluno.Aluno;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AlunoRepository {

    private final Map<Long, Aluno> alunos = new LinkedHashMap<>();
    private Long nextId = 1L;

    public Aluno save(Aluno aluno) {
        if (aluno.getId() == null) {
            aluno.setId(nextId++);  // gera ID automático
        }
        alunos.put(aluno.getId(), aluno);
        return aluno;
    }

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos.values());
    }

    public Optional<Aluno> findById(Long id) {
        return Optional.ofNullable(alunos.get(id));
    }

    public void deleteById(Long id) {
        alunos.remove(id);
    }
}
