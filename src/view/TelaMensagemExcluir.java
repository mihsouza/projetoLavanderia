package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.CaixaController;
import controller.ServicoController;

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

public class TelaMensagemExcluir extends JFrame {

	protected JPanel contentPane;
	protected JPanel panel;
	protected JLabel lbTitulo, lbImagem;
	protected String caminho; //definir o caminho da imagem para a tela
	private JPanel panel_1;
	public JLabel lbItem;
	private JButton btSim;
	private JButton btNao;
	public String modal;
	public String id;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaMensagemExcluir frame = new TelaMensagemExcluir();
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
	public TelaMensagemExcluir() {
		TelaMensagemExcluir.this.setUndecorated(true);
		UIManager.put("TabbedPane.selected", new Color(0, 0, 98));
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
		
		setTitle("Excluir"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaMensagemErro.class.getResource("/view/excluirmodal.png")));
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
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/excluirmodal.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(10, 11, 24, 27);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/excluirmodal.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 49, 329, 114);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lbTitulo = new JLabel("Deseja excluir este item?");
		lbTitulo.setBounds(10, 22, 201, 21);
		panel_1.add(lbTitulo);
		lbTitulo.setFont(new Font("Roboto", Font.PLAIN, 17));
		
		btSim = new JButton("Sim");
		btSim.setBackground(new Color(224, 255, 255));
		btSim.setForeground(new Color(0, 100, 0));
		btSim.setFont(new Font("Roboto", Font.PLAIN, 12));
		btSim.setBounds(66, 80, 89, 23);
		panel_1.add(btSim);
		
		btNao = new JButton("N\u00E3o");
		btNao.setForeground(new Color(255, 0, 0));
		btNao.setFont(new Font("Roboto", Font.PLAIN, 12));
		btNao.setBackground(new Color(255, 228, 225));
		btNao.setBounds(165, 80, 89, 23);
		panel_1.add(btNao);
		
		lbItem = new JLabel("Item");
		lbItem.setFont(new Font("Roboto", Font.PLAIN, 14));
		lbItem.setBounds(44, 17, 295, 17);
		panel.add(lbItem);
		
		controlarEvento();
		
	}
	
	public void excluirModal() {
		if(modal.equals("servico")) {
			ServicoController c = new ServicoController();
			c.excluir(id);
		}else {
			System.out.println("Não foi");
		}
	}
	
	public void controlarEvento() {
		
		btNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMensagemExcluir.this.setVisible(false);
			}

		});
		
		btSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMensagemExcluir.this.setVisible(false);
				excluirModal();
			}

		});
	}
}
