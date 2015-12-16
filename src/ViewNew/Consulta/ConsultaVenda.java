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

import Bin.Venda;
import Controller.Model.Tabela.Movimentacao.ModelTabelaListaVendas;
import Persistence.Ponte.Dao;
import ViewNew.Movimentacao.VendaProduto;

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

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class ConsultaVenda extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ModelTabelaListaVendas model = new ModelTabelaListaVendas();
	private Dao banco = new Dao();
	private JButton btnAlterar;
	private Venda VendaEscolhido;
	private JDateChooser dataFinal;
	private JTextField txtTotal;
	private float total = 0;
	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	private JDateChooser dataInicial;
	private JButton btnImprimir;
	DecimalFormat dfValor = new DecimalFormat("0.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			ConsultaVenda dialog = new ConsultaVenda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaVenda() {
		setTitle("PESQUISA DE VENDA");
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
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(2, 4, 5, 5));
			{
				JLabel lblTotal = new JLabel("TOTAL");
				lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
				lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
				buttonPane.add(lblTotal);
			}
			{
				txtTotal = new JTextField();
				txtTotal.setPreferredSize(new Dimension(6, 30));
				txtTotal.setDisabledTextColor(Color.DARK_GRAY);
				txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtTotal.setEnabled(false);
				buttonPane.add(txtTotal);
				txtTotal.setColumns(8);
			}
			{
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.add(GregorianCalendar.DATE, -7);
				dataInicial = new JDateChooser(calendar.getTime());
				dataInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
				dataInicial.setPreferredSize(new Dimension(120, 30));
				dataInicial.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
				buttonPane.add(dataInicial);
			}
			{
				dataFinal = new JDateChooser(new Date());
				dataFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
				dataFinal.setPreferredSize(new Dimension(120, 30));
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
				btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 14));
				buttonPane.add(btnImprimir);
			}
			{
				JButton btnBuscar = new JButton("BUSCAR");
				btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				buttonPane.add(btnBuscar);
				btnBuscar.addActionListener(this);
			}
			{
				btnAlterar = new JButton("ALTERAR");
				btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnAlterar.setActionCommand("ALTERAR");
				buttonPane.add(btnAlterar);
				getRootPane().setDefaultButton(btnAlterar);
				btnAlterar.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
				PdfPTable table = new PdfPTable((new float[] { 0.15f, 0.45f, 0.15f, 0.1f, 0.15f }));
				// Título para a tabela
				Paragraph tableHeader = new Paragraph("RELAÇÃO DE COMPRAS");

				PdfPCell header = new PdfPCell(tableHeader);
				// Definindo que o header vai ocupar as 3 colunas
				header.setColspan(5);
				// Definindo alinhamento do header
				header.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
				// Adicionando o header à tabela
				table.addCell(header);

				List<String> list = new ArrayList<String>();
				list.add("CÓD");
				list.add("DATA");
				list.add("CUSTO");
				list.add("DESCONTO");
				list.add("TOTAL");

				float custo = 0;
				float valor = 0;
				float desconto = 0;
				for (int i = 0; i < model.getRowCount(); i++) {
					Venda venda = model.retornaVenda(i);
					list.add((String) (String.valueOf(venda.getId())));
					list.add((String) (String.valueOf(dt.format(venda.getData()))));
					list.add((String) (String.valueOf(dfValor.format(venda.getCusto()))));
					list.add((String) (String.valueOf(dfValor.format(venda.getDesconto()))));
					list.add((String) (String.valueOf(dfValor.format(venda.getValor()))));

					custo = custo + venda.getCusto();
					valor = valor + venda.getValor();
					desconto = desconto + venda.getDesconto();
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
				Paragraph s = new Paragraph("Total das vendas :" + dfValor.format(valor) + "");

				s.setAlignment(Paragraph.ALIGN_CENTER);
				s.setSpacingAfter(50);
				doc.add(s);
				s = new Paragraph("Total de custo :" + dfValor.format(custo) + "");
				s.setAlignment(Paragraph.ALIGN_CENTER);
				s.setSpacingAfter(10);
				doc.add(s);
				s = new Paragraph("Total de descontos :" + dfValor.format(desconto) + "");
				s.setAlignment(Paragraph.ALIGN_CENTER);
				s.setSpacingAfter(10);
				doc.add(s);
				s = new Paragraph("Lucro real das vendas :" + dfValor.format(valor - custo - desconto) + "");
				s.setAlignment(Paragraph.ALIGN_CENTER);
				s.setSpacingAfter(10);
				doc.add(s);

				JOptionPane.showMessageDialog(this, "Relação de Vendas salva com sucesso!");
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
			int dias = (int) ((dataFinal.getDate().getTime() - dataInicial.getDate().getTime()) / 86400000L);
			// caso a quantidade de dias seja menor que trinta e um a pesquisa é
			// feita
			if (dias <= 7) {
				// pesquisa feita no banco de dados entre as duas datas
				@SuppressWarnings("unchecked")
				List<Venda> lista = (List<Venda>) banco.BuscaEntreData(Venda.class, dataInicial.getDate(),
						dataFinal.getDate(), "data");

				// ordena a lista com base no id
				Collections.sort(lista);

				// preenche o model com as vendas pesquisadas
				for (Venda venda : lista) {
					model.addRow(venda);
					total = total + venda.getValor();
					btnAlterar.setEnabled(true);
				}
			} else {
				// caso a quantidade de dias seja maior que um mes a msn é a
				// presentada e os valores padões são restaurados nas datas
				JOptionPane.showMessageDialog(this, "A consulta tem que carrgar até 7 dias");
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.add(GregorianCalendar.DATE, -20);
				dataInicial = new JDateChooser(calendar.getTime());
				dataFinal = new JDateChooser(new Date());
			}
			// setamos o valor total da pesquisa todas as vendas
			txtTotal.setText(String.valueOf(total));
		} catch (java.lang.ClassCastException e) {
			// entrará aqui se digitar uma data ivalida
			btnAlterar.setEnabled(false);
			JOptionPane.showMessageDialog(contentPanel, "Data inválida escolha uma data correta.");
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, -20);
			dataInicial.setDate(calendar.getTime());
			dataFinal.setDate(new Date());
		} catch (NullPointerException eu) {
			JOptionPane.showMessageDialog(this, "Inserir datas válidas");
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, -20);
			dataInicial.setDate(calendar.getTime());
			dataFinal.setDate(new Date());
		} catch (Exception e) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DATE, -20);
			dataInicial.setDate(calendar.getTime());
			dataFinal.setDate(new Date());
			btnAlterar.setEnabled(false);
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPanel, "ERRO ao buscar um Venda.");
		} finally {
			// para desbloquerar ou bloquear o btnimprimir
			verificaTamanho();
		}
	}

	private void verificaTamanho() {
		if (model.getRowCount() > 0) {
			btnImprimir.setEnabled(true);
		} else {
			btnImprimir.setEnabled(false);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		System.out.println(acao);
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
			VendaEscolhido = (Venda) banco.buscarPorId(Venda.class,
					(Integer) table.getValueAt(table.getSelectedRow(), 0));
			setVisible(false);
			break;

		default:
			break;
		}

	}

	private void alterar() {
		try {

			Venda Venda = (Venda) banco.buscarPorId(Venda.class, (Integer) table.getValueAt(table.getSelectedRow(), 0));
			VendaProduto c = new VendaProduto();
			c.inserirVenda(Venda);
			c.setModal(true);
			c.setVisible(true);
			buscar();
		} catch (ArrayIndexOutOfBoundsException a) {
			// entrara aqui se nem uma tupla for selecionado quando acionado o
			// botão
			JOptionPane.showMessageDialog(this, "Primeiro selecione uma venda para poder alterar");

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPanel, "Erro ao alterar Venda");
		}
	}

	public Venda getObj() {

		return VendaEscolhido;
	}

	public void moduloEscolher() {
		this.btnAlterar.setText("ESCOLHER");
		this.btnAlterar.setActionCommand("ESCOLHER");
	}

}