package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jboss.jandex.Main;

import Bin.ItemVenda;
import Bin.Produto;
import Bin.Venda;
import Controller.ModelTabelaItemVenda;
import Persistence.Dao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.ComponentOrientation;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class Inicial extends JFrame implements ActionListener {

	private JPanel contentPane;
	Calendar dataAtual = Calendar.getInstance();
	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField txtId;
	private JTextField txtDescricao;
	private JTable table;
	private JTextField txtTotal;
	private JTextField txtRecebido;
	private JLabel foto;
	private JTextField txtTotalVenda;
	private JTextField txtTroco;
	private ModelTabelaItemVenda model = new ModelTabelaItemVenda();
	private JButton btnBuscar;
	private JButton btnInserir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnSair;
	private Dao banco = new Dao();
	private JLabel lblMsnErro;
	private Venda venda = null;
	private JTextField txtPrecoUnitario;
	DecimalFormat dfValor = new DecimalFormat("0.00");
	private JMenuBar menuBar;
	private JTextField txtDesconto;
	private JLabel lblFatordesconto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {// com.jtattoo.plaf.luna.LunaLookAndFeel azulado simples
						// com.jtattoo.plaf.smart.SmartLookAndFeel o padrão que
						// eu
						// usava muito
						// com.jtattoo.plaf.hifi.HiFiLookAndFeel preto bem
						// massa!!
						// com.jtattoo.plaf.graphite.GraphiteLookAndFeel preto
						// com
						// detalhes marrons
						// com.jtattoo.plaf.fast.FastLookAndFeel cinza basico
						// com.jtattoo.plaf.aluminium.AluminiumLookAndFeel -
						// com.jtattoo.plaf.acryl.AcrylLookAndFeel - um pouco
						// transparente detalhes em preto, bonito
						// com.jtattoo.plaf.bernstein.BernsteinLookAndFeel
						// bonito
						// detalhes em amarelo laranja
						// com.jtattoo.plaf.mcwin.McWinLookAndFeel interface do
						// MacOs
						// com.jtattoo.plaf.mint.MintLookAndFeel basico cores
						// cinsas
						// padrao
						// com.jtattoo.plaf.noire.NoireLookAndFeel preto com
						// detalhe
						// em laranja
						// com.jtattoo.plaf.smart.SmartLookAndFeel tons em azul
						// com.jtattoo.plaf.texture.TextureLookAndFeel banco
						// gelo
						// com detalhes em preto fosco muito massa
						// Properties props = new Properties();
						// props.put("logoString", "");
						// SmartLookAndFeel.setCurrentTheme(props);

					UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
					Inicial frame = new Inicial();
					frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("betin");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicial() {
		setTitle("Controle de Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("CADASTRO");
		mnCadastro.setOpaque(true);
		mnCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnCadastro);

		JMenuItem mntmProduto = new JMenuItem("PRODUTO");
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto cadpro = new CadastroProduto();
				cadpro.setVisible(true);
			}
		});
		mnCadastro.add(mntmProduto);

		JMenu mnPesquisa = new JMenu("PESQUISA");
		mnPesquisa.setOpaque(true);
		mnPesquisa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnPesquisa);

		JMenuItem mntmProduto_1 = new JMenuItem("PRODUTO");
		mntmProduto_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaProduto conPro = new ConsultaProduto();
				conPro.setVisible(true);
			}
		});
		mnPesquisa.add(mntmProduto_1);

		JMenuItem mntmCompra = new JMenuItem("COMPRA");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaComprar conscom = new ConsultaComprar();
				conscom.setVisible(true);
			}
		});
		mnPesquisa.add(mntmCompra);

		JMenuItem mntmVenda = new JMenuItem("VENDA");
		mntmVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaVenda consVend = new ConsultaVenda();
				consVend.setVisible(true);
			}
		});
		mnPesquisa.add(mntmVenda);

		JMenu mnCompra = new JMenu("COMPRA");
		mnCompra.setOpaque(true);
		mnCompra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnCompra);

		JMenuItem mntmProduto_2 = new JMenuItem("PRODUTO");
		mntmProduto_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraProduto comPro = new CompraProduto();
				comPro.setModal(true);
				comPro.setVisible(true);
				comPro.validate();
				comPro.repaint();
			}
		});
		mnCompra.add(mntmProduto_2);

		JMenuItem mntmAjusteEstoque = new JMenuItem("AJUSTE ESTOQUE");
		mnCompra.add(mntmAjusteEstoque);
		mntmAjusteEstoque.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelRodape = new JPanel();
		panelRodape.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRodape.setBounds(11, 635, 1339, 53);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);

		JLabel lblData = new JLabel(String.valueOf(dt.format(dataAtual.getTime())));
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblData.setBounds(5, 10, 98, 14);
		lblData.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblData.setVerticalAlignment(SwingConstants.TOP);
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		panelRodape.add(lblData);

		JLabel lblContato = new JLabel("(88) 9.8878-0587 / (88) 9.9786-7735");
		lblContato.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContato.setForeground(new Color(255, 165, 0));
		lblContato.setBounds(963, 10, 340, 20);
		panelRodape.add(lblContato);

		JLabel lblUsbetingmailcom = new JLabel("usbetin@gmail.com");
		lblUsbetingmailcom.setForeground(new Color(255, 165, 0));
		lblUsbetingmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsbetingmailcom.setBounds(565, 10, 212, 20);
		panelRodape.add(lblUsbetingmailcom);

		JPanel panel = new JPanel();
		panel.setBounds(1, 1, 1349, 633);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, new Color(211, 211, 211)));
		panel_1.setBounds(10, 203, 549, 419);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtRecebido = new JTextField();
		txtRecebido.setHorizontalAlignment(SwingConstants.CENTER);
		txtRecebido.setText("0,00");
		txtRecebido.setForeground(new Color(0, 0, 0));
		txtRecebido.setBackground(new Color(211, 211, 211));
		txtRecebido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtRecebido.setColumns(10);
		txtRecebido.setBounds(307, 65, 232, 40);
		panel_1.add(txtRecebido);

		JLabel lblRecebido = new JLabel("DINHEIRO RECEBIDO");
		lblRecebido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRecebido.setBounds(307, 30, 232, 30);
		panel_1.add(lblRecebido);

		JLabel lblTotalDaCompra = new JLabel("TOTAL DA VENDA ");
		lblTotalDaCompra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalDaCompra.setBounds(307, 115, 232, 30);
		panel_1.add(lblTotalDaCompra);

		txtTotalVenda = new JTextField();
		txtTotalVenda.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalVenda.setText("0,00");
		txtTotalVenda.setDisabledTextColor(new Color(109, 109, 109));
		txtTotalVenda.setEnabled(false);
		txtTotalVenda.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTotalVenda.setColumns(10);
		txtTotalVenda.setBounds(307, 150, 232, 40);
		panel_1.add(txtTotalVenda);

		JLabel lblTroco = new JLabel("TROCO");
		lblTroco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTroco.setBounds(307, 295, 232, 30);
		panel_1.add(lblTroco);

		txtTroco = new JTextField();
		txtTroco.setHorizontalAlignment(SwingConstants.CENTER);
		txtTroco.setText("0,00");
		txtTroco.setDisabledTextColor(new Color(109, 109, 109));
		txtTroco.setEnabled(false);
		txtTroco.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTroco.setColumns(10);
		txtTroco.setBounds(307, 330, 232, 40);
		panel_1.add(txtTroco);

		lblMsnErro = new JLabel("msn erro");
		lblMsnErro.setVisible(false);
		lblMsnErro.setForeground(Color.RED);
		lblMsnErro.setBounds(10, 394, 529, 14);
		panel_1.add(lblMsnErro);

		JButton btnCalcularTroco = new JButton("Calcular Troco");
		btnCalcularTroco.addActionListener(this);
		btnCalcularTroco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCalcularTroco.setBounds(307, 225, 232, 40);
		panel_1.add(btnCalcularTroco);
		
		txtDesconto = new JTextField();
		txtDesconto.setText("0,00");
		txtDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		txtDesconto.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		txtDesconto.setDisabledTextColor(SystemColor.textInactiveText);
		txtDesconto.setColumns(10);
		txtDesconto.setBounds(25, 65, 232, 40);
		panel_1.add(txtDesconto);
		
		JLabel lblDesconto = new JLabel("DESCONTO");
		lblDesconto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDesconto.setBounds(25, 30, 232, 30);
		panel_1.add(lblDesconto);

		txtId = new JTextField();
		txtId.setDisabledTextColor(new Color(109, 109, 109));
		txtId.setEnabled(false);
		txtId.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtId.setBounds(10, 152, 162, 40);
		panel.add(txtId);
		txtId.setColumns(10);

		txtDescricao = new JTextField();
		txtDescricao.setDisabledTextColor(new Color(109, 109, 109));
		txtDescricao.setEnabled(false);
		txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtDescricao.setBounds(182, 152, 1027, 40);
		panel.add(txtDescricao);
		txtDescricao.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(569, 203, 773, 368);
		panel.add(scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);
		// tamanho especifico da coluna
		// tabelaProduto.getColumn("Fornecedor").setPreferredWidth(220);
		table.getColumn("Descrição").setPreferredWidth(220);

		btnBuscar = new JButton("Produto");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.setBounds(569, 582, 120, 40);
		btnBuscar.addActionListener(this);
		panel.add(btnBuscar);

		btnInserir = new JButton("Inserir");
		btnInserir.setEnabled(false);
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserir.setBounds(699, 582, 120, 40);
		btnInserir.addActionListener(this);
		
		lblFatordesconto = new JLabel("75000000");
		lblFatordesconto.setVisible(false);
		lblFatordesconto.setForeground(new Color(255, 222, 173));
		lblFatordesconto.setBounds(20, 10, 155, 14);
		panel.add(lblFatordesconto);
		panel.add(btnInserir);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setEnabled(false);
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAlterar.setBounds(829, 582, 120, 40);
		btnAlterar.addActionListener(this);
		panel.add(btnAlterar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExcluir.setBounds(959, 582, 120, 40);
		btnExcluir.addActionListener(this);
		panel.add(btnExcluir);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalvar.setBounds(1089, 582, 120, 40);
		btnSalvar.addActionListener(this);
		panel.add(btnSalvar);

		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSair.setBounds(1219, 582, 120, 40);
		btnSair.addActionListener(this);
		panel.add(btnSair);

		JLabel lblCdigo = new JLabel("C\u00D3DIGO");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCdigo.setBounds(10, 116, 162, 25);
		panel.add(lblCdigo);

		JLabel lblDescrio = new JLabel("DESCRI\u00C7\u00C3O");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescrio.setBounds(182, 116, 275, 25);
		panel.add(lblDescrio);

		txtTotal = new JTextField();
		txtTotal.setText("0,00");
		txtTotal.setDisabledTextColor(new Color(109, 109, 109));
		txtTotal.setEnabled(false);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 35));
		txtTotal.setBounds(1114, 11, 214, 80);
		panel.add(txtTotal);
		txtTotal.setColumns(10);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, screenSize.width, screenSize.height);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// imagem no lugar do painel dos modulos
		foto = new JLabel();
		foto.setBounds(10, 3, 1329, 102);
		ImageIcon imagem = new ImageIcon(Main.class.getResource("/img/img.jpg"));
		Image img = imagem.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT);
		foto.setIcon(new ImageIcon(img));
		panel.add(foto);
		
				txtPrecoUnitario = new JTextField();
				txtPrecoUnitario.setBounds(1219, 152, 123, 40);
				panel.add(txtPrecoUnitario);
				txtPrecoUnitario.setText("0,00");
				txtPrecoUnitario.setHorizontalAlignment(SwingConstants.CENTER);
				txtPrecoUnitario.setFont(new Font("Tahoma", Font.BOLD, 18));
				txtPrecoUnitario.setEnabled(false);
				txtPrecoUnitario.setDisabledTextColor(SystemColor.textInactiveText);
				txtPrecoUnitario.setColumns(10);
				
						JLabel lblPreoDoProduto = new JLabel("PRE\u00C7O");
						lblPreoDoProduto.setBounds(1219, 113, 123, 30);
						panel.add(lblPreoDoProduto);
						lblPreoDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		System.out.println(acao);

		switch (acao) {
		case "Produto":
			buscar();
			valorTotal();
			break;
		case "Inserir":
			inserir();
			valorTotal();
			break;
		case "Alterar":
			alterar();
			valorTotal();
			break;
		case "Excluir":
			remover();
			valorTotal();
			break;
		case "Salvar":
			salvar();
			valorTotal();
			txtDesconto.setText("0,00");
			btnSalvar.setEnabled(false);
			break;
		case "Sair":
			dispose();
			break;
		case "AJUSTE ESTOQUE":
			ajuste();
			valorTotal();
			break;

		case "Calcular Troco":
			calcularTroco();
		case "DESCONTO":
			desconto();
		default:
			break;
		}

	}

	private void desconto() {
		txtDesconto.setEnabled(true);
		
	}

	private void ajuste() {
		ConsultaEstoque con = new ConsultaEstoque();
		con.setVisible(true);

	}

	private void calcularTroco() {
		try {
			System.out.println(txtTotal.getText().replace(",", ".").replace("-", ""));
			if (Float.parseFloat(txtTotal.getText().replace(",", ".").replace("-", "")) > 0) {
				float desconto = (Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", "")));
				float total = Float.parseFloat(txtTotal.getText().replace(",", ".").replace("-", ""));
				if (desconto>(total*0.1)&&desconto>0) {
					JOptionPane.showMessageDialog(contentPane, "Desconto não pode ser dado");
					Senha senha = new Senha();
					senha.setModal(true);
					senha.setVisible(true);
					String senhaAdmin = senha.getSenha();
					if (senhaAdmin.equals("qwe456")) {
						desconto=Float.parseFloat(JOptionPane.showInputDialog("Digite o valor do desconto").replace(",", ".").replace("-", ""));
					}else {
						JOptionPane.showMessageDialog(contentPane, "Senha errada!!");
						desconto=0;
					}
					txtDesconto.setText(String.valueOf(desconto));
				}

				float troco = (Float.parseFloat(txtRecebido.getText().replace(",", ".").replace("-", ""))+Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", ""))
				- Float.parseFloat(txtTotal.getText().replace(",", ".").replace("-", "")));
				if (troco >= 0) {
					txtTroco.setText(String.valueOf(dfValor.format(troco)));
					btnSalvar.setEnabled(true);
				} else {
					lblMsnErro.setText("Valor Recebido é insuficiente.");
					lblMsnErro.setVisible(true);
					txtTroco.setText(String.valueOf(dfValor.format(troco)));
					btnSalvar.setEnabled(false);
				}
				if (Float.parseFloat(txtTroco.getText().replace(",", ".")) >= 0) {
					System.out.println(txtTroco.getText());
					btnSalvar.setEnabled(true);
					lblMsnErro.setVisible(false);
				} else {
					btnSalvar.setEnabled(false);
				}
				if (model.getRowCount() <= 0) {
					btnSalvar.setEnabled(false);
					btnExcluir.setEnabled(false);
					btnAlterar.setEnabled(false);
				}
			} if (Float.parseFloat(txtTotal.getText().replace(",", ".").replace("-", "")) <= 0) {
				
				lblMsnErro.setText( "Insira produtos, para gerar valor");
				lblMsnErro.setVisible(true);
			}
		} catch (java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPane,
					"Insira numeros validos no compo de recebimento e insira \n produtos com preços acima de zero para gerar um total.");
			txtTroco.setToolTipText("0,00");
			txtDesconto.setToolTipText("0,00");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void limpaTudo() {
		model.removeTudo();
		txtDescricao.setText("");
		txtId.setText("");
		txtTotal.setText("0,00");
		txtTotalVenda.setText("0,00");

	}

	private void alterar() {
		try {
			txtPrecoUnitario.setText(String
					.valueOf(dfValor.format(model.retornaItemVenda(table.getSelectedRow()).getProduto().getPreco())));
			txtDescricao.setText(model.retornaItemVenda(table.getSelectedRow()).getProduto().getDescricao());
			txtId.setText(String.valueOf(model.retornaItemVenda(table.getSelectedRow()).getProduto().getId()));
			model.removeRow(table.getSelectedRow());
			valorTotal();
			if (model.getRowCount() < 1) {
				btnSalvar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
			btnAlterar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnInserir.setEnabled(true);

			calcularTroco();
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(contentPane, "Selecione um item que você quer alterar a quantidade");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Erro, tente refazer novamente a venda.");
		}

	}

	private void valorTotal() {
		try {

			float valor = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				String a = (String) (model.getValueAt(i, 4));
				valor = valor + ((Float.parseFloat(a.replace(",", ".").replace("-", ""))));
			}
			txtTotal.setText(String.valueOf(dfValor.format(valor)));
			txtTotalVenda.setText(String.valueOf(dfValor.format(valor)));
			if (model.getRowCount() < 1) {
				btnSalvar.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
			if (!txtTotal.getText().equals("0,00")) {
				float custo = 0;
				for (int i = 0; i < model.getRowCount(); i++) {
					System.out.println(i);
					custo = custo + (model.retornaItemVenda(i).getCusto() * model.retornaItemVenda(i).getQuantidade());
				}
				lblFatordesconto.setText(""+custo*valor);
				lblFatordesconto.setVisible(true);
			}else {
				lblFatordesconto.setVisible(false);
			}
			
			calcularTroco();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "ERRO!  : " + e);
		}

	}

	private void remover() {
		try {
			model.removeRow(table.getSelectedRow());
			if (model.getRowCount() <= 0) {
				btnSalvar.setEnabled(false);
				btnExcluir.setEnabled(false);
				btnAlterar.setEnabled(false);
			}
			valorTotal();
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(contentPane, "Selecione um item que você quer excluir");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Erro, tente novamente : " + e);
		}

	}

	private void inserir() {
		try {
			String msn="";
			boolean liberado = true;
			Produto produto = (Produto) banco.buscarPorId(Produto.class, Integer.parseInt(txtId.getText()));
			float quantidade = Float.parseFloat(JOptionPane.showInputDialog("Digite a quantidade").replace(",", ".").replace("-", ""));
			if (quantidade>produto.getQuantidade()) {
				quantidade=0;
				liberado = false;
				msn="Produto com quantidade zero";
			}
			float custo = produto.getCusto();
			float preco = produto.getPreco();
			ItemVenda item = new ItemVenda(produto, custo, preco, quantidade);

			
			for (int i = 0; i < model.getRowCount(); i++) {
				if (produto.getId().equals(model.getValueAt(i, 0))) {
					msn= msn+"\nproduto igual, para modificar a quantidade altere o produto inserido.";
					liberado = false;
				}
			}
			if (liberado) {
				model.addRow(item);

			}if (!liberado) {
				
				JOptionPane.showMessageDialog(contentPane, msn);
			}
			valorTotal();
			limpatxt();
			btnSalvar.setEnabled(true);
			btnAlterar.setEnabled(true);
			btnExcluir.setEnabled(true);

			float troco = (Float.parseFloat(txtRecebido.getText().replace(",", ".").replace("-", ""))+Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", ""))
					- Float.parseFloat(txtTotal.getText().replace(",", ".").replace("-", "")));
			txtTroco.setText(String.valueOf(dfValor.format(troco)));
			if (txtRecebido.getText().equals("0,00") || txtRecebido.getText().equals("")) {
				lblMsnErro.setText("Não esqueça de colocar o valor recebido na caixa de texto acima");
				lblMsnErro.setVisible(true);
			}

			if (Float.parseFloat(txtTroco.getText().replace(",", ".").replace("-", "")) >= 0) {
				btnSalvar.setEnabled(true);
			} else {
				btnSalvar.setEnabled(false);
			}
			txtPrecoUnitario.setText("0,00");

		} catch (java.lang.NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPane, "Insira numeros válidos nos espeços de numericos.");
		} catch (java.lang.NullPointerException e) {
			JOptionPane.showMessageDialog(contentPane, "Você não adicionou quantidade tente inserir novamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Erro ao inserir produto : " + e);
		}

	}

	private boolean limpatxt() {
		try {

			txtDescricao.setText("");
			txtId.setText("");
			btnInserir.setEnabled(false);
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Erro ao limpar texto : " + e);
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
			txtId.setText(String.valueOf(p.getId()));
			txtDescricao.setText(String.valueOf(p.getDescricao()));
			con.dispose();
			btnInserir.setEnabled(true);

			txtPrecoUnitario.setText(String.valueOf(dfValor.format(p.getPreco())));

		} catch (java.lang.NullPointerException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPane, "Nem um produto foi selecionado");
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPane, "Erro ao buscar produto : " + e);
		}

	}

	private void salvar() {
		try {

			Date data = new Date(dataAtual.getTimeInMillis());
			float valor = Float.parseFloat(txtTotal.getText().replace(",", ".").replace("-", ""));
			Set<ItemVenda> lista = new HashSet<>();
			float custo = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				System.out.println(i);
				custo = custo + (model.retornaItemVenda(i).getCusto() * model.retornaItemVenda(i).getQuantidade());
				lista.add(model.retornaItemVenda(i));
			}
			lblFatordesconto.setText("75"+custo*75);

			if (Float.parseFloat(txtRecebido.getText().replace(",", ".").replace("-", ""))+(Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", ""))) >= Float
					.parseFloat(txtTotal.getText().replace(",", ".").replace("-", ""))) {

				Bin.Venda Venda = new Bin.Venda(data, valor, custo, lista);
				Venda.setDesconto(Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", "")));
				boolean salvou = false;
				if ((lista.size() >0)) {
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
						limpaTudo();
					} else {
						JOptionPane.showMessageDialog(contentPane, "A Venda não pode ser salva!!");
					}
				} else {
					lblMsnErro.setText("Insira algum item antes de finalizar a Venda!");
					lblMsnErro.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "O Valor recebido é menor que o total.");
			}
		} catch (java.lang.NumberFormatException a) {
			JOptionPane.showMessageDialog(contentPane, "Insira numeros válidos nos campos numericos.");
		} catch (Exception e) {

			JOptionPane.showMessageDialog(contentPane, "Erro ao salvar o produto" + e);
			System.out.println(e);
		}

	}

	private boolean atualizaEstoque(Set<ItemVenda> lista) {
		try {
			for (ItemVenda ItemVenda : lista) {
				Produto produto = (Produto) banco.buscarPorId(Produto.class, ItemVenda.getProduto().getId());
				float quantidadeVelha = produto.getQuantidade();
				float quantidadeSaida = ItemVenda.getQuantidade();
				float quantidadeAtual = quantidadeVelha - quantidadeSaida;
				produto.setQuantidade(quantidadeAtual);

				banco.salvarOuAtualizarObjeto(produto);

			}
			JOptionPane.showMessageDialog(contentPane,
					"A Venda foi salva com sucesso! \n Estoque atualizado com sucesso");
			txtTroco.setText("0,00");
			txtRecebido.setText("0,00");
			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Não conseguimos atualizar o estoque faça um balanço");
			return false;
		}

	}

	public void inserirVenda(Venda Venda) {

		try {

			txtId.setText(String.valueOf(Venda.getId()));
			txtTotalVenda.setText(String.valueOf(dfValor.format(Venda.getValor())));
			txtTotal.setText(String.valueOf(dfValor.format(Venda.getValor())));

			for (ItemVenda instanciaVenda : Venda.getLista()) {
				model.addRow(instanciaVenda);

			}

			btnBuscar.setEnabled(true);

			btnInserir.setEnabled(false);
			valorTotal();
			txtRecebido.setText(String.valueOf(dfValor.format(venda.getValor())));

			if (Float.parseFloat(txtTroco.getText().replace(",", ".").replace("-", "")) >= 0) {
				btnSalvar.setEnabled(true);
			} else {
				btnSalvar.setEnabled(false);
			}
		} catch (Exception e)

		{
			JOptionPane.showMessageDialog(contentPane, "Erro ao resgatar os produtos : " + e);
		}

	}

	public void operador() {
		menuBar.setVisible(false);
	}
}