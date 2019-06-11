package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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

import controller.ClienteController;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class ListaCliente extends JFrame {
	protected JTextField tfPesquisa;
	private JTable tbLista;
	private JLabel lblPesquisa, lblTitle, lblOrdenar;
	protected JComboBox cbOrdenar;
	protected JTextField tfCpf;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btNovo;
	private JButton btFiltrar;
	private JButton btVoltar;
	private JLabel label;
	private JButton btVisualizar;
	

	/**
	 * Create the panel.
	 */
	public ListaCliente() {
		
		setTitle("Clientes"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 638);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		this.setResizable(false);
		
		lblTitle = new JLabel("Clientes");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitle.setBounds(107, 11, 180, 56);
		getContentPane().add(lblTitle);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setForeground(Color.WHITE);
		lblOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOrdenar.setBounds(20, 147, 154, 30);
		getContentPane().add(lblOrdenar);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPesquisa.setBounds(97, 80, 544, 25);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
		cbOrdenar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbOrdenar.setBounds(149, 152, 166, 25);
		getContentPane().add(cbOrdenar);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBackground(Color.DARK_GRAY);
		pnBotao.setBounds(12, 200, 765, 58);
		getContentPane().add(pnBotao);
		pnBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(Color.WHITE);
		btFiltrar.setBackground(new Color(218, 165, 32));
		btFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btFiltrar.setBounds(12, 10, 120, 40);
		pnBotao.add(btFiltrar);
		
		btNovo = new JButton("Novo");
		btNovo.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/novo.png")));
		btNovo.setForeground(Color.WHITE);
		btNovo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btNovo.setBackground(new Color(218, 165, 32));
		btNovo.setBounds(151, 10, 120, 40);
		pnBotao.add(btNovo);
		
		btVisualizar = new JButton("Detalhes");
		btVisualizar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(218, 165, 32));
		btVisualizar.setBounds(293, 10, 132, 40);
		pnBotao.add(btVisualizar);
		
		tbLista = new JTable();
		tbLista.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tbLista.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tbLista.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"id", "Nome", "Remunera\u00E7\u00E3o"
			}
		));
		tbLista.setBounds(789, 220, -769, 351);
		getContentPane().add(tbLista);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCpf.setBounds(20, 114, 45, 29);
		contentPane.add(lblCpf);
		
		tfCpf = new JTextField();
		tfCpf.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCpf.setColumns(10);
		tfCpf.setBounds(97, 116, 180, 25);
		contentPane.add(tfCpf);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(224, 255, 255)));
		scrollPane.setToolTipText("");
		scrollPane.setBounds(12, 258, 764, 332);
		getContentPane().add(scrollPane);
		scrollPane.setBackground(new Color(224, 255, 255));
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		lblPesquisa = new JLabel("Nome:");
		lblPesquisa.setBounds(22, 78, 89, 24);
		contentPane.add(lblPesquisa);
		lblPesquisa.setForeground(Color.WHITE);
		lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		btVoltar = new JButton("Voltar");
		btVoltar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(0, 139, 139));
		btVoltar.setBounds(654, 11, 120, 40);
		contentPane.add(btVoltar);
		
		JLabel lblImgCliente = new JLabel("");
		lblImgCliente.setIcon(new ImageIcon(ListaCliente.class.getResource("/view/clientes.png")));
		lblImgCliente.setForeground(Color.WHITE);
		lblImgCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblImgCliente.setBounds(37, 11, 60, 50);
		contentPane.add(lblImgCliente);
		
		ImageIcon Cliente = new ImageIcon(TelaCargo.class.getResource("/view/clientes.png"));
		Image Cli = Cliente.getImage().getScaledInstance(lblImgCliente.getWidth(), lblImgCliente.getHeight(), Image.SCALE_SMOOTH);	
		lblImgCliente.setIcon(new ImageIcon(Cli));
		
		modelo.addColumn("Código");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Telefone");
		
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente frame = new TelaCliente();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCliente.this.setVisible(false);
				TelaMenu frame = new TelaMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btFiltrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClienteController c = new ClienteController(tfPesquisa, cbOrdenar, tfCpf, modelo);
				c.consulta();
			}
		});
		
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente frame = new TelaCliente();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.contentPane.add(frame.btVisualizar);
				frame.contentPane.add(frame.btExcluir);
				frame.tfId.setEditable(true);
				frame.lblTitulo.setText("Cliente");
			}

		});
	}
	

	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			/**
//			 * método run - método que inicia a aplicação
//			 */
//			public void run() {
//				try {
//					ListaCliente frame = new ListaCliente();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
}
