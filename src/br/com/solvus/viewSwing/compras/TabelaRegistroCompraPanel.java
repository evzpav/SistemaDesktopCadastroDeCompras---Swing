package br.com.solvus.viewSwing.compras;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.solvus.controller.ItemDeCompraController;
import br.com.solvus.model.ItemDeCompra;
import br.com.solvus.viewSwing.util.ButtonEditor;
import br.com.solvus.viewSwing.util.ButtonRenderer;

public class TabelaRegistroCompraPanel extends JPanel {

	private JTable tableItemDeCompra;
	private ConteudoCompraPanel conteudoCompraPanel;
	private DefaultTableModel dm;
	private ItemDeCompraController itemDeCompraController;

	public TabelaRegistroCompraPanel() {
		super(new GridLayout(1, 0));

		dm = new DefaultTableModel();

		tableItemDeCompra = new JTable(dm);
		itemDeCompraController = new ItemDeCompraController();
		tableItemDeCompra.setPreferredScrollableViewportSize(new Dimension(700, 500));
		tableItemDeCompra.setFillsViewportHeight(true);

		atualizar();
		JScrollPane scrollPane = new JScrollPane(tableItemDeCompra);
		add(scrollPane);
	}

	public void atualizar() {
		List<ItemDeCompra> listaItemDeCompra = null;
		try {
			listaItemDeCompra = itemDeCompraController.list();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		settarDataVector(dm, listaItemDeCompra);
	}

	private void settarDataVector(DefaultTableModel dm, List<ItemDeCompra> listaItemDeCompra) {
		Object[][] data = new Object[listaItemDeCompra.size()][5];

		for (int i = 0; i < listaItemDeCompra.size(); i++) {

			data[i][0] = listaItemDeCompra.get(i).getIdItemDeCompra();
			data[i][1] = listaItemDeCompra.get(i).getProduto().getNome();
			data[i][2] = listaItemDeCompra.get(i).getQuantidade();
			data[i][3] = listaItemDeCompra.get(i).getValorUnitario();
			data[i][4] = "Excluir";
		}

		Object[] types = new Object[] { "ID", "Produto", "Quantidade", "Valor Unitario", "Excluir" };

		dm.setDataVector(data, types);

		tableItemDeCompra.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
		tableItemDeCompra.getColumn("Excluir").setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				excluir(tableItemDeCompra);

			}

		}));
	}

	// private void editar(final ConteudoCompraPanel conteudoCompraPanel, final
	// JTable tableItemDeCompra) {
	// int idItemDeCompra = (int)
	// tableItemDeCompra.getValueAt(tableItemDeCompra.getSelectedRow(), 0);
	//
	// ItemDeCompra itemdecompra;
	//
	// try {
	// itemdecompra = itemDeCompraController.findById(idItemDeCompra);
	// itemDeCompraController.update(itemdecompra);
	//
	//
	// }
	//
	//
	// } catch (SQLException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// }

	private void excluir(final JTable tableItemDeCompra) {
		int idItemDeCompra = (int) tableItemDeCompra.getValueAt(tableItemDeCompra.getSelectedRow(), 0);

		try {
			itemDeCompraController.deleteById(idItemDeCompra);
			atualizar();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<Integer> getListaIdItemDeCompraDaTabela() {
		List<Integer> listaItemDeCompraDaTabela = new ArrayList<Integer>();

		for (int i = 0; i < tableItemDeCompra.getRowCount(); i++) {
			Integer idItemDeCompra = (Integer) tableItemDeCompra.getValueAt(i, 0);

			listaItemDeCompraDaTabela.add(idItemDeCompra);
		}
		System.out.println(listaItemDeCompraDaTabela);
		return listaItemDeCompraDaTabela;

	}

	public String somaValorTotal() {
		Double valorTotal = 0.00;
		for (int i = 0; i < tableItemDeCompra.getRowCount(); i++) {
			Double valorUnitario = (Double) tableItemDeCompra.getValueAt(i, 3);
			Integer quantidade = (Integer) tableItemDeCompra.getValueAt(i, 2);

			valorTotal = valorTotal + valorUnitario * quantidade;
		}
		String valorTotalString = String.valueOf(valorTotal);
		return valorTotalString;
	}

	public void limparTabelaItemDeCompra() {
		for (int i = 0; i < tableItemDeCompra.getRowCount(); i++) {
			int idItemDeCompra = (int) tableItemDeCompra.getValueAt(i, 0);
			try {
				itemDeCompraController.deleteById(idItemDeCompra);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		atualizar();
	}

}