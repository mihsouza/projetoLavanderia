package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.CargoController;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;

public class ListaCargo extends JFrame {

	protected JPanel contentPane;
	public JButton btVoltar, btBuscar;
	public JButton btNovo;
	public JButton btFiltrar;
	protected JPanel panel, plBotao;
	protected JLabel lbTitulo, lbNome, lblOrdenar, lbImagem;
	protected JTextField tfNome;
	protected JComboBox cbOrdenar;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
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

	/**
	 * Create the frame.
	 */
	public ListaCargo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1366, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Cargos");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(177, 18, 297, 36);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(ListaCargo.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(1258, 11, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/maleta.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(60, 4, 91, 76);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/maleta.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		plBotao = new JPanel();
		plBotao.setBackground(new Color(95, 158, 160));
		plBotao.setBounds(10, 216, 1320, 62);
		panel.add(plBotao);
		plBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setIcon(new ImageIcon(ListaCargo.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(new Color(0, 0, 0));
		btFiltrar.setBackground(new Color(255, 255, 255));
		btFiltrar.setFont(new Font("Roboto", Font.BOLD, 18));
		btFiltrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btFiltrar.setBounds(10, 11, 120, 40);
		plBotao.add(btFiltrar);
		
		btBuscar = new JButton("Ver");
		btBuscar.setIcon(new ImageIcon(ListaCargo.class.getResource("/view/detalhes.png")));
		btBuscar.setForeground(new Color(0, 128, 128));
		btBuscar.setFont(new Font("Roboto", Font.BOLD, 18));
		btBuscar.setBackground(Color.WHITE);
		btBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128), new Color(0, 128, 128), new Color(0, 128, 128)));
		btBuscar.setBounds(150, 11, 120, 40);
		plBotao.add(btBuscar);
		
		btNovo = new JButton("Novo");
		btNovo.setIcon(new ImageIcon(ListaCargo.class.getResource("/view/novo.png")));
		btNovo.setForeground(new Color(0, 0, 128));
		btNovo.setFont(new Font("Roboto", Font.BOLD, 18));
		btNovo.setBackground(new Color(255, 255, 255));
		btNovo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128)));
		btNovo.setBounds(292, 11, 120, 40);
		plBotao.add(btNovo);
		
		lbNome = new JLabel("Cargo:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setFont(new Font("Roboto", Font.BOLD, 15));
		lbNome.getHorizontalAlignment();
		lbNome.setBounds(10, 91, 141, 14);
		panel.add(lbNome);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenar.setFont(new Font("Roboto", Font.BOLD, 15));
		lblOrdenar.setBounds(10, 140, 141, 14);
		panel.add(lblOrdenar);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfNome.setBounds(164, 89, 443, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"ID", "Cargo", "Remuneracao"}));
		cbOrdenar.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbOrdenar.setBounds(164, 137, 203, 20);
		panel.add(cbOrdenar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 289, 1320, 396);
		panel.add(scrollPane);
		
		table = new JTable(modelo);
		table.setFont(new Font("Roboto", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		modelo.addColumn("ID");
		modelo.addColumn("Cargo");
		modelo.addColumn("Remuneração");
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		/**
		 * Chamar uma tela para cadastrar
		 */
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCargo frame = new TelaCargo();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		/**
		 * Chamar uma tela para visualizar
		 */
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCargo frame = new TelaCargo();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.panel.add(frame.btExcluir);
				frame.panel.add(frame.btBuscar);
				frame.lbTitulo.setText("Cargo");
				frame.tfId.setEditable(true);
			}

		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCargo.this.setVisible(false);
			}
		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CargoController l = new CargoController(tfNome, cbOrdenar,modelo);
				l.listar();
			}
		});
	}
}
