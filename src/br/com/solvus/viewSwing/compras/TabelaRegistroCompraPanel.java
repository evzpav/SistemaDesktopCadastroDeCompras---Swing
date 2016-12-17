package br.com.solvus.viewSwing.compras;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	private List<ItemDeCompra> listaPreenchida;
	private RegistroCompraPanel registroCompraPanel;
	private Double valorTotal;

	public TabelaRegistroCompraPanel(RegistroCompraPanel registroCompraPanel) {
		super(new GridLayout(1, 0));
		this.registroCompraPanel = registroCompraPanel;

		dm = new DefaultTableModel();

		tableItemDeCompra = new JTable(dm);
		itemDeCompraController = new ItemDeCompraController();
		tableItemDeCompra.setPreferredScrollableViewportSize(new Dimension(700, 500));
		tableItemDeCompra.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(tableItemDeCompra);
		add(scrollPane);
	}

	public void atualizar() {
		listaPreenchida = getListaPreenchida();
		settarDataVector(dm, listaPreenchida);
	}

	private void settarDataVector(DefaultTableModel dm, List<ItemDeCompra> listaItemDeCompra) {

		Object[][] data = new Object[listaItemDeCompra.size()][5];

		for (int i = 0; i < listaItemDeCompra.size(); i++) {
			data[i][0] = listaItemDeCompra.get(i).getIdItemDeCompra();
			data[i][1] = listaItemDeCompra.get(i).getProduto().getNome();
			data[i][2] = listaItemDeCompra.get(i).getQuantidade();
			data[i][3] = converteDoubleToMoney(listaItemDeCompra.get(i).getValorUnitario());

			data[i][4] = "Excluir";

			Object[] types = new Object[] { "ID", "Produto", "Quantidade", "Valor Unitario", "Excluir" };

			dm.setDataVector(data, types);

			tableItemDeCompra.getColumn("Excluir").setCellRenderer(new ButtonRenderer());
			tableItemDeCompra.getColumn("Excluir")
					.setCellEditor(new ButtonEditor(new JCheckBox(), new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							excluir(tableItemDeCompra);

						}

					}));
		}
	}

	private void excluir(JTable tableItemDeCompra) {
		this.listaPreenchida.remove(tableItemDeCompra.getSelectedRow());

		atualizar();

		registroCompraPanel.mostrarValorTotal();

		if (listaPreenchida.isEmpty()) {
			dm.removeRow(dm.getRowCount() - 1);
		}
	//	registroCompraPanel.mostrarValorTotal();

	}

	public List<Integer> getListaIdItemDeCompraDaTabela() {
		List<Integer> listaItemDeCompraDaTabela = new ArrayList<Integer>();

		for (int i = 0; i < tableItemDeCompra.getRowCount(); i++) {
			Integer idItemDeCompra = (Integer) tableItemDeCompra.getValueAt(i, 0);

			listaItemDeCompraDaTabela.add(idItemDeCompra);
		}
		return listaItemDeCompraDaTabela;

	}

	public String somaValorTotal() {
		Double valorTotal = 0.00;
		String valorTotalString = "00,00";
		if (listaPreenchida.size() > 0) {
			for (ItemDeCompra itemDeCompra : listaPreenchida) {
				Integer	quantidade = itemDeCompra.getQuantidade();
				Double	valorUnitario = itemDeCompra.getValorUnitario();
				valorTotal = valorTotal + valorUnitario * quantidade;
			}

			setValorTotalDouble(valorTotal);
			valorTotalString = converteDoubleToMoney(valorTotal);
		}
	
		return valorTotalString;
	}

	public String converteDoubleToMoney(Double valorTotal) {
		Locale ptBr = new Locale("pt", "BR");
		
		String valorTotalString = NumberFormat.getCurrencyInstance(ptBr).format(valorTotal);
	
	
		
		return valorTotalString;
		
	}

	public void setValorTotalDouble(Double valorTotal) {
		this.valorTotal = valorTotal;

	}

	public Double getValorTotalDouble() {
		return valorTotal;
	}

	public void limparTabelaItemDeCompra() {
		for (int i = 0; i < listaPreenchida.size(); i++) {
			this.listaPreenchida.remove(i);
		}

		atualizar();
		dm.removeRow(dm.getRowCount() - 1);
	}

	public void setListaPreenchida(List<ItemDeCompra> listaPreenchida2) {

		this.listaPreenchida = listaPreenchida2;

	}

	public List<ItemDeCompra> getListaPreenchida() {
		return listaPreenchida;
	}

}