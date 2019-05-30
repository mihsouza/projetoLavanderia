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
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ListaPedido extends JFrame {
	private JTextField tfPesquisa;
	private JLabel lblPesquisa, lblTitle, lblOrdenar;
	private JComboBox cbOrdenar;
	private JButton btNovo, btFiltrar;
	private JRadioButton rbAtraso;
	private JRadioButton rbEspera;
	private JRadioButton rbFinalizado;
	private JButton btVoltar;

	/**
	 * Create the panel.
	 */
	public ListaPedido() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		lblPesquisa = new JLabel("Cliente:");
		lblPesquisa.setBounds(10, 54, 46, 14);
		lblPesquisa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Pedidos");
		lblTitle.setBounds(10, 11, 89, 20);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));
		getContentPane().add(lblTitle);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setBounds(10, 119, 72, 14);
		lblOrdenar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		getContentPane().add(lblOrdenar);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setBounds(56, 51, 544, 20);
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setBounds(88, 116, 166, 20);
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Cliente", "C\u00F3digo", "Status", "Pre\u00E7o"}));
		cbOrdenar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		getContentPane().add(cbOrdenar);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBounds(10, 161, 764, 48);
		pnBotao.setBackground(SystemColor.activeCaption);
		getContentPane().add(pnBotao);
		pnBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setBackground(new Color(222, 184, 135));
		btFiltrar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btFiltrar.setBounds(10, 11, 89, 23);
		pnBotao.add(btFiltrar);
		
		btNovo = new JButton("Novo");
		btNovo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btNovo.setBackground(new Color(222, 184, 135));
		btNovo.setBounds(112, 11, 89, 23);
		pnBotao.add(btNovo);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 90, 44, 14);
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 13));
		contentPane.add(lblStatus);
		
		rbFinalizado = new JRadioButton("Finalizado");
		rbFinalizado.setBounds(56, 86, 109, 23);
		rbFinalizado.setBackground(new Color(224, 255, 255));
		contentPane.add(rbFinalizado);
		
		rbEspera = new JRadioButton("Em espera");
		rbEspera.setBounds(167, 86, 109, 23);
		rbEspera.setBackground(new Color(224, 255, 255));
		contentPane.add(rbEspera);
		
		rbAtraso = new JRadioButton("Em atraso");
		rbAtraso.setBounds(279, 86, 109, 23);
		rbAtraso.setBackground(new Color(224, 255, 255));
		contentPane.add(rbAtraso);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbFinalizado);
		bg.add(rbEspera);
		bg.add(rbAtraso);
		
		btVoltar = new JButton("Voltar");
		btVoltar.setBounds(685, 12, 89, 23);
		contentPane.add(btVoltar);
		
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
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPedido.this.setVisible(false);
			}
		});
		
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PedidoController p = new PedidoController(rbAtraso, rbEspera, rbFinalizado, tfPesquisa, cbOrdenar);
				p.consulta();
			}
		});
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/**
			 * método run - método que inicia a aplicação
			 */
			public void run() {
				try {
					ListaPedido frame = new ListaPedido();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
