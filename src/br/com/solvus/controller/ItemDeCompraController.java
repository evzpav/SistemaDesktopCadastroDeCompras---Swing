package br.com.solvus.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.solvus.model.Compra;
import br.com.solvus.model.CompraDao;
import br.com.solvus.model.ConnectionPool;
import br.com.solvus.model.ItemDeCompra;
import br.com.solvus.model.ItemDeCompraDao;
import br.com.solvus.model.Produto;
import br.com.solvus.viewSwing.util.ValidationError;
import br.com.solvus.viewSwing.util.ValidationException;

public class ItemDeCompraController {

	private ItemDeCompraDao dao;
	private CompraDao daoCompra;
	Connection con;

	public ItemDeCompraController() {
		this.con = ConnectionPool.CONNECTIONPOOL.getConnection();
		dao = new ItemDeCompraDao(con);
		daoCompra = new CompraDao(con);

	}

	public ItemDeCompra adicionarItemDeCompra(Produto produto, String quantidadeString, String valorUnitarioString)
			throws ValidationException {

		ItemDeCompra itemdecompra = null;
		ValidationError validation = null;
		validation = validateInputEntry(produto, quantidadeString, valorUnitarioString);
		if (validation.isValid()) {
			Integer quantidade = Integer.parseInt(quantidadeString);
			Double valorUnitario = Double.parseDouble(valorUnitarioString);

			itemdecompra = new ItemDeCompra(produto, quantidade, valorUnitario);

		} else {
			throw new ValidationException(validation);
		}

		return itemdecompra;
	}

	public void saveItemDeCompra(List<ItemDeCompra> listaDeItemDeCompraAdicionadosNaTabela) {
		for (ItemDeCompra itemDeCompra : listaDeItemDeCompraAdicionadosNaTabela) {
			try {
				dao.save(itemDeCompra);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void saveRelationship(List<ItemDeCompra> listaDeItemDeCompraAdicionadosNaTabela, Compra compra) {
		try {
			dao.saveRelationship(listaDeItemDeCompraAdicionadosNaTabela, compra);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void deleteRelationship(List<ItemDeCompra>
	// listaDeItemDeCompraAdicionadosNaTabela, Compra compra) {
	// try {
	// dao.deleteRelationship(listaDeItemDeCompraAdicionadosNaTabela, compra);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void excluirItemDeCompra(ItemDeCompra itemDeCompra) throws SQLException {
		deleteById(itemDeCompra.getIdItemDeCompra());
	}

	public void update(ItemDeCompra itemdecompra) throws SQLException {

		dao.update(itemdecompra);
	}

	public ItemDeCompra findById(Integer idItemdecompra) throws SQLException {
		return dao.findById(idItemdecompra);
	}

	public List<ItemDeCompra> list(int idCompra) throws SQLException {
		return dao.list(idCompra);

	}

	public void deleteById(Integer idItemDeCompra) throws SQLException {
		dao.deleteById(idItemDeCompra);
	}

	public ValidationError validateInputEntry(Produto produto, String quantidadeString, String valorUnitarioString) {

		ValidationError validation = new ValidationError();
		validation.setValid(true);

		if (produto == null) {
			validation.setValid(false);
			validation.setMsg("Nenhum produto selecionado");
		}

		if (quantidadeString.isEmpty() || valorUnitarioString.isEmpty()) {
			validation.setValid(false);
			validation.setMsg("Quantidade ou valor está em branco.");
		}
		if (!isInteger(quantidadeString)) {
			validation.setValid(false);
			validation.setMsg("Quantidade não é válida.");
		}

		if (!isDouble(valorUnitarioString)) {
			validation.setValid(false);
			validation.setMsg("Valor não é válido.");
		}

		if (validation.isValid()) {
			Integer quantidade = Integer.parseInt(quantidadeString);
			Double valorUnitario = Double.parseDouble(valorUnitarioString);

			if (quantidade <= 0 || valorUnitario <= 0) {
				validation.setValid(false);
				validation.setMsg("Quantidade ou valor não válidos. Digite quantidade ou valor positivos.");
			}
		}

		return validation;

	}

	public boolean isInteger(String string) {
		try {
			Integer.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isDouble(String string) {
		try {
			Double.valueOf(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}