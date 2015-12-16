package ViewNew;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Bin.ItemVenda;
import Bin.Produto;
import Controller.Model.Tabela.ItensCompostos.ModelTabelaItemVenda;
import Persistence.Ponte.Dao;
import ViewNew.Cadastro.CadastroProduto;
import ViewNew.Consulta.ConsultaComprar;
import ViewNew.Consulta.ConsultaEstoque;
import ViewNew.Consulta.ConsultaProduto;
import ViewNew.Consulta.ConsultaVenda;
import ViewNew.Movimentacao.CompraProduto;
import ViewNew.Seguranca.Senha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.Desktop;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class JanelaInicial extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRecebido;
	private JTextField txtTotalVenda;
	private JTextField txtDesconto;
	private JTextField txtTroco;
	private JTextField txtId;
	private JTextField txtDescricao;
	private JTextField txtPrecoUnitario;

	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	private ModelTabelaItemVenda model = new ModelTabelaItemVenda();
	DecimalFormat dfValor = new DecimalFormat("0.00");
	private JButton btnBuscar;
	private JButton btnInserir;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSalvar;
	private JButton btnImprimir;
	private JLabel lblFatordesconto;
	private Dao banco = new Dao();
	Calendar dataAtual = Calendar.getInstance();
	private JMenuBar menuBar;
	private JTable table;

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

					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					JanelaInicial frame = new JanelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaInicial() {
		setTitle("CONTROLE DE ESTOQUE   V 1.0");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));

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
				cadpro.setModal(true);
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
				conPro.setModal(true);
				conPro.setVisible(true);

			}
		});
		mnPesquisa.add(mntmProduto_1);

		JMenuItem mntmCompra = new JMenuItem("COMPRA");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaComprar conscom = new ConsultaComprar();
				conscom.setModal(true);
				conscom.setVisible(true);

			}
		});
		mnPesquisa.add(mntmCompra);

		JMenuItem mntmVenda = new JMenuItem("VENDA");
		mntmVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaVenda consVend = new ConsultaVenda();
				consVend.setModal(true);
				consVend.setVisible(true);

			}
		});
		mnPesquisa.add(mntmVenda);

		JMenu mnCompra = new JMenu("COMPRA");
		mnCompra.setOpaque(true);
		mnCompra.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnCompra);

		JMenuItem mntmComprar = new JMenuItem("COMPRAR");
		mntmComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompraProduto comPro = new CompraProduto();
				comPro.setModal(true);
				comPro.setVisible(true);
				comPro.validate();
				comPro.repaint();
			}
		});
		mnCompra.add(mntmComprar);

		JMenuItem mntmAjusteEstoque = new JMenuItem("AJUSTE ESTOQUE");
		mntmAjusteEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajuste();
				valorTotal();
			}
		});
		mnCompra.add(mntmAjusteEstoque);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		panel_2.add(menuBar);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_4, BorderLayout.CENTER);

		lblFatordesconto = new JLabel("");
		panel_4.add(lblFatordesconto);

		txtId = new JTextField();
		txtId.setBackground(new Color(224, 255, 255));
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setText("0000");
		txtId.setDisabledTextColor(new Color(0, 0, 0));
		txtId.setEnabled(false);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_4.add(txtId);
		txtId.setColumns(6);

		txtDescricao = new JTextField();
		txtDescricao.setBackground(new Color(224, 255, 255));
		txtDescricao.setText("###");
		txtDescricao.setDisabledTextColor(new Color(0, 0, 0));
		txtDescricao.setEnabled(false);
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_4.add(txtDescricao);
		txtDescricao.setColumns(25);

		txtPrecoUnitario = new JTextField();
		txtPrecoUnitario.setBackground(new Color(224, 255, 255));
		txtPrecoUnitario.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecoUnitario.setText("0,00");
		txtPrecoUnitario.setDisabledTextColor(new Color(0, 0, 0));
		txtPrecoUnitario.setEnabled(false);
		txtPrecoUnitario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_4.add(txtPrecoUnitario);
		txtPrecoUnitario.setColumns(8);

		JPanel lateralEsquerda = new JPanel();
		lateralEsquerda.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(lateralEsquerda, BorderLayout.WEST);
		lateralEsquerda.setLayout(new GridLayout(11, 2, 1, 5));

		JLabel lblNewLabel_3 = new JLabel("DINH. RECEBIDO");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lateralEsquerda.add(lblNewLabel_3);

		txtRecebido = new JTextField();
		txtRecebido.setText("0,00");
		txtRecebido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRecebido.setHorizontalAlignment(SwingConstants.CENTER);
		lateralEsquerda.add(txtRecebido);
		txtRecebido.setColumns(15);

		JLabel lblDesconto = new JLabel("DESCONTO");
		lblDesconto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		lateralEsquerda.add(lblDesconto);

		txtDesconto = new JTextField();
		txtDesconto.setText("0,00");
		txtDesconto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDesconto.setHorizontalAlignment(SwingConstants.CENTER);
		txtDesconto.setColumns(15);
		lateralEsquerda.add(txtDesconto);

		JLabel lblTotal = new JLabel("TOTAL VENDA");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lateralEsquerda.add(lblTotal);

		txtTotalVenda = new JTextField();
		txtTotalVenda.setBackground(new Color(224, 255, 255));
		txtTotalVenda.setText("0,00");
		txtTotalVenda.setDisabledTextColor(new Color(0, 0, 0));
		txtTotalVenda.setEnabled(false);
		txtTotalVenda.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTotalVenda.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalVenda.setColumns(15);
		lateralEsquerda.add(txtTotalVenda);

		JButton btnCalcularTroco = new JButton("CALCULAR");
		btnCalcularTroco.setHorizontalAlignment(SwingConstants.LEADING);
		btnCalcularTroco.setIcon(new ImageIcon(JanelaInicial.class.getResource("/img/015.png")));
		btnCalcularTroco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lateralEsquerda.add(btnCalcularTroco);

		JLabel lblTroco = new JLabel("TROCO");
		lblTroco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lateralEsquerda.add(lblTroco);

		txtTroco = new JTextField();
		txtTroco.setBackground(new Color(224, 255, 255));
		txtTroco.setText("0,00");
		txtTroco.setDisabledTextColor(new Color(0, 0, 0));
		txtTroco.setEnabled(false);
		txtTroco.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTroco.setHorizontalAlignment(SwingConstants.CENTER);
		txtTroco.setColumns(15);
		lateralEsquerda.add(txtTroco);

		btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.setPreferredSize(new Dimension(85, 23));
		btnImprimir.setMinimumSize(new Dimension(85, 23));
		btnImprimir.setMaximumSize(new Dimension(85, 23));
		btnImprimir.setHorizontalAlignment(SwingConstants.LEADING);
		btnImprimir.setIcon(new ImageIcon(JanelaInicial.class.getResource("/img/024.png")));
		btnImprimir.setEnabled(false);
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lateralEsquerda.add(btnImprimir);

		btnSalvar = new JButton("SALVAR");
		btnSalvar.setPreferredSize(new Dimension(85, 23));
		btnSalvar.setMinimumSize(new Dimension(85, 23));
		btnSalvar.setMaximumSize(new Dimension(85, 23));
		btnSalvar.setHorizontalAlignment(SwingConstants.LEADING);
		btnSalvar.setIcon(new ImageIcon(JanelaInicial.class.getResource("/img/018.png")));
		btnSalvar.setEnabled(false);
		// button.setBackground(new Color(152, 251, 152));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lateralEsquerda.add(btnSalvar);

		JPanel rodape = new JPanel();
		rodape.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(rodape, BorderLayout.SOUTH);
		rodape.setLayout(new GridLayout(0, 3, 5, 5));

		JLabel lblNewLabel_4 = new JLabel(dt.format(new Date()));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_4.setForeground(new Color(30, 144, 255));
		rodape.add(lblNewLabel_4);

		JLabel lblUsbetingmailcom = new JLabel("usbetin@gmail.com");
		lblUsbetingmailcom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsbetingmailcom.setForeground(new Color(30, 144, 255));
		lblUsbetingmailcom.setHorizontalAlignment(SwingConstants.CENTER);
		rodape.add(lblUsbetingmailcom);

		JLabel label_4 = new JLabel("(88) 9.8878-0587 | (88) 9.9787-7735");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setForeground(new Color(30, 144, 255));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		rodape.add(label_4);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(5, 5));

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 4, 5, 5));

		btnBuscar = new JButton("PRODUTOS");
		btnBuscar.setHorizontalAlignment(SwingConstants.LEADING);
		btnBuscar.setIcon(new ImageIcon(JanelaInicial.class.getResource("/img/010.png")));
		btnBuscar.setPreferredSize(new Dimension(85, 50));
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(btnBuscar);

		btnInserir = new JButton("INSERIR");
		btnInserir.setHorizontalAlignment(SwingConstants.LEADING);
		btnInserir.setIcon(new ImageIcon(JanelaInicial.class.getResource("/img/008.png")));
		btnInserir.setEnabled(false);
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(btnInserir);

		btnAlterar = new JButton("ALTERAR");
		btnAlterar.setHorizontalAlignment(SwingConstants.LEADING);
		btnAlterar.setIcon(new ImageIcon(JanelaInicial.class.getResource("/img/023.png")));
		btnAlterar.setEnabled(false);
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(btnAlterar);

		btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setHorizontalAlignment(SwingConstants.LEADING);
		btnExcluir.setIcon(new ImageIcon(JanelaInicial.class.getResource("/img/004.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumn("DESCRIÇÃO").setPreferredWidth(200);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);

		btnBuscar.addActionListener(this);
		btnInserir.addActionListener(this);
		btnExcluir.addActionListener(this);
		btnAlterar.addActionListener(this);
		btnSalvar.addActionListener(this);
		btnImprimir.addActionListener(this);
		btnCalcularTroco.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		System.out.println(acao);
		try {

			switch (acao) {
			case "PRODUTOS":
				buscar();
				break;
			case "INSERIR":
				inserir();
				valorTotal();
				break;
			case "ALTERAR":
				alterar();
				valorTotal();
				break;
			case "EXCLUIR":
				remover();
				valorTotal();
				break;
			case "SALVAR":
				salvar();
				valorTotal();
				txtDesconto.setText("0,00");
				btnSalvar.setEnabled(false);
				break;
			case "IMPRIMIR":
				imprimir();
				break;
			case "AJUSTE ESTOQUE":
				ajuste();
				valorTotal();
				break;

			case "CALCULAR":
				calcularTroco();
			case "DESCONTO":
				desconto();
			default:
				break;
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Erro " + e2.getMessage());
		} finally {
			calcularTroco();
		}

	}

	private void imprimir() {
		// Document documentoPDF = new Document();
		Document doc = new Document(PageSize.A4, 10, 10, 10, 10);
		String a = "";
		try {
			// Cria um novo documento com tamanho e margens definidas pelo
			// usuário
			// new Document(tamanho da página, margem esquerda, margem direita,
			// margem topo, margem rodapé);

			a = JOptionPane.showInputDialog("Nome do solicitante:");
			// Criando o arquivo de saída.
			OutputStream os = new FileOutputStream("D:\\Orcamento/" + a + ".pdf");
			System.out.println("--" + a);
			if (a != null) {

				// Associando o doc ao arquivo de saída.
				PdfWriter.getInstance(doc, os);

				// Abrindo o documento para a edição
				doc.open();

				// Adicionando um parágrafo ao PDF,
				Paragraph p = new Paragraph("Solicitação de " + a + ", dia " + dt.format(new java.util.Date()));

				// Setando o alinhamento p/ o centro
				p.setAlignment(Paragraph.ALIGN_CENTER);

				// Definindo
				p.setSpacingAfter(50);
				doc.add(p);

				// Criando uma tabela com 3 colunas
				PdfPTable table = new PdfPTable((new float[] { 0.15f, 0.45f, 0.15f, 0.1f, 0.15f }));
				// Título para a tabela
				Paragraph tableHeader = new Paragraph("ORÇAMENTO");

				PdfPCell header = new PdfPCell(tableHeader);
				// Definindo que o header vai ocupar as 3 colunas
				header.setColspan(5);
				// Definindo alinhamento do header
				header.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
				// Adicionando o header à tabela
				table.addCell(header);

				List<String> list = new ArrayList<String>();
				list.add("CÓD");
				list.add("DESCRIÇÃO");
				list.add("PREÇO");
				list.add("QNT");
				list.add("TOTAL");

				for (int i = 0; i < model.getRowCount(); i++) {
					list.add((String) (String.valueOf(model.getValueAt(i, 0))));
					list.add((String) (String.valueOf(model.getValueAt(i, 1))));
					list.add((String) (String.valueOf(model.getValueAt(i, 2))));
					list.add((String) (String.valueOf(model.getValueAt(i, 3))));
					list.add((String) (String.valueOf(model.getValueAt(i, 4))));
				}

				// list.add("Testando linha 1, coluna 1");
				// list.add("Testando linha 1, coluna 2");
				// list.add("Testando linha 1, coluna 3");
				// list.add("Testando linha 2, coluna 1");
				// list.add("Testando linha 2, coluna 2");
				// list.add("Testando linha 2, coluna 3");
				// list.add("Testando linha 3, coluna 1");
				// list.add("Testando linha 3, coluna 2");
				// list.add("Testando linha 3, coluna 3");

				for (String s : list) {
					table.addCell(s);
				}
				table.setSpacingAfter(50);
				doc.add(table);
				Paragraph s = new Paragraph("Valor total " + dfValor.format(valorTotal()) + "");
				s.setAlignment(Paragraph.ALIGN_CENTER);
				s.setSpacingAfter(50);
				doc.add(s);

				JOptionPane.showMessageDialog(contentPane, "Orçamento salvo com sucesso!");
				// JOptionPane.showInputDialog("Nome do solicitante:");
				// PdfWriter.getInstance(documentoPDF, new
				// FileOutputStream("C:\\pdf.pdf"));
				//
				// documentoPDF.open();
				//
				// documentoPDF.setPageSize(PageSize.A4);
			}

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			doc.close();
			// documentoPDF.close();
			try {
				if (a != null) {
					Desktop.getDesktop().open(new File("D:\\Orcamento/" + a + ".pdf"));
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro no Desktop: " + ex);
			}
		}

	}

	private void desconto() {
		txtDesconto.setEnabled(true);

	}

	private void ajuste() {
		ConsultaEstoque con = new ConsultaEstoque();
		con.setModal(true);
		con.setVisible(true);

	}

	private void calcularTroco() {
		try {
			// só calcula o troco de o total da venda for maior que zero
			if (Float.parseFloat(txtTotalVenda.getText().replace(",", ".").replace("-", "")) > 0) {
				// coleta desconto e total para calculos posteriores trocando ,
				// por . e - por +
				float desconto = (Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", "")));
				float total = Float.parseFloat(txtTotalVenda.getText().replace(",", ".").replace("-", ""));
				// caso o desconto seja maior que 10% o maior que zero entrará
				// nessa condição
				if (desconto > (total * 0.1) && desconto > 0) {
					// avisa ao usuario que o desconto não pode ser dado
					JOptionPane.showMessageDialog(contentPane, "Desconto não pode ser dado");
					// abra a janela de inserção de senha
					Senha senha = new Senha();
					// faz com que a janela seja exclusiva
					senha.setModal(true);
					// torna a janela visivel ao usuario
					senha.setVisible(true);
					// coleta da janela de inserção de senha a senha inseida
					// pelo usuario
					String senhaAdmin = senha.getSenha();
					// caso essa senha seja igual a qwe456 o desconto poderá ser
					// dado maior que 10 %
					if (senhaAdmin.equals("qwe456")) {
						// pedimos ao usuario para digitar o valor do desconto
						// em reais
						desconto = Float.parseFloat(JOptionPane.showInputDialog("Digite o valor do desconto")
								.replace(",", ".").replace("-", ""));
					} else {
						// caso a senha esta errada o usuario será informado que
						// a senha não poderá ser dada
						// e o desconto seja igualado a zero
						JOptionPane.showMessageDialog(contentPane, "Senha errada!!");
						desconto = 0;
					}
					// o desnto seja inserido na caixa de texto
					txtDesconto.setText(String.valueOf(dfValor.format(desconto)));
				}

				// aqui seja calculado otroco
				// instaciamos uma variavel troco que é a soma do valor recebido
				// mais o desconto menos o total da venda
				float troco = (Float.parseFloat(txtRecebido.getText().replace(",", ".").replace("-", ""))
						+ Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", ""))
						- Float.parseFloat(txtTotalVenda.getText().replace(",", ".").replace("-", "")));
				// setamos o troco na caixa de texto
				txtTroco.setText(String.valueOf(dfValor.format(troco)));
				// aqui verificamos se o troca é maior ou igual a zero
				// caso seja o botao salvar será liberado para salvar a compra
				// com todos os item que estão no model
				if (troco >= 0) {
					btnSalvar.setEnabled(true);
				} else {
					// caso contrario a venda não poderá ser salva pois o botão
					// salvar sera bloqueado
					btnSalvar.setEnabled(false);
				}
				// verifica o tamnho do model para bloquear alguns botão
				verificaTamanhoModel();
			} else {
				// caso o valortotal seja menor igual a zero entrara aqui
				// informando que o troco só poderá ser calculado mediante a
				// inserção de produtos
//				JOptionPane.showMessageDialog(this,
//						"Primeiro você deve inserir produtos para gerar valor a ser calculado");
			}
		} catch (java.lang.NumberFormatException e) {
			// entraremos aqui quando for digitado uma string no local de
			// valores numericos
			JOptionPane.showMessageDialog(contentPane,
					"Insira numeros validos no compo de recebimento e insira \n produtos com preços acima de zero para gerar um total.");
			// colocamos valores de troco desconto e recebido iguais a zero
			txtTroco.setToolTipText("0,00");
			txtDesconto.setToolTipText("0,00");
			txtRecebido.setText("0,00");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void limpaTudo() {
		// função que simplesmente remove tudo e coloca valores iguais a zero
		model.removeTudo();
		txtDescricao.setText("");
		txtId.setText("");
		txtTotalVenda.setText("0,00");
		txtTotalVenda.setText("0,00");
		txtRecebido.setText("0,00");
		txtDesconto.setText("0,00");
		txtTroco.setText("0,00");

	}

	private void alterar() {
		try {
			// pede ao model o item que esta selecionado na tabela
			ItemVenda item = (ItemVenda) model.getObj(table.getSelectedRow());
			// retorna para as caixas de texto o item anteriormente entrege pelo
			// model
			txtPrecoUnitario.setText(String.valueOf(dfValor.format(item.getPreco())));
			txtDescricao.setText(item.getProduto().getDescricao());
			txtId.setText(String.valueOf(item.getProduto().getId()));
			// remove o item que deve ser alterado do model ja este item esta em
			// condições de ser inserido
			model.removeRow(table.getSelectedRow());
			// calcula o valor total
			valorTotal();
			verificaTamanhoModel();
			// faz com que o botão inserir seja liberado para posterir inserção
			// ou se nao quisir pode fazer outra atividade
			btnInserir.setEnabled(true);
			calcularTroco();

		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// entrará nessa condição quando nem um item for selecionado e o
			// botão alterar for acionado
			JOptionPane.showMessageDialog(contentPane, "Selecione um item que você quer alterar a quantidade");
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPane, "Erro, tente refazer novamente a venda.");
		}

	}

	private float valorTotal() {
		try {

			// função de calcula o valor total
			float valor = 0;
			// passa por todos os prodtos calculando o valor com base na coluna
			// do model em que o valor esta alocado
			for (int i = 0; i < model.getRowCount(); i++) {
				String a = (String) (model.getValueAt(i, 4));
				valor = valor + ((Float.parseFloat(a.replace(",", ".").replace("-", ""))));
			}

			// seta o valor total da venda
			txtTotalVenda.setText(String.valueOf(dfValor.format(valor)));

			// verifica o tamanho do model para bloquear alguns botões
			verificaTamanhoModel();
			// caso o total da venda seja igual a zero zeramos tambem o custo
			// pois o custo só existirá enquanto houver estoque positivado
			if (!txtTotalVenda.getText().equals("0,00")) {
				float custo = 0;
				for (int i = 0; i < model.getRowCount(); i++) {
					System.out.println(i);
					ItemVenda item = (ItemVenda) model.getObj(i);
					custo = custo + (item.getCusto() * item.getQuantidade());
				}
				// mutiplica o valor do custo total pelo valor de venda exibindo
				// em um label na tela
				// essa informação o proprietario utilizará para calcular o
				// custo de forma rapida e nem um outro funcionario saber
				// ele simplesmente pegara esse numero e ira dividir pelo total
				// da venda sabendo de forma imediata qual foi o custo total dos
				// produtos no modl
				lblFatordesconto.setText("" + custo * valor);
				lblFatordesconto.setVisible(true);

			} else {
				// caso a venda seja igual a zero o label de informação não irá
				// aparecer pois o custo terá sido igualado a zero
				lblFatordesconto.setVisible(false);
			}

			// retorna o valor total para calculos posteriores
			return valor;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "ERRO!  : " + e);
			model.removeTudo();
			return 0;
		}

	}

	private void remover() {
		try {
			// simplesmente remove o item o model
			model.removeRow(table.getSelectedRow());
			// deleta e verifica o tamanho do model
			verificaTamanhoModel();
			// calcula o valor total da venda
			valorTotal();
			calcularTroco();
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// entrara aqui quando o botão for acionado e nem uma linha for
			// selecionada
			JOptionPane.showMessageDialog(contentPane, "Selecione um item que você quer excluir");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Erro, tente novamente : " + e);
		}

	}

	private void inserir() {
		try {
			String msn = "";
			boolean liberado = true;
			// busca no banco com base no id que esta na caixa de rxto
			Produto produto = (Produto) banco.buscarPorId(Produto.class, Integer.parseInt(txtId.getText()));
			// pede ao usuario uma quantidade antes de inseir no carrinho de
			// compra ou model
			float quantidade = Float
					.parseFloat(JOptionPane.showInputDialog("Digite a quantidade").replace(",", ".").replace("-", ""));
			// verifica a integridade da informação requerida caso seja menor
			// que zero a quantidade se iguala a zero caso contrario passa
			// direto, e quando o usuario digitar uma string entratá na exceção
			// abaixo do metodo
			if (quantidade > produto.getQuantidade()) {
				quantidade = 0;
				liberado = false;
				// quando entra nessa condição escreve a mensagem
				msn = "Produto com quantidade zero";
			}

			// formando o item de venda com base nas informações coletadas de
			// quandidade que o cliente informou e custo e preço na hora da
			// transação
			float custo = produto.getCusto();
			float preco = produto.getPreco();
			ItemVenda item = new ItemVenda(produto, custo, preco, quantidade);

			// verifica se existe no model algum produto com o mesmo id
			for (int i = 0; i < model.getRowCount(); i++) {
				if (produto.getId().equals(model.getValueAt(i, 0))) {
					// adiciona a msn uma nova informação
					msn = msn + "\n produto igual, para modificar a quantidade altere o produto inserido.";
					liberado = false;
				}
			}
			// caso nem um erro tenha acontecido insere o produto no model
			if (liberado) {
				model.addRow(item);

			}
			// caso contrario aparecerá uma msn de erro que foi montada com base
			// nos if's anteriores
			if (!liberado) {

				JOptionPane.showMessageDialog(contentPane, msn);
				// função que calcula o valor total
				valorTotal();
			}

			// depois de tudo limpa o texto nas caixas de texto referidas e
			// verifica o tamanho do model para bloquerar alguns botões
			limpatxt();
			verificaTamanhoModel();

			// aqui era quando eu colocava pra calcular o troco mais agora temos
			// uma função especifica acionada pelo botão
			// float troco =
			// (Float.parseFloat(txtRecebido.getText().replace(",",
			// ".").replace("-", ""))
			// + Float.parseFloat(txtDesconto.getText().replace(",",
			// ".").replace("-", ""))
			// - Float.parseFloat(txtTotalVenda.getText().replace(",",
			// ".").replace("-", "")));
			// txtTroco.setText(String.valueOf(dfValor.format(troco)));
			// if (txtRecebido.getText().equals("0,00") ||
			// txtRecebido.getText().equals("")) {
			// }
			//
			// if (Float.parseFloat(txtTroco.getText().replace(",",
			// ".").replace("-", "")) >= 0) {
			// btnSalvar.setEnabled(true);
			// } else {
			// btnSalvar.setEnabled(false);
			// }
			// com todas as caixas de texto foram limpas colocamos o preço
			// unitario igual a zero
			txtPrecoUnitario.setText("0,00");

		} catch (java.lang.NumberFormatException e) {
			// entrará nessa função quando for digitado Strings no local
			// numerico
			JOptionPane.showMessageDialog(contentPane, "Insira numeros válidos nos espaços de numericos.");
		} catch (java.lang.NullPointerException e) {
			// entrara aqui quando nada for digitado
			JOptionPane.showMessageDialog(contentPane, "Você não adicionou quantidade tente inserir novamente");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane, "Erro ao inserir produto : " + e);
		}

	}

	private void verificaTamanhoModel() {
		// simplesmente verifica se o model esta vazio ou não bloqueando ou
		// desbloqueando alguns botões
		if (model.getRowCount() <= 0) {
			btnSalvar.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnImprimir.setEnabled(false);
			txtTroco.setText("0,00");
			txtRecebido.setText("0,00");
		} else if (model.getRowCount() > 0) {
			if (Float.parseFloat(txtTroco.getText().replace(",", "")) > 0) {
				btnSalvar.setEnabled(true);
			} else {
				btnSalvar.setEnabled(false);
			}

			btnAlterar.setEnabled(true);
			btnExcluir.setEnabled(true);
			btnImprimir.setEnabled(true);
		}

	}

	// limpa o texto do produto selecionado será utilizado quando o produto for
	// inserirdo
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
			// abre a janela de consulta de produto
			ConsultaProduto con = new ConsultaProduto();
			// moficica a função de um botão para o de seção
			con.moduloEscolher();
			// faz com que a janela seja tratada de forma exclusiva
			con.setModal(true);
			// depois deixa a janela visivel para o usuario
			con.setVisible(true);

			// cria um produto resgatando da janela aberta
			Produto p = (Produto) con.getObj();
			// aloca as informações do produto nos campos de texto para poder
			// ser inserido
			txtId.setText(String.valueOf(p.getId()));
			txtDescricao.setText(String.valueOf(p.getDescricao()));
			txtPrecoUnitario.setText(String.valueOf(dfValor.format(p.getPreco())));

			// feixa a janela de pesquisa de produto
			con.dispose();
			// verifica se existem dados no campo de texto veja a funcao
			verificaSelecao();

		} catch (java.lang.NullPointerException e) {
			// entrara aqui quando nem um produto for selecionado
			JOptionPane.showMessageDialog(contentPane, "Nem um produto foi selecionado");
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPane, "Erro ao buscar produto : " + e);
		}

	}

	private void verificaSelecao() {
		// função que verifica na caixa de texto id se existe um id para poder
		// ser inserido no model
		if (txtId.getText().length() <= 0) {
			btnImprimir.setEnabled(false);
		} else if (txtId.getText().length() > 0) {
			btnInserir.setEnabled(true);
		}

	}

	private void salvar() {
		try {

			// salva a venda
			// instancia a data e o valor com base na data atual e valor exibido
			// na caixa de texto
			Date data = new Date(dataAtual.getTimeInMillis());
			float valor = Float.parseFloat(txtTotalVenda.getText().replace(",", ".").replace("-", ""));
			// instancia um set para guardar todos os itens de venda de forma a
			// são se repetirem
			Set<ItemVenda> lista = new HashSet<>();
			// cria um custo igual a zero para poder ser calculado dentro do for
			float custo = 0;
			// percorrerá todos os produtos calculando o custo total
			for (int i = 0; i < model.getRowCount(); i++) {
				ItemVenda item = (ItemVenda) model.getObj(i);
				custo = custo + (item.getCusto() * item.getQuantidade());
				lista.add((ItemVenda) model.getObj(i));
			}
			// verificamos se o valor recebido mais o desconto é maior que o
			// valor da venda
			if (Float.parseFloat(txtRecebido.getText().replace(",", ".").replace("-", ""))
					+ (Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", ""))) >= Float
							.parseFloat(txtTotalVenda.getText().replace(",", ".").replace("-", ""))) {

				// caso seja criamos a venda
				Bin.Venda Venda = new Bin.Venda(data, valor, custo, lista);
				// setamos o desconto, pois foi criado depois e não esta dentro
				// do construtor padrão
				Venda.setDesconto(Float.parseFloat(txtDesconto.getText().replace(",", ".").replace("-", "")));
				// criamos a variavel salvar pra verificar se a venda será salva
				// com sucesso
				boolean salvou = false;
				if ((lista.size() > 0)) {
					boolean salvouProds = false;
					// salva a venda
					salvou = banco.salvarOuAtualizarObjeto(Venda);

					// salva os produtos itens de venda
					for (ItemVenda ItemVenda : lista) {
						ItemVenda.setVenda(Venda);
						salvouProds = banco.salvarOuAtualizarObjeto(ItemVenda);

					}
					// caso algum produto não seja salvo deleta a venda
					if (!salvouProds) {
						banco.deletarObjeto(Venda);
						salvou = false;
					}

					// caso seja atualiza o estoque e limpa tudo
					if (salvou) {
						atualizaEstoque(lista);
						limpaTudo();
					} else {
						// caso contrario aparecerá essa msn para o usuario
						JOptionPane.showMessageDialog(contentPane, "A Venda não pode ser salva!!");
					}
				} else {
				}
			} else {
				JOptionPane.showMessageDialog(contentPane, "O Valor recebido é menor que o total.");
			}
		} catch (java.lang.NumberFormatException a) {
			// entrará aqui quando for inserido String no local numerico
			JOptionPane.showMessageDialog(contentPane, "Insira numeros válidos nos campos numericos.");
		} catch (Exception e) {

			JOptionPane.showMessageDialog(contentPane, "Erro ao salvar o produto" + e);
			System.out.println(e);
		}

	}

	private boolean atualizaEstoque(Set<ItemVenda> lista) {
		try {
			// atualizaremos o estoque e salvar no banco item por item
			for (ItemVenda ItemVenda : lista) {
				Produto produto = (Produto) banco.buscarPorId(Produto.class, ItemVenda.getProduto().getId());
				float quantidadeVelha = produto.getQuantidade();
				float quantidadeSaida = ItemVenda.getQuantidade();
				float quantidadeAtual = quantidadeVelha - quantidadeSaida;
				produto.setQuantidade(quantidadeAtual);

				banco.salvarOuAtualizarObjeto(produto);

			}
			// msn de sucesso para atualização dos itens
			// TODO não consegui um modo de reverter esse processo caso não
			// desse certo algo para melhar
			JOptionPane.showMessageDialog(contentPane,
					"A Venda foi salva com sucesso! \n Estoque atualizado com sucesso");
			// troco igual a zero e recebido igual a zero
			txtTroco.setText("0,00");
			txtRecebido.setText("0,00");
			btnInserir.setEnabled(false);
			return true;

		} catch (Exception e) {
			// caso alguma exceção lançada aparecerá essa mensgem
			JOptionPane.showMessageDialog(contentPane, "Não conseguimos atualizar o estoque faça um balanço");
			return false;
		}

	}
	// função sem usabilidade feita para posteriores alterações coisa que não é
	// mais necessaria

	// public void inserirVenda(Venda venda) {
	//
	// try {
	//
	// txtId.setText(String.valueOf(venda.getId()));
	// txtTotalVenda.setText(String.valueOf(dfValor.format(venda.getValor())));
	// txtTotalVenda.setText(String.valueOf(dfValor.format(venda.getValor())));
	//
	// for (ItemVenda instanciaVenda : venda.getLista()) {
	// model.addRow(instanciaVenda);
	//
	// }
	//
	// btnBuscar.setEnabled(true);
	//
	// btnInserir.setEnabled(false);
	// valorTotal();
	// txtRecebido.setText(String.valueOf(dfValor.format(venda.getValor())));
	//
	// if (Float.parseFloat(txtTroco.getText().replace(",", ".").replace("-",
	// "")) >= 0) {
	// btnSalvar.setEnabled(true);
	// } else {
	// btnSalvar.setEnabled(false);
	// }
	// } catch (Exception e)
	//
	// {
	// JOptionPane.showMessageDialog(contentPane, "Erro ao resgatar os produtos
	// : " + e);
	// }
	//
	// }
	//
	// public void operador() {
	// menuBar.setVisible(false);
	// }

	public void operador() {
		menuBar.setVisible(false);
	}
}
