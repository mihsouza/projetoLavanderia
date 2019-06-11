package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ServicoController;
import services.BD;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ListaServico extends JFrame {
	protected JTextField tfPesquisa;
	private JTable tbLista;
	private JLabel lblPesquisa, lblTitle, lblOrdenar;
	protected JComboBox cbOrdenar;
	private JButton btVoltar;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btFiltrar;
	private JButton btNovo;
	private JButton btVisualizar;

	/**
	 * Create the panel.
	 */
	public ListaServico() {
		
		setTitle("Serviços"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		
		lblPesquisa = new JLabel("Servi\u00E7o:");
		lblPesquisa.setForeground(Color.WHITE);
		lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPesquisa.setBounds(10, 74, 112, 29);
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Servi\u00E7os");
		lblTitle.setIcon(new ImageIcon(ListaServico.class.getResource("/view/servicos-icon.png")));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitle.setBounds(10, 12, 199, 51);
		getContentPane().add(lblTitle);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setForeground(Color.WHITE);
		lblOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOrdenar.setBounds(10, 114, 150, 29);
		getContentPane().add(lblOrdenar);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPesquisa.setBounds(99, 83, 544, 20);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo", "Tipo", "Pre\u00E7o"}));
		cbOrdenar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cbOrdenar.setBounds(137, 120, 166, 25);
		getContentPane().add(cbOrdenar);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBackground(Color.DARK_GRAY);
		pnBotao.setBounds(9, 154, 765, 58);
		getContentPane().add(pnBotao);
		pnBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(Color.WHITE);
		btFiltrar.setBackground(new Color(222, 184, 135));
		btFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btFiltrar.setBounds(12, 10, 120, 40);
		pnBotao.add(btFiltrar);
		
		btNovo = new JButton("Novo");
		btNovo.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/novo.png")));
		btNovo.setForeground(Color.WHITE);
		btNovo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btNovo.setBackground(new Color(222, 184, 135));
		btNovo.setBounds(151, 10, 120, 40);
		pnBotao.add(btNovo);
		
		btVisualizar = new JButton("Detalhes");
		btVisualizar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(222, 184, 135));
		btVisualizar.setBounds(293, 10, 132, 40);
		pnBotao.add(btVisualizar);
		
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
		scrollPane.setBounds(10, 223, 764, 327);
		getContentPane().add(scrollPane);
		scrollPane.setBackground(new Color(224, 255, 255));
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		modelo.addColumn("Código");
		modelo.addColumn("Serviço");
		modelo.addColumn("Preço");
		modelo.addColumn("Tipo");
		
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaServico frame = new TelaServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServicoController s = new ServicoController(tfPesquisa, cbOrdenar, modelo);
				s.consulta();
			}
		});
		
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaServico frame = new TelaServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.contentPane.add(frame.btVisualizar);
				frame.contentPane.add(frame.btExcluir);
				frame.tfId.setEditable(true);
				frame.lblTitulo.setText("Serviço");
			}

		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaServico.this.setVisible(false);
				TelaMenu frame = new TelaMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
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
//					ListaServico frame = new ListaServico();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//					BD b = new BD();
//					b.getConnection();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
}
