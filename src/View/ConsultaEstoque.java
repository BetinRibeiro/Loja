package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Produto;
import Controller.ModelTabelaPesquisaProdutoInventario;
import Persistence.Dao;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Font;

public class ConsultaEstoque extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ModelTabelaPesquisaProdutoInventario model = new ModelTabelaPesquisaProdutoInventario();
	private JTextField txtBusca;
	private Dao banco = new Dao();
	private JButton btnAlterar;
	private Produto produtoEscolhido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			ConsultaEstoque dialog = new ConsultaEstoque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConsultaEstoque() {
		setTitle("Balanço do estoque");
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
		table.getTableHeader().setReorderingAllowed(false);
		// tamanho especifico da coluna
		// tabelaProduto.getColumn("Fornecedor").setPreferredWidth(220);
		table.getColumn("Descrição").setPreferredWidth(220);
		scrollPane.setViewportView(table);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				txtBusca = new JTextField();
				txtBusca.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtBusca.setAlignmentX(Component.RIGHT_ALIGNMENT);
				buttonPane.add(txtBusca);
				txtBusca.setColumns(20);
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
			model.removeTudo();
			List<?> lista = banco.BuscaNome(Produto.class, txtBusca.getText(), "descricao");
			int tamanho = lista.size();
			if (lista.size() >= 30) {
				tamanho = 30;
			}
			for (int i = 0; i < tamanho; i++) {
				Produto classif = (Produto) lista.get(i);
				if (classif.getPreco()<classif.getCusto()) {
					setVisible(true);
					JOptionPane.showMessageDialog(contentPanel, "No produto "+classif.getDescricao()+" seu preço é menor que o custo do produto!!\n colocaremos 10% de lucro por dentro para não ter prejuiso");
					classif.setPreco((float) (classif.getCusto()/0.9));
					banco.salvarOuAtualizarObjeto(classif);
				}
				model.addRow(classif);
				btnAlterar.setEnabled(true);
			}
		} catch (Exception e) {
			btnAlterar.setEnabled(false);
			JOptionPane.showMessageDialog(contentPanel, "ERRO ao buscar um Produto.");
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
			produtoEscolhido = (Produto) banco.buscarPorId(Produto.class,
					(Integer) table.getValueAt(table.getSelectedRow(), 0));
			setVisible(false);
			break;

		default:
			break;
		}

	}

	private void alterar() {
		try {
			
		
		Produto produto = (Produto) banco.buscarPorId(Produto.class,
				(Integer) table.getValueAt(table.getSelectedRow(), 0));
		AjustaEstoque c = new AjustaEstoque();
		c.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		c.inseirProduto(produto);
		c.setModal(true);
		c.setVisible(true);
		c.validate();
		c.repaint();
		buscar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao ajustar produto");
		}
		}

	public Produto getObj() {

		return produtoEscolhido;
	}

	public void moduloEscolher() {
		this.btnAlterar.setText("Escolher");
		this.btnAlterar.setActionCommand("Escolher");
	}
	
	
	
}