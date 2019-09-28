package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import controller.UsuarioController;

import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JButton btLogin;
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbMenu = new JLabel("New label");
		lbMenu.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/agua.png")));
		lbMenu.setBackground(new Color(0, 102, 51));
		lbMenu.setBounds(23, 11, 156, 122);
		contentPane.add(lbMenu);
		
		ImageIcon novoMenu = new ImageIcon(TelaSistema.class.getResource("/view/agua.png"));
		Image novoMen = novoMenu.getImage().getScaledInstance(lbMenu.getWidth(), lbMenu.getHeight(), Image.SCALE_SMOOTH);	
		lbMenu.setIcon(new ImageIcon(novoMen));
		
		JLabel label = new JLabel("Lava");
		label.setForeground(new Color(199, 21, 133));
		label.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 35));
		label.setBounds(180, 11, 86, 44);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("&");
		label_1.setForeground(new Color(199, 21, 133));
		label_1.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 35));
		label_1.setBounds(275, 41, 36, 44);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Passa");
		label_2.setForeground(new Color(199, 21, 133));
		label_2.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 35));
		label_2.setBounds(309, 74, 102, 44);
		contentPane.add(label_2);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setForeground(new Color(199, 21, 133));
		lblUsurio.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblUsurio.setBounds(130, 162, 156, 28);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(199, 21, 133));
		lblSenha.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblSenha.setBounds(130, 219, 156, 28);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(130, 188, 156, 20);
		contentPane.add(tfUsuario);
		
		JLabel lblUsuarioImg = new JLabel("", new ImageIcon(TelaLogin.class.getResource("/view/usuario1.png")),JLabel.CENTER);
		lblUsuarioImg.setBackground(new Color(255, 0, 255));
		lblUsuarioImg.setBounds(43, 168, 83, 40);
		contentPane.add(lblUsuarioImg);

		JLabel lblSenhaImg = new JLabel("", new ImageIcon(TelaLogin.class.getResource("/view/senha1.png")),JLabel.CENTER); 
		lblSenhaImg.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSenhaImg.setForeground(Color.white);
		lblSenhaImg.setBounds(53, 227, 57, 40);
		contentPane.add(lblSenhaImg);
		
		btLogin = new JButton("Login");
		btLogin.setForeground(new Color(0, 0, 128));
		btLogin.setFont(new Font("Roboto", Font.BOLD, 18));
		btLogin.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128)));
		btLogin.setBackground(Color.WHITE);
		btLogin.setBounds(146, 344, 120, 40);
		contentPane.add(btLogin);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(130, 247, 156, 20);
		contentPane.add(tfSenha);
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		/**
		 * Chamar uma tela para cadastrar
		 */
		btLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController u = new UsuarioController(tfUsuario, tfSenha);
				u.logar();
				TelaLogin.this.setVisible(false);
			}

		});
		
	}
}
