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

import Bin.Compra;
import Controller.ModelTabelaListaCompras;
import Persistence.Dao;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

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
	private JDateChooser data;
	private JTextField txtTotal;
	private float total = 0;

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
		setTitle("Pesquisa Compra");
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
				JLabel label = new JLabel("TOTAL");
				label.setFont(new Font("Tahoma", Font.PLAIN, 16));
				buttonPane.add(label);
			}
			{
				txtTotal = new JTextField();
				txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
				txtTotal.setDisabledTextColor(Color.DARK_GRAY);
				txtTotal.setEnabled(false);
				txtTotal.setColumns(10);
				buttonPane.add(txtTotal);
			}
			{
				data = new JDateChooser(new Date());
				data.setAutoscrolls(true);
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
			List<?> lista = banco.BuscaData(Compra.class, data.getDate(), "data");
			@SuppressWarnings("unchecked")
			Set<Compra> listaNew = new HashSet<Compra>((Collection<? extends Compra>) lista);
			// int tamanho = lista.size();
			// if (lista.size() >= 30) {
			// tamanho = 30;
			// }
			System.out.println("chegou aqui");
			// for (int i = 0; i < tamanho; i++) {
			// Compra comp = (Compra) lista.get(i);
			// System.out.println(comp.getData());
			// model.addRow(comp);
			// btnAlterar.setEnabled(true);
			// }

			int a = 0;
			for (Compra compra : listaNew) {
				model.addRow(compra);
				total = total +compra.getTotal();
				btnAlterar.setEnabled(true);
				if (a > 30) {
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
			System.out.println(e);
			JOptionPane.showMessageDialog(contentPanel, "ERRO ao buscar um Compra.");
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

			Compra Compra = (Compra) banco.buscarPorId(Compra.class,
					(Integer) table.getValueAt(table.getSelectedRow(), 0));
			CompraProduto c = new CompraProduto();
			c.inserirCompra(Compra);
			c.setModal(true);
			c.setVisible(true);
			buscar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao alterar Compra");
		}
	}

	public Compra getObj() {

		return CompraEscolhido;
	}

	public void moduloEscolher() {
		this.btnAlterar.setText("Escolher");
		this.btnAlterar.setActionCommand("Escolher");
	}

}