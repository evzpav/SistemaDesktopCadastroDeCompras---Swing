package br.com.solvus.viewSwing.util;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.security.auth.Refreshable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import br.com.solvus.model.ConnectionPool;
import br.com.solvus.viewSwing.fornecedor.CadastroFornecedorPanel;
import br.com.solvus.viewSwing.fornecedor.ConteudoFornecedorPanel;
import br.com.solvus.viewSwing.fornecedor.TabelaFornecedorPanel;
import br.com.solvus.viewSwing.produto.CadastroProdutoPanel;
import br.com.solvus.viewSwing.produto.ConteudoProdutoPanel;
import br.com.solvus.viewSwing.produto.TabelaProdutoPanel;

public class MainFrame extends JFrame {

	private JButton btn;
	public CadastroFornecedorPanel cadastroFornecedorPanel;
	public CadastroProdutoPanel cadastroProdutoPanel;
	public CardLayout meuCardlayout;
	public JPanel panelCont;
	public ConteudoFornecedorPanel conteudoFornecedorPanel;
	public ConteudoProdutoPanel conteudoProdutoPanel;
	private TabelaProdutoPanel tabelaProduto;// meu
	private TabelaFornecedorPanel tabelaFornecedor;


	public MainFrame() {

		super("Meu App");

		meuCardlayout = new CardLayout();

		// tabelaProduto = new TabelaProdutoPanel(); // meu
		// tabelaFornecedor = new TabelaFornecedorPanel(); // meu

		panelCont = new JPanel();
		conteudoFornecedorPanel = new ConteudoFornecedorPanel();
		conteudoProdutoPanel = new ConteudoProdutoPanel();
		// cadastroFornecedorPanel = new CadastroFornecedorPanel();
		// cadastroProdutoPanel = new CadastroProdutoPanel();

		panelCont.setLayout(meuCardlayout);

		setJMenuBar(createMenuBar());

		panelCont.add(conteudoFornecedorPanel, "1");
		panelCont.add(conteudoProdutoPanel, "2");

		// this.add(panelCont);
		this.add(panelCont);

		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void mudaTituloFornecedor() {
		this.setTitle("Cadastro de Fornecedores");
	}

	private void mudaTituloProduto() {
		this.setTitle("Cadastro de Produtos");
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		final JMenu fornecedoresMenu = new JMenu("Fornecedores");
		final JMenu produtosMenu = new JMenu("Produtos");
		menuBar.add(fornecedoresMenu);
		menuBar.add(produtosMenu);

		fornecedoresMenu.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent ev) {
				meuCardlayout.show(panelCont, "1");
				conteudoFornecedorPanel.mostrarTabelaFornPanel();
				mudaTituloFornecedor();
			}

			public void menuDeselected(MenuEvent ev) {
			}

			public void menuCanceled(MenuEvent ev) {
			}
		});

		produtosMenu.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent evv) {
				meuCardlayout.show(panelCont, "2");
				conteudoProdutoPanel.mostrarTabelaProdPanel();
				mudaTituloProduto();
			}

			public void menuDeselected(MenuEvent evv) {
			}

			public void menuCanceled(MenuEvent evv) {
			}
		});

		return menuBar;
	}
}
