package br.com.solvus.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.ls.LSInput;

import br.com.solvus.model.ConnectionPool;
import br.com.solvus.model.Produto;
import br.com.solvus.model.ProdutoDao;
import br.com.solvus.viewSwing.util.ButtonEditor;
import br.com.solvus.viewSwing.util.ButtonRenderer;
import br.com.solvus.viewSwing.util.MainFrame;
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
