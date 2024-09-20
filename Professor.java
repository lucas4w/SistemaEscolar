
public class Professor {
	private String nome;
	private int identificador;
	private Disciplina disciplina;
	
	public Professor(String nome, int identificador, Disciplina disciplina) throws CadastroInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new CadastroInvalidoException("Nome ínvalido");
	 	}
		if (identificador <= 0) {
			throw new CadastroInvalidoException("Identificador deve ser maior que zero");
		}
		this.nome = nome;
		this.identificador = identificador;
		this.disciplina = disciplina;
	}
	
	public String getProfessor() {
		return nome;
	}
	
	public int getID() {
		return identificador;
	}
	
	public void dar_nota(Aluno aluno, double nota, int bimestre_atual) throws NotaInvalidaException {
		if (nota < 0 || nota > 10.0) {
			throw new NotaInvalidaException("A nota precisa estar entre 0 a 10");
		}
		if (bimestre_atual > 3 || bimestre_atual < 0) {
			throw new NotaInvalidaException("Valor de bimestre inválido. Tente 1, 2 ou 3 (se for a nota da prova final)");
		}
		//Soma i em 4 para olhar apenas os códigos das disciplinas no array de notas
		//Caso o código seja achado, atribui a nota ao aluno
		for (int i = 0; i < aluno.getNotas().length; i+=4) {
			if (aluno.getNotas()[i] == this.disciplina.getCodigo()) {
				aluno.getNotas()[i+bimestre_atual] = nota;
				return;
			}
		}
		throw new NotaInvalidaException("O aluno não está nessa disciplina.");
	}
	
	
	
	public Boolean mediaCalc( Aluno aluno) throws NotaInvalidaException {
		double nota1 = aluno.getNota(disciplina, 1);
		double nota2 = aluno.getNota(disciplina, 2);
		double nota_provaFinal = aluno.getNota(disciplina, 3);
		if (nota1 == -1 || nota2 == -1)
			throw new NotaInvalidaException("Não há notas suficientes para calcular");
		double media_semestral = (nota1 + nota2)/2;
		if (nota_provaFinal == -1) { //Se não houver nota da prova final, não inclui-la no cálculo
			if (media_semestral >= 60) { 
				aluno.getDisciplinas().replace(disciplina,true); //Aluno aprovado, retorna true
				return true;									 //E muda o status de aprovação no hashmap para true
			}
			else return false; 
		}
		else { //Recalcula a media incluindo a prova final
			double media_final = (media_semestral + nota_provaFinal)/2;
			aluno.getDisciplinas().replace(disciplina, media_final>=60);
			return media_final>=60;
		}
		
	}
	 
	@Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false; //
        Professor professor = (Professor) o; 
        return identificador == professor.identificador; 
}
