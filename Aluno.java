import  java.util.HashMap;

public class Aluno {
	private String nome;
	private int matricula;
	private HashMap<Disciplina,Boolean> disciplinas;
	private double[] notas; 
	
	public Aluno(String nome, int matricula) throws CadastroInvalidoException{
		if (nome == null || nome.trim().isEmpty()) {
			throw new CadastroInvalidoException("Nome ínvalido");
	 	}
		if (matricula <= 0) {
			throw new CadastroInvalidoException("Número de matricula deve ser maior que zero");
		}
		this.nome = nome;
		this.matricula = matricula;
		this.disciplinas = new HashMap<>();
		this.notas = new double[100];
	} 
	//As notas do aluno estão armazenadas no array [notas]
	//A forma que as notas estão dispostas no array é a seguinte: 
	//[CódigoDaDisciplina1, Nota1, Nota2, NotaProvaFinal,  CódigoDaDisciplina2, Nota1, Nota2, NotaProvaFinal...]
	//Esse padrão é seguido para cada disciplina adicionada ao array
	
	//Essa forma foi mais fácil pra mim, apesar de ser menos eficiente
	
	//Ao adicionar uma disciplina, armazena o código da disciplina e -1 nos espaços da nota dessa disciplina
	//Multiplicar o tamanho da lista por 4 dá o index certo para uma nova disciplina, já que cada disciplina ocupa 4 espaços na memória
	public void addDisciplina(Disciplina disciplina) {
		notas[disciplinas.size()*4] = disciplina.getCodigo();
		notas[disciplinas.size()*4+1] = -1;
		notas[disciplinas.size()*4+2] = -1;
		notas[disciplinas.size()*4+3] = -1;
		disciplinas.put(disciplina,null);
	}
	
	public int getMatricula() {
		return matricula;
	}

	public double[] getNotas(){ 
		return notas;
	}
	
	public HashMap<Disciplina,Boolean> getDisciplinas(){
		return disciplinas;
	}
	
	//Busca o código da matéria dada no parâmetro e retorna a nota da máteria somando o index + o bimestre da nota procurada
	//Se procurar a nota final, o bimestre deve ser 3
	public double getNota(Disciplina disciplina, int bimestre_atual) {
		for (int i = 0; i < disciplinas.size()*4;i+=4) {
			if (notas[i] == disciplina.getCodigo()) {
				 return notas[i+bimestre_atual];
				 
			}
		}
		return -1;
	}
	
	public String notaToString(double nota) {
		if (nota == -1)
			return "_";
		return Double.toString(nota);
	}
	
	public void emitirBoletim() {
		System.out.println("--- BOLETIM ----");
		System.out.println("Nome completo:" + nome +" Matricula:" + matricula);
		//Para cada disciplina, imprime cada uma das notas. caso o aluno não possua uma nota válida (-1), imprime "_"
		disciplinas.forEach((disciplina,status) -> {
			System.out.print(disciplina.getNomeDisciplina()+ ": " + "Nota1 "+ notaToString(getNota(disciplina,1)) + " Nota2 " + notaToString(getNota(disciplina,2)) 
			+ " Nota da prova final:" + notaToString(getNota(disciplina,3)) + " Aprovado:" + disciplinas.get(disciplina) + '\n');});	
	}
	
	public String toString() {
		return "nome:" + nome + " matricula:" + matricula;
	}
	
	// Sobrescrevendo equals() para comparar pela matrícula
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se são o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // Verifica se são da mesma classe
        Aluno aluno = (Aluno) o; // Converte o objeto
        return matricula == aluno.matricula; // Compara pela matrícula
    }

    // Sobrescrevendo hashCode() para ser consistente com equals()
    @Override
    public int hashCode() {
        return Integer.hashCode(matricula); // Usa a matrícula para gerar o hash
    }
}
