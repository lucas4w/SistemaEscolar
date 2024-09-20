import java.util.ArrayList;
import java.util.List;

public class Repositorio {
	public List<Aluno> alunos_total;
	public List<Disciplina> disciplinas_total;
	public List<Turmas> turmas_total;
	public List<Professor> professores_total;
	
	public Repositorio() {
		alunos_total = new ArrayList<>();
		disciplinas_total = new ArrayList<>();
		turmas_total = new ArrayList<>();
	 	professores_total = new ArrayList<>();
	}
	
	public void addAluno(Aluno aluno) throws CadastroInvalidoException {
		for (Aluno alunos : alunos_total) 
			if (alunos.equals(aluno)) 
				throw new CadastroInvalidoException("Já existe um aluno com essa matricula");	
		alunos_total.add(aluno);
	}
	
	public void removeAluno(Aluno aluno) {
		alunos_total.remove(aluno);
		for (Turmas turma : turmas_total) 
			turma.getAlunos().remove(aluno);
		
	}
	
	public Aluno getAluno(int matricula) throws CadastroInvalidoException {
		for (Aluno aluno : alunos_total) 
			if (aluno.getMatricula() == matricula)
				return aluno;
		 throw new CadastroInvalidoException("Aluno não encontrado.");  
	}
	
	public void addProfessor(Professor professor) throws CadastroInvalidoException {
		for (Professor professores : professores_total) 
			if (professores.equals(professor)) 
				throw new CadastroInvalidoException("Já existe um professor com esse identificador");
		professores_total.add(professor);
	}
	
	public void removeProfessor(Professor professor) {
		professores_total.remove(professor);
		for (Turmas turma : turmas_total) 
			if (turma.getProfessor() == professor)
				turma.setProfessor(null);
	}
	
	public Professor getProfessor(int id) throws CadastroInvalidoException {
		for (Professor professor : professores_total) 
			if (professor.getID() == id)
				return professor;
		 throw new CadastroInvalidoException("Professor não encontrado.");  
	}
	
	public void addTurma(Turmas turma) {
		turmas_total.add(turma);
	}
	
	public void removeTurma(Turmas turma) {
		turma.setDisciplina(null);
		turma.setProfessor(null);
		turma.getAlunos().clear();
		turmas_total.remove(turma);
	}
	
	public Turmas getTurma(String nome) throws CadastroInvalidoException {
		for (Turmas turma : turmas_total) {
			if (turma.getDisciplina().getNomeDisciplina().equals(nome))
				return turma;
		}
		throw new CadastroInvalidoException("Turma não encontrada."); 
	}
	
	public void addDisciplina(Disciplina disciplina) throws CadastroInvalidoException {
		for (Disciplina disciplinas : disciplinas_total) {
			if (disciplinas.equals(disciplina)) 
				throw new CadastroInvalidoException("Já existe uma disciplina com esse código");
			if (disciplinas.getNomeDisciplina().equals(disciplina.getNomeDisciplina()))
				throw new CadastroInvalidoException("Essa disciplina já foi cadastrada");
		}
		disciplinas_total.add(disciplina);
	}
	
	public void removeDisciplina(Disciplina disciplina) {
		disciplinas_total.remove(disciplina);
		for (Turmas turma : turmas_total) {
			if (turma.getDisciplina() == disciplina) {
				removeTurma(turma);
			}
		}
	}
	
	public Disciplina getDisciplina(String nome) throws CadastroInvalidoException {
		for (Disciplina disciplina : disciplinas_total) 
			if (disciplina.getNomeDisciplina().equals(nome))
				return disciplina;
		 throw new CadastroInvalidoException("Disciplina não encontrada.");  
	}
}
