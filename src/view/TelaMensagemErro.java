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

public class TelaMensagemErro extends JFrame {

	protected JPanel contentPane;
	protected JPanel panel;
	protected JLabel lbImagem;
	protected String caminho; //definir o caminho da imagem para a tela
	public JLabel lbCampo;
	private JLabel lblAviso;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaMensagemErro frame = new TelaMensagemErro();
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
	public TelaMensagemErro() {
		TelaMensagemErro.this.setUndecorated(true);
		UIManager.put("TabbedPane.selected", new Color(0, 0, 98));
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
		
		setTitle("Aviso"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMensagemErro.class.getResource("/view/aviso.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 448, 177);
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
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/aviso.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(10, 10, 25, 26);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/aviso.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		lblAviso = new JLabel("Aviso");
		lblAviso.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblAviso.setBounds(40, 16, 87, 17);
		panel.add(lblAviso);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 39, 428, 128);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lbCampo = new JLabel("Nome");
		lbCampo.setBounds(10, 37, 408, 21);
		panel_1.add(lbCampo);
		lbCampo.setForeground(Color.RED);
		lbCampo.setFont(new Font("Roboto", Font.PLAIN, 17));
		
		/**
		 * Faz a tela aperecer por 2 segundos e depois a fecha
		 */
		addWindowListener(new WindowAdapter() {
		      @Override
		      public void windowOpened(WindowEvent wevt) {
		          Timer timer = new Timer(2000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent evt) {
		              TelaMensagemErro.this.dispose();
		            }
		          });
		          timer.setRepeats(false);
		          timer.start();
		        }
		      });
		
	}
}
