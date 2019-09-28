package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;

public class TelaPeriodo extends JFrame {

	protected JPanel contentPane;
	protected JPanel panel;
	protected JLabel lbTitulo, lbImagem;
	protected String caminho; //definir o caminho da imagem para a tela
	private JButton btOk;
	private JLabel lblNewLabel;
	private JLabel lblAt;
	private JTextField tfDe;
	private JTextField tfAte;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaPeriodo frame = new TelaPeriodo();
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
	public TelaPeriodo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 637, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Para qual data gostaria de gerar o relat\u00F3rio?");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 18));
		lbTitulo.setBounds(151, 25, 431, 36);
		panel.add(lbTitulo);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/relatorio.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(21, 11, 106, 104);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/relatorio.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		btOk = new JButton("OK");
		btOk.setBackground(new Color(143, 188, 143));
		btOk.setFont(new Font("Roboto", Font.BOLD, 18));
		btOk.setBounds(276, 151, 71, 40);
		panel.add(btOk);
		
		lblNewLabel = new JLabel("De:");
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 15));
		lblNewLabel.setBounds(151, 86, 22, 14);
		panel.add(lblNewLabel);
		
		lblAt = new JLabel("at\u00E9:");
		lblAt.setFont(new Font("Roboto", Font.BOLD, 15));
		lblAt.setBounds(350, 86, 31, 14);
		panel.add(lblAt);
		
		tfDe = new JTextField();
		tfDe.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfDe.setBounds(183, 83, 114, 20);
		panel.add(tfDe);
		tfDe.setColumns(10);
		
		tfAte = new JTextField();
		tfAte.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfAte.setColumns(10);
		tfAte.setBounds(384, 84, 114, 20);
		panel.add(tfAte);
		
	}
}
