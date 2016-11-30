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
		this.add(tabelaProduto, "3");
		this.add(cadastroProdutoPanel, "4");
		cardlayout.show(this, "3");

	}

	public void mostrarCadastroProdPanel() {
		cardlayout.show(this, "4");
	}

	public void mostrarTabelaProdPanel() {
		cardlayout.show(this, "3");
		try {
			tabelaProduto.refreshTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void setEditing() {
		cadastroProdutoPanel.isEditing();
		mostrarCadastroProdPanel();			
	}

	public void isNew() {
		cadastroProdutoPanel.isNew();
		mostrarCadastroProdPanel();
	}
	public void produtoAEditar(Produto produto){
		cadastroProdutoPanel.setProdutoAEditar(produto);
	
	}


}
