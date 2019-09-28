package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.CaixaController;

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

public class TelaMensagemPdf extends JFrame {

	protected JPanel contentPane;
	protected JPanel panel;
	protected JLabel lbTitulo, lbImagem;
	protected String caminho; //definir o caminho da imagem para a tela
	private JPanel panel_1;
	private JLabel lblPdf;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaMensagemPdf frame = new TelaMensagemPdf();
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
	public TelaMensagemPdf() {
		TelaMensagemPdf.this.setUndecorated(true);
		UIManager.put("TabbedPane.selected", new Color(0, 0, 98));
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
		
		setTitle("PDF"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMensagemErro.class.getResource("/view/pdf_gerado.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 349, 174);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(176, 196, 222));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/pdf_gerado.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(10, 2, 38, 36);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/pdf_gerado.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 49, 329, 114);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lbTitulo = new JLabel("PDF gerado com sucesso!");
		lbTitulo.setBounds(10, 43, 201, 21);
		panel_1.add(lbTitulo);
		lbTitulo.setFont(new Font("Roboto", Font.PLAIN, 17));
		
		lblPdf = new JLabel("PDF");
		lblPdf.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblPdf.setBounds(58, 17, 87, 17);
		panel.add(lblPdf);
		
		/**
		 * Faz a tela aperecer por 2 segundos e depois a fecha
		 */
		addWindowListener(new WindowAdapter() {
		      @Override
		      public void windowOpened(WindowEvent wevt) {
		          Timer timer = new Timer(2000, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent evt) {
		              TelaMensagemPdf.this.dispose();
		            }
		          });
		          timer.setRepeats(false);
		          timer.start();
		        }
		      });
	}
}
