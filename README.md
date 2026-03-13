# Sistema de Cadastro de Alunos

Aplicação web de cadastro de alunos desenvolvida com Spring Boot e Thymeleaf, como parte da Atividade 2 do 3º Semestre de Engenharia de Software.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 4.0.3
- Thymeleaf
- Maven
- Bean Validation (Jakarta Validation)

## Funcionalidades

- Cadastro, edição e exclusão de alunos
- Validação de formulários com mensagens de erro (nome, e-mail, curso, período e data de nascimento)
- Filtro de alunos por status: Todos, Ativos e Inativos
- Contador de alunos no topo da lista
- Ativar e desativar alunos sem excluí-los

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- Java 21 ou superior
- Maven (ou utilize o wrapper `./mvnw` já incluído no projeto)

## Como Executar

Clone o repositório e acesse a pasta do projeto:

```bash
git clone <URL_DO_REPOSITORIO>
cd cadastro
```

Em seguida, execute a aplicação com o Maven Wrapper:

```bash
# No Linux/Mac
./mvnw spring-boot:run

# No Windows
mvnw.cmd spring-boot:run
```

A aplicação será iniciada na porta **8081**. Acesse pelo navegador:

```
http://localhost:8081/alunos
```

> **Atenção:** a porta é 8081 para não conflitar com a Atividade 1 (que roda na 8080).

## Estrutura do Projeto

```
com.biopark.alunosapp/
├── CadastroApplication.java         # Classe principal — ponto de entrada da aplicação
├── ControleAluno/
│   └── AlunoControle.java           # Controller — recebe as requisições e retorna as views
├── ServiceAluno/
│   └── AlunoService.java            # Service — regras de negócio e filtros
├── RepositorioAluno/
│   └── AlunoRepository.java         # Repository — armazenamento em memória
└── ModelAluno/
    └── Aluno.java                   # Model — representa os dados de um aluno
```

## URLs Disponíveis

| URL | Método | Descrição |
|-----|--------|-----------|
| `/alunos` | GET | Listar todos os alunos |
| `/alunos?filtro=ativos` | GET | Listar apenas alunos ativos |
| `/alunos?filtro=inativos` | GET | Listar apenas alunos inativos |
| `/alunos/novo` | GET | Formulário para cadastrar novo aluno |
| `/alunos/editar/{id}` | GET | Formulário para editar um aluno existente |
| `/alunos/salvar` | POST | Salvar aluno (criação ou edição) |
| `/alunos/excluir/{id}` | POST | Excluir aluno |
| `/alunos/status/{id}` | GET | Alternar status entre Ativo e Inativo |

## Campos do Aluno

| Campo | Validação |
|-------|-----------|
| Nome | Obrigatório, entre 3 e 100 caracteres |
| E-mail | Obrigatório, formato válido |
| Curso | Obrigatório |
| Período | Obrigatório, entre 1 e 10 |
| Data de Nascimento | Obrigatória, deve ser uma data no passado |
