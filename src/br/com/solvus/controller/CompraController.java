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

public class CompraController {

	private CompraDao dao;
	private FornecedorController fornecedorController;
	private ItemDeCompraController itemDeCompraController;
	private ItemDeCompraDao daoItemdecompra;
	Connection con;

	public CompraController() {
		this.con = ConnectionPool.CONNECTIONPOOL.getConnection();
		dao = new CompraDao(con);
		daoItemdecompra = new ItemDeCompraDao(con);
		fornecedorController = new FornecedorController();
		itemDeCompraController = new ItemDeCompraController();
	}

	public void saveCompra(Fornecedor fornecedor, String dataCompra,
			List<ItemDeCompra> listaDeItemDeCompraAdicionadosNaTabela, String valorTotalString) throws SQLException {
		ValidationError validation = null;
		validation = validateInputEntry(dataCompra, listaDeItemDeCompraAdicionadosNaTabela);
		if (validation.isValid()) {
			Date convertedDate = convertStringToDate(dataCompra);
			Double valorTotal = Double.parseDouble(valorTotalString);
			System.out.println(valorTotalString);
			Compra compra = new Compra(fornecedor, convertedDate);
			compra.setValorTotal(valorTotal);
			compra.setListaDeItemDeCompra(listaDeItemDeCompraAdicionadosNaTabela);
			save(compra);
			itemDeCompraController.saveItemDeCompra(listaDeItemDeCompraAdicionadosNaTabela);
			itemDeCompraController.saveRelationship(listaDeItemDeCompraAdicionadosNaTabela, compra);
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

	public ValidationError validateInputEntry(String inputDate,
			List<ItemDeCompra> listaDeItemDeCompraAdicionadosNaTabela) throws SQLException {

		ValidationError validation = new ValidationError();
		validation.setValid(true);

		Date dataConvertida = convertStringToDate(inputDate);
		if (dataConvertida == null) {

			validation.setValid(false);
			validation.setMsg("Data invalida.");

		}

		if (listaDeItemDeCompraAdicionadosNaTabela.isEmpty()) {

			validation.setValid(false);
			validation.setMsg("Nenhum item adicionado.");
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

	public List<Compra> filtrarListaDeCompra(Fornecedor fornecedorSelecionadoNoCombo, String dataInicialString,
			String dataFinalString) {
		List<Compra> listaFiltrada = new ArrayList<Compra>();
		
		if (dataInicialString.isEmpty() && dataFinalString.isEmpty()) {
			try {
				listaFiltrada = dao.filtrarPorFornecedorApenas(fornecedorSelecionadoNoCombo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			Date dataInicialConvertida = convertStringToDate(dataInicialString);
			Date dataFinalConvertida = convertStringToDate(dataFinalString);
			java.sql.Date dataInicialSql = new java.sql.Date(dataInicialConvertida.getTime());
			java.sql.Date DataFinalSql = new java.sql.Date(dataFinalConvertida.getTime());
			

			try {
				listaFiltrada = dao.filtrarListaCompra(fornecedorSelecionadoNoCombo, dataInicialSql, DataFinalSql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listaFiltrada;
	}

	private void getListaFiltrada(List<Compra> listaFiltrada) {

	}

}
