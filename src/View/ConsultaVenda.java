package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Venda;
import Controller.ModelTabelaListaVendas;
import Persistence.Dao;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

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
	private JDateChooser data;
	private JTextField txtTotal;
	private float total=0;

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
		setTitle("Pesquisa Venda");
		setBounds(100, 100, 784, 438);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 768, 361);
		contentPanel.add(scrollPane);

		table = new JTable(model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblTotal = new JLabel("TOTAL");
				lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
				buttonPane.add(lblTotal);
			}
			{
				txtTotal = new JTextField();
				txtTotal.setDisabledTextColor(Color.DARK_GRAY);
				txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtTotal.setEnabled(false);
				buttonPane.add(txtTotal);
				txtTotal.setColumns(10);
			}
			{
				 data = new JDateChooser(new Date());
				data.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 14));
				buttonPane.add(data);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				buttonPane.add(btnBuscar);
				btnBuscar.addActionListener(this);
			}
			{
				btnAlterar = new JButton("Alterar");
				btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnAlterar.setActionCommand("Alterar");
				buttonPane.add(btnAlterar);
				getRootPane().setDefaultButton(btnAlterar);
				btnAlterar.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
		buscar();
	}

	void buscar() {
		try {
			total=0;
			model.removeTudo();
			List<?> lista = banco.BuscaData(Venda.class, data.getDate(), "data");
			@SuppressWarnings("unchecked")
			Set<Venda> listaNew = new HashSet<Venda>((Collection<? extends Venda>) lista);
//			int tamanho = lista.size();
//			if (lista.size() >= 30) {
//				tamanho = 30;
//			}
			System.out.println("chegou aqui");
//			for (int i = 0; i < tamanho; i++) {
//				Venda comp = (Venda) lista.get(i);
//				System.out.println(comp.getData());
//				model.addRow(comp);
//				btnAlterar.setEnabled(true);
//			}
			 int a=0;
			 for (Venda Venda : listaNew) {
			 model.addRow(Venda);
			 total=total+Venda.getValor();
			 btnAlterar.setEnabled(true);
			 if (a>30) {
			 break;
			 }
			 a++;
			 }
			 txtTotal.setText(String.valueOf(total));
		} catch (java.lang.ClassCastException e) {
			btnAlterar.setEnabled(false);
			JOptionPane.showMessageDialog(contentPanel, "Não temos pesquisa nessa modalidade.");
		} catch (Exception e) {
			btnAlterar.setEnabled(false);
			JOptionPane.showMessageDialog(contentPanel, "ERRO ao buscar um Venda.");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		switch (acao) {
		case "Cancel":
			dispose();
			break;
		case "Buscar":
			buscar();
			break;
		case "Alterar":
			alterar();
			break;
		case "Escolher":
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
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao alterar Venda");
		}
	}

	public Venda getObj() {

		return VendaEscolhido;
	}

	public void moduloEscolher() {
		this.btnAlterar.setText("Escolher");
		this.btnAlterar.setActionCommand("Escolher");
	}

}