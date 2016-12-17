package br.com.solvus.viewSwing.util;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import br.com.solvus.viewSwing.compras.ConteudoCompraPanel;
import br.com.solvus.viewSwing.fornecedor.CadastroFornecedorPanel;
import br.com.solvus.viewSwing.fornecedor.ConteudoFornecedorPanel;
import br.com.solvus.viewSwing.graficos.GraficoTeste;
import br.com.solvus.viewSwing.graficos.Graficos;
import br.com.solvus.viewSwing.produto.CadastroProdutoPanel;
import br.com.solvus.viewSwing.produto.ConteudoProdutoPanel;

public class MainFrame extends JFrame {

	public CadastroFornecedorPanel cadastroFornecedorPanel;
	public CadastroProdutoPanel cadastroProdutoPanel;
	public CardLayout meuCardlayout;
	public JPanel panelCont;
	public ConteudoFornecedorPanel conteudoFornecedorPanel;
	public ConteudoProdutoPanel conteudoProdutoPanel;
	private ConteudoCompraPanel conteudoCompraPanel;
	private Graficos graficos;
	private GraficoTeste graficoTeste;
	
	public MainFrame() throws SQLException {

		super("Meu App");

		meuCardlayout = new CardLayout();

		panelCont = new JPanel();
		conteudoFornecedorPanel = new ConteudoFornecedorPanel();
		conteudoProdutoPanel = new ConteudoProdutoPanel();

		conteudoCompraPanel = new ConteudoCompraPanel();
		graficos = new Graficos();
		graficoTeste = new GraficoTeste();
		
		panelCont.setLayout(meuCardlayout);

		setJMenuBar(createMenuBar());

		panelCont.add(conteudoFornecedorPanel, "1");
		panelCont.add(conteudoProdutoPanel, "2");
		panelCont.add(conteudoCompraPanel, "3");
	//	panelCont.add(grafico, "4");
		panelCont.add(graficoTeste, "4");
		
		this.add(panelCont);

		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	private void mudaTituloFornecedor() {
		this.setTitle("Cadastro de Fornecedores");
	}

	private void mudaTituloProduto() {
		this.setTitle("Cadastro de Produtos");
	}

	private void mudaTituloCompras() {
		this.setTitle("Cadastro de Compras");
	}
	private void mudaTituloGraficos() {
		this.setTitle("Graficos");
	}
	

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		final JMenu fornecedoresMenu = new JMenu("Fornecedores");
		final JMenu produtosMenu = new JMenu("Produtos");
		final JMenu comprasMenu = new JMenu("Compras");
		final JMenu graficosMenu = new JMenu("Graficos");
		menuBar.add(fornecedoresMenu);
		menuBar.add(produtosMenu);
		menuBar.add(comprasMenu);
		menuBar.add(graficosMenu);


		
		fornecedoresMenu.addMouseListener(new MouseListener() {
			public void menuSelected(MenuEvent ev) {
				
			}

			public void menuDeselected(MenuEvent ev) {
			}

			public void menuCanceled(MenuEvent ev) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				meuCardlayout.show(panelCont, "1");
				conteudoFornecedorPanel.showTabelaFornPanel();
				mudaTituloFornecedor();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		produtosMenu.addMouseListener(new MouseListener() {
			public void menuSelected(MenuEvent evv) {
				
			}

			public void menuDeselected(MenuEvent evv) {
			}

			public void menuCanceled(MenuEvent evv) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				meuCardlayout.show(panelCont, "2");
				conteudoProdutoPanel.showTabelaProdPanel();
				mudaTituloProduto();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

//		produtosMenu.addMenuListener(new MenuListener() {
//			public void menuSelected(MenuEvent evv) {
//				meuCardlayout.show(panelCont, "2");
//				conteudoProdutoPanel.showTabelaProdPanel();
//				mudaTituloProduto();
//			}
//
//			public void menuDeselected(MenuEvent evv) {
//			}
//
//			public void menuCanceled(MenuEvent evv) {
//			}
//		});
		
//		comprasMenu.addMenuListener(new MenuListener() {
//			public void menuSelected(MenuEvent evv) {
//				meuCardlayout.show(panelCont, "3");
//				conteudoCompraPanel.showListagemComprasPanel();
//				
//				mudaTituloCompras();
//			}
//
//			public void menuDeselected(MenuEvent evv) {
//			}
//
//			public void menuCanceled(MenuEvent evv) {
//			}
//		});
//		return menuBar;
		
		comprasMenu.addMouseListener(new MouseListener() {
			public void menuSelected(MenuEvent evv) {
		
			}

			public void menuDeselected(MenuEvent evv) {
			}

			public void menuCanceled(MenuEvent evv) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				meuCardlayout.show(panelCont, "3");
				conteudoCompraPanel.showListagemComprasPanel();
				
				mudaTituloCompras();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		graficosMenu.addMouseListener(new MouseListener() {
			public void menuSelected(MenuEvent evv) {
		
			}

			public void menuDeselected(MenuEvent evv) {
			}

			public void menuCanceled(MenuEvent evv) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				meuCardlayout.show(panelCont, "4");
				mudaTituloGraficos();
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return menuBar;
	}
}
