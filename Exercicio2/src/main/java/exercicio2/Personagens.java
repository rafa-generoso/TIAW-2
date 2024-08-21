package exercicio2;

public class Personagens {
	private int id;
	private String nome;
	private int idade;
	private char sexo;
	private String especie;
	
	public Personagens() {
		this.id = -1;
		this.nome = "";
		this.idade = -1;
		this.sexo = '*';
		this.especie = "";
	}
	
	public Personagens(int id, String nome, int idade, char sexo, String especie) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.especie = especie;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void serIdade(int idade) {
		this.idade = idade;
	}


	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	@Override
	public String toString() {
		return "Personagem [id=" + id + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", especie=" + especie + "]";
	}	
}
