package ViewNew.Ajuste;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Produto;
import Persistence.Ponte.Dao;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class AjustaEstoque extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtDescricao;
	private JTextField txtQuant;
	private JTextField txtPreco;
	private JTextField txtCusto;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JTextField txtLocal;
	private JTextField txtEstMin;
	private JLabel lblEstoqueMinimo;
	private Dao banco = new Dao();
	private JLabel lblMsnErr;
	DecimalFormat dfValor = new DecimalFormat("0.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AjustaEstoque dialog = new AjustaEstoque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AjustaEstoque() {
		setTitle("AJUSTE DE ESTOQUE");
		setAlwaysOnTop(true);
		setBounds(100, 100, 343, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new GridLayout(0, 1, 5, 0));

		JLabel lblCdigo = new JLabel("C\u00D3DIGO");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPanel.add(lblCdigo);
		
				txtid = new JTextField();
				txtid.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtid.setEnabled(false);
				txtid.setDisabledTextColor(Color.BLACK);
				txtid.setColumns(10);
				txtid.setBackground(new Color(255, 250, 205));
				contentPanel.add(txtid);
		
				JLabel lblDescrioProduto = new JLabel("DESCRI\u00C7\u00C3O PRODUTO");
				lblDescrioProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
				contentPanel.add(lblDescrioProduto);
		
				txtDescricao = new JTextField();
				txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtDescricao.setEnabled(false);
				txtDescricao.setDisabledTextColor(Color.BLACK);
				txtDescricao.setColumns(10);
				txtDescricao.setBackground(new Color(255, 250, 205));
				contentPanel.add(txtDescricao);
		
				JLabel lblQuantidade = new JLabel("QUANTIDADE");
				lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
				contentPanel.add(lblQuantidade);
		
				txtQuant = new JTextField();
				txtQuant.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtQuant.setColumns(10);
				contentPanel.add(txtQuant);
		
				JLabel lblPreoUnd = new JLabel("PRE\u00C7O VENDA UNIDADE");
				lblPreoUnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				contentPanel.add(lblPreoUnd);
		
				txtPreco = new JTextField();
				txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtPreco.setColumns(10);
				contentPanel.add(txtPreco);
		
				JLabel lblCustoUnd = new JLabel("CUSTO DE COMPRA UNIDADE");
				lblCustoUnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
				contentPanel.add(lblCustoUnd);
		
				txtCusto = new JTextField();
				txtCusto.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtCusto.setColumns(10);
				contentPanel.add(txtCusto);
		
				lblEstoqueMinimo = new JLabel("ESTOQUE MINIMO");
				lblEstoqueMinimo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				contentPanel.add(lblEstoqueMinimo);
		
				txtEstMin = new JTextField();
				txtEstMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtEstMin.setColumns(10);
				contentPanel.add(txtEstMin);
		
				JLabel lblLocalAondeEst = new JLabel("LOCALIZA\u00C7\u00C3O");
				lblLocalAondeEst.setFont(new Font("Tahoma", Font.PLAIN, 14));
				contentPanel.add(lblLocalAondeEst);
		
				txtLocal = new JTextField();
				txtLocal.setFont(new Font("Tahoma", Font.PLAIN, 16));
				txtLocal.setDisabledTextColor(Color.BLACK);
				txtLocal.setColumns(10);
				contentPanel.add(txtLocal);
		
				lblMsnErr = new JLabel("msn err");
				lblMsnErr.setVisible(false);
				lblMsnErr.setForeground(Color.RED);
				contentPanel.add(lblMsnErr);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSalvar = new JButton("SALVAR");
				btnSalvar.setIcon(new ImageIcon(AjustaEstoque.class.getResource("/img/018.png")));
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salvar();
					}

				});
				buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
				btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnSalvar.setActionCommand("SALVAR");
				buttonPane.add(btnSalvar);
				getRootPane().setDefaultButton(btnSalvar);
			}
			{
				btnCancelar = new JButton("CANCELAR");
				btnCancelar.setIcon(new ImageIcon(AjustaEstoque.class.getResource("/img/019.png")));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnCancelar.setActionCommand("CANCELAR");
				buttonPane.add(btnCancelar);
			}
		}
		validate();
	}

	private void salvar() {
		try {

			Integer id = Integer.valueOf(txtid.getText());
			String descricao = txtDescricao.getText();
			float custo = Float.valueOf(txtCusto.getText().replace(",", "."));
			if (custo <= 0) {
				JOptionPane.showMessageDialog(contentPanel, "Seu custo não pode ser menor que zero!");
				custo = 0;
			}

			float quantidade = Float.valueOf(txtQuant.getText().replace(",", "."));
			if (quantidade <= 0) {
				JOptionPane.showMessageDialog(contentPanel,
						"Quando sua quantidade é igual ou manor que zero seu custo é zero!");
				quantidade = 0;
				custo = 0;
			}
			float preco = Float.valueOf(txtPreco.getText().replace(",", "."));
			float estMin = Float.valueOf(txtEstMin.getText().replace(",", "."));
			String local = txtLocal.getText();
			if (preco < custo) {
				setVisible(true);
				JOptionPane.showMessageDialog(contentPanel,
						"Seu preço é menor que o custo do produto!!\n colocaremos 10% de lucro por dentro para não ter prejuiso");
				preco = (float) (custo / 0.9);
			}
			Produto produto = new Produto(id, descricao, custo, quantidade, preco, estMin, local);

			if (descricao.length() < 10) {
				lblMsnErr.setText("Inserir pelo menos 10 caracteres no campo da descrição");
				lblMsnErr.setVisible(true);
				;
			} else {
				boolean a = banco.salvarOuAtualizarObjeto(produto);

				if (a) {
					JOptionPane.showMessageDialog(contentPanel, "Produto alterado com sucesso");
					dispose();

				} else {
					JOptionPane.showMessageDialog(contentPanel, "Erro ao salvar o produto");
					dispose();

				}
			}
		} catch (java.lang.NumberFormatException e) {
			lblMsnErr.setText("Insira numeros válidos nos campos numericos");
			lblMsnErr.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Erro ao alterar o produto!:" + e);
		}
	}

	public boolean inseirProduto(Produto produto) {
		try {
			txtid.setText(String.valueOf(produto.getId()));
			txtDescricao.setText(String.valueOf(produto.getDescricao()));
			txtPreco.setText(String.valueOf(dfValor.format(produto.getPreco())));
			txtCusto.setText(String.valueOf(dfValor.format(produto.getCusto())));
			txtQuant.setText(String.valueOf(produto.getQuantidade()));
			txtLocal.setText(String.valueOf(produto.getLocal()));
			txtEstMin.setText(String.valueOf(produto.getEstMin()));
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Faça um balanço de estoque");
			return false;
		}
	}

}
