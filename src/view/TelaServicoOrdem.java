package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.ServicoController;
import controller.ServicoOrdemController;

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
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaServicoOrdem extends JFrame {

	protected JPanel contentPane;
	protected JButton btVoltar, btSalvar, btInserir;
	protected JPanel panel;
	protected JLabel lbTitulo, lbNome, lblOrdenar, lbImagem;
	protected JTextField tfQuantidade;
	protected JTextField tfPedido;
	private JTextField tfPreco;
	private JPanel Roupas;
	private JTextField tfAltura;
	private JTextField tfLargura;
	protected JTextField tfId;
	private JTable servicos;
	DefaultTableModel modelo = new DefaultTableModel();
	private int selecionar;
	private JButton btListar;
	public JTextField tfSaldo;
	private JLabel lblSaldo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaServicoOrdem frame = new TelaServicoOrdem();
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
	public TelaServicoOrdem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 622, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(102, 205, 170));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Adicionar servi\u00E7o");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(127, 21, 297, 28);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(TelaServicoOrdem.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(507, 4, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/servicos.png"));
		
		lbNome = new JLabel("Pedido:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setFont(new Font("Roboto", Font.BOLD, 15));
		lbNome.getHorizontalAlignment();
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/servicos.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(21, 11, 106, 104);
		panel.add(lbImagem);
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		lbNome.setBounds(441, 93, 81, 14);
		panel.add(lbNome);
		
		tfPedido = new JTextField();
		tfPedido.setEditable(false);
		tfPedido.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfPedido.setColumns(10);
		tfPedido.setBounds(532, 90, 43, 20);
		panel.add(tfPedido);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 126, 554, 147);
		panel.add(tabbedPane);
		
		Roupas = new JPanel();
		tabbedPane.addTab("Roupas", null, Roupas, null);
		Roupas.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 11, 79, 14);
		Roupas.add(lblId);
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Roboto", Font.BOLD, 15));
		
		JLabel lblLavar = new JLabel("Quantidade:");
		lblLavar.setBounds(0, 53, 89, 14);
		Roupas.add(lblLavar);
		lblLavar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLavar.setFont(new Font("Roboto", Font.BOLD, 15));
		
		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(99, 50, 92, 20);
		Roupas.add(tfQuantidade);
		tfQuantidade.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfQuantidade.setColumns(10);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(419, 53, 120, 40);
		Roupas.add(btSalvar);
		btSalvar.setIcon(new ImageIcon(TelaServicoOrdem.class.getResource("/view/salvar.png")));
		btSalvar.setForeground(new Color(72, 61, 139));
		btSalvar.setFont(new Font("Roboto", Font.BOLD, 18));
		btSalvar.setBackground(new Color(255, 255, 255));
		btSalvar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		
		tfId = new JTextField();
		tfId.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfId.setColumns(10);
		tfId.setBounds(99, 9, 92, 20);
		Roupas.add(tfId);
		
		JPanel Tapete = new JPanel();
		tabbedPane.addTab("Tapete", null, Tapete, null);
		Tapete.setLayout(null);
		
		JLabel lblPassar = new JLabel("Pre\u00E7o m\u00B2:");
		lblPassar.setBounds(10, 11, 81, 14);
		Tapete.add(lblPassar);
		lblPassar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassar.setFont(new Font("Roboto", Font.BOLD, 15));
		
		tfPreco = new JTextField();
		tfPreco.setText("12.00");
		tfPreco.setBounds(101, 8, 121, 20);
		Tapete.add(tfPreco);
		tfPreco.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfPreco.setColumns(10);
		
		JLabel lblAltura = new JLabel("Altura:");
		lblAltura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAltura.setFont(new Font("Roboto", Font.BOLD, 15));
		lblAltura.setBounds(10, 59, 81, 14);
		Tapete.add(lblAltura);
		
		tfAltura = new JTextField();
		tfAltura.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfAltura.setColumns(10);
		tfAltura.setBounds(101, 57, 62, 20);
		Tapete.add(tfAltura);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblX.setFont(new Font("Roboto", Font.BOLD, 15));
		lblX.setBounds(173, 60, 10, 14);
		Tapete.add(lblX);
		
		lblOrdenar = new JLabel("Largura:");
		lblOrdenar.setBounds(193, 59, 81, 14);
		Tapete.add(lblOrdenar);
		lblOrdenar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenar.setFont(new Font("Roboto", Font.BOLD, 15));
		
		tfLargura = new JTextField();
		tfLargura.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfLargura.setColumns(10);
		tfLargura.setBounds(284, 57, 62, 20);
		Tapete.add(tfLargura);
		
		btInserir = new JButton("Salvar");
		btInserir.setIcon(new ImageIcon(TelaServicoOrdem.class.getResource("/view/salvar.png")));
		btInserir.setForeground(new Color(72, 61, 139));
		btInserir.setFont(new Font("Roboto", Font.BOLD, 18));
		btInserir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		btInserir.setBackground(Color.WHITE);
		btInserir.setBounds(419, 56, 120, 40);
		Tapete.add(btInserir);
		
		modelo.addColumn("Serviço");
		modelo.addColumn("Preço (R$)");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Total");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 333, 554, 238);
		panel.add(scrollPane_1);
		
		servicos = new JTable(modelo);
		scrollPane_1.setViewportView(servicos);
		
		btListar = new JButton("Listar");
		btListar.setForeground(new Color(72, 61, 139));
		btListar.setIcon(new ImageIcon(TelaServicoOrdem.class.getResource("/view/Visualizar.png")));
		btListar.setFont(new Font("Roboto", Font.BOLD, 18));
		btListar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		btListar.setBackground(Color.WHITE);
		btListar.setBounds(21, 282, 120, 40);
		panel.add(btListar);
		
		tfSaldo = new JTextField();
		tfSaldo.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfSaldo.setEditable(false);
		tfSaldo.setColumns(10);
		tfSaldo.setBounds(309, 90, 92, 20);
		panel.add(tfSaldo);
		
		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setFont(new Font("Roboto", Font.BOLD, 15));
		lblSaldo.setBounds(218, 93, 81, 14);
		panel.add(lblSaldo);
		
		controlarEvento();
		
	}
	
	public void controlarEvento() {
		ServicoOrdemController c = new ServicoOrdemController(tfQuantidade, tfPedido, tfPreco, tfAltura, tfLargura, tfId, modelo, tfSaldo);
		//c.listarServicos();
		
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.inserir();
					c.listarServicosNosPedidos();
			}

		});
		
//		btServico.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				modelo.addColumn("ID");
//				modelo.addColumn("Serviço");
//				modelo.addColumn("Tipo");
//				modelo.addColumn("Lavar (R$)");
//				modelo.addColumn("Passa");
//			}
//
//		});
		
		btInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.inserirTapete();
					c.listarServicosNosPedidos();
			}

		});
		
		btListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.listarServicosNosPedidos();
			}

		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaServicoOrdem.this.setVisible(false);
			}
		});
	}
}
