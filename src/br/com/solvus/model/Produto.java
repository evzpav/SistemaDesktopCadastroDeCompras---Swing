package br.com.solvus.model;

public class Produto {

	private String nome;
	private int id;

	public Produto(String nome) {
		this.nome = nome;
	
	}

	public String getNome() {
		return nome;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
