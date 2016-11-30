package br.com.solvus.viewSwing.fornecedor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.solvus.controller.FornecedorController;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.viewSwing.util.ButtonEditor;
import br.com.solvus.viewSwing.util.ButtonRenderer;

public class TabelaFornecedorPanel extends JPanel {

	private JTable table;
	private JButton botaoCadastroFornecedor;
	private CadastroFornecedorPanel cadastroFornecedorPanel;
	private ConteudoFornecedorPanel conteudoFornecedorPanel;
	private DefaultTableModel dm;
	private FornecedorController controller;

	public TabelaFornecedorPanel(final ConteudoFornecedorPanel conteudoFornecedorPanel) {

		super(new GridLayout(1, 0));
		this.conteudoFornecedorPanel = conteudoFornecedorPanel;
		setBorder(BorderFactory.createEtchedBorder());

		controller = new FornecedorController();
		botaoCadastroFornecedor = new JButton("Adicionar Fornecedor");
		cadastroFornecedorPanel = new CadastroFornecedorPanel(conteudoFornecedorPanel);

		dm = new DefaultTableModel();

		
		
		table = new JTable(dm);

		table.setPreferredScrollableViewportSize(new Dimension(700, 500));
		table.setFillsViewportHeight(true);

		atualizar();

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);

		// Add button
		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(botaoCadastroFornecedor);

		botaoCadastroFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conteudoFornecedorPanel.mostrarCadastroFornPanel();
			}
		});

	}



	public void atualizar() {
		List<Fornecedor> listaFornecedores = null;
		try {
			listaFornecedores = controller.listFornecedor();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		settarDataVector(dm, listaFornecedores);
	}

	private void settarDataVector(DefaultTableModel dm, List<Fornecedor> listaFornecedores) {
		Object[][] data = new Object[listaFornecedores.size()][6];
		String dataContrato;
		for (int i = 0; i < listaFornecedores.size(); i++) {

			data[i][0] = listaFornecedores.get(i).getId();
			data[i][1] = listaFornecedores.get(i).getNome();
			dataContrato = controller.convertDateToString(listaFornecedores.get(i).getDataContrato());
			data[i][2] = dataContrato;

			Fornecedor fornecedor = listaFornecedores.get(i);
			String produtosConcatenados = "";
			for (int j = 0; j < fornecedor.getListagemProdutos().size(); j++) {
				produtosConcatenados += fornecedor.getListagemProdutos().get(j).getNome() + " ,";
			}
			data[i][3] = produtosConcatenados.substring(0, produtosConcatenados.length() - 1);
			data[i][4] = "Editar";
			data[i][5] = "Excluir";

		}

		Object[] types = new Object[] { "ID", "Nome", "Data de Contrato", "Produtos", "Editar", "Excluir" };

		dm.setDataVector(data, types);

		table.getColumn("Editar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				editar(conteudoFornecedorPanel, table);

			}
		}));
		table.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
		table.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				excluir(table);

			}

		}));

	}



	
	private void editar(final ConteudoFornecedorPanel conteudoFornecedorPanel, final JTable table) {
		int idFornecedorAEditar = (int) table.getValueAt(table.getSelectedRow(), 0);
		Fornecedor fornecedor;
		try {
			fornecedor = controller.findById(idFornecedorAEditar);

			conteudoFornecedorPanel.fornecedorAEditar(fornecedor);

			conteudoFornecedorPanel.setEditingForn();

			atualizar();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void excluir(final JTable table) {	
		int idFornecedorAExcluir = (int) table.getValueAt(table.getSelectedRow(), 0);
		try {
			controller.deleteById(idFornecedorAExcluir);
			atualizar();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


}
