package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JButton btCadastrar, btEntrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(117, 132, 153, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);

		tfSenha = new JTextField();
		tfSenha.setToolTipText("");
		tfSenha.setBounds(117, 176, 153, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);

		btEntrar = new JButton("Entrar");
		btEntrar.setBounds(117, 247, 89, 23);
		contentPane.add(btEntrar);

		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsuario.setBounds(55, 134, 52, 14);
		contentPane.add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSenha.setBounds(64, 179, 43, 14);
		contentPane.add(lblSenha);

		btCadastrar = new JButton("Cadastrar novo usu\u00E1rio");
		btCadastrar.setBounds(41, 323, 242, 23);
		contentPane.add(btCadastrar);

		controlarEventos();
	}

	/**
	 * Ação do botão Entrar
	 */
	public void controlarEventos() {
		btEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController u = new UsuarioController(tfUsuario, tfSenha);
				boolean valido = u.acesso();
				if (u.acesso()) {
					TelaMenu frame = new TelaMenu();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					TelaInicial.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario ou senha inválidos!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		/**
		 * Ação do botão Cadastrar
		 */
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					TelaInicial.this.setVisible(false);
			}
		});
	}
}
