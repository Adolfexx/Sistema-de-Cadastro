package com.biopark.alunosapp.ModelAluno;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Aluno {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Informe um e-mail válido")
    private String email;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;

    @NotNull(message = "O período é obrigatório")
    @Min(value = 1, message = "O período mínimo é 1")
    @Max(value = 10, message = "O período máximo é 10")
    private Integer periodo;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "Deve ser uma data no passado")
    private LocalDate dataNascimento;

    private LocalDate dataMatricula = LocalDate.now();
    private boolean ativo = true;

    public Aluno() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public Integer getPeriodo() { return periodo; }
    public void setPeriodo(Integer periodo) { this.periodo = periodo; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
