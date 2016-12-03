package br.com.solvus.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.solvus.model.Compra;
import br.com.solvus.model.CompraDao;
import br.com.solvus.model.ConnectionPool;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.ItemDeCompra;
import br.com.solvus.model.ItemDeCompraDao;
import br.com.solvus.viewSwing.util.ValidationError;

public class CompraController {

	private CompraDao dao;
	private FornecedorController fornecedorController;
	private ItemDeCompraDao daoItemdecompra;
	Connection con;

	public CompraController() {
		this.con = ConnectionPool.CONNECTIONPOOL.getConnection();
		dao = new CompraDao(con);
		daoItemdecompra = new ItemDeCompraDao(con);
		fornecedorController = new FornecedorController();
		
	}

	public void salvarCompra(Fornecedor fornecedor, String inputDate, ItemDeCompra itemdecompra) throws SQLException {
		ValidationError validation = null;
		validation = validateInputEntry(inputDate);
		if (validation.isValid()) {
			Date convertedDate = convertStringToDate(inputDate);
			Compra compra = new Compra(fornecedor, convertedDate);
			save(compra);
			daoItemdecompra.saveRelationship(itemdecompra, compra);
		} else {
			validation.getMsg();
		}
	}
	
	
	public void save(Compra compra) throws SQLException {
		dao.save(compra);
	}

	public void update(Compra compra) throws SQLException {
		dao.deleteRelationship(compra.getId());
		List<ItemDeCompra> listaItemDeCompra = compra.getListaDeItemDeCompra();
		for (ItemDeCompra itemdecompra1 : listaItemDeCompra) {
			daoItemdecompra.saveRelationship(itemdecompra1, compra);
			dao.update(compra);
		}
	}

	public Compra findById(Integer idCompra) throws SQLException {
		return dao.findById(idCompra);
	}

	public List<Compra> list() throws SQLException {
		return dao.list();
		}
	
	public List<Fornecedor> listFornecedor() throws SQLException {
		return fornecedorController.listFornecedor();
	}

	public void deleteById(int idCompra) throws SQLException {
		dao.deleteRelationship(idCompra);
		dao.deleteById(idCompra);
		
	}

	public ValidationError validateInputEntry(String inputDate) throws SQLException {

		ValidationError validation = new ValidationError();
		validation.setValid(true);

		Date dataConvertida = convertStringToDate(inputDate);
		if (dataConvertida == null) {

			validation.setValid(false);
			validation.setMsg("Data inválida");
		}

		// if (!isEditing) {
		// boolean nomeDuplicado = dao.checkIfDuplicate(inputName);
		// if (nomeDuplicado) {
		// validation.setValid(false);
		// validation.setMsg("O nome está duplicado");
		// }
		// }

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
