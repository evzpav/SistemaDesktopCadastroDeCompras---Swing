package br.com.solvus.viewSwing.produto;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.solvus.controller.ProdutoController;
import br.com.solvus.model.Produto;
import br.com.solvus.viewSwing.util.ButtonEditor;
import br.com.solvus.viewSwing.util.ButtonRenderer;

public class TabelaProdutoPanel extends JPanel {

	private JTable table;
	private JButton botaoCadastroProduto;
	private FlowLayout flowlayout;
	boolean editando;
	private DefaultTableModel dm;
	private ConteudoProdutoPanel conteudoProdutoPanel;
	private ProdutoController controller;

	public TabelaProdutoPanel(final ConteudoProdutoPanel conteudoProdutoPanel) throws SQLException {
		super(new GridLayout(1, 0));
		setBorder(BorderFactory.createEtchedBorder());
		botaoCadastroProduto = new JButton("Adicionar Produto");
		this.conteudoProdutoPanel = conteudoProdutoPanel;

		controller = new ProdutoController();
	

		dm = new DefaultTableModel();

		List<Produto> listaProduto = controller.list();

		table = new JTable(dm);

		table.setPreferredScrollableViewportSize(new Dimension(700, 500));
		table.setFillsViewportHeight(true);

		settarDataVector(dm, listaProduto);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);
		flowlayout = new FlowLayout(FlowLayout.LEFT);
		setLayout(flowlayout);

		add(botaoCadastroProduto);
		botaoCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == botaoCadastroProduto) {
					conteudoProdutoPanel.isNew();
				}
			}
		});

	}

	private void settarDataVector(DefaultTableModel dm, List<Produto> listaProduto) {
		Object[][] data = new Object[listaProduto.size()][4];
		for (int i = 0; i < listaProduto.size(); i++) {

			data[i][0] = listaProduto.get(i).getId();
			data[i][1] = listaProduto.get(i).getNome();
			data[i][2] = "Editar";
			data[i][3] = "Excluir";
		}

		Object[] types = new Object[] { "ID", "Nome", "Editar", "Excluir" };

		dm.setDataVector(data, types);

		table.getColumn("Editar").setCellRenderer(new ButtonRenderer());
		table.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				editar(conteudoProdutoPanel, table);

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

	public void mostrarCadastroProdutoPanel() {

	}

	

	

	private void mostrarMensagemNaoPodeExcluirProduto() {
		JOptionPane.showMessageDialog(this,
				"O produto não pode ser excluido porque já está relacionado à um fornecedor");
	}

	private void mostrarMensagemProdutoExcluido() {
		JOptionPane.showMessageDialog(this, "Produto excluido.");
	}

	public boolean editando(Boolean editando) {
		return editando;
	}

	private void excluir(final JTable table) {
		int idProdutoaExcluir = (int) table.getValueAt(table.getSelectedRow(), 0);
		try {
			if (!controller.hasRelationshipFornecedor(idProdutoaExcluir) || !controller.hasRelationshipCompra(idProdutoaExcluir)) {
				
				controller.deleteById(idProdutoaExcluir);
				mostrarMensagemProdutoExcluido();
				refreshTable();

			} else {
				mostrarMensagemNaoPodeExcluirProduto();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void editar(final ConteudoProdutoPanel conteudoProdutoPanel, final JTable table) {
		int idProdutoaExcluir = (int) table.getValueAt(table.getSelectedRow(), 0);
		Produto produto;
		try {
			produto = controller.findById(idProdutoaExcluir);
			conteudoProdutoPanel.setEditing();
			conteudoProdutoPanel.produtoAEditar(produto);
			refreshTable();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void refreshTable() throws SQLException {
		List<Produto> listaProduto = controller.list();
		settarDataVector(dm, listaProduto);
	}
}
