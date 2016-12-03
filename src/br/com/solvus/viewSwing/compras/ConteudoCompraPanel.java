package br.com.solvus.viewSwing.compras;

import java.awt.CardLayout;
import java.sql.SQLException;

import javax.swing.JPanel;

public class ConteudoCompraPanel extends JPanel {

	private CardLayout cardlayout;

	private RegistroCompraPanel registroCompraPanel;
	private ListagemCompraPanel listagemCompraPanel;

	private TabelaListagemCompraPanel tableListagemCompra;
	
	public ConteudoCompraPanel() throws SQLException {
		cardlayout = new CardLayout();

		registroCompraPanel = new RegistroCompraPanel(this);
		listagemCompraPanel = new ListagemCompraPanel(this);
		
		tableListagemCompra = new TabelaListagemCompraPanel(this);

		this.setLayout(cardlayout);
		this.add(listagemCompraPanel, "1");
	//	this.add(tableListagemCompra, "1");
		this.add(registroCompraPanel, "2");
		cardlayout.show(this, "1");
	}

	public void showRegistroComprasPanel() {
		cardlayout.show(this, "2");

	}

	public void showListagemComprasPanel() {
		cardlayout.show(this, "1");
	}
}
