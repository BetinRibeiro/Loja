package ViewNew.Seguranca;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Senha extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private String senha="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Senha dialog = new Senha();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Senha() {
		setTitle("SENHA DE AUTORIZA\u00C7\u00C3O");
		setBounds(100, 100, 254, 187);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new GridLayout(0, 1, 10, 10));
		{
			JLabel lblSenha = new JLabel("SENHA");
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
			lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 20));
			contentPanel.add(lblSenha);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setHorizontalAlignment(SwingConstants.CENTER);
			passwordField.setToolTipText("");
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(Senha.class.getResource("/img/018.png")));
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.addActionListener(new ActionListener() {

					

					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						senha = passwordField.getText();
						setVisible(false);
					}
				});
				buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
				cancelButton.setIcon(new ImageIcon(Senha.class.getResource("/img/004.png")));
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
