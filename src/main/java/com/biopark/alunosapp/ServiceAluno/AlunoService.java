package com.biopark.alunosapp.ServiceAluno;

import com.biopark.alunosapp.ModelAluno.Aluno;
import com.biopark.alunosapp.RepositorioAluno.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public List<Aluno> listarAtivos() {
        return alunoRepository.findAll().stream()
                .filter(Aluno::isAtivo).toList();
    }

    public List<Aluno> listarInativos() {
        return alunoRepository.findAll().stream()
                .filter(a -> !a.isAtivo()).toList();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void excluir(Long id) {
        alunoRepository.deleteById(id);
    }

    public void alternarStatus(Long id) {
        alunoRepository.findById(id).ifPresent(aluno -> {
            aluno.setAtivo(!aluno.isAtivo());
            alunoRepository.save(aluno);
        });
    }
}
