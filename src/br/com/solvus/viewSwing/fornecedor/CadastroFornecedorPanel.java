package br.com.solvus.viewSwing.fornecedor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import br.com.solvus.controller.FornecedorController;
import br.com.solvus.controller.ProdutoController;
import br.com.solvus.model.Fornecedor;
import br.com.solvus.model.Produto;
import br.com.solvus.viewSwing.util.ValidationError;

public class CadastroFornecedorPanel extends JPanel {

	private JLabel nameLabel;
	private JTextField nameField;
	private JButton botaoSalvar;
	private JButton botaoCancelar;
	private JLabel dateLabel;
	private JTextField dateField;
	private JLabel produtosLabel;
	private JCheckBox produtosCheckbox;
	private List<JCheckBox> checkboxLista;
	private List<Produto> listaProdutos;
	private boolean isEditing;
	private Fornecedor fornecedorAEditar;
	private int idFornecedorAEditar;
	private GridBagConstraints gc;
	private JPanel checkBoxPanel;
	private FornecedorController controller;
	ProdutoController produtoController;

	public CadastroFornecedorPanel(final ConteudoFornecedorPanel conteudoFornecedorPanel) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		nameLabel = new JLabel("Nome: ");
		nameField = new JTextField(10);
		dateLabel = new JLabel("Data de Contrato: ");
		dateField = new JTextField(10);
		produtosLabel = new JLabel("Produtos: ");
		checkboxLista = new ArrayList<JCheckBox>();
		checkBoxPanel = new JPanel();
		controller = new FornecedorController();
		produtoController = new ProdutoController();

		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

		botaoSalvar = new JButton("Salvar");

		final CadastroFornecedorPanel cadastroPanel = this;

		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String inputName = nameField.getText();
				String inputDate = dateField.getText();
				List<Produto> produtosSelecionadosLista = listSelectedProdutos();

				try {

					ValidationError validation = controller.validateInputEntry(inputName, inputDate, isEditing,
							produtosSelecionadosLista);

					if (validation.isValid()) {
						if (isEditing) {

							fornecedorAEditar.setListagemProdutos(produtosSelecionadosLista);
							fornecedorAEditar.setNome(inputName);
							fornecedorAEditar.setDataContrato(controller.convertStringToDate(inputDate));
							controller.update(fornecedorAEditar);

						} else {

							Date dataConvertida = controller.convertStringToDate(inputDate);
							Fornecedor fornecedor = new Fornecedor(inputName, dataConvertida);
							fornecedor.setListagemProdutos(produtosSelecionadosLista);
							controller.save(fornecedor);
						}

						mostrarMensagemSalvoComSucesso();
						conteudoFornecedorPanel.showTabelaFornPanel();

						clearFields();

					} else {
						JOptionPane.showMessageDialog(cadastroPanel, validation.getMsg());
					}

				} catch (Exception ee) {

				}
			}

		});

		// botão cancelar
		botaoCancelar = new JButton("Cancelar"); // meu
		botaoCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				dateField.setText("");
				conteudoFornecedorPanel.showTabelaFornPanel();

			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Adicione Fornecedor");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		setLayout(new GridBagLayout());

		gc = new GridBagConstraints();

		// 1st row

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);

		// 2.1 row

		gc.weightx = 1;
		gc.weighty = 0.3;

		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(dateLabel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(dateField, gc);

		// produtos checkbox

		gc.weightx = 1;
		gc.weighty = 0.3;

		gc.gridx = 0;
		gc.gridy = 2;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(produtosLabel, gc);

		gc.gridx = 1;
		gc.gridy++;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;

		generateCheckbox();

		// 3rd row LEFT
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridy++;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(botaoSalvar, gc);

		// 3rd row RIGHT
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 1;
		gc.gridy++;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(botaoCancelar, gc);

	}

	public void generateCheckbox() {
		checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		try {

			listaProdutos = produtoController.list();
			for (Produto produto : listaProdutos) {
				produtosCheckbox = new JCheckBox(produto.getNome());
				checkboxLista.add(produtosCheckbox);
				checkBoxPanel.add(produtosCheckbox);
				if (isEditing) {
					for (Produto produto1 : fornecedorAEditar.getListagemProdutos()) {
						if (produto1.getNome().equals(produto.getNome())) {
							produtosCheckbox.setSelected(true);
						}
					}
				}
			}
			gc.gridx = 1;
			gc.gridy = 2;
			gc.insets = new Insets(0, 0, 0, 0);
			gc.anchor = GridBagConstraints.LINE_START;

			this.add(checkBoxPanel, gc);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.revalidate();
		this.repaint();
	}

	private void clearFields() {
		nameField.setText("");
		dateField.setText("");

		removeCheckboxPanel();
		generateCheckbox();
	}

	private void mostrarMensagemSalvoComSucesso() {
		JOptionPane.showMessageDialog(this, "Fornecedor salvo com sucesso");
	}

	public void removeCheckboxPanel() {
		List<JPanel> listadeCheckbox = new ArrayList<JPanel>();
		for (int i = 0; i < getComponentCount(); i++) {
			Component check = this.getComponent(i);

			if (check instanceof JPanel) {
				JPanel checkConvertido = (JPanel) check;
				listadeCheckbox.add(checkConvertido);
			}
		}
		for (JPanel jCheckBox : listadeCheckbox) {
			this.remove(jCheckBox);
		}

		this.revalidate();
		this.repaint();

	}

	private List<Produto> listSelectedProdutos() {
		List<Produto> produtosSelecionadosLista = new ArrayList<Produto>();
		for (JCheckBox produtoCheckbox : checkboxLista) {
			if (produtoCheckbox.isSelected()) {
				for (Produto produto1 : listaProdutos) {
					if (produto1.getNome().equals(produtoCheckbox.getText())) {
						produtosSelecionadosLista.add(produto1);
					}

				}
			}
		}
		return produtosSelecionadosLista;
	}

	public void setEditFornecedor(Fornecedor fornecedor) {
		String dataContrato;
		fornecedorAEditar = fornecedor;
		nameField.setText(fornecedor.getNome());
		dataContrato = controller.convertDateToString(fornecedor.getDataContrato());
		System.out.println(dataContrato);
		dateField.setText(dataContrato);

		idFornecedorAEditar = fornecedor.getId();
	}

	public void isNew() {
		isEditing = false;
	}

	public void isEditing() {
		isEditing = true;
	}

}
