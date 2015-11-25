package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
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
		setTitle("Login");
		setBounds(100, 100, 295, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(30, 30, 46, 14);
		contentPanel.add(lblLogin);
		
		String[] lista = {"Administrador","Operador de caixa"};
		
		 box = new JComboBox<String>(lista);
		box.setFont(new Font("Tahoma", Font.PLAIN, 16));
		box.setBounds(30, 60, 216, 30);
		contentPanel.add(box);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(30, 110, 46, 14);
		contentPanel.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSenha.setBounds(30, 140, 216, 30);
		contentPanel.add(txtSenha);
		
		lblMsn = new JLabel("msn");
		lblMsn.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsn.setForeground(Color.RED);
		lblMsn.setBounds(30, 183, 216, 14);
		contentPanel.add(lblMsn);
		lblMsn.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String usuario = (String) box.getSelectedItem();
						System.out.println(usuario);
						
						@SuppressWarnings("deprecation")
						String senha = txtSenha.getText();
						
						System.out.println(senha);
						
						if (usuario.equals("Administrador")&& senha.equals("qwe456")) {
							Inicial i = new Inicial();
							i.setVisible(true);
							dispose();
						}if (usuario.equals("Operador de caixa")&& senha.equals("0000")) {
							Inicial i = new Inicial();
							i.setVisible(true);
							i.operador();
							dispose();
						}else {
							lblMsn.setText("SENHA ERRADA!");
							lblMsn.setVisible(true);
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
