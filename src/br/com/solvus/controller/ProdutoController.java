package br.com.solvus.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.solvus.model.ConnectionPool;
import br.com.solvus.model.Produto;
import br.com.solvus.model.ProdutoDao;
import br.com.solvus.viewSwing.util.ValidationError;

public class ProdutoController {

	private ProdutoDao dao;
	Connection con;

	public ProdutoController() {
		this.con = ConnectionPool.CONNECTIONPOOL.getConnection();
		dao = new ProdutoDao(con);
	}
	
	

	public void save(Produto produto) throws SQLException {
		dao.save(produto);
	}

	public void update(Produto produto) throws SQLException {
		dao.update(produto);
	}

	public Produto findById(Integer idProduto) throws SQLException {
		return dao.findById(idProduto);
	}

	public List<Produto> list() throws SQLException {
		List<Produto> listaProdutos = new ArrayList<Produto>();
		listaProdutos = dao.list();

		return listaProdutos;
	}

	public void deleteById(int idProduto) throws SQLException {
		dao.deleteById(idProduto);
	}

	public boolean hasRelationship(int idProduto) throws SQLException {

		return dao.hasRelationship(idProduto);
	}

	public ValidationError validateInputEntry(String inputName, boolean isEditing) throws SQLException {
		ValidationError validation = new ValidationError();
		validation.setValid(true);

		if (inputName.isEmpty()) {
			validation.setValid(false);
			validation.setMsg("O nome está em branco");

		}

		if (!isEditing) {
			boolean nomeDuplicado = dao.checkIfDuplicate(inputName);
			if (nomeDuplicado) {
				validation.setValid(false);
				validation.setMsg("O nome está duplicado");

			}
		}
		return validation;
	}

}
