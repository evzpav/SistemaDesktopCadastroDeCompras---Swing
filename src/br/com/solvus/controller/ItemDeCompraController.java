package br.com.solvus.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.solvus.model.CompraDao;
import br.com.solvus.model.ConnectionPool;
import br.com.solvus.model.ItemDeCompra;
import br.com.solvus.model.ItemDeCompraDao;
import br.com.solvus.model.Produto;
import br.com.solvus.viewSwing.util.ValidationError;

public class ItemDeCompraController {

	private ItemDeCompraDao dao;
	private CompraDao daoCompra;
	Connection con;

	public ItemDeCompraController() {
		this.con = ConnectionPool.CONNECTIONPOOL.getConnection();
		dao = new ItemDeCompraDao(con);
		daoCompra = new CompraDao(con);
	}

	public void adicionarItemDeCompra(Produto produto, Integer quantidade, Double valorUnitario) throws SQLException {
		ValidationError validation = null;
		validation = validateInputEntry(quantidade, valorUnitario);
		if (validation.isValid()) {
			ItemDeCompra itemdecompra = new ItemDeCompra(produto, quantidade, valorUnitario);
			dao.save(itemdecompra);
		} else {
			validation.getMsg();
		}
	}

	public void excluirItemDeCompra(ItemDeCompra itemDeCompra) throws SQLException{
				deleteById(itemDeCompra.getIdItemDeCompra());
	}

	public void update(ItemDeCompra itemdecompra) throws SQLException {
//		dao.deleteRelationship(itemdecompra.getIdItemDeCompra());
//		dao.saveRelationship(itemdecompra, compra);
		dao.update(itemdecompra);
	}

	public ItemDeCompra findById(Integer idItemdecompra) throws SQLException {
		return dao.findById(idItemdecompra);
	}

	public List<ItemDeCompra> list() throws SQLException {
		System.out.println("chegou no controller");
		return dao.list();

	}

	public void deleteById(int idItemDeCompra) throws SQLException {
		dao.deleteById(idItemDeCompra);
	}

	public ValidationError validateInputEntry(Integer quantidade, Double valorUnitario) throws SQLException {

		ValidationError validation = new ValidationError();
		validation.setValid(true);

		if (quantidade == null) {
			validation.setValid(false);
			validation.setMsg("Quantidade está em branco");
		}

		if (valorUnitario == null) {
			validation.setValid(false);
			validation.setMsg("Valor unitário está em branco");
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

}
