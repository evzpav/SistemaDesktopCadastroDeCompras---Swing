package br.com.solvus.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.solvus.model.Compra;
import br.com.solvus.model.CompraDao;
import br.com.solvus.model.ConnectionPool;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.ItemDeCompra;
import br.com.solvus.model.ItemDeCompraDao;
import br.com.solvus.viewSwing.util.ValidationError;
import br.com.solvus.viewSwing.util.ValidationException;

public class CompraController {

	private CompraDao dao;
	private FornecedorController fornecedorController;
	private ItemDeCompraController itemDeCompraController;
	private boolean salvoComSucesso;
	Connection con;

	public CompraController() {
		this.con = ConnectionPool.CONNECTIONPOOL.getConnection();
		dao = new CompraDao(con);
		new ItemDeCompraDao(con);
		fornecedorController = new FornecedorController();
		itemDeCompraController = new ItemDeCompraController();
		salvoComSucesso = false;
	}

	public void saveCompra(Fornecedor fornecedor, Date dataCompra,
			List<ItemDeCompra> listaDeItemDeCompraAdicionadosNaTabela, Double valorTotalDouble)
			throws ValidationException, SQLException {
		ValidationError validation = null;
		validation = validateInputEntry(fornecedor, dataCompra, listaDeItemDeCompraAdicionadosNaTabela);

		if (validation.isValid()) {
			Compra compra = new Compra(fornecedor, dataCompra);
			compra.setValorTotal(valorTotalDouble);
			compra.setListaDeItemDeCompra(listaDeItemDeCompraAdicionadosNaTabela);
			save(compra);
			itemDeCompraController.saveItemDeCompra(listaDeItemDeCompraAdicionadosNaTabela);
			itemDeCompraController.saveRelationship(listaDeItemDeCompraAdicionadosNaTabela, compra);
			setSalvoComSucesso();
		} else {
			throw new ValidationException(validation);
		}
	}

	public void save(Compra compra) throws SQLException {
		dao.save(compra);
	}

	public void updateCompra(Fornecedor fornecedor, Date dataCompra, Compra compraAEditar,
			List<ItemDeCompra> listaDeItemDeCompraEditada) throws ValidationException, SQLException {
		ValidationError validation = null;
		validation = validateInputEntry(fornecedor, dataCompra, listaDeItemDeCompraEditada);

		if (validation.isValid()) {
			dao.deleteRelationship(compraAEditar.getId());
			compraAEditar.setDataCompra(dataCompra);
			compraAEditar.setListaDeItemDeCompra(listaDeItemDeCompraEditada);
			compraAEditar.setFornecedor(fornecedor);
			dao.update(compraAEditar);
			itemDeCompraController.saveItemDeCompra(listaDeItemDeCompraEditada);
			itemDeCompraController.saveRelationship(listaDeItemDeCompraEditada, compraAEditar);
			setSalvoComSucesso();
		} else {
			throw new ValidationException(validation);
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

	public ValidationError validateInputEntry(Fornecedor fornecedor, Date dataCompra,
			List<ItemDeCompra> listaDeItemDeCompraAdicionadosNaTabela) throws SQLException {
		ValidationError validation = new ValidationError();
		validation.setValid(true);

		if (fornecedor == null) {
			validation.setValid(false);
			validation.setMsg("Nenhum fornecedor selecionado.");
		}

		if (dataCompra == null) {

			validation.setValid(false);
			validation.setMsg("Data invalida.");

		}

		if (listaDeItemDeCompraAdicionadosNaTabela.isEmpty()) {

			validation.setValid(false);
			validation.setMsg("Nenhum item adicionado.");
		}

	
		return validation;
	}

	public ValidationError validateInputEntryFiltrar(Fornecedor fornecedorSelecionadoNoCombo) throws SQLException {
		ValidationError validation = new ValidationError();
		validation.setValid(true);

		if (fornecedorSelecionadoNoCombo == null) {
			validation.setValid(false);
			validation.setMsg("Nenhum fornecedor selecionado.");
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

	public List<Compra> filtrarListaDeCompra(Fornecedor fornecedorSelecionadoNoCombo, Date dataInicial,
			Date dataFinal) throws SQLException, ValidationException {

		List<Compra> listaFiltrada = new ArrayList<Compra>();

		ValidationError validation = null;
		validation = validateInputEntryFiltrar(fornecedorSelecionadoNoCombo);
		
		if (validation.isValid()) {
			
					
			if (dataInicial == null || dataFinal == null) {
				try {
					listaFiltrada = dao.filtrarPorFornecedorApenas(fornecedorSelecionadoNoCombo);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {

				java.sql.Date dataInicialSql = new java.sql.Date(dataInicial.getTime());
				java.sql.Date dataFinalSql = new java.sql.Date(dataFinal.getTime());
				try {
					listaFiltrada = dao.filtrarListaCompra(fornecedorSelecionadoNoCombo, dataInicialSql, dataFinalSql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new ValidationException(validation);
		}
		return listaFiltrada;
	}

	private void getListaFiltrada(List<Compra> listaFiltrada) {

	}
	
	public boolean fornecedorHasRelationshipCompra (int idFornecedor) throws SQLException {

		return dao.fornecedorHasRelationshipCompra(idFornecedor);
	}

	public void setSalvoComSucesso() {
		salvoComSucesso = true;
		
	}

	public boolean getSalvoComSucesso() {
		return salvoComSucesso;
		
	}
	


}
