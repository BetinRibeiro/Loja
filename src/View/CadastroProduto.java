package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Produto;
import Persistence.Dao;

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
		setBounds(100, 100, 685, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		{
			JLabel lblNewLabel = new JLabel("C\u00D3DIGO ");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 10, 120, 25);
			contentPanel.add(lblNewLabel);
		}
		{
			txtId = new JTextField();
			txtId.setHorizontalAlignment(SwingConstants.CENTER);
			txtId.setDisabledTextColor(Color.BLACK);
			txtId.setFont(new Font("Tunga", Font.PLAIN, 16));
			txtId.setEnabled(false);
			txtId.setBounds(10, 40, 120, 30);
			contentPanel.add(txtId);
			txtId.setColumns(10);
		}
		{
			JLabel lblDescrioDoProduto = new JLabel("DESCRI\u00C7\u00C3O DO PRODUTO");
			lblDescrioDoProduto.setFont(new Font("Arial", Font.PLAIN, 12));
			lblDescrioDoProduto.setBounds(10, 80, 635, 25);
			contentPanel.add(lblDescrioDoProduto);
		}
		{
			txtDescricao = new JTextField();
			txtDescricao.setText("Nome, marca e unidade de medida");
			txtDescricao.setFont(new Font("Arial", Font.PLAIN, 12));
			txtDescricao.setColumns(10);
			txtDescricao.setBounds(10, 110, 635, 30);
			contentPanel.add(txtDescricao);
		}
		{
			JLabel lblPreoDeVenda = new JLabel("PRE\u00C7O UNITARIO ");
			lblPreoDeVenda.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPreoDeVenda.setBounds(10, 145, 120, 25);
			contentPanel.add(lblPreoDeVenda);
		}
		{
			txtPreco = new JTextField();
			txtPreco.setHorizontalAlignment(SwingConstants.CENTER);
			txtPreco.setText("0,00");
			txtPreco.setFont(new Font("Arial", Font.PLAIN, 12));
			txtPreco.setColumns(10);
			txtPreco.setBounds(10, 175, 120, 30);
			contentPanel.add(txtPreco);
		}
		{
			JLabel lblEstoqueMinimo = new JLabel("ESTOQUE MINIMO");
			lblEstoqueMinimo.setFont(new Font("Arial", Font.PLAIN, 12));
			lblEstoqueMinimo.setBounds(10, 216, 120, 25);
			contentPanel.add(lblEstoqueMinimo);
		}
		{
			txtEstMin = new JTextField();
			txtEstMin.setHorizontalAlignment(SwingConstants.CENTER);
			txtEstMin.setText("1");
			txtEstMin.setFont(new Font("Arial", Font.PLAIN, 12));
			txtEstMin.setColumns(10);
			txtEstMin.setBounds(10, 250, 120, 30);
			contentPanel.add(txtEstMin);
		}
		{
			JLabel lblCustoUnitario = new JLabel("CUSTO UNITARIO");
			lblCustoUnitario.setFont(new Font("Arial", Font.PLAIN, 12));
			lblCustoUnitario.setBounds(163, 145, 120, 25);
			contentPanel.add(lblCustoUnitario);
		}
		{
			txtCusto = new JTextField();
			txtCusto.setText("0,00");
			txtCusto.setHorizontalAlignment(SwingConstants.CENTER);
			txtCusto.setDisabledTextColor(Color.BLACK);
			txtCusto.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCusto.setEnabled(false);
			txtCusto.setColumns(10);
			txtCusto.setBounds(163, 175, 120, 30);
			contentPanel.add(txtCusto);
		}
		{
			JLabel lblQuantidade = new JLabel("QUANTIDADE");
			lblQuantidade.setFont(new Font("Arial", Font.PLAIN, 12));
			lblQuantidade.setBounds(163, 216, 120, 25);
			contentPanel.add(lblQuantidade);
		}
		{
			txtQuant = new JTextField();
			txtQuant.setText("0,00");
			txtQuant.setHorizontalAlignment(SwingConstants.CENTER);
			txtQuant.setDisabledTextColor(Color.BLACK);
			txtQuant.setFont(new Font("Arial", Font.PLAIN, 12));
			txtQuant.setEnabled(false);
			txtQuant.setColumns(10);
			txtQuant.setBounds(163, 250, 120, 30);
			contentPanel.add(txtQuant);
		}
		{
			txtlocal = new JTextField();
			txtlocal.setText("Nome do Local");
			txtlocal.setFont(new Font("Arial", Font.PLAIN, 12));
			txtlocal.setColumns(10);
			txtlocal.setBounds(10, 356, 635, 30);
			contentPanel.add(txtlocal);
		}
		{
			JLabel lblLocalAondeEsta = new JLabel("LOCAL AONDE ESTA ARMAZENADO O PRODUTO");
			lblLocalAondeEsta.setFont(new Font("Arial", Font.PLAIN, 12));
			lblLocalAondeEsta.setBounds(10, 320, 635, 25);
			contentPanel.add(lblLocalAondeEsta);
		}
		{
			lblMensagemDeErro = new JLabel("MENSAGEM DE ERRO QUANDO ACONTECE ALGUMA EXCESS\u00C3O");
			lblMensagemDeErro.setVisible(false);
			lblMensagemDeErro.setForeground(Color.RED);
			lblMensagemDeErro.setFont(new Font("Arial", Font.PLAIN, 12));
			lblMensagemDeErro.setBounds(10, 387, 635, 25);
			contentPanel.add(lblMensagemDeErro);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SALVAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						salvar();
					}

				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
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
