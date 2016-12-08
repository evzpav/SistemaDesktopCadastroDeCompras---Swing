package br.com.solvus.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompraDao implements DAO<Compra> {

	private final Connection connection;
	private FornecedorDao fornecedorDao;

	public CompraDao(Connection connection) {
		this.connection = connection;
		fornecedorDao = new FornecedorDao(connection);
	}

	@Override
	public void save(Compra compra) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"insert into compra(id_fornecedor, data_compra, valor_total) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, compra.getFornecedor().getId());
			stmt.setDate(2, new Date(compra.getDataCompra().getTime()));
			stmt.setDouble(3,  compra.getValorTotal());
			stmt.execute();

			try (ResultSet keys = stmt.getGeneratedKeys()) {
				keys.next();
				int id = keys.getInt("id_compra");
				compra.setId(id);
				
			}
			System.out.println("saveCompra acionado");

		}

	}

	@Override
	public void update(Compra compra) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"update compra set (id_fornecedor, data_compra) = (?,?) where id_compra = (?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, compra.getFornecedor().getId());
			stmt.setDate(2, new Date(compra.getDataCompra().getTime()));
			stmt.setInt(3, compra.getId());
			stmt.execute();

		}

	}

	@Override
	public Compra findById(Integer idCompra) throws SQLException {
		Compra compra = null;
		String sql = "select * from compra where id_compra = (?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, idCompra);
			stmt.execute();

			Fornecedor fornecedor = null;
			ResultSet resultSet = stmt.getResultSet();
			while (resultSet.next()) {
				Date dataCompra = resultSet.getDate("data_compra");
				int idFornecedor = resultSet.getInt("id_fornecedor");
				fornecedor = fornecedorDao.findById(idFornecedor);
				compra = new Compra(fornecedor, dataCompra);
				compra.setId(idCompra);
			}
		}
		return compra;
	}

	// String sql1 = "select * from fornecedor_produto"
	// + " join produto on produto.id_produto = fornecedor_produto.id_produto "
	// + "where id_fornecedor = (?)";
	// try (PreparedStatement pstmt = connection.prepareStatement(sql1)) {
	// pstmt.setInt(1, idFornecedor);
	// pstmt.execute();
	//
	// ResultSet resultSet1 = pstmt.getResultSet();
	// Produto produto = null;
	// List<Produto> listagemProdutos = new ArrayList<Produto>();
	//
	// while (resultSet1.next()) {
	// int produtoId = resultSet1.getInt("id_produto");
	// String nomeProduto = resultSet1.getString("nome_produto");
	// produto = new Produto(nomeProduto);
	// produto.setId(produtoId);
	// listagemProdutos.add(produto);
	//
	// }
	// fornecedor.setListagemProdutos(listagemProdutos);
	// return fornecedor;
	// }
	// }
	// }

	@Override
	public void deleteById(Integer idCompra) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement("delete from compra where id_compra = (?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, idCompra);
			stmt.execute();
		}

	}

	public void deleteRelationship(int idCompra) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement("delete from itemdecompra where id_compra = (?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, idCompra);
			stmt.execute();
		}

	}

	@Override
	public List<Compra> list() throws SQLException {
		String sql = "select * from compra";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();

			ResultSet resultSet = stmt.getResultSet();
			ArrayList<Compra> listaCompra = new ArrayList<>();
			Fornecedor fornecedor = null;
			Compra compra = null;
			while (resultSet.next()) {
				int idCompra = resultSet.getInt("id_compra");
				int idFornecedor = resultSet.getInt("id_fornecedor");
				Date dataCompra = resultSet.getDate("data_compra");
				fornecedor = fornecedorDao.findById(idFornecedor);
				compra = new Compra(fornecedor, dataCompra);
				listaCompra.add(compra);
				compra.setId(idCompra);
			}
			return listaCompra;
		}
	}

	public List<Compra> filtrarListaCompra(Fornecedor fornecedorSelecionadoNoCombo, java.sql.Date dataInicialConvertida,
			java.sql.Date dataFinalConvertida) throws SQLException {
		String sql = "select * from compra where id_fornecedor = (?) and data_compra between (?) and (?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, fornecedorSelecionadoNoCombo.getId());
			stmt.setDate(2, dataInicialConvertida);
			stmt.setDate(3, dataFinalConvertida);
			stmt.execute();

			ResultSet resultSet = stmt.getResultSet();
			ArrayList<Compra> listaCompra = new ArrayList<>();
			Fornecedor fornecedor = null;
			Compra compra = null;
			while (resultSet.next()) {
				int idCompra = resultSet.getInt("id_compra");
				int idFornecedor = resultSet.getInt("id_fornecedor");
				Date dataCompra = resultSet.getDate("data_compra");
				fornecedor = fornecedorDao.findById(idFornecedor);
				compra = new Compra(fornecedor, dataCompra);
				listaCompra.add(compra);
				compra.setId(idCompra);
			}
			return listaCompra;

		}

	}

	public List<Compra> filtrarPorFornecedorApenas(Fornecedor fornecedorSelecionadoNoCombo) throws SQLException {
		String sql = "select * from compra where id_fornecedor = (?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, fornecedorSelecionadoNoCombo.getId());
			stmt.execute();

			ResultSet resultSet = stmt.getResultSet();
			ArrayList<Compra> listaCompra = new ArrayList<>();
			Fornecedor fornecedor = null;
			Compra compra = null;
			while (resultSet.next()) {
				int idCompra = resultSet.getInt("id_compra");
				int idFornecedor = resultSet.getInt("id_fornecedor");
				Date dataCompra = resultSet.getDate("data_compra");
				fornecedor = fornecedorDao.findById(idFornecedor);
				compra = new Compra(fornecedor, dataCompra);
				listaCompra.add(compra);
				compra.setId(idCompra);
			}
			return listaCompra;
		}
	}
}
