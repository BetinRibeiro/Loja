package ViewNew.Cadastro;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Produto;
import Persistence.Ponte.Dao;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class CadastroProduto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtDescricao;
	private JTextField txtPreco;
	private JTextField txtEstMin;
	private JTextField txtCusto;
	private JTextField txtQuant;
	private JTextField txtlocal;
	private JLabel lblMensagemDeErro;
	private Dao banco = new Dao();
	DecimalFormat dfValor = new DecimalFormat("0.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			CadastroProduto dialog = new CadastroProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroProduto() {
		setTitle("CADASTRO DE PRODUTO");
		setBounds(100, 100, 339, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new GridLayout(0, 1, 10, 5));
		{
			JLabel lblNewLabel = new JLabel("C\u00D3DIGO ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblNewLabel);
		}
		{
			txtId = new JTextField();
			txtId.setHorizontalAlignment(SwingConstants.CENTER);
			txtId.setDisabledTextColor(Color.BLACK);
			txtId.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtId.setEnabled(false);
			contentPanel.add(txtId);
			txtId.setColumns(10);
		}
		{
			JLabel lblDescrioDoProduto = new JLabel("DESCRI\u00C7\u00C3O");
			lblDescrioDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblDescrioDoProduto);
		}
		{
			txtDescricao = new JTextField();
			txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtDescricao.setColumns(10);
			contentPanel.add(txtDescricao);
		}
		{
			JLabel lblPreoDeVenda = new JLabel("PRE\u00C7O UNITARIO ");
			lblPreoDeVenda.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblPreoDeVenda);
		}
		{
			txtPreco = new JTextField();
			txtPreco.setHorizontalAlignment(SwingConstants.CENTER);
			txtPreco.setText("0,00");
			txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtPreco.setColumns(10);
			contentPanel.add(txtPreco);
		}
		{
			JLabel lblCustoUnitario = new JLabel("CUSTO UNITARIO");
			lblCustoUnitario.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblCustoUnitario);
		}
		{
			txtCusto = new JTextField();
			txtCusto.setBackground(new Color(255, 228, 181));
			txtCusto.setText("0,00");
			txtCusto.setHorizontalAlignment(SwingConstants.CENTER);
			txtCusto.setDisabledTextColor(Color.BLACK);
			txtCusto.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtCusto.setEnabled(false);
			txtCusto.setColumns(10);
			contentPanel.add(txtCusto);
		}
		{
			JLabel lblEstoqueMinimo = new JLabel("ESTOQUE MINIMO");
			lblEstoqueMinimo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblEstoqueMinimo);
		}
		{
			txtEstMin = new JTextField();
			txtEstMin.setHorizontalAlignment(SwingConstants.CENTER);
			txtEstMin.setText("1");
			txtEstMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtEstMin.setColumns(10);
			contentPanel.add(txtEstMin);
		}
		{
			JLabel lblQuantidade = new JLabel("QUANTIDADE");
			lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblQuantidade);
		}
		{
			txtQuant = new JTextField();
			txtQuant.setBackground(new Color(255, 228, 181));
			txtQuant.setText("0,00");
			txtQuant.setHorizontalAlignment(SwingConstants.CENTER);
			txtQuant.setDisabledTextColor(Color.BLACK);
			txtQuant.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtQuant.setEnabled(false);
			txtQuant.setColumns(10);
			contentPanel.add(txtQuant);
		}
		{
			JLabel lblLocalAondeEsta = new JLabel("LOCALIZA\u00C7\u00C3O");
			lblLocalAondeEsta.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblLocalAondeEsta);
		}
		{
			lblMensagemDeErro = new JLabel("MENSAGEM DE ERRO QUANDO ACONTECE ALGUMA EXCESS\u00C3O");
			lblMensagemDeErro.setVisible(false);
			{
				txtlocal = new JTextField();
				txtlocal.setText("A");
				txtlocal.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtlocal.setColumns(10);
				contentPanel.add(txtlocal);
			}
			lblMensagemDeErro.setForeground(Color.RED);
			lblMensagemDeErro.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPanel.add(lblMensagemDeErro);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SALVAR");
				okButton.setIcon(new ImageIcon(CadastroProduto.class.getResource("/img/018.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						salvar();
					}

				});
				buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.setIcon(new ImageIcon(CadastroProduto.class.getResource("/img/019.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private boolean salvar() {
		try {

			String descricao = txtDescricao.getText().toUpperCase();

			float custo = Float.parseFloat(txtCusto.getText().replace(",", "."));
			float preco = Float.parseFloat(txtPreco.getText().replace(",", "."));
			float quantidade = Float.parseFloat(txtQuant.getText().replace(",", "."));
			float estMin = Float.parseFloat(txtEstMin.getText());
			String local = txtlocal.getText().toUpperCase();
			Produto produto = new Produto(descricao, custo, quantidade, preco, estMin, local);
			boolean a = false;
			
			if (!txtId.getText().equals("")) {
				produto.setId(Integer.parseInt(txtId.getText()));
				
			}
			if (descricao.length() < 10) {
				lblMensagemDeErro.setText("Inserir pelo menos 10 caracteres no campo da descrição");
				lblMensagemDeErro.setVisible(true);
				;
			} else {
				a = banco.salvarOuAtualizarObjeto(produto);

				if (a) {
					JOptionPane.showMessageDialog(contentPanel, "Produto Salvo com sucesso");
					dispose();

				} else {
					JOptionPane.showMessageDialog(contentPanel, "Erro ao salvar o produto");
					dispose();

				}
			}
			return a;
		} catch (java.lang.NumberFormatException e) {
			lblMensagemDeErro.setText("Insira numeros válidos nos campos numericos");
			lblMensagemDeErro.setVisible(true);
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "ERRO! " + e.getMessage());
			dispose();
			return false;
		}

	}
	

	public boolean inserirProduto(Produto produto) {
		try {
			
		
		txtId.setText(String.valueOf(produto.getId()));
		txtCusto.setText(String.valueOf(dfValor.format(produto.getCusto())));
		txtDescricao.setText(String.valueOf(produto.getDescricao()));
		txtEstMin.setText(String.valueOf(produto.getEstMin()));
		txtlocal.setText(String.valueOf(produto.getLocal()));
		txtPreco.setText( String.valueOf(dfValor.format(produto.getPreco())));
		txtQuant.setText(String.valueOf(produto.getQuantidade()));
		return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "ERRO! " + e.getMessage());
			dispose();
			return false;
		}
	}

}
