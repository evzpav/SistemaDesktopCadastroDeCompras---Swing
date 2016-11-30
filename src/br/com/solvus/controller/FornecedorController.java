package br.com.solvus.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.com.solvus.model.ConnectionPool;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.FornecedorDao;
import br.com.solvus.model.Produto;
import br.com.solvus.model.ProdutoDao;
import br.com.solvus.viewSwing.fornecedor.ConteudoFornecedorPanel;
import br.com.solvus.viewSwing.util.MainFrame;
import br.com.solvus.viewSwing.util.ValidationError;

public class FornecedorController {

	private FornecedorDao dao;
	Connection con;

	public FornecedorController() {
		this.con = ConnectionPool.CONNECTIONPOOL.getConnection();
		dao = new FornecedorDao(con);
	}

	public void save(Fornecedor fornecedor) throws SQLException {
		dao.save(fornecedor);
	}

	public void update(Fornecedor fornecedor) throws SQLException {
		dao.deleteRelationship(fornecedor.getId());
		List<Produto> produtosSelecionadosLista = fornecedor.getListagemProdutos();
		for (Produto produto : produtosSelecionadosLista) {
			dao.saveRelationship(fornecedor, produto);
			dao.update(fornecedor);
		}
	}

	public Fornecedor findById(Integer idFornecedor) throws SQLException {
		return dao.findById(idFornecedor);
	}

	public List<Fornecedor> listFornecedor() throws SQLException {
		return dao.listWithRelationship();
	}

	public void deleteById(int idFornecedor) throws SQLException {
		dao.deleteRelationship(idFornecedor);
		dao.deleteById(idFornecedor);
	}

	public ValidationError validateInputEntry(String inputName, String inputDate, boolean isEditing,
			List<Produto> listSelected) throws SQLException {

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

		Date dataConvertida = convertStringToDate(inputDate);
		if (dataConvertida == null) {

			validation.setValid(false);
			validation.setMsg("Data inválida");
		}

		if (listSelected.isEmpty()) {

			validation.setValid(false);
			validation.setMsg("Nenhum produto selecionado");
		}

		return validation;
	}

	public Date convertStringToDate(String inputStringDate) {
		Date convertedDate = null;

		try {
			DateFormat formatter = null;

			formatter = new SimpleDateFormat("dd/MM/yyyy");
			convertedDate = (Date) formatter.parse(inputStringDate);
		} catch (ParseException parse) {
			convertedDate = null;

		}

		return convertedDate;
	}

	public String convertDateToString(Date dataValorCampo) {
		String convertedDate = null;

		try {

			DateFormat formatter = null;
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			convertedDate = (String) formatter.format(dataValorCampo);

		} catch (Exception parse) {
			convertedDate = null;
		}
		return convertedDate;
	}

}
