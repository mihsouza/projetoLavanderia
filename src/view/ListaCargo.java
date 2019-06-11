package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.CargoController;
import javax.swing.ImageIcon;

public class ListaCargo extends JFrame {
	
	/**
	 * Componentes da tela
	 */
	public JTextField tfPesquisa;
	public JTable tbLista;
	public JLabel lblPesquisa, lblTitle, lblOrdenar;
	public JComboBox cbOrdenar;
	public JButton btNovo, btFiltrar, btVisualizar;
	public JButton btVoltar;
	public JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Criando o JFrame.
	 */
	public ListaCargo() {
		
		setTitle("Cargos"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 672);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		this.setResizable(false);
		
		lblPesquisa = new JLabel("Cargo:");
		lblPesquisa.setForeground(Color.WHITE);
		lblPesquisa.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPesquisa.setBounds(10, 78, 89, 39);
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Cargos");
		lblTitle.setIcon(new ImageIcon(ListaCargo.class.getResource("/view/cargo-icon.png")));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitle.setBounds(10, 17, 146, 50);
		getContentPane().add(lblTitle);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setForeground(Color.WHITE);
		lblOrdenar.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblOrdenar.setBounds(10, 122, 161, 38);
		getContentPane().add(lblOrdenar);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tfPesquisa.setBounds(81, 89, 544, 25);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo", "Remunera\u00E7\u00E3o"}));
		cbOrdenar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbOrdenar.setBounds(144, 131, 166, 25);
		getContentPane().add(cbOrdenar);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBackground(Color.DARK_GRAY);
		pnBotao.setBounds(10, 187, 765, 58);
		getContentPane().add(pnBotao);
		pnBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(Color.WHITE);
		btFiltrar.setBackground(new Color(204, 153, 51));
		btFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btFiltrar.setBounds(12, 10, 120, 40);
		pnBotao.add(btFiltrar);
		
		btNovo = new JButton("Novo");
		btNovo.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/novo.png")));
		btNovo.setForeground(Color.WHITE);
		btNovo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btNovo.setBackground(new Color(204, 153, 51));
		btNovo.setBounds(151, 10, 120, 40);
		pnBotao.add(btNovo);
		
		btVisualizar = new JButton("Detalhes");
		btVisualizar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(204, 153, 51));
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
		scrollPane.setBounds(11, 260, 764, 361);
		getContentPane().add(scrollPane);
		scrollPane.setBackground(new Color(224, 255, 255));
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		ImageIcon Cargo = new ImageIcon(TelaCargo.class.getResource("/view/cargo.png"));
		
		modelo.addColumn("Código");
		modelo.addColumn("Nome");
		modelo.addColumn("Remuneração");
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCargo frame = new TelaCargo();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCargo frame = new TelaCargo();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.contentPane.add(frame.btVisualizar);
				frame.contentPane.add(frame.btExcluir);
				frame.tfId.setEditable(true);
				frame.lblTitulo.setText("Cargo");
			}

		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCargo.this.setVisible(false);
				TelaMenu t = new TelaMenu();
				t.setVisible(true);
			}
		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CargoController l = new CargoController(tfPesquisa, cbOrdenar,modelo);
				l.consulta();
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
//					ListaCargo frame = new ListaCargo();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
