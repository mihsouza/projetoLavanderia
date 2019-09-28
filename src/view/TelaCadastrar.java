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
import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class TelaCadastrar extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	public JComboBox cbPermissao;
	public JButton btCadastrar, btVoltar;
	private JPasswordField tfSenha;
	private JPasswordField tfCadastrar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastrar frame = new TelaCadastrar();
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
	public TelaCadastrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 535);
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
		lblUsurio.setBounds(155, 162, 156, 28);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(199, 21, 133));
		lblSenha.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblSenha.setBounds(155, 219, 156, 28);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfUsuario.setColumns(10);
		tfUsuario.setBounds(155, 188, 156, 20);
		contentPane.add(tfUsuario);
		
		JLabel lblUsuarioImg = new JLabel("", new ImageIcon(TelaCadastrar.class.getResource("/view/usuario1.png")),JLabel.CENTER);
		lblUsuarioImg.setBackground(new Color(255, 0, 255));
		lblUsuarioImg.setBounds(68, 168, 83, 40);
		contentPane.add(lblUsuarioImg);

		JLabel lblSenhaImg = new JLabel("", new ImageIcon(TelaCadastrar.class.getResource("/view/senha1.png")),JLabel.CENTER); 
		lblSenhaImg.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSenhaImg.setForeground(Color.white);
		lblSenhaImg.setBounds(78, 227, 57, 40);
		contentPane.add(lblSenhaImg);
		
		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setIcon(new ImageIcon(ListaCaixa.class.getResource("/view/novo.png")));
		btCadastrar.setForeground(new Color(199,21,133));
		btCadastrar.setFont(new Font("Roboto", Font.BOLD, 18));
		btCadastrar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(199,21,133), new Color(199,21,133), new Color(199,21,133), new Color(199,21,133)));
		btCadastrar.setBackground(Color.WHITE);
		btCadastrar.setBounds(155, 445, 120, 40);
		contentPane.add(btCadastrar);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha:");
		lblConfirmarSenha.setForeground(new Color(199, 21, 133));
		lblConfirmarSenha.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblConfirmarSenha.setBounds(155, 278, 156, 28);
		contentPane.add(lblConfirmarSenha);
		
		JLabel lbConfirmar = new JLabel("", new ImageIcon(TelaCadastrar.class.getResource("/view/senha1.png")),JLabel.CENTER);
		lbConfirmar.setForeground(Color.WHITE);
		lbConfirmar.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbConfirmar.setBounds(78, 288, 57, 40);
		contentPane.add(lbConfirmar);
		
		JLabel lblPermisso = new JLabel("Permiss\u00E3o:");
		lblPermisso.setForeground(new Color(199, 21, 133));
		lblPermisso.setFont(new Font("Lucida Bright", Font.PLAIN, 18));
		lblPermisso.setBounds(155, 335, 156, 28);
		contentPane.add(lblPermisso);
		
		cbPermissao = new JComboBox();
		cbPermissao.setModel(new DefaultComboBoxModel(new String[] {"Funcionario", "Administrador", "Gerente"}));
		cbPermissao.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbPermissao.setBounds(155, 363, 156, 20);
		contentPane.add(cbPermissao);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(155, 247, 156, 20);
		contentPane.add(tfSenha);
		
		tfCadastrar = new JPasswordField();
		tfCadastrar.setBounds(155, 308, 156, 20);
		contentPane.add(tfCadastrar);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(ListaPedido.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btVoltar.setBounds(335, 11, 58, 45);
		contentPane.add(btVoltar);
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		/**
		 * Chamar uma tela para cadastrar
		 */
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioController u = new UsuarioController(tfUsuario, tfSenha, tfCadastrar, cbPermissao);
				u.inserir();
			}

		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrar.this.setVisible(false);
			}

		});
		
	}
}
