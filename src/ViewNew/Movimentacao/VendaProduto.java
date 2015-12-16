package ViewNew.Movimentacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Bin.Venda;
import Controller.Model.Tabela.ItensCompostos.ModelTabelaItemVenda;
import Bin.ItemVenda;
import Bin.Produto;
import Persistence.Ponte.Dao;
import ViewNew.Consulta.ConsultaProduto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

public class VendaProduto extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tableProduto;
	private JTextField txtId;
	private JTextField txtValorTotal;
	private JDateChooser dtVenda;
	private JTextField txtProdutoId;
	private JTextField txtProdutoDescricao;
	private JTextField txtQuantidade;
	private JTextField txtPrecoProduto;
	private JButton btnBuscar;
	private ModelTabelaItemVenda model = new ModelTabelaItemVenda();
	private JButton btnInserir;
	private Dao banco = new Dao();
	private JButton btnFinalizar;
	private JLabel lblMsnErro;
	private JButton btnCancelar;
	private JButton btnDeletarVenda;
	DecimalFormat dfValor = new DecimalFormat("0.00");
	private JPopupMenu popupMenu;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_10;
	private JLabel label_12;
	private JLabel label_13;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VendaProduto dialog = new VendaProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VendaProduto() {
		setTitle("VENDA DE PRODUTO");
		setBounds(100, 100, 770, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new BorderLayout(5, 05));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);

			tableProduto = new JTable(model);
			tableProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			scrollPane.setViewportView(tableProduto);
			tableProduto.getTableHeader().setReorderingAllowed(false);
			// tamanho especifico da coluna
			// tabelaProduto.getColumn("Fornecedor").setPreferredWidth(220);
			tableProduto.getColumn("DESCRIÇÃO").setPreferredWidth(220);

			popupMenu = new JPopupMenu();
			addPopup(tableProduto, popupMenu);

			JMenuItem mntmRemover = new JMenuItem("Remover");
			mntmRemover.addActionListener(this);
			popupMenu.add(mntmRemover);

		}
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new GridLayout(0, 4, 5, 5));
				
						JLabel lblCdigo_1 = new JLabel("C\u00F3digo");
						panel_1.add(lblCdigo_1);
						lblCdigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
						
						label_10 = new JLabel("");
						panel_1.add(label_10);
								
										JLabel lblData = new JLabel("Data");
										panel_1.add(lblData);
										lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
								
										JLabel lblValor = new JLabel("Valor Total");
										panel_1.add(lblValor);
										lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
								
										txtId = new JTextField();
										panel_1.add(txtId);
										txtId.setFont(new Font("Tahoma", Font.PLAIN, 16));
										txtId.setEnabled(false);
										txtId.setBackground(new Color(255, 250, 205));
										txtId.setColumns(10);
								
								label_1 = new JLabel("");
								panel_1.add(label_1);
										
												dtVenda = new JDateChooser(new java.util.Date());
												panel_1.add(dtVenda);
												dtVenda.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
												dtVenda.setEnabled(false);
														
																txtValorTotal = new JTextField();
																panel_1.add(txtValorTotal);
																txtValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
																txtValorTotal.setDisabledTextColor(new Color(0, 0, 0));
																txtValorTotal.setBackground(new Color(255, 250, 205));
																txtValorTotal.setEnabled(false);
																txtValorTotal.setColumns(10);
														
																JLabel lblCdigo = new JLabel("C\u00F3digo");
																panel_1.add(lblCdigo);
																lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
														
																JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto");
																panel_1.add(lblDescrioDoProduto);
																lblDescrioDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
														
														label = new JLabel("");
														panel_1.add(label);
																
																label_6 = new JLabel("");
																panel_1.add(label_6);
														
																txtProdutoId = new JTextField();
																panel_1.add(txtProdutoId);
																txtProdutoId.setFont(new Font("Tahoma", Font.PLAIN, 16));
																txtProdutoId.setDisabledTextColor(new Color(0, 0, 0));
																txtProdutoId.setBackground(new Color(255, 250, 205));
																txtProdutoId.setEnabled(false);
																txtProdutoId.setColumns(10);
														
																txtProdutoDescricao = new JTextField();
																panel_1.add(txtProdutoDescricao);
																txtProdutoDescricao.setFont(new Font("Tahoma", Font.PLAIN, 16));
																txtProdutoDescricao.setDisabledTextColor(new Color(0, 0, 0));
																txtProdutoDescricao.setBackground(new Color(255, 250, 205));
																txtProdutoDescricao.setEnabled(false);
																txtProdutoDescricao.setColumns(10);
														
																btnBuscar = new JButton("Buscar");
																panel_1.add(btnBuscar);
																btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
																btnBuscar.setActionCommand("Buscar");
																btnBuscar.addActionListener(this);
														
														label_7 = new JLabel("");
														panel_1.add(label_7);
														
																JLabel lblQuantidade = new JLabel("Quantidade");
																panel_1.add(lblQuantidade);
																lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
														
																JLabel lblValorUnitario = new JLabel("Valor Unitario");
																panel_1.add(lblValorUnitario);
																lblValorUnitario.setFont(new Font("Tahoma", Font.PLAIN, 14));
														
														label_2 = new JLabel("");
														panel_1.add(label_2);
														
														label_3 = new JLabel("");
														panel_1.add(label_3);
								
										txtQuantidade = new JTextField();
										panel_1.add(txtQuantidade);
										txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
										txtQuantidade.setColumns(10);
										txtQuantidade.setEnabled(false);
								
										txtPrecoProduto = new JTextField();
										panel_1.add(txtPrecoProduto);
										txtPrecoProduto.setBackground(new Color(255, 250, 205));
										txtPrecoProduto.setEnabled(false);
										txtPrecoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
										txtPrecoProduto.setColumns(10);
								
										btnInserir = new JButton("Inserir");
										panel_1.add(btnInserir);
										btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 16));
										btnInserir.setActionCommand("Inserir");
										btnInserir.addActionListener(this);
										
												btnInserir.setEnabled(false);
								
								label_4 = new JLabel("");
								panel_1.add(label_4);
																				
																						lblMsnErro = new JLabel("Msn ERRO");
																						panel_1.add(lblMsnErro);
																						lblMsnErro.setForeground(Color.RED);
																						lblMsnErro.setVisible(false);
																				
																				label_12 = new JLabel("");
																				panel_1.add(label_12);
																				
																				label_13 = new JLabel("");
																				panel_1.add(label_13);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnDeletarVenda = new JButton("Deletar");
			btnDeletarVenda.setVisible(false);
			btnDeletarVenda.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnDeletarVenda.setEnabled(false);
			btnDeletarVenda.addActionListener(this);
			btnDeletarVenda.setActionCommand("Deletar");
			buttonPane.add(btnDeletarVenda);
			{
				btnFinalizar = new JButton("Finalizar");
				btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnFinalizar.setActionCommand("Finalizar");
				buttonPane.add(btnFinalizar);
				getRootPane().setDefaultButton(btnFinalizar);
				btnFinalizar.addActionListener(this);
				btnFinalizar.setEnabled(false);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnCancelar.setActionCommand("Cancelar");
				buttonPane.add(btnCancelar);
				btnCancelar.addActionListener(this);
			}
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();

		switch (acao) {
		case "Buscar":
			buscar();
			break;
		case "Cancelar":
			dispose();
			break;

		case "Finalizar":
			salvar();
			break;
		case "Inserir":
			inserir();
			valorTotal();
			break;

		case "Remover":
			remover();
			valorTotal();
			break;
		case "Deletar":
			deletar();
			break;
		default:
			break;
		}
	}

	private void deletar() {
		// TODO Auto-generated method stub
		btnBuscar.setEnabled(true);
		tableProduto.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnDeletarVenda.setVisible(false);

		Venda Venda = (Venda) banco.buscarPorId(Venda.class, Integer.parseInt(txtId.getText()));

		for (ItemVenda itemVenda : Venda.getLista()) {
			Produto produto = (Produto) banco.buscarPorId(Produto.class, itemVenda.getProduto().getId());

			float quantidadeAtual = produto.getQuantidade();

			float quantidadeEntrada = itemVenda.getQuantidade();

			float quantidadeAntiga = quantidadeAtual + quantidadeEntrada;
			produto.setQuantidade(quantidadeAntiga);

			boolean a = banco.salvarOuAtualizarObjeto(produto);
			if (a) {
				JOptionPane.showMessageDialog(contentPanel,
						"Produto " + produto.getDescricao() + " foi restaurado valor e custo.");
			}

			boolean b = banco.deletarObjeto(itemVenda);
			if (b) {
				JOptionPane.showMessageDialog(contentPanel, "item de Venda deletado com sucesso!");
			}

		}
		boolean e = banco.deletarObjeto(Venda);
		if (e) {
			JOptionPane.showMessageDialog(contentPanel, "Venda delatada com sucesso!");
		}
		dispose();

	}

	private void valorTotal() {
		try {

			float valor = 0;
			System.out.println(valor);
			for (int i = 0; i < model.getRowCount(); i++) {
				String a = (String) (model.getValueAt(i, 4));
				valor = valor + ((Float.parseFloat(a.replace(",", "."))));
			}
			txtValorTotal.setText(String.valueOf(dfValor.format(valor)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "ERRO! Refaça a Venda novamente : " + e);
			dispose();
		}

	}

	private void remover() {
		try {
			model.removeRow(tableProduto.getSelectedRow());
			if (model.getRowCount() <= 0) {
				btnFinalizar.setEnabled(false);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "ERRO! Refaça a Venda novamente : " + e);
			dispose();
		}

	}

	private void inserir() {
		try {

			Produto produto = (Produto) banco.buscarPorId(Produto.class, Integer.parseInt(txtProdutoId.getText()));
			float quantidade = Float.parseFloat(txtQuantidade.getText().replace(",", "."));
			float custo = produto.getCusto();
			float preco = Float.parseFloat(txtPrecoProduto.getText().replace(",", "."));
			ItemVenda item = new ItemVenda(produto, custo, preco, quantidade);

			boolean liberado = true;
			for (int i = 0; i < model.getRowCount(); i++) {
				if (produto.getId().equals(model.getValueAt(i, 0))) {
					JOptionPane.showMessageDialog(contentPanel, "Produto Igual, remova o produto inserido.");
					liberado = false;
				}
			}
			if (liberado) {
				model.addRow(item);
				btnFinalizar.setEnabled(true);
			}
			limpatxt();

		} catch (java.lang.NumberFormatException e) {
			lblMsnErro.setText("Insira numeros válidos nos espeços de numericos.");
			lblMsnErro.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao inserir produto : " + e);
		}

	}

	private boolean limpatxt() {
		try {

			txtProdutoDescricao.setText("");
			txtProdutoId.setText("");
			txtQuantidade.setText("");
			txtPrecoProduto.setText("");
			btnInserir.setEnabled(false);
			txtQuantidade.setEnabled(false);
			txtPrecoProduto.setEnabled(false);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao limpar texto : " + e);
			return false;
		}

	}

	private void buscar() {
		try {
			ConsultaProduto con = new ConsultaProduto();
			con.moduloEscolher();
			con.setModal(true);
			con.setVisible(true);

			Produto p = (Produto) con.getObj();
			txtProdutoId.setText(String.valueOf(p.getId()));
			txtProdutoDescricao.setText(String.valueOf(p.getDescricao()));
			txtPrecoProduto.setText(String.valueOf(dfValor.format(p.getPreco())));
			con.dispose();
			btnInserir.setEnabled(true);
			txtQuantidade.setEnabled(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao buscar produto : " + e);
		}

	}

	private void salvar() {
		// try {

		Date data = new Date(dtVenda.getDate().getTime());
		float totalPreco = Float.parseFloat(txtValorTotal.getText());
		float totalCusto = 0;
		Set<ItemVenda> lista = new HashSet<>();
		System.out.println("Tamanho " + model.getRowCount());
		for (int i = 0; i < model.getRowCount(); i++) {
			System.out.println(i);
			ItemVenda item=(ItemVenda) model.getObj(i);
			lista.add(item);
			totalCusto = totalCusto + item.getCusto();

		}
		System.out.println(lista.size());

		Bin.Venda Venda = new Bin.Venda(data, totalPreco, totalCusto, lista);
		if (!txtId.getText().equals("")) {
			Venda.setId(Integer.parseInt(txtId.getText()));
		}
		boolean salvou = false;
		if (!(lista.size() < 1)) {
			boolean salvouProds = false;
			salvou = banco.salvarOuAtualizarObjeto(Venda);
			for (ItemVenda ItemVenda : lista) {
				ItemVenda.setVenda(Venda);
				salvouProds = banco.salvarOuAtualizarObjeto(ItemVenda);

			}
			if (!salvouProds) {
				banco.deletarObjeto(Venda);
				salvou = false;
			}

			if (salvou) {
				atualizaEstoque(lista);
				dispose();
			} else {
				JOptionPane.showMessageDialog(contentPanel, "A Venda não pode ser salva!!");
			}
		} else {
			lblMsnErro.setText("Insira algum item antes de finalizar a Venda!");
			lblMsnErro.setVisible(true);
		}
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(contentPanel, "Erro ao salvar o
		// produto" + e);
		// dispose();
		// }

	}

	private boolean atualizaEstoque(Set<ItemVenda> lista) {
		try {
			for (ItemVenda ItemVenda : lista) {
				Produto produto = (Produto) banco.buscarPorId(Produto.class, ItemVenda.getProduto().getId());
				float quantidadeVelha = produto.getQuantidade();
				float custoUnitarioVelho = produto.getCusto();
				float quantidadeEntrada = ItemVenda.getQuantidade();
				float custoUnitarioEntrada = ItemVenda.getCusto();

				float custoTotalVelho = quantidadeVelha * custoUnitarioVelho;
				float custoTotalEntrada = quantidadeEntrada * custoUnitarioEntrada;

				float quantidadeAtual = quantidadeEntrada + quantidadeVelha;
				float custoTotalAtual = custoTotalEntrada + custoTotalVelho;

				float custoUnitarioAtual = custoTotalAtual / quantidadeAtual;

				produto.setCusto(custoUnitarioAtual);
				produto.setQuantidade(quantidadeAtual);

				banco.salvarOuAtualizarObjeto(produto);

			}
			JOptionPane.showMessageDialog(contentPanel,
					"A Venda foi salva com sucesso! \n Estoque atualizado com sucesso");
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Não conseguimos atualizar o estoque faça um balanço");
			return false;
		}

	}

	public void inserirVenda(Venda Venda) {

		try {
			
			Venda = (Bin.Venda) banco.buscarPorIdcarregaListaVenda(Venda.getClass(), Venda.getId());

			txtId.setText(String.valueOf(Venda.getId()));
			dtVenda.setDate(Venda.getData());
			txtValorTotal.setText(String.valueOf(dfValor.format(Venda.getValor())));

			for (ItemVenda instanciaVenda : Venda.getLista()) {
				model.addRow(instanciaVenda);

			}

			btnBuscar.setEnabled(true);
			btnFinalizar.setEnabled(true);
			btnInserir.setEnabled(false);
			tableProduto.setEnabled(true);
			txtQuantidade.setEnabled(false);
			valorTotal();
			desbloquear(false);

			// novo
			btnDeletarVenda.setVisible(true);
			btnDeletarVenda.setEnabled(true);
			btnFinalizar.setEnabled(false);
		} catch (Exception e)

		{
			JOptionPane.showMessageDialog(contentPanel, "Erro ao resgatar os produtos : " + e);
			dispose();
		}

	}

	// novo
	private void desbloquear(boolean a) {
		btnBuscar.setEnabled(a);
		btnInserir.setEnabled(a);
		tableProduto.setEnabled(a);
		txtQuantidade.setEnabled(a);
		txtPrecoProduto.setEnabled(a);
		popupMenu.setEnabled(a);

	}
}
