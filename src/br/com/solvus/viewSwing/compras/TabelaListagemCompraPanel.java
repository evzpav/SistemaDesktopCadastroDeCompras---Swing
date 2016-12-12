package br.com.solvus.viewSwing.compras;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.com.solvus.controller.CompraController;
import br.com.solvus.controller.ItemDeCompraController;
import br.com.solvus.model.Compra;
import br.com.solvus.model.ItemDeCompra;
import br.com.solvus.viewSwing.util.ButtonEditor;
import br.com.solvus.viewSwing.util.ButtonRenderer;

public class TabelaListagemCompraPanel extends JPanel {

	private JTable tableListagemDeCompra;
	private DefaultTableModel dm;
	private CompraController compraController;
	private ItemDeCompraController itemDeCompraController;
	private ConteudoCompraPanel conteudoCompraPanel;
	private TabelaRegistroCompraPanel tableRegistroCompraPanel;
	
	private RegistroCompraPanel registroCompraPanel;

	public TabelaListagemCompraPanel(final ConteudoCompraPanel conteudoCompraPanel) {
		super(new GridLayout(1, 0));
		setBorder(BorderFactory.createEtchedBorder());

		dm = new DefaultTableModel();

		tableListagemDeCompra = new JTable(dm);
		compraController = new CompraController();
		itemDeCompraController = new ItemDeCompraController();
		tableListagemDeCompra.setPreferredScrollableViewportSize(new Dimension(700, 500));
		tableListagemDeCompra.setFillsViewportHeight(true);
		this.conteudoCompraPanel = conteudoCompraPanel;
		
		tableRegistroCompraPanel = new TabelaRegistroCompraPanel(registroCompraPanel);
		

		
		atualizar();
		JScrollPane scrollpane = new JScrollPane(tableListagemDeCompra);
		this.add(scrollpane);

	}

	public void atualizar() {
		List<Compra> listaCompra = null;
		try {
			listaCompra = compraController.list();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		settarDataVector(dm, listaCompra);

	}

	public void atualizarPosFiltro(List<Compra> listaFiltrada) {
		settarDataVector(dm, listaFiltrada);

	}

	private void settarDataVector(DefaultTableModel dm, List<Compra> listaCompra) {
		Object[][] data = new Object[listaCompra.size()][7];

		for (int i = 0; i < listaCompra.size(); i++) {

			data[i][0] = listaCompra.get(i).getId();
			data[i][1] = listaCompra.get(i).getFornecedor().getNome();
			data[i][2] = listaCompra.get(i).getDataCompra();
			data[i][3] = tableRegistroCompraPanel.converteDoubleToMoney(listaCompra.get(i).getValorTotal());
			

			int idCompra = listaCompra.get(i).getId();
			try {
				List<ItemDeCompra> listaItemDeCompra = itemDeCompraController.list(idCompra);
				String produtosConcatenados = "";

				for (int j = 0; j < listaItemDeCompra.size(); j++) {
					produtosConcatenados += listaItemDeCompra.get(j).getProduto() + ", ";
					data[i][4] = produtosConcatenados.substring(0, produtosConcatenados.length() - 1);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			data[i][5] = "Editar";
			data[i][6] = "Excluir";
		}

		Object[] types = new Object[] { "ID", "Fornecedor", "Data da Compra", "Total da Venda", "Produtos", "Editar",
				"Excluir" };

		dm.setDataVector(data, types);

		tableListagemDeCompra.getColumn("Editar").setCellRenderer(new ButtonRenderer());
		tableListagemDeCompra.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				editar(conteudoCompraPanel, tableListagemDeCompra);

			}
		}));

		tableListagemDeCompra.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
		tableListagemDeCompra.getColumn("Excluir")
				.setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						excluir(tableListagemDeCompra);
					}
				}));
	}

	private void editar(final ConteudoCompraPanel conteudoCompraPanel, final JTable tableListagemCompra) {

		int idCompra = (int) tableListagemCompra.getValueAt(tableListagemCompra.getSelectedRow(), 0);

		Compra compra;

		try {
			compra = compraController.findById(idCompra);
			conteudoCompraPanel.compraAEditar(compra);
			conteudoCompraPanel.setEditingCompra();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void excluir(final JTable tableListagemDeCompra) {
		int idCompra = (int) tableListagemDeCompra.getValueAt(tableListagemDeCompra.getSelectedRow(), 0);

		try {
			compraController.deleteById(idCompra);
			atualizar();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
