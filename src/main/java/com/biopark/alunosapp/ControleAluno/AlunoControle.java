package com.biopark.alunosapp.ControleAluno;

import com.biopark.alunosapp.ModelAluno.Aluno;
import com.biopark.alunosapp.ServiceAluno.AlunoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/alunos")
public class AlunoControle {

    private final AlunoService alunoService;

    public AlunoControle(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String filtro, Model model) {
        List<Aluno> alunos = switch (filtro == null ? "todos" : filtro) {
            case "ativos"   -> alunoService.listarAtivos();
            case "inativos" -> alunoService.listarInativos();
            default         -> alunoService.listarTodos();
        };

        List<Aluno> todos = alunoService.listarTodos();
        long totalAtivos   = todos.stream().filter(Aluno::isAtivo).count();
        long totalInativos = todos.stream().filter(a -> !a.isAtivo()).count();

        model.addAttribute("alunos",        alunos);
        model.addAttribute("filtroAtivo",   filtro == null ? "todos" : filtro);
        model.addAttribute("totalAlunos",   todos.size());
        model.addAttribute("totalAtivos",   totalAtivos);
        model.addAttribute("totalInativos", totalInativos);

        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Long id, Model model, RedirectAttributes ra) {
        return alunoService.buscarPorId(id)
                .map(aluno -> {
                    model.addAttribute("aluno", aluno);
                    return "alunos/formulario";
                })
                .orElseGet(() -> {
                    ra.addFlashAttribute("mensagemErro", "Aluno não encontrado!");
                    return "redirect:/alunos";
                });
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes ra) {
        if (result.hasErrors()) {
            return "alunos/formulario";
        }
        if (aluno.getId() != null) {
            alunoService.buscarPorId(aluno.getId()).ifPresent(existente ->
                    aluno.setDataMatricula(existente.getDataMatricula())
            );
        }
        alunoService.salvar(aluno);
        ra.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
        return "redirect:/alunos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        alunoService.excluir(id);
        ra.addFlashAttribute("mensagem", "Aluno excluído!");
        return "redirect:/alunos";
    }

    @GetMapping("/status/{id}")
    public String alternarStatus(@PathVariable Long id, RedirectAttributes ra) {
        alunoService.alternarStatus(id);
        ra.addFlashAttribute("mensagem", "Status alterado!");
        return "redirect:/alunos";
    }
}
