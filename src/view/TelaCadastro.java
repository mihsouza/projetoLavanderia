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

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfConfirmar;
	private JButton btCadastrar;
	private JButton btCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
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
		
		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setBounds(117, 279, 101, 23);
		contentPane.add(btCadastrar);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUsuario.setBounds(55, 134, 52, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSenha.setBounds(64, 179, 43, 14);
		contentPane.add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar:");
		lblConfirmarSenha.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblConfirmarSenha.setBounds(37, 223, 70, 14);
		contentPane.add(lblConfirmarSenha);
		
		tfConfirmar = new JTextField();
		tfConfirmar.setToolTipText("");
		tfConfirmar.setColumns(10);
		tfConfirmar.setBounds(117, 220, 153, 20);
		contentPane.add(tfConfirmar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setBounds(117, 342, 101, 23);
		contentPane.add(btCancelar);
		
		controlarEventos();
	}
	
	/**
	 * Ação do botão Entrar
	 */
	public void controlarEventos() {
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				TelaCadastro.this.setVisible(false);
			}
		});
		
		/**
		 * Ação do botão Cadastrar
		 */
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					UsuarioController u = new UsuarioController(tfUsuario, tfSenha, tfConfirmar);
					boolean valido = u.validarCampo();
					if (u.validarCampo()) {
						u.inserirUsuario();
						TelaInicial frame = new TelaInicial();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						TelaCadastro.this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro\nFavor contate o fornecedor", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
			}
		});
	}

}
