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

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ContasController;
import controller.FuncionarioController;
import services.GeneratorPDF;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class ListaContas extends JFrame {
	protected JTextField tfPesquisa;
	private JTable tbLista;
	protected JLabel lblPesquisa, lblTitle;
	private JButton btNovo, btFiltrar;
	protected JTextField tfInicio;
	private JButton btVoltar;
	protected JTextField tfFim;
	protected JRadioButton rbNaoPago, rbPago;
	private JButton btPdf;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	private JLabel label;
	private JButton btVisualizar;

	/**
	 * Create the panel.
	 */
	public ListaContas() {
		
		setTitle("Contas a pagar"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 666);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		this.setResizable(false);
		
		lblPesquisa = new JLabel("Pago a:");
		lblPesquisa.setForeground(Color.WHITE);
		lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPesquisa.setBounds(22, 82, 89, 29);
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Contas a pagar");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitle.setBounds(96, 15, 321, 53);
		getContentPane().add(lblTitle);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tfPesquisa.setBounds(96, 88, 544, 25);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBackground(Color.DARK_GRAY);
		pnBotao.setBounds(9, 210, 765, 58);
		getContentPane().add(pnBotao);
		pnBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setIcon(new ImageIcon(ListaContas.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(Color.WHITE);
		btFiltrar.setBackground(new Color(218, 165, 32));
		btFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btFiltrar.setBounds(12, 10, 120, 40);
		pnBotao.add(btFiltrar);
		
		btNovo = new JButton("Novo");
		btNovo.setIcon(new ImageIcon(ListaContas.class.getResource("/view/novo.png")));
		btNovo.setForeground(Color.WHITE);
		btNovo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btNovo.setBackground(new Color(218, 165, 32));
		btNovo.setBounds(151, 10, 120, 40);
		pnBotao.add(btNovo);
		
		btPdf = new JButton("PDF");
		btPdf.setIcon(new ImageIcon(ListaContas.class.getResource("/view/PDF.png")));
		btPdf.setForeground(Color.WHITE);
		btPdf.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btPdf.setBackground(new Color(218, 165, 32));
		btPdf.setBounds(293, 10, 120, 40);
		pnBotao.add(btPdf);
		
		btVisualizar = new JButton("Detalhes");
		btVisualizar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(218, 165, 32));
		btVisualizar.setBounds(433, 10, 132, 40);
		pnBotao.add(btVisualizar);
		
		JLabel lblInicio = new JLabel("In\u00EDcio:");
		lblInicio.setForeground(Color.WHITE);
		lblInicio.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblInicio.setBounds(22, 122, 85, 32);
		contentPane.add(lblInicio);
		
		tfInicio = new JTextField();
		tfInicio.setText("01/01/2019");
		tfInicio.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tfInicio.setColumns(10);
		tfInicio.setBounds(96, 130, 89, 25);
		contentPane.add(tfInicio);
		
		btVoltar = new JButton("Voltar");
		btVoltar.setIcon(new ImageIcon(ListaContas.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBounds(647, 24, 120, 40);
		btVoltar.setBackground(new Color(0, 139, 139));
		contentPane.add(btVoltar);
		
		JLabel lblFim = new JLabel("Fim:");
		lblFim.setForeground(Color.WHITE);
		lblFim.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblFim.setBounds(225, 124, 66, 29);
		contentPane.add(lblFim);
		
		tfFim = new JTextField();
		tfFim.setText("31/12/2019");
		tfFim.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tfFim.setColumns(10);
		tfFim.setBounds(274, 130, 89, 25);
		contentPane.add(tfFim);
		
		rbPago = new JRadioButton("Pago");
		rbPago.setBackground(SystemColor.textHighlight);
		rbPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbPago.setForeground(Color.WHITE);
		rbPago.setBounds(407, 131, 109, 23);
		contentPane.add(rbPago);
		
		rbNaoPago = new JRadioButton("N\u00E3o pago");
		rbNaoPago.setSelected(true);
		rbNaoPago.setBackground(SystemColor.textHighlight);
		rbNaoPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbNaoPago.setForeground(Color.WHITE);
		rbNaoPago.setBounds(531, 131, 109, 23);
		contentPane.add(rbNaoPago);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbPago);
		bg.add(rbNaoPago);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(224, 255, 255)));
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 281, 764, 332);
		getContentPane().add(scrollPane);
		scrollPane.setBackground(new Color(224, 255, 255));
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ListaContas.class.getResource("/view/contas.png")));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		label.setBounds(26, 15, 60, 50);
		contentPane.add(label);
		
		modelo.addColumn("Código");
		modelo.addColumn("Pago a");
		modelo.addColumn("Valor");
		modelo.addColumn("Data vencimento");
		modelo.addColumn("Status");
		
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarContas frame = new TelaCadastrarContas();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			ContasController c = new ContasController(rbNaoPago, rbPago, tfPesquisa, tfInicio, tfFim, modelo);
				c.listar();
			}
		});
		
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarContas frame = new TelaCadastrarContas();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.contentPane.add(frame.btVisualizar);
				frame.contentPane.add(frame.btExcluir);
				frame.tfId.setEditable(true);
				frame.lblTitulo.setText("Conta");
			}
		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaContas.this.setVisible(false);
				TelaMenu frame = new TelaMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btPdf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			GeneratorPDF g = new GeneratorPDF(rbNaoPago, rbPago, tfPesquisa, tfInicio, tfFim);
				g.pdfListaContas();
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
//					ListaContas frame = new ListaContas();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
