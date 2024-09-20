
public class Disciplina {
	private String nome;
	private double codigo;
	
	public Disciplina(String nome, double codigo) throws CadastroInvalidoException {
		if (nome == null || nome.trim().isEmpty()) {
			throw new CadastroInvalidoException("Nome ínvalido");
	 	}
		if (codigo <= 0) {
			throw new CadastroInvalidoException("Código deve ser maior que zero");
		}
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public String getNomeDisciplina() {
		return nome;
	}

	public double getCodigo() {
		return codigo;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true; // Verifica se são o mesmo objeto
        if (o == null || getClass() != o.getClass()) return false; // Verifica se são da mesma classe
        Disciplina disciplina = (Disciplina) o; // Converte o objeto
        return codigo == disciplina.codigo; // Compara pelo identificador
    }
}

