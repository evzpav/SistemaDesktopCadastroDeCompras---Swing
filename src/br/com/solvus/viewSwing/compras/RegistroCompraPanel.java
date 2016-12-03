package br.com.solvus.viewSwing.compras;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import br.com.solvus.controller.CompraController;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.Produto;

public class RegistroCompraPanel extends JPanel {
	private JTextField inputDate;
	private JTextField inputQuantidade;
	private JTextField inputValor;
	private final JTextField txtTotalEmCompras = new JTextField();
	private TabelaRegistroCompraPanel tableRegistroCompraPanel;
	private CompraController compraController;
	private List<Fornecedor> listaFornecedor;
	private List<Produto> listaProdutoFornecedorSelecionado;
	
	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public RegistroCompraPanel(final ConteudoCompraPanel conteudoCompraPanel) throws SQLException {

		tableRegistroCompraPanel = new TabelaRegistroCompraPanel();
		compraController = new CompraController();
		listaFornecedor = compraController.listFornecedor();
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 141, 184, 171, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 286, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblRegistroDeCompras = new JLabel("Registro de Compras");
		lblRegistroDeCompras.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblRegistroDeCompras = new GridBagConstraints();
		gbc_lblRegistroDeCompras.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegistroDeCompras.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblRegistroDeCompras.gridx = 1;
		gbc_lblRegistroDeCompras.gridy = 0;
		add(lblRegistroDeCompras, gbc_lblRegistroDeCompras);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		GridBagConstraints gbc_lblFornecedor = new GridBagConstraints();
		gbc_lblFornecedor.anchor = GridBagConstraints.WEST;
		gbc_lblFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_lblFornecedor.gridx = 1;
		gbc_lblFornecedor.gridy = 1;
		add(lblFornecedor, gbc_lblFornecedor);

		final JComboBox comboFornecedor = new JComboBox();
		GridBagConstraints gbc_comboFornecedor = new GridBagConstraints();
		gbc_comboFornecedor.insets = new Insets(0, 0, 5, 5);
		gbc_comboFornecedor.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboFornecedor.gridx = 1;
		gbc_comboFornecedor.gridy = 2;
		add(comboFornecedor, gbc_comboFornecedor);

		for (int i = 0; i < listaFornecedor.size(); i++) {
			Fornecedor fornecedor = listaFornecedor.get(i);
			comboFornecedor.addItem(fornecedor);
		}
		comboFornecedor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listaProdutoFornecedorSelecionado = new ArrayList<Produto>() ;
				Fornecedor fornecedorSelecionado = (Fornecedor) comboFornecedor.getSelectedItem();
				listaProdutoFornecedorSelecionado = fornecedorSelecionado.getListagemProdutos();
				
			}
		});
	

	
		JLabel lblDataDaCompra = new JLabel("Data da Compra");
		GridBagConstraints gbc_lblDataDaCompra = new GridBagConstraints();
		gbc_lblDataDaCompra.anchor = GridBagConstraints.WEST;
		gbc_lblDataDaCompra.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDaCompra.gridx = 1;
		gbc_lblDataDaCompra.gridy = 3;
		add(lblDataDaCompra, gbc_lblDataDaCompra);

		inputDate = new JTextField();
		GridBagConstraints gbc_inputDate = new GridBagConstraints();
		gbc_inputDate.insets = new Insets(0, 0, 5, 5);
		gbc_inputDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputDate.gridx = 1;
		gbc_inputDate.gridy = 4;
		add(inputDate, gbc_inputDate);
		inputDate.setColumns(10);

		JLabel lblProduto = new JLabel("Produto");
		GridBagConstraints gbc_lblProduto = new GridBagConstraints();
		gbc_lblProduto.anchor = GridBagConstraints.WEST;
		gbc_lblProduto.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduto.gridx = 1;
		gbc_lblProduto.gridy = 6;
		add(lblProduto, gbc_lblProduto);

		JLabel lblQuantidade = new JLabel("Quantidade");
		GridBagConstraints gbc_lblQuantidade = new GridBagConstraints();
		gbc_lblQuantidade.anchor = GridBagConstraints.WEST;
		gbc_lblQuantidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantidade.gridx = 2;
		gbc_lblQuantidade.gridy = 6;
		add(lblQuantidade, gbc_lblQuantidade);

		JLabel lblNewLabel = new JLabel("Valor");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 6;
		add(lblNewLabel, gbc_lblNewLabel);

		JComboBox comboProduto = new JComboBox();
		GridBagConstraints gbc_comboProduto = new GridBagConstraints();
		gbc_comboProduto.insets = new Insets(0, 0, 5, 5);
		gbc_comboProduto.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboProduto.gridx = 1;
		gbc_comboProduto.gridy = 7;
		add(comboProduto, gbc_comboProduto);

		for (int i = 0; i < listaProdutoFornecedorSelecionado.size(); i++) {
			Produto produto = listaProdutoFornecedorSelecionado.get(i);
			comboProduto.addItem(produto);
		}

		

		inputQuantidade = new JTextField();
		GridBagConstraints gbc_inputQuantidade = new GridBagConstraints();
		gbc_inputQuantidade.insets = new Insets(0, 0, 5, 5);
		gbc_inputQuantidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputQuantidade.gridx = 2;
		gbc_inputQuantidade.gridy = 7;
		add(inputQuantidade, gbc_inputQuantidade);
		inputQuantidade.setColumns(10);

		inputValor = new JTextField();
		GridBagConstraints gbc_inputValor = new GridBagConstraints();
		gbc_inputValor.insets = new Insets(0, 0, 5, 5);
		gbc_inputValor.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputValor.gridx = 3;
		gbc_inputValor.gridy = 7;
		add(inputValor, gbc_inputValor);
		inputValor.setColumns(10);

		JButton buttonAdicionar = new JButton("Adicionar");
		GridBagConstraints gbc_buttonAdicionar = new GridBagConstraints();
		gbc_buttonAdicionar.insets = new Insets(0, 0, 5, 0);
		gbc_buttonAdicionar.gridx = 4;
		gbc_buttonAdicionar.gridy = 7;
		add(buttonAdicionar, gbc_buttonAdicionar);

		GridBagConstraints gbc_panelforTable = new GridBagConstraints();
		gbc_panelforTable.insets = new Insets(0, 0, 5, 0);
		gbc_panelforTable.gridwidth = 4;
		gbc_panelforTable.fill = GridBagConstraints.BOTH;
		gbc_panelforTable.gridx = 1;
		gbc_panelforTable.gridy = 8;
		add(tableRegistroCompraPanel, gbc_panelforTable);

		GridBagConstraints gbc_txtTotalEmCompras = new GridBagConstraints();
		gbc_txtTotalEmCompras.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotalEmCompras.gridx = 1;
		gbc_txtTotalEmCompras.gridy = 9;
		txtTotalEmCompras.setText("Total em compras: R$");
		add(txtTotalEmCompras, gbc_txtTotalEmCompras);
		txtTotalEmCompras.setColumns(10);

		JTextPane txtpnValor = new JTextPane();
		txtpnValor.setText("VALOR");
		GridBagConstraints gbc_txtpnValor = new GridBagConstraints();
		gbc_txtpnValor.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnValor.fill = GridBagConstraints.BOTH;
		gbc_txtpnValor.gridx = 2;
		gbc_txtpnValor.gridy = 9;
		add(txtpnValor, gbc_txtpnValor);

		JButton buttonSalvar = new JButton("Salvar");
		GridBagConstraints gbc_buttonSalvar = new GridBagConstraints();
		gbc_buttonSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_buttonSalvar.gridx = 1;
		gbc_buttonSalvar.gridy = 10;
		add(buttonSalvar, gbc_buttonSalvar);

		JButton buttonCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_buttonCancelar = new GridBagConstraints();
		gbc_buttonCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_buttonCancelar.gridx = 2;
		gbc_buttonCancelar.gridy = 10;
		add(buttonCancelar, gbc_buttonCancelar);

	}

}
