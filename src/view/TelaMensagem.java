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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class TelaMensagem extends JFrame {

	private JPanel contentPane;
	private JTextField tfSenha;
	public JButton btOk;
	public JLabel lblAviso;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaMensagem frame = new TelaMensagem();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaMensagem() {
		
		setTitle("Aviso"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMensagem.class.getResource("/view/icone_NB.png"))); 
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 150);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 128));
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);

		btOk = new JButton("OK");
		btOk.setBounds(197, 69, 78, 30); 
		btOk.setFont(new Font("Arial Black", Font.PLAIN,20)); 
		btOk.setForeground(Color.white); 
		btOk.setBackground(new Color(96,204,204)); 
		contentPane.add(btOk);
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		lblAviso = new JLabel("Escreva aqui Mi");
		lblAviso.setForeground(Color.DARK_GRAY);
		lblAviso.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAviso.setBounds(89, 28, 352, 30);
		contentPane.add(lblAviso);

		JLabel lblAvisoImg = new JLabel("", new ImageIcon(TelaMensagem.class.getResource("/view/aviso.png")),JLabel.CENTER); //Rafa: Alocar a imagem na label
		lblAvisoImg.setBounds(34, 18, 57, 40);
		contentPane.add(lblAvisoImg);
		}
	}