package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UsuarioController;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfConfirmar;
	private JButton btCadastrar;
	private JButton btCancelar;
	private JLabel label;
	private JLabel lblSenha_1;
	private JLabel lblConfirmarSenhaImg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastro frame = new TelaCadastro();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaCadastro() {
		
		setTitle("Cadastro de Usuário"); //Rafa: Alterar nome da tela
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
		
		JLabel lblImagem2 = new JLabel(new ImageIcon(TelaCadastro.class.getResource("/view/cadastro_usuario3.png"))); //Rafa: inserindo a imagem central
		lblImagem2.setBounds(137, 120, 243, 154);
		contentPane.add(lblImagem2); //Rafa: inserindo a label acima
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(162, 305, 200, 30); //Rafa: Alterei os tamanhos, antes: 117, 132, 153, 20
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JPasswordField(); //Rafa: Troqeui o JText para esconder a senha, *cuidado com o banco
		tfSenha.setToolTipText("");
		tfSenha.setBounds(162, 365, 200, 30);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);
		
		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setBounds(269, 494, 175, 40); //Rafa: Alterei os tamanhos, antes: 117, 279, 101, 23
		btCadastrar.setFont(new Font("Arial Black", Font.PLAIN,20)); //Rafa: Para alterar a fonte do botão
		btCadastrar.setForeground(Color.white); //Rafa: Alterar cor da fonte
		btCadastrar.setBackground(new Color(96,204,204)); //Rafa: Alterar a cor do botão
		contentPane.add(btCadastrar);
		
		JLabel lblUsuarioImg = new JLabel("", new ImageIcon(TelaInicial.class.getResource("/view/usuario1.png")),JLabel.CENTER); //Rafa: Alocar a imagem na label
		lblUsuarioImg.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUsuarioImg.setForeground(Color.white); //Rafa: Alterar cor da fonte
		lblUsuarioImg.setBounds(106, 300, 52, 37);
		contentPane.add(lblUsuarioImg);
		
		JLabel lblSenhaImg = new JLabel("", new ImageIcon(TelaInicial.class.getResource("/view/senha1.png")),JLabel.CENTER); //Rafa: Alocar a imagem na label
		lblSenhaImg.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblSenhaImg.setForeground(Color.white); //Rafa: Alterar cor da fonte
		lblSenhaImg.setBounds(106, 353, 52, 49);
		contentPane.add(lblSenhaImg);
		
		lblConfirmarSenhaImg = new JLabel("");
		lblConfirmarSenhaImg.setIcon(new ImageIcon(TelaCadastro.class.getResource("/view/senha_Wrong1.png")));
		lblConfirmarSenhaImg.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblConfirmarSenhaImg.setForeground(Color.white); //Rafa: Alterar cor da fonte
		lblConfirmarSenhaImg.setBounds(114, 408, 52, 65);
		contentPane.add(lblConfirmarSenhaImg);
		
		tfConfirmar = new JPasswordField();
		tfConfirmar.setToolTipText("");
		tfConfirmar.setColumns(10);
		tfConfirmar.setBounds(162, 425, 200, 30);
		contentPane.add(tfConfirmar);
	
		btCancelar = new JButton("Cancelar");
		btCancelar.setBounds(59, 494, 175, 40); //Rafa: Alterei os tamanhos, antes: 117, 342, 101, 23
		btCancelar.setFont(new Font("Arial Black", Font.PLAIN,20)); //Rafa: Para alterar a fonte do botão
		btCancelar.setForeground(Color.white); //Rafa: Alterar cor da fonte
		btCancelar.setBackground(new Color(96,204,204)); //Rafa: Alterar a cor do botão
		contentPane.add(btCancelar);
		
		JLabel labelUsuario = new JLabel("Usu\u00E1rio:");
		labelUsuario.setForeground(Color.WHITE);
		labelUsuario.setFont(new Font("Times New Roman", Font.BOLD, 15));
		labelUsuario.setBounds(162, 287, 65, 18);
		contentPane.add(labelUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSenha.setBounds(162, 346, 65, 18);
		contentPane.add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		lblConfirmarSenha.setForeground(Color.WHITE);
		lblConfirmarSenha.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblConfirmarSenha.setBounds(162, 408, 144, 18);
		contentPane.add(lblConfirmarSenha);
		
		controlarEventos();
	}
	/**
	 * Ação do botão Entrar
	 */
	public void controlarEventos() {
		UsuarioController u = new UsuarioController(tfUsuario, tfSenha, tfConfirmar, lblConfirmarSenhaImg);
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
					UsuarioController u = new UsuarioController(tfUsuario, tfSenha, tfConfirmar, lblConfirmarSenhaImg);
					boolean valido = u.validarCampo();
					if (u.validarCampo()) {
						if(u.validarSenha() == false) {
							JOptionPane.showMessageDialog(null, "Campos não conferem", "ERROR", JOptionPane.ERROR_MESSAGE);
							tfSenha.setText("");
							tfConfirmar.setText("");
						}else {
							u.inserirUsuario();
							TelaInicial frame = new TelaInicial();
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);
							TelaCadastro.this.setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
				tfConfirmar.getDocument().addDocumentListener(
						new DocumentListener() {
							public void removeUpdate(DocumentEvent e) {
								u.verificarSenha();
								
							}
							
							@Override
							public void insertUpdate(DocumentEvent e) {
								u.verificarSenha();
								
							}
							
							@Override
							public void changedUpdate(DocumentEvent e) {
								u.verificarSenha();
							}
						});
			
	}

}
