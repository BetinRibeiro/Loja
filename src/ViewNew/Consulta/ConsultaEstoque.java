package ViewNew.Consulta;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Produto;
import Controller.Model.Tabela.Produto.ModelTabelaPesquisaProdutoInventario;
import Persistence.Ponte.Dao;
import ViewNew.Ajuste.AjustaEstoque;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Dimension;

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
		setTitle("BALAN\u00C7O DE ESTOQUE");
		setBounds(100, 100, 650, 450);
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
		table.getTableHeader().setReorderingAllowed(false);
		// tamanho especifico da coluna
		// tabelaProduto.getColumn("Fornecedor").setPreferredWidth(220);
		table.getColumn("DESCRIÇÃO").setPreferredWidth(380);
		scrollPane.setViewportView(table);

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(2, 4, 5, 5));
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			{
				txtBusca = new JTextField();
				txtBusca.setMinimumSize(new Dimension(6, 30));
				txtBusca.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtBusca.setAlignmentX(Component.RIGHT_ALIGNMENT);
				buttonPane.add(txtBusca);
				txtBusca.setColumns(30);
			}
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
			}
			{
				JLabel label = new JLabel("");
				buttonPane.add(label);
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
		this.btnAlterar.setText("ESCOLHER");
		this.btnAlterar.setActionCommand("ESCOLHER");
	}
	
	
	
}