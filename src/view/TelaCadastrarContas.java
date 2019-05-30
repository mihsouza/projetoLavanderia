package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ContasController;
import controller.PedidoController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class TelaCadastrarContas extends JFrame {

	private JPanel contentPane;
	private JTextField tfPagoa;
	private JTextField tfValor;
	private JTextField tfData;
	private JTextField tfParcela;
	private JTextField tfId;
	private JRadioButton rbNaoPago, rbPago;
	private JComboBox cbTipo, cbFrequencia, cbRec;
	private JButton btSalvar;
	private JButton btVisualizar, btExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastrarContas frame = new TelaCadastrarContas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastrarContas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pago a :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel.setBounds(24, 51, 43, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblValorR = new JLabel("Valor : R$");
		lblValorR.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblValorR.setBounds(10, 89, 57, 14);
		contentPane.add(lblValorR);
		
		JLabel lblData = new JLabel("Data :");
		lblData.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblData.setBounds(35, 117, 32, 14);
		contentPane.add(lblData);
		
		JLabel lblTipo = new JLabel("Tipo :");
		lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTipo.setBounds(35, 207, 32, 14);
		contentPane.add(lblTipo);
		
		JLabel lblNmeroDeParcelas = new JLabel("N\u00FAmero de parcelas :");
		lblNmeroDeParcelas.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNmeroDeParcelas.setBounds(10, 255, 115, 14);
		contentPane.add(lblNmeroDeParcelas);
		
		tfPagoa = new JTextField();
		tfPagoa.setBounds(77, 48, 347, 20);
		contentPane.add(tfPagoa);
		tfPagoa.setColumns(10);
		
		tfValor = new JTextField();
		tfValor.setColumns(10);
		tfValor.setBounds(77, 86, 92, 20);
		contentPane.add(tfValor);
		
		tfData = new JTextField();
		tfData.setColumns(10);
		tfData.setBounds(77, 114, 108, 20);
		contentPane.add(tfData);
		
		tfParcela = new JTextField();
		tfParcela.setColumns(10);
		tfParcela.setBounds(130, 252, 32, 20);
		contentPane.add(tfParcela);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Funcion\u00E1rios", "Manuten\u00E7\u00E3o", "Imposto", "Alimenta\u00E7\u00E3o", "Encargos"}));
		cbTipo.setBounds(77, 204, 196, 20);
		contentPane.add(cbTipo);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(354, 11, 14, 14);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setColumns(10);
		tfId.setBounds(378, 8, 46, 20);
		contentPane.add(tfId);
		
		rbPago = new JRadioButton("Pago");
		rbPago.setBounds(77, 285, 109, 23);
		contentPane.add(rbPago);
		
		rbNaoPago = new JRadioButton("N\u00E3o pago");
		rbNaoPago.setSelected(true);
		rbNaoPago.setBounds(188, 285, 109, 23);
		contentPane.add(rbNaoPago);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbPago);
		bg.add(rbNaoPago);
		
		cbFrequencia = new JComboBox();
		cbFrequencia.setModel(new DefaultComboBoxModel(new String[] {"Uma vez", "Toda semana", "Todo m\u00EAs", "Todo ano"}));
		cbFrequencia.setBounds(77, 173, 196, 20);
		contentPane.add(cbFrequencia);
		
		JLabel lblRecorrencia = new JLabel("Recorrencia:");
		lblRecorrencia.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblRecorrencia.setBounds(0, 145, 79, 14);
		contentPane.add(lblRecorrencia);
		
		cbRec = new JComboBox();
		cbRec.setModel(new DefaultComboBoxModel(new String[] {"Semanal", "Mensal", "Anual"}));
		cbRec.setBounds(77, 142, 196, 20);
		contentPane.add(cbRec);
		
		JLabel lblFrequencia = new JLabel("Frequencia :");
		lblFrequencia.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblFrequencia.setBounds(-1, 176, 68, 14);
		contentPane.add(lblFrequencia);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(243, 318, 89, 23);
		contentPane.add(btSalvar);
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setBounds(144, 318, 89, 23);
		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(45, 318, 89, 23);
		contentPane.add(btExcluir);
		
		controlarEventos();
	}
	
	public void controlarEventos() {
		ContasController c = new ContasController(tfId, tfPagoa, tfValor, tfData, tfParcela, rbNaoPago, rbPago, cbTipo, cbRec, cbFrequencia);
		
		/**
		 * Ações do botão salvar
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.verificarAcao();
			}
		});
		
		/**
		 * Ação do botão visualizar.
		 */
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.visualizar();
				}
		});
		
		/**
		 * Ação do botão Excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.excluir();
				}
		});
	}
}
