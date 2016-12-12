package br.com.solvus.viewSwing.compras;

import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JPanel;

import br.com.solvus.model.Compra;

public class ConteudoCompraPanel extends JPanel {

	private CardLayout cardlayout;

	private RegistroCompraPanel registroCompraPanel;
	private ListagemCompraPanel listagemCompraPanel;
	private TabelaRegistroCompraPanel tableRegistroCompraPanel;

	private TabelaListagemCompraPanel tableListagemCompra;
	
	public ConteudoCompraPanel() throws SQLException {
		cardlayout = new CardLayout();

		//registroCompraPanel = new RegistroCompraPanel(this);
		//listagemCompraPanel = new ListagemCompraPanel(this);
		


		this.setLayout(cardlayout);
	//	this.add(listagemCompraPanel, "1");
		//this.add(registroCompraPanel, "2");
		//cardlayout.show(this, "1");
	}

	public void showRegistroComprasPanel() throws SQLException {
		
		registroCompraPanel = new RegistroCompraPanel(this);
		tableRegistroCompraPanel = new TabelaRegistroCompraPanel(registroCompraPanel);
		this.add(registroCompraPanel, "2");
		cardlayout.show(this, "2");
	}

	public void showListagemComprasPanel() {
		try {
			listagemCompraPanel = new ListagemCompraPanel(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableListagemCompra = new TabelaListagemCompraPanel(this);
		this.add(listagemCompraPanel, "1");
		cardlayout.show(this, "1");
		tableListagemCompra.atualizar();
	}
	
	public void setEditingCompra() throws SQLException {
		registroCompraPanel.isEditing();
		showRegistroComprasPanel();
	}

	public void isNew() throws SQLException {
		registroCompraPanel.isNew();
		showRegistroComprasPanel();
	}
	
	public void compraAEditar(Compra compra) throws SQLException {
		registroCompraPanel.setEditingCompra(compra);

	}
}
