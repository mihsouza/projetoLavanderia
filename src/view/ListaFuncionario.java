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

import controller.FuncionarioController;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class ListaFuncionario extends JFrame {
	protected JTextField tfPesquisa;
	private JTable tbLista;
	private JLabel lblPesquisa, lblTitle, lblOrdenar;
	protected JComboBox cbOrdenar;
	private JButton btNovo, btFiltrar;
	protected JTextField tfCpf;
	private JButton btVoltar;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	private JLabel label;
	private JButton btVisualizar;

	/**
	 * Create the panel.
	 */
	public ListaFuncionario() {

		setTitle("Funcion�rios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 680);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		this.setResizable(false);

		lblPesquisa = new JLabel("Nome:");
		lblPesquisa.setForeground(Color.WHITE);
		lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPesquisa.setBounds(22, 102, 89, 24);
		getContentPane().add(lblPesquisa);

		lblTitle = new JLabel("Funcion\u00E1rios");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitle.setBounds(107, 37, 257, 39);
		getContentPane().add(lblTitle);

		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setForeground(Color.WHITE);
		lblOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOrdenar.setBounds(22, 178, 153, 45);
		getContentPane().add(lblOrdenar);

		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPesquisa.setBounds(101, 102, 544, 25);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);

		cbOrdenar = new JComboBox();
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] { "Nome", "C\u00F3digo" }));
		cbOrdenar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbOrdenar.setBounds(153, 190, 166, 25);
		getContentPane().add(cbOrdenar);

		JPanel pnBotao = new JPanel();
		pnBotao.setBackground(Color.DARK_GRAY);
		pnBotao.setBounds(10, 236, 764, 58);
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
		tbLista.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "id", "Nome", "Remunera\u00E7\u00E3o" }));
		tbLista.setBounds(789, 220, -769, 351);
		getContentPane().add(tbLista);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCpf.setBounds(22, 137, 57, 39);
		contentPane.add(lblCpf);

		tfCpf = new JTextField();
		tfCpf.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCpf.setColumns(10);
		tfCpf.setBounds(101, 144, 180, 25);
		contentPane.add(tfCpf);

		btVoltar = new JButton("Voltar");
		btVoltar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(0, 139, 139));
		btVoltar.setBounds(654, 11, 120, 40);
		contentPane.add(btVoltar);

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(224, 255, 255)));
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 303, 764, 328);
		getContentPane().add(scrollPane);
		scrollPane.setBackground(new Color(224, 255, 255));

		table = new JTable(modelo);
		scrollPane.setViewportView(table);

		JLabel lblFuncionarios = new JLabel("");
		lblFuncionarios.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/funcionarios.png")));
		lblFuncionarios.setForeground(Color.WHITE);
		lblFuncionarios.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblFuncionarios.setBounds(37, 26, 60, 50);
		contentPane.add(lblFuncionarios);

		ImageIcon Funcionario = new ImageIcon(TelaCargo.class.getResource("/view/funcionarios.png"));
		Image Fun = Funcionario.getImage().getScaledInstance(lblFuncionarios.getWidth(), lblFuncionarios.getHeight(),
				Image.SCALE_SMOOTH);
		lblFuncionarios.setIcon(new ImageIcon(Fun));

		modelo.addColumn("C�digo");
		modelo.addColumn("Nome");
		modelo.addColumn("CPF");
		modelo.addColumn("Telefone");

		controlarEventos();

	}

	public void controlarEventos() {
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFuncionario frame = new TelaFuncionario();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FuncionarioController f = new FuncionarioController(tfPesquisa, cbOrdenar, tfCpf, modelo);
				f.consulta();
			}
		});
		
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFuncionario frame = new TelaFuncionario();
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
				ListaFuncionario.this.setVisible(false);
				TelaMenu frame = new TelaMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
	}


//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			/**
//			 * m�todo run - m�todo que inicia a aplica��o
//			 */
//			public void run() {
//				try {
//					ListaFuncionario frame = new ListaFuncionario();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
