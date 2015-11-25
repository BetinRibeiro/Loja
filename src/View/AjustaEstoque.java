package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bin.Produto;
import Persistence.Dao;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

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
		setAlwaysOnTop(true);
		setBounds(100, 100, 649, 372);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		txtid = new JTextField();
		txtid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtid.setEnabled(false);
		txtid.setDisabledTextColor(Color.BLACK);
		txtid.setColumns(10);
		txtid.setBackground(new Color(255, 250, 205));
		txtid.setBounds(10, 55, 109, 29);
		contentPanel.add(txtid);

		JLabel label = new JLabel("C\u00F3digo");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 25, 86, 20);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("Descri\u00E7\u00E3o do Produto");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(137, 25, 280, 20);
		contentPanel.add(label_1);

		txtDescricao = new JTextField();
		txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDescricao.setEnabled(false);
		txtDescricao.setDisabledTextColor(Color.BLACK);
		txtDescricao.setColumns(10);
		txtDescricao.setBackground(new Color(255, 250, 205));
		txtDescricao.setBounds(129, 55, 489, 29);
		contentPanel.add(txtDescricao);

		txtQuant = new JTextField();
		txtQuant.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtQuant.setColumns(10);
		txtQuant.setBounds(10, 125, 90, 30);
		contentPanel.add(txtQuant);

		JLabel label_2 = new JLabel("Quantidade");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(10, 95, 86, 20);
		contentPanel.add(label_2);

		JLabel lblPreoUnd = new JLabel("Pre\u00E7o Und");
		lblPreoUnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreoUnd.setBounds(105, 95, 89, 20);
		contentPanel.add(lblPreoUnd);

		txtPreco = new JTextField();
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPreco.setColumns(10);
		txtPreco.setBounds(105, 125, 90, 30);
		contentPanel.add(txtPreco);

		JLabel lblCustoUnd = new JLabel("Custo Und");
		lblCustoUnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCustoUnd.setBounds(200, 95, 89, 20);
		contentPanel.add(lblCustoUnd);

		txtCusto = new JTextField();
		txtCusto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCusto.setColumns(10);
		txtCusto.setBounds(200, 125, 90, 30);
		contentPanel.add(txtCusto);

		txtLocal = new JTextField();
		txtLocal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLocal.setDisabledTextColor(Color.BLACK);
		txtLocal.setColumns(10);
		txtLocal.setBounds(10, 196, 489, 29);
		contentPanel.add(txtLocal);

		JLabel lblLocalAondeEst = new JLabel("Local aonde est\u00E1 armazenado");
		lblLocalAondeEst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLocalAondeEst.setBounds(10, 166, 280, 20);
		contentPanel.add(lblLocalAondeEst);

		txtEstMin = new JTextField();
		txtEstMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEstMin.setColumns(10);
		txtEstMin.setBounds(295, 125, 89, 30);
		contentPanel.add(txtEstMin);

		lblEstoqueMinimo = new JLabel("Estoque Minimo");
		lblEstoqueMinimo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstoqueMinimo.setBounds(295, 95, 130, 20);
		contentPanel.add(lblEstoqueMinimo);

		lblMsnErr = new JLabel("msn err");
		lblMsnErr.setVisible(false);
		lblMsnErr.setForeground(Color.RED);
		lblMsnErr.setBounds(10, 270, 582, 14);
		contentPanel.add(lblMsnErr);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSalvar = new JButton("Salvar");
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salvar();
					}

				});
				btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnSalvar.setActionCommand("Salvar");
				buttonPane.add(btnSalvar);
				getRootPane().setDefaultButton(btnSalvar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
				btnCancelar.setActionCommand("Canelar");
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
