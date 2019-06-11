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

import controller.OrdemServicoController;
import controller.PedidoController;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class ListaOrdemServico extends JFrame {
	protected JTextField tfPesquisa;
	private JLabel lblPesquisa, lblTitle, lblOrdenar;
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
	public ListaOrdemServico() {
		
		setTitle("Ordem de Serviço");
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
		
		lblPesquisa = new JLabel("Funcionário:");
		lblPesquisa.setForeground(Color.WHITE);
		lblPesquisa.setBounds(20, 54, 125, 23);
		lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Ordens de Serviços");
		lblTitle.setIcon(new ImageIcon(ListaOrdemServico.class.getResource("/view/ordemServico.png")));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(10, 11, 355, 42);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		getContentPane().add(lblTitle);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setForeground(Color.WHITE);
		lblOrdenar.setBounds(20, 119, 125, 31);
		lblOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		getContentPane().add(lblOrdenar);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setBounds(145, 56, 332, 23);
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setBounds(155, 127, 170, 25);
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Funcionario", "C\u00F3digo", "Status", "Pre\u00E7o"}));
		cbOrdenar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(cbOrdenar);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBounds(9, 161, 765, 58);
		pnBotao.setBackground(Color.DARK_GRAY);
		getContentPane().add(pnBotao);
		pnBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setIcon(new ImageIcon(ListaOrdemServico.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(SystemColor.inactiveCaptionBorder);
		btFiltrar.setBackground(new Color(222, 184, 135));
		btFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btFiltrar.setBounds(10, 11, 120, 40);
		pnBotao.add(btFiltrar);
		
		btNovo = new JButton("Novo");
		btNovo.setIcon(new ImageIcon(ListaOrdemServico.class.getResource("/view/novo.png")));
		btNovo.setForeground(SystemColor.inactiveCaptionBorder);
		btNovo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btNovo.setBackground(new Color(222, 184, 135));
		btNovo.setBounds(140, 11, 120, 40);
		pnBotao.add(btNovo);
		
		btVisualizar = new JButton("Detalhes");
		btVisualizar.setIcon(new ImageIcon(ListaOrdemServico.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(SystemColor.inactiveCaptionBorder);
		btVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btVisualizar.setBackground(new Color(222, 184, 135));
		btVisualizar.setBounds(270, 11, 132, 40);
		pnBotao.add(btVisualizar);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(20, 88, 64, 23);
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 20));
		contentPane.add(lblStatus);
		
		rbFinalizado = new JRadioButton("Finalizado");
		rbFinalizado.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbFinalizado.setForeground(Color.WHITE);
		rbFinalizado.setBounds(145, 89, 109, 23);
		rbFinalizado.setBackground(SystemColor.textHighlight);
		contentPane.add(rbFinalizado);
		
		rbEspera = new JRadioButton("Em espera");
		rbEspera.setSelected(true);
		rbEspera.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbEspera.setForeground(Color.WHITE);
		rbEspera.setBounds(256, 89, 109, 23);
		rbEspera.setBackground(SystemColor.textHighlight);
		contentPane.add(rbEspera);
		
		rbAtraso = new JRadioButton("Em atraso");
		rbAtraso.setFont(new Font("Tahoma", Font.BOLD, 11));
		rbAtraso.setForeground(Color.WHITE);
		rbAtraso.setBounds(368, 89, 109, 23);
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
		btVoltar.setIcon(new ImageIcon(ListaOrdemServico.class.getResource("/view/voltar.png")));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setBackground(new Color(0, 139, 139));
		btVoltar.setBounds(654, 13, 120, 40);
		contentPane.add(btVoltar);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(224, 255, 255)));
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 218, 764, 332);
		getContentPane().add(scrollPane);
		scrollPane.setBackground(new Color(224, 255, 255));
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		modelo.addColumn("Código");
		modelo.addColumn("Pedido");
		modelo.addColumn("Cliente");
		modelo.addColumn("Funcionário");
		modelo.addColumn("Status");
		modelo.addColumn("Total");
		modelo.addColumn("Data Execução");
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOrdemServico frame = new TelaOrdemServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.contentPane.add(frame.btVisualizar);
				frame.tfPedido.setEditable(true);
			}

		});
		
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOrdemServico frame = new TelaOrdemServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.contentPane.add(frame.btVisualizar);
				frame.contentPane.add(frame.btExcluir);
				frame.tfId.setEditable(true);
			}
		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaOrdemServico.this.setVisible(false);
				TelaMenu frame = new TelaMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OrdemServicoController o = new OrdemServicoController(rbAtraso, rbEspera, rbFinalizado, tfPesquisa, cbOrdenar, modelo);
				o.consulta();
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
//					ListaOrdemServico frame = new ListaOrdemServico();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
