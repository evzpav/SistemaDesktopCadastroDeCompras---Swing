package br.com.solvus.viewSwing.produto;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JTextPane;

import br.com.solvus.controller.ProdutoController;
import br.com.solvus.model.FornecedorDao;
import br.com.solvus.model.Produto;
import br.com.solvus.model.ProdutoDao;
import br.com.solvus.viewSwing.util.MainFrame;
import br.com.solvus.viewSwing.util.ValidationError;

import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CadastroProdutoPanel extends JPanel {
	private JTextField nomeField;
	private boolean isEditing;
	private Produto produtoAEditar;
	private int idProdutoAEditar;
	private ProdutoController controller;

	
	public CadastroProdutoPanel(final ConteudoProdutoPanel conteudoProdutoPanel) {
		
		controller = new ProdutoController();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 261, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel nomeProdutoLabel = new JLabel("Nome do Produto");
		GridBagConstraints gbc_nomeProdutoLabel = new GridBagConstraints();
		gbc_nomeProdutoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nomeProdutoLabel.gridx = 1;
		gbc_nomeProdutoLabel.gridy = 2;
		add(nomeProdutoLabel, gbc_nomeProdutoLabel);

		nomeField = new JTextField();
		GridBagConstraints gbc_nomeField = new GridBagConstraints();
		gbc_nomeField.insets = new Insets(0, 0, 5, 0);
		gbc_nomeField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomeField.gridx = 3;
		gbc_nomeField.gridy = 2;
		add(nomeField, gbc_nomeField);
		nomeField.setColumns(10);

		JButton botaoSalvar = new JButton("Salvar");
		GridBagConstraints gbc_botaoSalvar = new GridBagConstraints();
		gbc_botaoSalvar.gridwidth = 2;
		gbc_botaoSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_botaoSalvar.gridx = 1;
		gbc_botaoSalvar.gridy = 8;
		add(botaoSalvar, gbc_botaoSalvar);

		JButton botaoCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_botaoCancelar = new GridBagConstraints();
		gbc_botaoCancelar.gridx = 3;
		gbc_botaoCancelar.gridy = 8;
		add(botaoCancelar, gbc_botaoCancelar);

		botaoSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String inputName = nomeField.getText();
				
				ValidationError validation = null;
				try {
					validation = controller.validateInputEntry(inputName, isEditing);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {

					if (validation.isValid()) {
						if (isEditing) {
							produtoAEditar.setNome(inputName);
							controller.update(produtoAEditar);
		
						} else {
							Produto produto = new Produto(inputName);
							controller.save(produto);

						}
						mostrarMensagemSalvoComSucesso();
						conteudoProdutoPanel.showTabelaProdPanel();
						limpaCampo();
					}
				} catch (Exception exc) {
					exc.printStackTrace();
				}

			}

		});

		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampo();
				conteudoProdutoPanel.showTabelaProdPanel();
			}
		});

	}


	private void mostrarMensagemSalvoComSucesso() {
		String nomeProduto = nomeField.getText();
		JOptionPane.showMessageDialog(this, "Produto salvo com sucesso");
	}

	

	private void limpaCampo() {
		nomeField.setText("");
	}
	public void isNew() {
		isEditing = false;
	}

	public void isEditing() {
		isEditing = true;
	}

	public void setProdutoAEditar(Produto produto) {
		int idProduto = produto.getId();
		nomeField.setText(produto.getNome());
	}

}
