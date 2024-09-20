import java.util.ArrayList;
import java.util.List;

public class Turmas {
	private Disciplina disciplina;
	private Professor professor;
	private List<Aluno> alunos;
	
	public Turmas(Disciplina disciplina) {
		this.disciplina = disciplina;
		professor = null;
		alunos = new ArrayList<>();
	}
	
	public void alunoAdd(Aluno aluno) {
		alunos.add(aluno);
		aluno.addDisciplina(disciplina);
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	       
	public void removeProfessor() {
		professor = null;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public Boolean removeAluno(Aluno aluno) {
		if (alunos.contains(aluno)) {
			alunos.remove(aluno);
			return true;
		}
		return false;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public List<Aluno> getAlunos(){
		return alunos;
	}
	
	public String ListarAlunos() {
		return alunos.toString();
	}
	
	public String toString() {
		return "Professor:" + professor.getProfessor() + " Disciplina:" + disciplina.getNomeDisciplina() + " Alunos: " + this.ListarAlunos();
	}
}
