package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.PedidoController;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class ListaPedido extends JFrame {
	protected JTextField tfPesquisa;
	private JLabel lblPesquisa, lblTitle, lblOrdenar, lblImagem;
	protected JComboBox cbOrdenar;
	private JButton btNovo, btFiltrar;
	protected JRadioButton rbAtraso;
	protected JRadioButton rbEspera;
	protected JRadioButton rbFinalizado;
	private JButton btVoltar;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btVisualizar;

	/**
	 * Create the panel.
	 */
	public ListaPedido() {
		setTitle("Pedidos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		lblPesquisa = new JLabel("Cliente:");
		lblPesquisa.setForeground(Color.WHITE);
		lblPesquisa.setBounds(20, 54, 71, 23);
		lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Pedidos");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(54, 11, 155, 32);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 30));
		getContentPane().add(lblTitle);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setForeground(Color.WHITE);
		lblOrdenar.setBounds(20, 119, 125, 31);
		lblOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(lblOrdenar);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setBounds(101, 56, 332, 23);
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setBounds(155, 127, 170, 25);
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Cliente", "C\u00F3digo", "Status", "Pre\u00E7o"}));
		cbOrdenar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(cbOrdenar);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBounds(10, 161, 765, 58);
		pnBotao.setBackground(Color.DARK_GRAY);
		getContentPane().add(pnBotao);
		pnBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setForeground(SystemColor.inactiveCaptionBorder);
		btFiltrar.setIcon(new ImageIcon(ListaPedido.class.getResource("/view/filtrar.png")));
		btFiltrar.setBackground(new Color(222, 184, 135));
		btFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btFiltrar.setBounds(10, 11, 120, 40);
		pnBotao.add(btFiltrar);
		
		btNovo = new JButton("Novo");
		btNovo.setForeground(SystemColor.inactiveCaptionBorder);
		btNovo.setIcon(new ImageIcon(ListaPedido.class.getResource("/view/novo.png")));
		btNovo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btNovo.setBackground(new Color(222, 184, 135));
		btNovo.setBounds(140, 11, 120, 40);
		pnBotao.add(btNovo);
		
		btVisualizar = new JButton("Detalhes");
		btVisualizar.setForeground(SystemColor.inactiveCaptionBorder);
		btVisualizar.setIcon(new ImageIcon(ListaPedido.class.getResource("/view/Visualizar.png")));
		btVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(222, 184, 135));
		btVisualizar.setBounds(270, 11, 132, 40);
		pnBotao.add(btVisualizar);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(20, 88, 71, 23);
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 20));
		contentPane.add(lblStatus);
		
		rbFinalizado = new JRadioButton("Finalizado");
		rbFinalizado.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbFinalizado.setForeground(Color.WHITE);
		rbFinalizado.setBounds(111, 89, 109, 23);
		rbFinalizado.setBackground(SystemColor.textHighlight);
		contentPane.add(rbFinalizado);
		
		rbEspera = new JRadioButton("Em espera");
		rbEspera.setSelected(true);
		rbEspera.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbEspera.setForeground(Color.WHITE);
		rbEspera.setBounds(222, 89, 109, 23);
		rbEspera.setBackground(SystemColor.textHighlight);
		contentPane.add(rbEspera);
		
		rbAtraso = new JRadioButton("Em atraso");
		rbAtraso.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbAtraso.setForeground(Color.WHITE);
		rbAtraso.setBounds(334, 89, 109, 23);
		rbAtraso.setBackground(SystemColor.textHighlight);
		contentPane.add(rbAtraso);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbFinalizado);
		bg.add(rbEspera);
		bg.add(rbAtraso);
		
		btVoltar = new JButton("Voltar");
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(0, 139, 139));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setIcon(new ImageIcon(ListaPedido.class.getResource("/view/voltar.png")));
		btVoltar.setBounds(654, 15, 120, 40);
		contentPane.add(btVoltar);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(224, 255, 255)));
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 227, 764, 323);
		getContentPane().add(scrollPane);
		scrollPane.setBackground(new Color(224, 255, 255));
		
		lblImagem = new JLabel("");
		lblImagem.setForeground(Color.WHITE);
		lblImagem.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/novo.png")));
		lblImagem.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblImagem.setBounds(25, 11, 34, 40);
		contentPane.add(lblImagem);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		modelo.addColumn("Código");
		modelo.addColumn("Cliente");
		modelo.addColumn("Atendente");
		modelo.addColumn("Status");
		modelo.addColumn("Total");
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPedido frame = new TelaPedido();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPedido frame = new TelaPedido();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.contentPane.add(frame.btVisualizar);
				frame.contentPane.add(frame.btExcluir);
				frame.tfId.setEditable(true);
				frame.lblTitulo.setText("Funcionario");
			}
		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPedido.this.setVisible(false);
				TelaMenu frame = new TelaMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PedidoController p = new PedidoController(rbAtraso, rbEspera, rbFinalizado, tfPesquisa, cbOrdenar, modelo);
				p.consulta();
			}
		});
	}
	

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			/**
//			 * método run - método que inicia a aplicação
//			 */
//			public void run() {
//				try {
//					ListaPedido frame = new ListaPedido();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
