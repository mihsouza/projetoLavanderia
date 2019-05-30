package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
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
import javax.swing.SwingConstants;

public class ListaFuncionario extends JFrame {
	private JTextField tfPesquisa;
	private JTable tbLista;
	private JLabel lblPesquisa, lblTitle, lblOrdenar;
	private JComboBox cbOrdenar;
	private JButton btNovo, btFiltrar;
	private JTextField tfCpf;
	private JButton btVoltar;

	/**
	 * Create the panel.
	 */
	public ListaFuncionario() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		
		lblPesquisa = new JLabel("Nome:");
		lblPesquisa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPesquisa.setBounds(10, 54, 46, 14);
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Funcion\u00E1rios");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTitle.setBounds(10, 11, 112, 20);
		getContentPane().add(lblTitle);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblOrdenar.setBounds(10, 119, 72, 14);
		getContentPane().add(lblOrdenar);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPesquisa.setBounds(56, 51, 544, 20);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Nome", "C\u00F3digo"}));
		cbOrdenar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cbOrdenar.setBounds(88, 116, 166, 20);
		getContentPane().add(cbOrdenar);
		
		JPanel pnBotao = new JPanel();
		pnBotao.setBackground(SystemColor.activeCaption);
		pnBotao.setBounds(10, 161, 764, 48);
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
		
		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Times New Roman", Font.BOLD, 13));
		label.setBounds(10, 54, 46, 14);
		contentPane.add(label);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCpf.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCpf.setBounds(12, 85, 34, 14);
		contentPane.add(lblCpf);
		
		tfCpf = new JTextField();
		tfCpf.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCpf.setColumns(10);
		tfCpf.setBounds(56, 82, 180, 20);
		contentPane.add(tfCpf);
		
		btVoltar = new JButton("Voltar");
		btVoltar.setBounds(685, 11, 89, 23);
		contentPane.add(btVoltar);
		
		
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
				FuncionarioController f = new FuncionarioController(tfPesquisa, cbOrdenar, tfCpf);
				f.consulta();
			}
		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaFuncionario.this.setVisible(false);
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
					ListaFuncionario frame = new ListaFuncionario();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
