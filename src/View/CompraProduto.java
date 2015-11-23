package View;

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
import Controller.ModelTabelaItemCompra;
import Persistence.Dao;

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
		setTitle("Comprar Produtos");
		setBounds(100, 100, 770, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 248, 734, 169);
			contentPanel.add(scrollPane);

			tableProduto = new JTable(model);
			tableProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			scrollPane.setViewportView(tableProduto);
			tableProduto.getTableHeader().setReorderingAllowed(false);
			// tamanho especifico da coluna
			// tabelaProduto.getColumn("Fornecedor").setPreferredWidth(220);
			tableProduto.getColumn("Descri��o").setPreferredWidth(220);

			popupMenu = new JPopupMenu();
			addPopup(tableProduto, popupMenu);

			JMenuItem mntmRemover = new JMenuItem("Remover");
			mntmRemover.addActionListener(this);
			popupMenu.add(mntmRemover);

		}

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigo.setBounds(10, 10, 46, 20);
		contentPanel.add(lblCdigo);

		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtId.setEnabled(false);
		txtId.setBackground(new Color(255, 250, 205));
		txtId.setBounds(10, 35, 109, 30);
		contentPanel.add(txtId);
		txtId.setColumns(10);

		txtValorTotal = new JTextField();
		txtValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorTotal.setDisabledTextColor(new Color(0, 0, 0));
		txtValorTotal.setBackground(new Color(255, 250, 205));
		txtValorTotal.setEnabled(false);
		txtValorTotal.setColumns(10);
		txtValorTotal.setBounds(640, 35, 104, 30);
		contentPanel.add(txtValorTotal);

		JLabel lblValor = new JLabel("Valor Total");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(640, 10, 104, 20);
		contentPanel.add(lblValor);

		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(240, 10, 46, 20);
		contentPanel.add(lblData);

		dtCompra = new JDateChooser(new java.util.Date());
		dtCompra.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
		dtCompra.setBounds(240, 35, 122, 30);
		contentPanel.add(dtCompra);

		txtProdutoId = new JTextField();
		txtProdutoId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtProdutoId.setDisabledTextColor(new Color(0, 0, 0));
		txtProdutoId.setBackground(new Color(255, 250, 205));
		txtProdutoId.setEnabled(false);
		txtProdutoId.setBounds(10, 105, 109, 29);
		contentPanel.add(txtProdutoId);
		txtProdutoId.setColumns(10);

		txtProdutoDescricao = new JTextField();
		txtProdutoDescricao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtProdutoDescricao.setDisabledTextColor(new Color(0, 0, 0));
		txtProdutoDescricao.setBackground(new Color(255, 250, 205));
		txtProdutoDescricao.setEnabled(false);
		txtProdutoDescricao.setBounds(129, 105, 492, 29);
		contentPanel.add(txtProdutoDescricao);
		txtProdutoDescricao.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.setBounds(640, 105, 104, 30);
		contentPanel.add(btnBuscar);
		btnBuscar.setActionCommand("Buscar");
		btnBuscar.addActionListener(this);

		JLabel lblCdigo_1 = new JLabel("C\u00F3digo");
		lblCdigo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigo_1.setBounds(10, 75, 86, 20);
		contentPanel.add(lblCdigo_1);

		JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto");
		lblDescrioDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrioDoProduto.setBounds(137, 75, 280, 20);
		contentPanel.add(lblDescrioDoProduto);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantidade.setBounds(10, 145, 86, 20);
		contentPanel.add(lblQuantidade);

		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtQuantidade.setBounds(10, 175, 109, 30);
		contentPanel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		txtValorProduto = new JTextField();
		txtValorProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtValorProduto.setColumns(10);
		txtValorProduto.setBounds(137, 175, 89, 30);
		contentPanel.add(txtValorProduto);

		JLabel lblValorUnitario = new JLabel("Valor Unitario");
		lblValorUnitario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorUnitario.setBounds(129, 145, 280, 20);
		contentPanel.add(lblValorUnitario);

		btnInserir = new JButton("Inserir");
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserir.setActionCommand("Inserir");
		btnInserir.setBounds(236, 175, 98, 30);
		btnInserir.addActionListener(this);
		contentPanel.add(btnInserir);

		btnInserir.setEnabled(false);
		txtQuantidade.setEnabled(false);
		txtValorProduto.setEnabled(false);

		lblMsnErro = new JLabel("Msn ERRO");
		lblMsnErro.setForeground(Color.RED);
		lblMsnErro.setVisible(false);
		lblMsnErro.setBounds(10, 223, 734, 14);
		contentPanel.add(lblMsnErro);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnDeletarCompra = new JButton("Deletar");
			btnDeletarCompra.setVisible(false);
			btnDeletarCompra.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnDeletarCompra.setEnabled(false);
			btnDeletarCompra.addActionListener(this);
			btnDeletarCompra.setActionCommand("Deletar");
			buttonPane.add(btnDeletarCompra);
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
			JOptionPane.showMessageDialog(contentPanel, "ERRO! Refa�a a compra novamente : " + e);
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
			JOptionPane.showMessageDialog(contentPanel, "ERRO! Refa�a a compra novamente : " + e);
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
			lblMsnErro.setText("Insira numeros v�lidos nos espe�os de numericos.");
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
				JOptionPane.showMessageDialog(contentPanel, "A compra n�o pode ser salva!!");
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
			JOptionPane.showMessageDialog(contentPanel, "N�o conseguimos atualizar o estoque fa�a um balan�o");
			return false;
		}

	}

	public void inserirCompra(Compra compra) {

		try {

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
