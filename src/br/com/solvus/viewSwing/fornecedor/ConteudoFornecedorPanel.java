package br.com.solvus.viewSwing.fornecedor;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import br.com.solvus.controller.FornecedorController;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.Produto;

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
		this.add(tabelaFornecedor, "5");
		this.add(cadastroFornecedorPanel, "6");
		cardlayout.show(this, "5");

	}

	public void mostrarCadastroFornPanel() {
		cardlayout.show(this, "6");
	
		cadastroFornecedorPanel.removeCheckboxPanel();
		cadastroFornecedorPanel.generateCheckbox();


	}

	public void mostrarTabelaFornPanel() {
		cardlayout.show(this, "5");
		tabelaFornecedor.atualizar();
		cadastroFornecedorPanel.removeCheckboxPanel();
	}

	public void setEditingForn() {
		cadastroFornecedorPanel.isEditing();
		mostrarCadastroFornPanel();
	}

	public void isNew() {
		cadastroFornecedorPanel.isNew();
		mostrarCadastroFornPanel();
	}

	public void fornecedorAEditar(Fornecedor fornecedor) {
		cadastroFornecedorPanel.setEditFornecedor(fornecedor);

	}
}
