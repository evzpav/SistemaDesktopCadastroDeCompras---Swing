package br.com.solvus.viewSwing.compras;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import br.com.solvus.controller.CompraController;
import br.com.solvus.model.Fornecedor;

import java.awt.Font;

public class ListagemCompraPanel extends JPanel {

	private TabelaListagemCompraPanel tableListagemCompraPanel;
	private JTextField fieldDataInicial;
	private JTextField fieldDataFinal;
	private CompraController compraController;
	private List<Fornecedor> listaFornecedor;
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ListagemCompraPanel(final ConteudoCompraPanel conteudoCompraPanel) throws SQLException {
		
		tableListagemCompraPanel = new TabelaListagemCompraPanel(conteudoCompraPanel);
		compraController = new CompraController();
		listaFornecedor = compraController.listFornecedor();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 191, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblListagemdeCompra = new JLabel("Listagem de Compras");
		lblListagemdeCompra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblListagemdeCompras = new GridBagConstraints();
		gbc_lblListagemdeCompras.anchor = GridBagConstraints.WEST;
		gbc_lblListagemdeCompras.insets = new Insets(0, 0, 5, 5);
		gbc_lblListagemdeCompras.gridx = 1;
		gbc_lblListagemdeCompras.gridy = 0;
		add(lblListagemdeCompra, gbc_lblListagemdeCompras);
		
		JButton buttonAdicionarCompra = new JButton("Adicionar Compra");
		GridBagConstraints gbc_buttonAdicionarCompra = new GridBagConstraints();
		gbc_buttonAdicionarCompra.anchor = GridBagConstraints.WEST;
		gbc_buttonAdicionarCompra.insets = new Insets(0, 0, 5, 5);
		gbc_buttonAdicionarCompra.gridx = 1;
		gbc_buttonAdicionarCompra.gridy = 1;
		add(buttonAdicionarCompra, gbc_buttonAdicionarCompra);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		GridBagConstraints gbc_lblFornecedor = new GridBagConstraints();
		gbc_lblFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFornecedor.anchor = GridBagConstraints.WEST;
		gbc_lblFornecedor.gridx = 1;
		gbc_lblFornecedor.gridy = 2;
		add(lblFornecedor, gbc_lblFornecedor);
		
		JLabel lblDataDeCompra = new JLabel("Data de Compra Inicial");
		GridBagConstraints gbc_lblDataDeCompra = new GridBagConstraints();
		gbc_lblDataDeCompra.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDeCompra.gridx = 2;
		gbc_lblDataDeCompra.gridy = 2;
		add(lblDataDeCompra, gbc_lblDataDeCompra);
		
		JLabel lblDataDeCompra_1 = new JLabel("Data de Compra Final");
		GridBagConstraints gbc_lblDataDeCompra_1 = new GridBagConstraints();
		gbc_lblDataDeCompra_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDeCompra_1.gridx = 3;
		gbc_lblDataDeCompra_1.gridy = 2;
		add(lblDataDeCompra_1, gbc_lblDataDeCompra_1);
		
		
		
		JComboBox comboFornecedor = new JComboBox();
		GridBagConstraints gbc_comboFornecedor = new GridBagConstraints();
		gbc_comboFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_comboFornecedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboFornecedor.gridx = 1;
		gbc_comboFornecedor.gridy = 3;
		add(comboFornecedor, gbc_comboFornecedor);
		
		for(int i = 0; i < listaFornecedor.size(); i++){
			Fornecedor fornecedor =  listaFornecedor.get(i);
			comboFornecedor.addItem(fornecedor);
		}
		
		
		
		
		
		
		fieldDataInicial = new JTextField();
		GridBagConstraints gbc_fieldDataInicial = new GridBagConstraints();
		gbc_fieldDataInicial.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDataInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDataInicial.gridx = 2;
		gbc_fieldDataInicial.gridy = 3;
		add(fieldDataInicial, gbc_fieldDataInicial);
		fieldDataInicial.setColumns(10);
		
		fieldDataFinal = new JTextField();
		GridBagConstraints gbc_fieldDataFinal = new GridBagConstraints();
		gbc_fieldDataFinal.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDataFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDataFinal.gridx = 3;
		gbc_fieldDataFinal.gridy = 3;
		add(fieldDataFinal, gbc_fieldDataFinal);
		fieldDataFinal.setColumns(10);
		
		JButton buttonFiltrar = new JButton("Filtrar");
		GridBagConstraints gbc_buttonFiltrar = new GridBagConstraints();
		gbc_buttonFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_buttonFiltrar.gridx = 4;
		gbc_buttonFiltrar.gridy = 3;
		add(buttonFiltrar, gbc_buttonFiltrar);
		
		GridBagConstraints gbc_panelforTableListagem = new GridBagConstraints();
		gbc_panelforTableListagem.gridwidth = 4;
		gbc_panelforTableListagem.fill = GridBagConstraints.BOTH;
		gbc_panelforTableListagem.gridx = 1;
		gbc_panelforTableListagem.gridy = 4;
		add(tableListagemCompraPanel, gbc_panelforTableListagem);
		
		buttonAdicionarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conteudoCompraPanel.showRegistroComprasPanel();
			}

		});
	}
}
