package ViewNew.Consulta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Compra;
import Controller.Model.Tabela.Movimentacao.ModelTabelaListaCompras;
import Persistence.Ponte.Dao;
import ViewNew.Movimentacao.CompraProduto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.Font;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class ConsultaComprar extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ModelTabelaListaCompras model = new ModelTabelaListaCompras();
	private Dao banco = new Dao();
	private JButton btnAlterar;
	private Compra CompraEscolhido;
	private JDateChooser dataFinal;
	private JTextField txtTotal;
	private float total = 0;
	private JDateChooser dataInicial;
	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	private JButton btnImprimir;
	DecimalFormat dfValor = new DecimalFormat("0.00");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			ConsultaComprar dialog = new ConsultaComprar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaComprar() {
		setTitle("PESQUISA DE COMPRA");
		setBounds(100, 100, 500, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);

		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(2, 4, 5, 5));
			{
				JLabel label = new JLabel("TOTAL");
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				buttonPane.add(label);
			}
			{
				txtTotal = new JTextField();
				txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtTotal.setDisabledTextColor(Color.DARK_GRAY);
				txtTotal.setEnabled(false);
				buttonPane.add(txtTotal);
			}
			{
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.add(GregorianCalendar.DATE, -7);
				dataInicial = new JDateChooser(calendar.getTime());
				dataInicial.setSize(new Dimension(10, 10));
				dataInicial.setFont(new Font("Dialog", Font.PLAIN, 12));
				dataInicial.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
				dataInicial.setAutoscrolls(true);
				buttonPane.add(dataInicial);
			}
			{
				dataFinal = new JDateChooser(new Date());
				dataFinal.setFont(new Font("Dialog", Font.PLAIN, 12));
				dataFinal.setAutoscrolls(true);
				dataFinal.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
				buttonPane.add(dataFinal);
			}
			{
				btnImprimir = new JButton("IMPRIMIR");
				btnImprimir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						imprimir();
					}
				});
				btnImprimir.setEnabled(false);
				btnImprimir.setHorizontalTextPosition(SwingConstants.CENTER);
				btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 12));
				buttonPane.add(btnImprimir);
			}
			{
				JButton btnBuscar = new JButton("BUSCAR");
				btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
				btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				buttonPane.add(btnBuscar);
				btnBuscar.addActionListener(this);
			}
			{
				btnAlterar = new JButton("ALTERAR");
				btnAlterar.setHorizontalTextPosition(SwingConstants.CENTER);
				btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnAlterar.setActionCommand("ALTERAR");
				buttonPane.add(btnAlterar);
				getRootPane().setDefaultButton(btnAlterar);
				btnAlterar.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
				cancelButton.setActionCommand("CANCELAR");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
		buscar();
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

			a = JOptionPane.showInputDialog(this,"Nome do arquivo:");
			// Criando o arquivo de saída.
			OutputStream os = new FileOutputStream("D:\\Orcamento/" + a + ".pdf");
			System.out.println("--" + a);
			if (a != null) {

				// Associando o doc ao arquivo de saída.
				PdfWriter.getInstance(doc, os);

				// Abrindo o documento para a edição
				doc.open();

				// Adicionando um parágrafo ao PDF,
				Paragraph p = new Paragraph(
						"" + a + ", Gerada dia " + dt.format(new java.util.Date()));

				// Setando o alinhamento p/ o centro
				p.setAlignment(Paragraph.ALIGN_CENTER);

				// Definindo
				p.setSpacingAfter(50);
				doc.add(p);

				// Criando uma tabela com 3 colunas
				PdfPTable table = new PdfPTable((new float[] { 0.15f, 0.45f, 0.15f}));
				// Título para a tabela
				Paragraph tableHeader = new Paragraph("RELAÇÃO DE COMPRAS");

				PdfPCell header = new PdfPCell(tableHeader);
				// Definindo que o header vai ocupar as 3 colunas
				header.setColspan(3);
				// Definindo alinhamento do header
				header.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
				// Adicionando o header à tabela
				table.addCell(header);

				List<String> list = new ArrayList<String>();
				list.add("CÓD");
				list.add("DATA");
				list.add("TOTAL");

				float valor = 0;
				for (int i = 0; i < model.getRowCount(); i++) {
					Compra compra = model.retornaCompra(i);
					list.add((String) (String.valueOf(compra.getId())));
					list.add((String) (String.valueOf(dt.format(compra.getData()))));
					list.add((String) (String.valueOf(dfValor.format(compra.getTotal()))));

					valor = valor + compra.getTotal();
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
				Paragraph s = new Paragraph("Total das compras :" + dfValor.format(valor) + "");

				s.setAlignment(Paragraph.ALIGN_CENTER);
				s.setSpacingAfter(50);
				doc.add(s);
				

				JOptionPane.showMessageDialog(this, "Relação de Compras salva com sucesso!");
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
			
			dispose();
		}
	}

	void buscar() {
		try {
			total = 0;
			model.removeTudo();
			// contas os dias para não ultrapassar a quantidade de memoria ran
			System.out.println("DATA INICIAL "+ dataInicial.getDate().getTime());
			System.out.println("DATA FINAL "+ dataFinal.getDate().getTime());
			int dias = (int) ((dataFinal.getDate().getTime() - dataInicial.getDate().getTime()) / 86400000L);
			System.out.println("QUANT DIAS "+dias);
			// caso a quantidade de dias seja menor que trinta e um a pesquisa é
			// feita
			if (dias <= 7) {
				// pesquisa feita no banco de dados entre as duas datas
				@SuppressWarnings("unchecked")
				List<Compra> lista = (List<Compra>) banco.BuscaEntreData(Compra.class, dataInicial.getDate(),
						dataFinal.getDate(), "data");

				// ordena a lista com base no id
				Collections.sort(lista);
				
				System.out.println("TAMNHO ABSOLUTO DA LISTA "+lista.size());

				// preenche o model com as compras pesquisadas
				for (Compra compra : lista) {
					model.addRow(compra);
					total = total + compra.getTotal();
					btnAlterar.setEnabled(true);
				}
				
			} else {
				// caso a quantidade de dias seja maior que um mes a msn é a
				// presentada e os valores padões são restaurados nas datas
				JOptionPane.showMessageDialog(this, "A consulta tem que ter atá 7 dias");
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.add(GregorianCalendar.DATE, -20);
				dataInicial = new JDateChooser(calendar.getTime());
				dataFinal = new JDateChooser(new Date());
			}
			//setamos o valor total da pesquisa todas as compras
			txtTotal.setText(String.valueOf(total));
		} catch (java.lang.ClassCastException e) {
			//entrará aqui se digitar uma data ivalida
			btnAlterar.setEnabled(false);
			JOptionPane.showMessageDialog(contentPanel, "Data inválida escolha uma data correta.");
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, -20);
			dataInicial.setDate(calendar.getTime());
			dataFinal.setDate(new Date());
		} catch (NullPointerException eu) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, -20);
			dataInicial.setDate(calendar.getTime());
			dataFinal.setDate(new Date());
			JOptionPane.showMessageDialog(this, "Inserir datas válidas");
		}catch (Exception e) {
			btnAlterar.setEnabled(false);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, -20);
			dataInicial.setDate(calendar.getTime());
			dataFinal.setDate(new Date());
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPanel, "ERRO ao buscar um Compra.");
		}finally {
			//para desbloquerar ou bloquear o btnimprimir
			verificaTamanho();
		}
	}

	private void verificaTamanho() {
		if (model.getRowCount()>0) {
			btnImprimir.setEnabled(true);
		}else {
			btnImprimir.setEnabled(false);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		switch (acao) {
		case "CANCELAR":
			dispose();
			break;
		case "BUSCAR":
			buscar();
			break;
		case "ALTERAR":
			alterar();
			break;
		case "ESCOLHER":
			CompraEscolhido = (Compra) banco.buscarPorId(Compra.class,
					(Integer) table.getValueAt(table.getSelectedRow(), 0));
			setVisible(false);
			break;

		default:
			break;
		}

	}

	private void alterar() {
		try {
			//instancia uma compra com dados do banco de acordo  com o id exibido no model
			Compra Compra = (Compra) banco.buscarPorId(Compra.class,
					(Integer) table.getValueAt(table.getSelectedRow(), 0));
//			abre janela de alteração de compra 
			CompraProduto c = new CompraProduto();
			//insere a compra para ser alterada
			c.inserirCompra(Compra);
			//deixa a janela de forma exclusiva
			c.setModal(true);
			//deixa a janela visivel ao usuario
			c.setVisible(true);
			//quando a janela for feixada elabora uma nova busca
			buscar();
		} catch (ArrayIndexOutOfBoundsException a) {
			//entrara aqui se nem uma tupla for selecionado quando acionado o botão 
			JOptionPane.showMessageDialog(this, "Primeiro selecione uma compra para poder alterar");

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPanel, "Erro ao alterar Compra");
		}
	}

	public Compra getObj() {
		//retorna a compra escolhida, essa modalidade é a de escolher na qual não temos a opção de alterar
		return CompraEscolhido;
	}

	public void moduloEscolher() {
		//troca os valores do btn alterar para escolher mudando a função da janela
		this.btnAlterar.setText("ESCOLHER");
		this.btnAlterar.setActionCommand("ESCOLHER");
	}

}