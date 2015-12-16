package ViewNew.Movimentacao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Bin.Compra;
import Bin.ItemCompra;
import Bin.Produto;
import Controller.Model.Tabela.ItensCompostos.ModelTabelaItemCompra;
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
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class CompraProduto extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private final JPanel contentPanel = new JPanel();
	private JTable tableProduto;
	private JTextField txtId;
	private JTextField txtValorTotal;
	private JDateChooser dtCompra;
	private JTextField txtProdutoId;
	private JTextField txtProdutoDescricao;
	private JTextField txtQuantidade;
	private JTextField txtValorProduto;
	private JButton btnBuscar;
	private ModelTabelaItemCompra model = new ModelTabelaItemCompra();
	private JButton btnInserir;
	private Dao banco = new Dao();
	private JButton btnFinalizar;
	private JLabel lblMsnErro;
	private JButton btnDeletarCompra;
	private JButton btnCancelar;
	DecimalFormat dfValor = new DecimalFormat("0.00");
	private JPopupMenu popupMenu;
	private JPanel panel_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_11;
	private JLabel label_13;
	private JLabel label_17;
	private JLabel label_19;
	private JLabel label_21;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CompraProduto dialog = new CompraProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CompraProduto() {
		setTitle("COMPRA DE PRODUTO");
		setBounds(100, 100, 770, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new BorderLayout(5, 5));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			panel.add(scrollPane, BorderLayout.CENTER);

			tableProduto = new JTable(model);
			tableProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
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

		JLabel lblCdigo_1 = new JLabel("C\u00D3DIGO");
		panel_1.add(lblCdigo_1);
		lblCdigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		label_13 = new JLabel("");
		panel_1.add(label_13);

		JLabel lblData = new JLabel("DATA");
		panel_1.add(lblData);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblValor = new JLabel("VALOR TOTAL");
		panel_1.add(lblValor);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtId = new JTextField();
		panel_1.add(txtId);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtId.setEnabled(false);
		txtId.setBackground(new Color(255, 250, 205));
		txtId.setColumns(10);

		label_5 = new JLabel("");
		panel_1.add(label_5);

		dtCompra = new JDateChooser(new java.util.Date());
		panel_1.add(dtCompra);
		dtCompra.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));

		txtValorTotal = new JTextField();
		panel_1.add(txtValorTotal);
		txtValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorTotal.setDisabledTextColor(new Color(0, 0, 0));
		txtValorTotal.setBackground(new Color(255, 250, 205));
		txtValorTotal.setEnabled(false);
		txtValorTotal.setColumns(10);

		JLabel lblCdigo = new JLabel("C\u00D3DIGO");
		panel_1.add(lblCdigo);
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblDescrioDoProduto = new JLabel("DESCRI\u00C7\u00C3O");
		panel_1.add(lblDescrioDoProduto);
		lblDescrioDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));

		label = new JLabel("");
		panel_1.add(label);

		label_17 = new JLabel("");
		panel_1.add(label_17);

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

		btnBuscar = new JButton("BUSCAR");
		panel_1.add(btnBuscar);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.setActionCommand("Buscar");
		btnBuscar.addActionListener(this);

		label_2 = new JLabel("");
		panel_1.add(label_2);

		JLabel lblQuantidade = new JLabel("QUANTIDADE");
		panel_1.add(lblQuantidade);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblValorUnitario = new JLabel("VALOR UNITARIO");
		panel_1.add(lblValorUnitario);
		lblValorUnitario.setFont(new Font("Tahoma", Font.PLAIN, 14));

		label_6 = new JLabel("");
		panel_1.add(label_6);

		label_4 = new JLabel("");
		panel_1.add(label_4);

		txtQuantidade = new JTextField();
		panel_1.add(txtQuantidade);
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtQuantidade.setColumns(10);
		txtQuantidade.setEnabled(false);

		txtValorProduto = new JTextField();
		panel_1.add(txtValorProduto);
		txtValorProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorProduto.setColumns(10);
		txtValorProduto.setEnabled(false);

		btnInserir = new JButton("INSERIR");
		panel_1.add(btnInserir);
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserir.setActionCommand("Inserir");
		btnInserir.addActionListener(this);

		btnInserir.setEnabled(false);

		label_11 = new JLabel("");
		panel_1.add(label_11);

		lblMsnErro = new JLabel("Msn ERRO");
		panel_1.add(lblMsnErro);
		lblMsnErro.setForeground(Color.RED);
		lblMsnErro.setVisible(false);

		label_1 = new JLabel("");
		panel_1.add(label_1);

		label_19 = new JLabel("");
		panel_1.add(label_19);

		label_21 = new JLabel("");
		panel_1.add(label_21);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnDeletarCompra = new JButton("DELETAR");
			btnDeletarCompra.setVisible(false);
			btnDeletarCompra.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnDeletarCompra.setEnabled(false);
			btnDeletarCompra.addActionListener(this);
			btnDeletarCompra.setActionCommand("Deletar");
			buttonPane.add(btnDeletarCompra);
			{
				btnFinalizar = new JButton("FINALIZAR");
				btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnFinalizar.setActionCommand("Finalizar");
				buttonPane.add(btnFinalizar);
				getRootPane().setDefaultButton(btnFinalizar);
				btnFinalizar.addActionListener(this);
				btnFinalizar.setEnabled(false);
			}
			{
				btnCancelar = new JButton("CANCELAR");
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

		System.out.println(acao);

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
		btnBuscar.setEnabled(true);
		tableProduto.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnDeletarCompra.setVisible(false);

		Compra compra = (Compra) banco.buscarPorId(Compra.class, Integer.parseInt(txtId.getText()));

		for (ItemCompra itemCompra : compra.getLista()) {
			Produto produto = (Produto) banco.buscarPorId(Produto.class, itemCompra.getProduto().getId());

			float quantidadeAtual = produto.getQuantidade();

			float custoUnitarioAtual = produto.getCusto();

			float quantidadeEntrada = itemCompra.getQuantidade();
			float custoUnitarioEntrada = itemCompra.getCusto();

			float custoTotalAtual = quantidadeAtual * custoUnitarioAtual;
			System.out.println("custo total atual ;" + custoTotalAtual);
			float custoTotalEntrada = quantidadeEntrada * custoUnitarioEntrada;
			System.out.println("custo total entrada ;" + custoTotalEntrada);
			float custoTotalAntigo = 0;

			float quantidadeAntiga = quantidadeAtual - quantidadeEntrada;
			System.out.println("quantidade antiga  ;" + quantidadeAntiga);
			if (quantidadeAntiga >= 0) {
				custoTotalAntigo = custoTotalAtual - custoTotalEntrada;
				System.out.println("Custo total antigo: " + custoTotalAntigo);
			}

			float custoUnitarioAntigo = custoTotalAntigo / quantidadeAntiga;
			if (quantidadeAntiga <= 0) {
				custoUnitarioAntigo = 0;
			}

			produto.setCusto(custoUnitarioAntigo);
			produto.setQuantidade(quantidadeAntiga);
			System.out.println("\n\n\natualizado custo" + produto.getCusto());
			System.out.println("atualizado quantidade" + produto.getQuantidade());

			boolean a = banco.salvarOuAtualizarObjeto(produto);
			if (a) {
				JOptionPane.showMessageDialog(contentPanel,
						"Produto " + produto.getDescricao() + " foi restaurado valor e custo.");
			}

			boolean b = banco.deletarObjeto(itemCompra);
			if (b) {
				JOptionPane.showMessageDialog(contentPanel, "item de compra deletado com sucesso!");
			}

		}
		boolean e = banco.deletarObjeto(compra);
		if (e) {
			JOptionPane.showMessageDialog(contentPanel, "Compra delatada com sucesso!");
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
			JOptionPane.showMessageDialog(contentPanel, "ERRO! Refaça a compra novamente : " + e);
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
			JOptionPane.showMessageDialog(contentPanel, "ERRO! Refaça a compra novamente : " + e);
			dispose();
		}

	}

	private void inserir() {
		try {
			Produto produto = (Produto) banco.buscarPorId(Produto.class, Integer.parseInt(txtProdutoId.getText()));
			float quantidade = Float.parseFloat(txtQuantidade.getText().replace(",", "."));
			float custo = Float.parseFloat(txtValorProduto.getText().replace(",", "."));
			ItemCompra item = new ItemCompra(produto, quantidade, custo);

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
			txtValorProduto.setText("");
			btnInserir.setEnabled(false);
			txtQuantidade.setEnabled(false);
			txtValorProduto.setEnabled(false);
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
			txtProdutoId.setText(String.valueOf((p.getId())));
			txtProdutoDescricao.setText(String.valueOf(p.getDescricao()));
			con.dispose();
			btnInserir.setEnabled(true);
			txtQuantidade.setEnabled(true);
			txtValorProduto.setEnabled(true);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao buscar produto : " + e);
		}

	}

	private void salvar() {
		// try {

		Date data = new Date(dtCompra.getDate().getTime());
		float total = Float.parseFloat(txtValorTotal.getText().replace(",", "."));
		Set<ItemCompra> lista = new HashSet<>();
		System.out.println("Tamanho " + model.getRowCount());
		for (int i = 0; i < model.getRowCount(); i++) {
			System.out.println(i);
			lista.add(model.retornaItemCompra(i));

		}
		System.out.println(lista.size());

		Bin.Compra compra = new Bin.Compra(data, total, lista);
		if (!txtId.getText().equals("")) {
			compra.setId(Integer.parseInt(txtId.getText()));
		}
		boolean salvou = false;
		if (!(lista.size() < 1)) {
			boolean salvouProds = false;
			salvou = banco.salvarOuAtualizarObjeto(compra);
			for (ItemCompra itemCompra : lista) {
				itemCompra.setCompra(compra);
				salvouProds = banco.salvarOuAtualizarObjeto(itemCompra);

			}
			if (!salvouProds) {
				banco.deletarObjeto(compra);
				salvou = false;
			}

			if (salvou) {
				atualizaEstoque(lista);
				dispose();
			} else {
				JOptionPane.showMessageDialog(contentPanel, "A compra não pode ser salva!!");
			}
		} else {
			lblMsnErro.setText("Insira algum item antes de finalizar a compra!");
			lblMsnErro.setVisible(true);
		}
		// } catch (Exception e) {
		// JOptionPane.showMessageDialog(contentPanel, "Erro ao salvar o
		// produto" + e);
		// dispose();
		// }

	}

	private boolean atualizaEstoque(Set<ItemCompra> lista) {
		try {
			for (ItemCompra itemCompra : lista) {
				Produto produto = (Produto) banco.buscarPorId(Produto.class, itemCompra.getProduto().getId());
				float quantidadeVelha = produto.getQuantidade();
				float custoUnitarioVelho = produto.getCusto();
				float quantidadeEntrada = itemCompra.getQuantidade();
				float custoUnitarioEntrada = itemCompra.getCusto();

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
					"A compra foi salva com sucesso! \n Estoque atualizado com sucesso");
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Não conseguimos atualizar o estoque faça um balanço");
			return false;
		}

	}

	public void inserirCompra(Compra compra) {

		try {
			compra= (Compra) banco.buscarPorIdcarregaListaCompra(Compra.class, compra.getId());
			txtId.setText(String.valueOf(compra.getId()));
			dtCompra.setDate(compra.getData());
			txtValorTotal.setText(String.valueOf(dfValor.format(compra.getTotal())));

			for (ItemCompra instanciaCompra : compra.getLista()) {
				model.addRow(instanciaCompra);

			}

			btnBuscar.setEnabled(true);
			btnFinalizar.setEnabled(true);
			btnInserir.setEnabled(false);
			tableProduto.setEnabled(true);
			txtQuantidade.setEnabled(false);
			txtValorProduto.setEnabled(false);
			valorTotal();
			desbloquear(false);

			// novo
			btnDeletarCompra.setVisible(true);
			btnDeletarCompra.setEnabled(true);
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
		dtCompra.setEnabled(a);
		tableProduto.setEnabled(a);
		txtQuantidade.setEnabled(a);
		txtValorProduto.setEnabled(a);
		popupMenu.setEnabled(a);

	}
}
