package br.com.solvus.viewSwing.produto;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.sql.SQLException;

import javax.swing.JPanel;

import br.com.solvus.controller.ProdutoController;
import br.com.solvus.model.Produto;

public class ConteudoProdutoPanel extends JPanel {

	public CardLayout cardlayout;

	private CadastroProdutoPanel cadastroProdutoPanel;
	private TabelaProdutoPanel tabelaProduto;
	private ProdutoController controller;

	public ConteudoProdutoPanel() {
		cardlayout = new CardLayout();

		cadastroProdutoPanel = new CadastroProdutoPanel(this);
		try {
			tabelaProduto = new TabelaProdutoPanel(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setLayout(cardlayout);
		this.add(tabelaProduto, "1");
		this.add(cadastroProdutoPanel, "2");
		cardlayout.show(this, "1");

	}

	public void showCadastroProdPanel() {
		cardlayout.show(this, "2");
	}

	public void showTabelaProdPanel() {
		cardlayout.show(this, "1");
		try {
			tabelaProduto.refreshTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void setEditing() {
		cadastroProdutoPanel.isEditing();
		showCadastroProdPanel();			
	}

	public void isNew() {
		cadastroProdutoPanel.isNew();
		showCadastroProdPanel();
	}
	public void produtoAEditar(Produto produto){
		cadastroProdutoPanel.setProdutoAEditar(produto);
	
	}


}
