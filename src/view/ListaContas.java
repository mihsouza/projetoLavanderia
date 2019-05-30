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

import controller.ContasController;
import controller.FuncionarioController;
import services.GeneratorPDF;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class ListaContas extends JFrame {
	private JTextField tfPesquisa;
	private JTable tbLista;
	private JLabel lblPesquisa, lblTitle;
	private JButton btNovo, btFiltrar;
	private JTextField tfInicio;
	private JButton btVoltar;
	private JTextField tfFim;
	private JRadioButton rbNaoPago, rbPago;
	private JButton btPdf;

	/**
	 * Create the panel.
	 */
	public ListaContas() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);
		
		lblPesquisa = new JLabel("Pago a:");
		lblPesquisa.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPesquisa.setBounds(6, 54, 40, 14);
		getContentPane().add(lblPesquisa);
		
		lblTitle = new JLabel("Contas a pagar");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblTitle.setBounds(10, 11, 112, 20);
		getContentPane().add(lblTitle);
		
		tfPesquisa = new JTextField();
		tfPesquisa.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfPesquisa.setBounds(56, 51, 544, 20);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
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
		
		btPdf = new JButton("PDF");
		btPdf.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btPdf.setBackground(new Color(222, 184, 135));
		btPdf.setBounds(211, 11, 89, 23);
		pnBotao.add(btPdf);
		
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
		
		JLabel lblInicio = new JLabel("Inicio:");
		lblInicio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInicio.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblInicio.setBounds(10, 85, 36, 14);
		contentPane.add(lblInicio);
		
		tfInicio = new JTextField();
		tfInicio.setText("01/01/2019");
		tfInicio.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfInicio.setColumns(10);
		tfInicio.setBounds(56, 82, 89, 20);
		contentPane.add(tfInicio);
		
		btVoltar = new JButton("Voltar");
		btVoltar.setBounds(685, 11, 89, 23);
		contentPane.add(btVoltar);
		
		JLabel lblFim = new JLabel("Fim:");
		lblFim.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFim.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblFim.setBounds(176, 85, 36, 14);
		contentPane.add(lblFim);
		
		tfFim = new JTextField();
		tfFim.setText("31/12/2019");
		tfFim.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfFim.setColumns(10);
		tfFim.setBounds(222, 82, 89, 20);
		contentPane.add(tfFim);
		
		rbPago = new JRadioButton("Pago");
		rbPago.setSelected(true);
		rbPago.setBounds(56, 118, 109, 23);
		contentPane.add(rbPago);
		
		rbNaoPago = new JRadioButton("N\u00E3o pago");
		rbNaoPago.setSelected(true);
		rbNaoPago.setBounds(167, 118, 109, 23);
		contentPane.add(rbNaoPago);
		
		
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
			ContasController c = new ContasController(rbNaoPago, rbPago, tfPesquisa, tfInicio, tfFim);
				c.listar();
			}
		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaContas.this.setVisible(false);
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
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			/**
			 * método run - método que inicia a aplicação
			 */
			public void run() {
				try {
					ListaContas frame = new ListaContas();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
