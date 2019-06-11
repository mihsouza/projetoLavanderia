package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UsuarioController;

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
		
		setTitle("Login"); //Rafa: Alterar nome da tela
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png"))); //Rafa: Alterar ícone da tela
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600); //Rafa: Alterei os tamanhos, antes: 100, 100, 350, 415
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0,0,98)); //Rafa: Atribuir cor de fundo
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false); //Rafa: Bloquear o redimensionamento da tela
		
		JLabel lblImagem1 = new JLabel(); //Rafa: inserindo a imagem central
		lblImagem1.setIcon(new ImageIcon(TelaInicial.class.getResource("/view/LaundrIT_Logo_TI4.png")));
		lblImagem1.setBounds(64, 13, 363, 122);
		contentPane.add(lblImagem1); //Rafa: inserindo a label acima
		
		JLabel lblImagem2 = new JLabel(new ImageIcon(TelaInicial.class.getResource("/view/login_usuario5.png"))); //Rafa: inserindo a imagem central
		lblImagem2.setBounds(120, 125, 258, 143);
		contentPane.add(lblImagem2); //Rafa: inserindo a label acima

		tfUsuario = new JTextField("");
		tfUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		tfUsuario.setEditable(true);
		
		
		tfUsuario.setBounds(170, 315, 200, 30); //Rafa: Alterei os tamanhos, antes: 117, 132, 153, 20
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);

		tfSenha = new JPasswordField("");
		tfSenha.setToolTipText("");
		tfSenha.setBounds(170, 374, 200, 30);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);

		btEntrar = new JButton("Entrar");
		btEntrar.setBounds(120, 429, 250, 40); //Rafa: Alterei os tamanhos, antes: 41, 323, 242, 23
		btEntrar.setFont(new Font("Arial Black", Font.PLAIN,20)); //Rafa: Para alterar a fonte do botão
		btEntrar.setForeground(Color.white); //Rafa: Alterar cor da fonte
		btEntrar.setBackground(new Color(96,204,204)); //Rafa: Alterar a cor do botão
		contentPane.add(btEntrar);

		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblUsuario.setForeground(Color.white); //Rafa: Alterar cor da fonte
		lblUsuario.setBounds(170, 297, 65, 18);
		contentPane.add(lblUsuario);
		
		JLabel lblUsuarioImg = new JLabel("", new ImageIcon(TelaInicial.class.getResource("/view/usuario1.png")),JLabel.CENTER); //Rafa: Alocar a imagem na label
		lblUsuarioImg.setBounds(99, 310, 83, 40);
		contentPane.add(lblUsuarioImg);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSenha.setBounds(170, 356, 65, 18);
		contentPane.add(lblSenha);

		JLabel lblSenhaImg = new JLabel("", new ImageIcon(TelaInicial.class.getResource("/view/senha1.png")),JLabel.CENTER); //Rafa: Alocar a imagem na label
		lblSenhaImg.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSenhaImg.setForeground(Color.white); //Rafa: Alterar cor da fonte
		lblSenhaImg.setBounds(112, 367, 57, 40);
		contentPane.add(lblSenhaImg);

		btCadastrar = new JButton("Novo usu\u00E1rio");
		btCadastrar.setBounds(120, 495, 250, 40); //Rafa: Alterei os tamanhos, antes: 41, 323, 242, 23
		btCadastrar.setFont(new Font("Arial Black", Font.PLAIN,20)); //Rafa: Para alterar a fonte do botão
		btCadastrar.setForeground(Color.white); //Rafa: Alterar cor da fonte
		btCadastrar.setBackground(new Color(96,204,204)); //Rafa: Alterar a cor do botão
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
					frame.lblUsuario.setText(tfUsuario.getText());
					TelaInicial.this.setVisible(false);
				} else {
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("Usuario ou senha inválidos!");
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
