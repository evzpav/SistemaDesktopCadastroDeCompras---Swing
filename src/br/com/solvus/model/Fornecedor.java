package br.com.solvus.model;

import java.util.Date;
import java.util.List;

public class Fornecedor {

	private  String nome;
	private  Date dataContrato;	
	private int id;
	private List <Produto> listagemProdutos;

	
	
	public Fornecedor(String nome, Date dataContrato) {
		this.nome = nome;
		this.dataContrato = dataContrato;
	
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public Date getDataContrato() {
		return dataContrato;
	}

	public List<Produto> getListagemProdutos() {
		return listagemProdutos;
	}

	public void setListagemProdutos(List<Produto> listagemProdutos) {
		this.listagemProdutos = listagemProdutos;
	}

	public int getId() {
		return id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}
	
	
	
	

}
