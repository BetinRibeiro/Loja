package ViewNew.Seguranca;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ViewNew.JanelaInicial;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtSenha;
	private JComboBox<String> box;
	private JLabel lblMsn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		setTitle("LOGIN");
		setBounds(100, 100, 295, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblLogin);

		String[] lista = { "Administrador", "Operador de caixa" };

		box = new JComboBox<String>(lista);
		box.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(box);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(txtSenha);

		lblMsn = new JLabel("msn");
		lblMsn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsn.setForeground(Color.RED);
		contentPanel.add(lblMsn);
		lblMsn.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(Login.class.getResource("/img/018.png")));
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String usuario = (String) box.getSelectedItem();
						System.out.println(usuario);

						@SuppressWarnings("deprecation")
						String senha = txtSenha.getText();

						System.out.println(senha);

						if (usuario.equals("Administrador") && senha.equals("qwe456")) {
							JanelaInicial i = new JanelaInicial();
							i.setVisible(true);
							dispose();
						}
						if (usuario.equals("Operador de caixa") && senha.equals("0000")) {
							JanelaInicial i = new JanelaInicial();
							i.setVisible(true);
							i.operador();
							dispose();
						} else {
							lblMsn.setText("SENHA ERRADA!");
							lblMsn.setVisible(true);

						}
					}
				});
				buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.setIcon(new ImageIcon(Login.class.getResource("/img/019.png")));
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
