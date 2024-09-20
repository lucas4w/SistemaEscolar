import java.util.Scanner;

public class SistemaEscolar {
    private static Repositorio repositorio = new Repositorio();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            exibirMenu(1);
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (escolha) {
                case 1:
                    cadastros();
                    break;
                case 2:
                	gerenciar_turma();
                    break;
                case 3:
                	listagens();
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void exibirMenu(int numero) {
    	if (numero == 1) {
	        System.out.println("---- Menu do Sistema Escolar ----");
	        System.out.println("1. Cadastros");
	        System.out.println("2. Gerenciar turmas");
	        System.out.println("3. Listagens");
	        System.out.println("0. Sair");
	        System.out.print("Escolha uma opção: ");
    	}
    	else if (numero == 2) {
    		System.out.println("---- Cadastros ----");
	        System.out.println("1. Cadastrar aluno");
	        System.out.println("2. Cadastrar disciplina");
	        System.out.println("3. Cadastrar turma");
	        System.out.println("4. Cadastrar professor");
	        System.out.println("0. Voltar");
	        System.out.print("Escolha uma opção: ");
    	}
    	else if (numero == 3) {
    		System.out.println("---- Gerenciar Turmas ----");
	        System.out.println("1. Adicionar aluno à turma");
	        System.out.println("2. Dar nota a um aluno");
	        System.out.println("3. Verificar se o aluno foi aprovado");
	        System.out.println("4. Remover aluno");
	        System.out.println("5. Listar alunos");
	        System.out.println("6. Trocar professor");
	        System.out.println("7. Remover professor");
	        System.out.println("8. Trocar disciplina");
	        System.out.println("0. Voltar");
	        System.out.print("Escolha uma opção: ");
    	}
    	else if (numero == 4) {
    		System.out.println("---- Listagens ----");
    		System.out.println("1. Emitir boletim de aluno");
	        System.out.println("2. Exibir alunos cadastrados");
	        System.out.println("3. Exibir professores cadastrados");
	        System.out.println("4. Exibir disciplinas cadastradas");
	        System.out.println("5. Exibir turmas cadastradas");
	        System.out.println("0. Voltar");
	        System.out.print("Escolha uma opção: ");
    	}
    }
    
    private static void cadastros() {
    	exibirMenu(2);
    	int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        switch (escolha) {
            case 1:
            	cadastrarAluno();
                break;
            case 2:
            	cadastrarDisciplina();
                break;
            case 3:
            	cadastrarTurma();
                break;
            case 4:
            	cadastrarProfessor();
            case 0:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }
    
    private static void gerenciar_turma() {
    	exibirMenu(3);
    	int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        switch (escolha) {
            case 1:
            	adicionarAluno();
                break;
            case 2:
            	darNota();
                break;
            case 3:
            	calcMedia();
                break;
            case 4:
            	removerAluno();
                break;
            case 5:
            	listarAlunosdaTurma();
                break;
            case 6:
            	trocarProfessor();
                break;
            case 7:
            	removerProfessor();
            	break;
            case 8:
            	trocarDisciplina();
            	break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }
    
    private static void listagens() {
    	exibirMenu(4);
    	int escolha = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha

        switch (escolha) {
        	case 1:
        		emitirBoletim();
        		break;
            case 2:
            	listarAlunos();
                break;
            case 3:
            	listarDisciplinas();
                break;
            case 4:
            	listarTurmas();
                break;
            case 54:
            	listarProfessores();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }
    
    private static void cadastrarAluno() {
        try {
            System.out.print("Nome do aluno: ");
            String nome = scanner.nextLine();

            System.out.print("Número de matrícula: ");
            int matricula = scanner.nextInt();
            scanner.nextLine();

            Aluno aluno = new Aluno(nome, matricula);
            repositorio.addAluno(aluno);
            System.out.println("Aluno cadastrado com sucesso!");

        } catch (CadastroInvalidoException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
    
    private static void adicionarAluno() {
    	try {
    		System.out.println("0. Cancelar");
    		System.out.print("Número da matrícula do aluno: ");
            int matricula = scanner.nextInt();
            if (matricula == 0) return;
            Aluno aluno = repositorio.getAluno(matricula);
            System.out.print("Nome da disciplina da turma: ");
            scanner.nextLine();
            String nome = scanner.nextLine();
            Turmas turma = repositorio.getTurma(nome);
            turma.alunoAdd(aluno);
            System.out.println("Aluno cadastrado com sucesso!");
    	}
    	catch (CadastroInvalidoException e) {
    		System.out.println("Erro ao adicionar aluno: " + e.getMessage());
    	}
    }

    private static void removerAluno() {
    	try {
	    	System.out.println("0. Cancelar");
			System.out.print("Número da matrícula do aluno: ");
	        int matricula = scanner.nextInt();
	        if (matricula == 0) return;
	        Aluno aluno = repositorio.getAluno(matricula);
	        System.out.print("Nome da disciplina da turma: ");
	        scanner.nextLine();
	        String nome = scanner.nextLine();
	        Turmas turma = repositorio.getTurma(nome);
	        if(turma.removeAluno(aluno)) {
	        	System.out.println("Aluno removido com sucesso!");
	        	return;
	        }
	        else System.out.println("Aluno não encontrado na turma");
	        return;
    	}
    	catch (CadastroInvalidoException e) {
    		System.out.println("Erro ao remover aluno: " + e.getMessage());
    	}
    }
    
    private static void listarAlunosdaTurma() {
    	try {
        System.out.println("---- Lista de Alunos ----");
        System.out.print("Nome da disciplina da turma: ");
		String nome = scanner.nextLine();
		Turmas turma = repositorio.getTurma(nome);
		System.out.print(turma.ListarAlunos() + " ");
		System.out.println('\n');
    	}
    	catch (CadastroInvalidoException e) {
    		System.out.println("Erro ao listar alunos: " + e.getMessage());
    	}
    }
    
    private static void emitirBoletim() {
    	try {
    		System.out.println("0. Cancelar");
    		System.out.print("Número da matrícula do aluno: ");
            int matricula = scanner.nextInt();
            if (matricula == 0) return;
            Aluno aluno = repositorio.getAluno(matricula);
            aluno.emitirBoletim();
            
    	}
    	catch (CadastroInvalidoException e) {
    		System.out.println("Erro ao emitir boletim: " + e.getMessage());
    	}
    }
    
    private static void listarAlunos() {
    	System.out.println("---- Lista de Alunos ----");
    	System.out.println(repositorio.alunos_total);
    }

    private static void cadastrarProfessor() {
        try {
            System.out.print("Nome do professor: ");
            String nome = scanner.nextLine();

            System.out.print("ID do professor: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            System.out.print("Disciplina que ele leciona (nome): ");
            String nome_disciplina = scanner.nextLine();
            
            Disciplina disciplina = repositorio.getDisciplina(nome_disciplina);
            
            Professor professor = new Professor(nome, id, disciplina);
            repositorio.addProfessor(professor);
            System.out.println("Professor cadastrado com sucesso!");
            
        } catch (CadastroInvalidoException e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }
    
    private static void darNota() {
    	try {
    		System.out.print("ID do professor : ");
            int id = scanner.nextInt();
            Professor professor = repositorio.getProfessor(id);
            System.out.print("Número da matrícula do aluno: ");
	        int matricula = scanner.nextInt();
	        scanner.nextLine();
	        Aluno aluno = repositorio.getAluno(matricula);
	        System.out.print("Bimestre atual: ");
	        int bimestre_atual = scanner.nextInt();
	        scanner.nextLine();
	        System.out.print("Nota do aluno: ");
	        double nota = scanner.nextDouble();
	        professor.dar_nota(aluno, nota, bimestre_atual);
	        System.out.println("Nota cadastrada com sucesso!");
    	}
    	catch (NotaInvalidaException | CadastroInvalidoException e) {
    		System.out.println("Erro ao cadastrar nota: " + e.getMessage());
    	}
    }
    private static void calcMedia() {
    	try {
    		System.out.print("ID do professor : ");
            int id = scanner.nextInt();
            Professor professor = repositorio.getProfessor(id);
            System.out.print("Número da matrícula do aluno: ");
            int matricula = scanner.nextInt();
            scanner.nextLine();
	        Aluno aluno = repositorio.getAluno(matricula);
	        if (professor.mediaCalc(aluno)) {
	        	System.out.println("O aluno foi aprovado!");
	        	return;
	        }
	        System.out.println("Aluno ficou em recuperação!");
	        System.out.print("Nota da prova final: ");
	        double nota_prova = scanner.nextDouble();
	        professor.dar_nota(aluno, nota_prova, 3);
	        if (professor.mediaCalc(aluno)){
	        	System.out.println("O aluno foi aprovado!");
	        	return;
	        }
	        System.out.println("O aluno foi reprovado.");      
    	}
    	catch (NotaInvalidaException | CadastroInvalidoException e) {
    		System.out.println("Erro ao calcular média: " + e.getMessage());
    	}
    }
    
    private static void trocarProfessor() {
    	try {
    		System.out.print("ID do professor: ");
            int id = scanner.nextInt();
            Professor professor = repositorio.getProfessor(id);
            System.out.print("Nome da disciplina da turma: ");
	        scanner.nextLine();
	        String nome = scanner.nextLine();
	        Turmas turma = repositorio.getTurma(nome);
	        turma.setProfessor(professor);
	        System.out.println("Professor adicionado com sucesso!");
    	}
    	 catch (CadastroInvalidoException e) {
             System.out.println("Erro ao adicionar professor: " + e.getMessage());
         }
    }
    
    private static void removerProfessor() {
    	try {
    		System.out.print("Nome da disciplina da turma: ");
    		String nome = scanner.nextLine();
    		Turmas turma = repositorio.getTurma(nome);
    		turma.removeProfessor();
    		System.out.println("Professor removido com sucesso!");
    	}
    	catch (CadastroInvalidoException e) {
            System.out.println("Erro ao remover professor: " + e.getMessage());
        }
    }
    
    private static void listarProfessores() {
    	System.out.println("---- Lista de Professores ----");
    	for (Professor professor : repositorio.professores_total) {
        	System.out.print(professor.getProfessor() + " ");
        }
    	System.out.println("");
    }
    
    private static void cadastrarTurma() {
    	try {
    		System.out.print("Disciplina da turma (nome): ");
            String nome = scanner.nextLine();
            Disciplina disciplina = repositorio.getDisciplina(nome);
            System.out.print("ID do professor: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Professor professor = repositorio.getProfessor(id);
            Turmas turma = new Turmas(disciplina);
            turma.setProfessor(professor);
            repositorio.addTurma(turma);
            System.out.println("Turma cadastrada com sucesso!");
            
    	}
    	catch (CadastroInvalidoException e) {
    		System.out.println("Erro ao cadastrar turma: " + e.getMessage());
    	}
    }
    
    private static void listarTurmas() {
    	System.out.println("---- Lista de Turmas ----");
    	for (Turmas turma : repositorio.turmas_total) {
        	System.out.print(turma.getDisciplina() + " ");
        }
    	System.out.println("");
    }

    private static void cadastrarDisciplina() {
        try {
            System.out.print("Nome da disciplina: ");
            String nome = scanner.nextLine();

            System.out.print("Código da disciplina: ");
            double codigo = scanner.nextDouble();
            scanner.nextLine(); // Consumir nova linha

            Disciplina disciplina = new Disciplina(nome, codigo);
            repositorio.addDisciplina(disciplina);
            System.out.println("Disciplina cadastrada com sucesso!");

        } catch (CadastroInvalidoException e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }
    
    private static void trocarDisciplina() {
    	try {
    		System.out.print("Nome da nova disciplina: ");
            String nome = scanner.nextLine();
            Disciplina disciplina = repositorio.getDisciplina(nome);
            System.out.print("Nome da atual disciplina da turma: ");
	        scanner.nextLine();
	        String nome_antiga = scanner.nextLine();
	        Turmas turma = repositorio.getTurma(nome_antiga);
	        turma.setDisciplina(disciplina);
	        System.out.println("Disciplina trocada com sucesso!");
    	}
    	catch (CadastroInvalidoException e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }

    private static void listarDisciplinas() {
        System.out.println("---- Lista de Disciplinas ----");
        for (Disciplina disciplina : repositorio.disciplinas_total) {
        	System.out.print(disciplina.getNomeDisciplina() + " ");
        }
        System.out.println("");
    }
}
