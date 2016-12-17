package br.com.solvus.viewSwing.compras;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import br.com.solvus.controller.CompraController;
import br.com.solvus.model.Compra;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.Produto;
import br.com.solvus.viewSwing.util.DateLabelFormatter;
import br.com.solvus.viewSwing.util.ValidationException;

import java.awt.Font;

public class ListagemCompraPanel extends JPanel {

	private TabelaListagemCompraPanel tableListagemCompraPanel;
	private JTextField fieldDataInicial;
	private JTextField fieldDataFinal;
	private CompraController compraController;
	private List<Fornecedor> listaFornecedor;
	private Fornecedor fornecedorSelecionado;
	private RegistroCompraPanel registroCompraPanel;
	
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ListagemCompraPanel(final ConteudoCompraPanel conteudoCompraPanel) throws SQLException {
		
		tableListagemCompraPanel = new TabelaListagemCompraPanel(conteudoCompraPanel);
		compraController = new CompraController();
		listaFornecedor = compraController.listFornecedor();
		registroCompraPanel = new RegistroCompraPanel(conteudoCompraPanel);
		
		//JDatePicker
				UtilDateModel dateModel1 = new UtilDateModel();
				Properties p1 = new Properties();
				p1.put("text.today", "Today");
				p1.put("text.month", "Month");
				p1.put("text.year", "Year");
				
				UtilDateModel dateModel2 = new UtilDateModel();
				Properties p2 = new Properties();
				p2.put("text.today", "Today");
				p2.put("text.month", "Month");
				p2.put("text.year", "Year");
				
				JDatePanelImpl datePanelInicial = new JDatePanelImpl(dateModel1, p1);
				JDatePanelImpl datePanelFinal = new JDatePanelImpl(dateModel2, p2);
						
				final JDatePickerImpl datePickerInicial = new JDatePickerImpl(datePanelInicial, new DateLabelFormatter());
				final JDatePickerImpl datePickerFinal = new JDatePickerImpl(datePanelFinal, new DateLabelFormatter());
		
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
		
		
		
		final JComboBox comboFornecedor = new JComboBox();
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
		comboFornecedor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Fornecedor fornecedorSelecionado = (Fornecedor) comboFornecedor.getSelectedItem();
				getFornecedorSelecionado(fornecedorSelecionado);
			}
		});
		
		
		fieldDataInicial = new JTextField();
		GridBagConstraints gbc_fieldDataInicial = new GridBagConstraints();
		gbc_fieldDataInicial.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDataInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDataInicial.gridx = 2;
		gbc_fieldDataInicial.gridy = 3;
		//add(fieldDataInicial, gbc_fieldDataInicial);
		add(datePickerInicial,gbc_fieldDataInicial);
		//fieldDataInicial.setColumns(10);
		
		fieldDataFinal = new JTextField();
		GridBagConstraints gbc_fieldDataFinal = new GridBagConstraints();
		gbc_fieldDataFinal.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDataFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDataFinal.gridx = 3;
		gbc_fieldDataFinal.gridy = 3;
		//add(fieldDataFinal, gbc_fieldDataFinal);
		add(datePickerFinal,gbc_fieldDataFinal);
		//fieldDataFinal.setColumns(10);
		
		JButton buttonFiltrar = new JButton("Filtrar");
		GridBagConstraints gbc_buttonFiltrar = new GridBagConstraints();
		gbc_buttonFiltrar.insets = new Insets(0, 0, 5, 0);
		gbc_buttonFiltrar.gridx = 4;
		gbc_buttonFiltrar.gridy = 3;
		add(buttonFiltrar, gbc_buttonFiltrar);
		
		
		buttonFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Fornecedor fornecedorSelecionadoNoCombo = fornecedorSelecionado;
			//	String dataInicialString = fieldDataInicial.getText();
			//	String dataFinalString = fieldDataFinal.getText();
				Date dataInicial = (Date) datePickerInicial.getModel().getValue();
				Date dataFinal = (Date) datePickerFinal.getModel().getValue();
				
				List<Compra> listaFiltrada = new ArrayList<Compra>();
				try {
					listaFiltrada = compraController.filtrarListaDeCompra(fornecedorSelecionadoNoCombo, dataInicial, dataFinal);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ValidationException e2) {
				showErrorMessage(e2);
				}
				tableListagemCompraPanel.atualizarPosFiltro(listaFiltrada);
			}
		});
		
		
		GridBagConstraints gbc_panelforTableListagem = new GridBagConstraints();
		gbc_panelforTableListagem.gridwidth = 4;
		gbc_panelforTableListagem.fill = GridBagConstraints.BOTH;
		gbc_panelforTableListagem.gridx = 1;
		gbc_panelforTableListagem.gridy = 4;
		add(tableListagemCompraPanel, gbc_panelforTableListagem);
		
		buttonAdicionarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conteudoCompraPanel.showRegistroComprasPanel();
				
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			

		});
	}

	protected void getFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
				
	}
	protected void showErrorMessage(ValidationException e2) {
		JOptionPane.showMessageDialog(this, e2.getError().getMsg());
		
	}
}
