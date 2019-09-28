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
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

public class TelaMensagemAviso extends JFrame {

	public JPanel contentPane;
	public JPanel panel;
	public JLabel lbTitulo, lbImagem;
	private JLabel lblNewLabel;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaMensagemAviso frame = new TelaMensagemAviso();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//					frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//					frame.setResizable(false);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaMensagemAviso() {
		TelaMensagemAviso.this.setUndecorated(true);
		UIManager.put("TabbedPane.selected", new Color(0, 0, 98));
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
		
		setTitle("Mensagem"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMensagemAviso.class.getResource("/view/mensagem.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 313, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(176, 196, 222));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/mensagem.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(10, 4, 28, 26);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/mensagem.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		lblNewLabel = new JLabel("Mensagem");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNewLabel.setBounds(41, 14, 87, 17);
		panel.add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 41, 293, 99);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lbTitulo = new JLabel("Salvo!");
		lbTitulo.setBounds(10, 37, 283, 21);
		panel_1.add(lbTitulo);
		lbTitulo.setFont(new Font("Roboto", Font.PLAIN, 17));
		
		/**
		 * Faz a tela aperecer por 2 segundos e depois a fecha
		 */
		addWindowListener(new WindowAdapter() {
		      @Override
		      public void windowOpened(WindowEvent wevt) {
		          Timer timer = new Timer(2000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent evt) {
		              TelaMensagemAviso.this.dispose();
		            }
		          });
		          timer.setRepeats(false);
		          timer.start();
		        }
		      });
		
	}
}
