package br.com.solvus.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProdutoDao implements DAO<Produto> {

	private final Connection connection;

	public ProdutoDao(Connection connection) {
		this.connection = connection;
	}

	public boolean checkIfDuplicate(String nomeProduto) throws SQLException {
		String sql = "select * from produto where nome_produto = (?)";
		boolean retorno = false;
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, nomeProduto);
			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				retorno = true;
			}
		}
		return retorno;
	}


	public boolean hasRelationship(int idProduto) throws SQLException {

		boolean retorno = false;
		try (PreparedStatement stmt = connection.prepareStatement(
				"select * from fornecedor_produto where fornecedor_produto.id_produto = (?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, idProduto);
			stmt.execute();

			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				retorno = true;
			}
		}
		return retorno;
	}


	@Override
	public Produto findById(Integer idProduto) throws SQLException {
		String sql = "select * from produto where id_produto = (?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, idProduto);
			stmt.execute();

			ResultSet resultSet = stmt.getResultSet();
			Produto produto = null;
			while (resultSet.next()) {
				String nome = resultSet.getString("nome_produto");
				int id = resultSet.getInt("id_produto");
				produto = new Produto(nome);
				produto.setId(id);
			}
			return produto;
		}

	}

	@Override
	public void save(Produto produto) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement("insert into produto(nome_produto) values (?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.execute();

			try (ResultSet keys = stmt.getGeneratedKeys()) {
				keys.next();
				int id = keys.getInt("id_produto");
				produto.setId(id);
			}

		}

	}

	@Override
	public void deleteById(Integer idProduto) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement("delete from produto where id_produto = (?)",
				Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, idProduto);
			stmt.execute();
		}

	}

	@Override
	public List<Produto> list() throws SQLException {
		String sql = "select * from produto order by nome_produto ASC";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.execute();

			ResultSet resultSet = stmt.getResultSet();
			ArrayList<Produto> produtos = new ArrayList<>();
			while (resultSet.next()) {
				String nome = resultSet.getString("nome_produto");
				int id = resultSet.getInt("id_produto");
				Produto p = new Produto(nome);
				p.setId(id);
				produtos.add(p);
			}
			return produtos;
		}
	}

	@Override
	public void update(Produto produto) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(
				"update produto set nome_produto = (?) where id_produto = (?)", Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setInt(2, produto.getId());
			stmt.execute();

		}

	}
}
