package br.com.solvus.viewSwing.graficos;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.ui.RefineryUtilities;

import br.com.solvus.controller.CompraController;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.viewSwing.util.DateLabelFormatter;
import br.com.solvus.viewSwing.util.ValidationException;

import java.awt.BorderLayout;
import java.awt.Font;

public class Graficos extends JPanel {

	private JTextField fieldDataInicial;
	private JTextField fieldDataFinal;
	private CompraController compraController;
	private List<Fornecedor> listaFornecedor;
	private Fornecedor fornecedorSelecionado;
	private PieChartDemo1 demoPie;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public Graficos() {
	
		
		demoPie = new PieChartDemo1("blablabla");
		
		
		this.setLayout(new BorderLayout());
		demoPie.setVisible(true);
		this.add(demoPie, BorderLayout.CENTER);
		this.validate();
//		// JDatePicker
//		UtilDateModel dateModel1 = new UtilDateModel();
//		Properties p1 = new Properties();
//		p1.put("text.today", "Today");
//		p1.put("text.month", "Month");
//		p1.put("text.year", "Year");
//
//		UtilDateModel dateModel2 = new UtilDateModel();
//		Properties p2 = new Properties();
//		p2.put("text.today", "Today");
//		p2.put("text.month", "Month");
//		p2.put("text.year", "Year");
//
//		JDatePanelImpl datePanelInicial = new JDatePanelImpl(dateModel1, p1);
//		JDatePanelImpl datePanelFinal = new JDatePanelImpl(dateModel2, p2);
//
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[] { 0, 191, 0, 0, 0, 0 };
//		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
//		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
//		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
//		setLayout(gridBagLayout);
//
//		JLabel lblPainelDeControle = new JLabel("Painel de Controle");
//		lblPainelDeControle.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		GridBagConstraints gbc_lblPainelDeControle = new GridBagConstraints();
//		gbc_lblPainelDeControle.anchor = GridBagConstraints.WEST;
//		gbc_lblPainelDeControle.insets = new Insets(0, 0, 5, 5);
//		gbc_lblPainelDeControle.gridx = 1;
//		gbc_lblPainelDeControle.gridy = 0;
//		add(lblPainelDeControle, gbc_lblPainelDeControle);
//
//		JLabel lblFornecedor = new JLabel("Fornecedores");
//		GridBagConstraints gbc_lblFornecedor = new GridBagConstraints();
//		gbc_lblFornecedor.insets = new Insets(0, 0, 5, 5);
//		gbc_lblFornecedor.anchor = GridBagConstraints.WEST;
//		gbc_lblFornecedor.gridx = 1;
//		gbc_lblFornecedor.gridy = 1;
//		add(lblFornecedor, gbc_lblFornecedor);
//
//		JLabel labelProdutos = new JLabel("Produtos");
//		GridBagConstraints gbc_labelProdutos = new GridBagConstraints();
//		gbc_labelProdutos.anchor = GridBagConstraints.WEST;
//		gbc_labelProdutos.insets = new Insets(0, 0, 5, 5);
//		gbc_labelProdutos.gridx = 3;
//		gbc_labelProdutos.gridy = 1;
//		add(labelProdutos, gbc_labelProdutos);
//
//		final JComboBox comboFornecedor = new JComboBox();
//		GridBagConstraints gbc_comboFornecedor = new GridBagConstraints();
//		gbc_comboFornecedor.insets = new Insets(0, 0, 5, 5);
//		gbc_comboFornecedor.fill = GridBagConstraints.HORIZONTAL;
//		gbc_comboFornecedor.gridx = 1;
//		gbc_comboFornecedor.gridy = 2;
//		add(comboFornecedor, gbc_comboFornecedor);
//
//		fieldDataInicial = new JTextField();
//
//		JComboBox comboProduto = new JComboBox();
//		GridBagConstraints gbc_comboProduto = new GridBagConstraints();
//		gbc_comboProduto.insets = new Insets(0, 0, 5, 5);
//		gbc_comboProduto.fill = GridBagConstraints.HORIZONTAL;
//		gbc_comboProduto.gridx = 3;
//		gbc_comboProduto.gridy = 2;
//		add(comboProduto, gbc_comboProduto);
//
//		JLabel lblDataDeCompraInicial = new JLabel("Data de Compra Inicial");
//		GridBagConstraints gbc_lblDataDeCompraInicial = new GridBagConstraints();
//		gbc_lblDataDeCompraInicial.anchor = GridBagConstraints.WEST;
//		gbc_lblDataDeCompraInicial.insets = new Insets(0, 0, 5, 5);
//		gbc_lblDataDeCompraInicial.gridx = 1;
//		gbc_lblDataDeCompraInicial.gridy = 3;
//		add(lblDataDeCompraInicial, gbc_lblDataDeCompraInicial);
//		// fieldDataInicial.setColumns(10);
//
//		fieldDataFinal = new JTextField();
//
//		JLabel lblDataDeCompraFinal = new JLabel("Data de Compra Final");
//		GridBagConstraints gbc_lblDataDeCompraFinal = new GridBagConstraints();
//		gbc_lblDataDeCompraFinal.anchor = GridBagConstraints.WEST;
//		gbc_lblDataDeCompraFinal.insets = new Insets(0, 0, 5, 5);
//		gbc_lblDataDeCompraFinal.gridx = 3;
//		gbc_lblDataDeCompraFinal.gridy = 3;
//		add(lblDataDeCompraFinal, gbc_lblDataDeCompraFinal);
//
//		final JDatePickerImpl datePickerInicial = new JDatePickerImpl(datePanelInicial, new DateLabelFormatter());
//		GridBagConstraints gbc_fieldDataInicial = new GridBagConstraints();
//		gbc_fieldDataInicial.insets = new Insets(0, 0, 5, 5);
//		gbc_fieldDataInicial.fill = GridBagConstraints.HORIZONTAL;
//		gbc_fieldDataInicial.gridx = 1;
//		gbc_fieldDataInicial.gridy = 4;
//		// add(fieldDataInicial, gbc_fieldDataInicial);
//		add(datePickerInicial, gbc_fieldDataInicial);
//		final JDatePickerImpl datePickerFinal = new JDatePickerImpl(datePanelFinal, new DateLabelFormatter());
//		GridBagConstraints gbc_fieldDataFinal = new GridBagConstraints();
//		gbc_fieldDataFinal.insets = new Insets(0, 0, 5, 5);
//		gbc_fieldDataFinal.fill = GridBagConstraints.HORIZONTAL;
//		gbc_fieldDataFinal.gridx = 3;
//		gbc_fieldDataFinal.gridy = 4;
//		// add(fieldDataFinal, gbc_fieldDataFinal);
//		add(datePickerFinal, gbc_fieldDataFinal);
//		// fieldDataFinal.setColumns(10);
//
//		JButton buttonFiltrar = new JButton("Filtrar");
//		GridBagConstraints gbc_buttonFiltrar = new GridBagConstraints();
//		gbc_buttonFiltrar.insets = new Insets(0, 0, 5, 0);
//		gbc_buttonFiltrar.gridx = 4;
//		gbc_buttonFiltrar.gridy = 4;
//		add(buttonFiltrar, gbc_buttonFiltrar);
//
//		JPanel panelGraph = new JPanel();
//		GridBagConstraints gbc_panelGraph = new GridBagConstraints();
//		gbc_panelGraph.gridwidth = 5;
//		gbc_panelGraph.insets = new Insets(0, 0, 0, 5);
//		gbc_panelGraph.fill = GridBagConstraints.BOTH;
//		gbc_panelGraph.gridx = 0;
//		gbc_panelGraph.gridy = 5;
//		// add(panelGraph, gbc_panelGraph);
//
//		// panelGraph.add(demoPie.pack());
//
//		//demoPie.pack();
//		//RefineryUtilities.centerFrameOnScreen(demoPie);
//		//demoPie.setVisible(true);

		
	}

	protected void getFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;

	}

	protected void showErrorMessage(ValidationException e2) {
		JOptionPane.showMessageDialog(this, e2.getError().getMsg());

	}
}
