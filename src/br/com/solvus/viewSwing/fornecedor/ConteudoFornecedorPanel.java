package br.com.solvus.viewSwing.fornecedor;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import br.com.solvus.controller.FornecedorController;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.Produto;
import br.com.solvus.viewSwing.compras.RegistroCompraPanel;

public class ConteudoFornecedorPanel extends JPanel {

	private CardLayout cardlayout;

	private CadastroFornecedorPanel cadastroFornecedorPanel;
	private TabelaFornecedorPanel tabelaFornecedor;
	private FornecedorController controller;

	
	
	public ConteudoFornecedorPanel() {
		cardlayout = new CardLayout();
		
		controller = new FornecedorController();
		cadastroFornecedorPanel = new CadastroFornecedorPanel(this);
		tabelaFornecedor = new TabelaFornecedorPanel(this);
		
		
		this.setLayout(cardlayout);
		this.add(tabelaFornecedor, "1");
		this.add(cadastroFornecedorPanel, "2");
		cardlayout.show(this, "1");

	}

	public void showCadastroFornPanel() {
		cardlayout.show(this, "2");
	
		cadastroFornecedorPanel.removeCheckboxPanel();
		cadastroFornecedorPanel.generateCheckbox();
	}

	public void showTabelaFornPanel() {
		cardlayout.show(this, "1");
		tabelaFornecedor.atualizar();
		cadastroFornecedorPanel.removeCheckboxPanel();
	}

	public void setEditingForn() {
		cadastroFornecedorPanel.isEditing();
		showCadastroFornPanel();
	}

	public void isNew() {
		cadastroFornecedorPanel.isNew();
		showCadastroFornPanel();
	}

	public void fornecedorAEditar(Fornecedor fornecedor) {
		cadastroFornecedorPanel.setEditFornecedor(fornecedor);

	}
	
	
}
