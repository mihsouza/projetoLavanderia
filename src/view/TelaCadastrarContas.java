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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class TelaCadastrarContas extends JFrame {

	protected JPanel contentPane;
	private JTextField tfPagoa;
	private JTextField tfValor;
	private JTextField tfData;
	private JTextField tfParcela;
	protected JTextField tfId;
	private JRadioButton rbNaoPago, rbPago;
	private JComboBox cbTipo, cbFrequencia, cbRec;
	private JButton btSalvar;
	protected JButton btVisualizar, btExcluir, btCancelar;
	protected JLabel lblTitulo;
	private JLabel label;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastrarContas frame = new TelaCadastrarContas();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaCadastrarContas() {
		
		setTitle("Conta"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setBackground(SystemColor.textHighlight);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Pago a :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setBounds(71, 75, 56, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblValorR = new JLabel("Valor R$:");
		lblValorR.setForeground(Color.WHITE);
		lblValorR.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblValorR.setBounds(57, 114, 70, 27);
		contentPane.add(lblValorR);
		
		JLabel lblData = new JLabel("Data :");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblData.setBounds(380, 114, 43, 27);
		contentPane.add(lblData);
		
		JLabel lblTipo = new JLabel("Tipo :");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTipo.setBounds(84, 224, 43, 35);
		contentPane.add(lblTipo);
		
		JLabel lblNmeroDeParcelas = new JLabel("N\u00FAmero de parcelas :");
		lblNmeroDeParcelas.setForeground(Color.WHITE);
		lblNmeroDeParcelas.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNmeroDeParcelas.setBounds(41, 270, 152, 27);
		contentPane.add(lblNmeroDeParcelas);
		
		tfPagoa = new JTextField();
		tfPagoa.setBounds(135, 79, 413, 25);
		contentPane.add(tfPagoa);
		tfPagoa.setColumns(10);
		
		tfValor = new JTextField();
		tfValor.setColumns(10);
		tfValor.setBounds(135, 118, 152, 25);
		contentPane.add(tfValor);
		
		tfData = new JTextField();
		tfData.setColumns(10);
		tfData.setBounds(433, 116, 115, 25);
		contentPane.add(tfData);
		
		tfParcela = new JTextField();
		tfParcela.setColumns(10);
		tfParcela.setBounds(203, 274, 56, 25);
		contentPane.add(tfParcela);
		
		cbTipo = new JComboBox();
		cbTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Funcion\u00E1rios", "Manuten\u00E7\u00E3o", "Imposto", "Alimenta\u00E7\u00E3o", "Encargos"}));
		cbTipo.setBounds(137, 230, 196, 25);
		contentPane.add(cbTipo);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblId.setBounds(467, 29, 25, 25);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setColumns(10);
		tfId.setBounds(502, 32, 46, 25);
		contentPane.add(tfId);
		
		rbPago = new JRadioButton("Pago");
		rbPago.setForeground(Color.WHITE);
		rbPago.setBackground(SystemColor.textHighlight);
		rbPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbPago.setBounds(111, 319, 109, 25);
		contentPane.add(rbPago);
		
		rbNaoPago = new JRadioButton("N\u00E3o pago");
		rbNaoPago.setForeground(Color.WHITE);
		rbNaoPago.setBackground(SystemColor.textHighlight);
		rbNaoPago.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rbNaoPago.setSelected(true);
		rbNaoPago.setBounds(224, 319, 109, 25);
		contentPane.add(rbNaoPago);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbPago);
		bg.add(rbNaoPago);
		
		cbFrequencia = new JComboBox();
		cbFrequencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbFrequencia.setModel(new DefaultComboBoxModel(new String[] {"Uma vez", "Toda semana", "Todo m\u00EAs", "Todo ano"}));
		cbFrequencia.setBounds(137, 195, 196, 25);
		contentPane.add(cbFrequencia);
		
		JLabel lblRecorrencia = new JLabel("Recorrencia:");
		lblRecorrencia.setForeground(Color.WHITE);
		lblRecorrencia.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblRecorrencia.setBounds(41, 152, 86, 35);
		contentPane.add(lblRecorrencia);
		
		cbRec = new JComboBox();
		cbRec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbRec.setModel(new DefaultComboBoxModel(new String[] {"Semanal", "Mensal", "Anual"}));
		cbRec.setBounds(137, 159, 196, 25);
		contentPane.add(cbRec);
		
		JLabel lblFrequencia = new JLabel("Frequencia :");
		lblFrequencia.setForeground(Color.WHITE);
		lblFrequencia.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblFrequencia.setBounds(41, 189, 86, 35);
		contentPane.add(lblFrequencia);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setIcon(new ImageIcon(TelaCadastrarContas.class.getResource("/view/Salvar.png")));
		btSalvar.setBounds(408, 398, 140, 40);
		btSalvar.setBackground(new Color(60, 179, 113));
		contentPane.add(btSalvar);
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setIcon(new ImageIcon(TelaCadastrarContas.class.getResource("/view/Visualizar.png")));
		btVisualizar.setBounds(408, 257, 140, 40);
		btVisualizar.setBackground(new Color(218, 165, 32));
//		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btExcluir.setForeground(Color.WHITE);
		btExcluir.setIcon(new ImageIcon(TelaCadastrarContas.class.getResource("/view/Excluir.png")));
		btExcluir.setBounds(408, 310, 140, 40);
		btExcluir.setBackground(new Color(218, 165, 32));
//		contentPane.add(btExcluir);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setIcon(new ImageIcon(TelaCadastrarContas.class.getResource("/view/cancel-button-white.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.setBounds(273, 398, 125, 40);
		contentPane.add(btCancelar);
		
		lblTitulo = new JLabel("Nova Conta");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setBounds(103, 7, 218, 56);
		contentPane.add(lblTitulo);
		
		JLabel lblImgNovaConta = new JLabel("");
		lblImgNovaConta.setIcon(new ImageIcon(TelaCadastrarContas.class.getResource("/view/nova_conta.png")));
		lblImgNovaConta.setForeground(Color.WHITE);
		lblImgNovaConta.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblImgNovaConta.setBounds(33, 7, 60, 50);
		contentPane.add(lblImgNovaConta);
		
		ImageIcon NovaConta = new ImageIcon(TelaCargo.class.getResource("/view/nova_conta.png"));
		Image NovaCo = NovaConta.getImage().getScaledInstance(lblImgNovaConta.getWidth(), lblImgNovaConta.getHeight(), Image.SCALE_SMOOTH);	
		lblImgNovaConta.setIcon(new ImageIcon(NovaCo));
		
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
				if(tfId.getText() .trim().equals("")){
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("O campo 'ID' não pode estar vazio!");
				}else {
					c.visualizar();
				}
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
		
		/**
		 * Ação do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaCadastrarContas.this.setVisible(false);
				}
		});
	}
}
