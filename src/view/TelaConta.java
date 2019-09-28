package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.CargoController;
import controller.ContaController;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class TelaConta extends JFrame {

	protected JPanel contentPane;
	protected JButton btVoltar, btBuscar, btSalvar, btExcluir;
	protected JPanel panel;
	protected JLabel lbTitulo, lbNome, lbEndereco, lbAtributo, lbImagem;
	protected JTextField tfNome, tfValor;
	private JTextField tfId;
	private JTextField tfData;
	private JLabel lbStatus;
	public JRadioButton rbNaoPago, rbPago;
	public JComboBox cbTipo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaConta frame = new TelaConta();
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
	public TelaConta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 637, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(102, 205, 170));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Nova despesa");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(127, 21, 297, 36);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(TelaConta.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(507, 4, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/conta.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(21, 11, 96, 92);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/conta.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		lbNome = new JLabel("Pago \u00E0*:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setFont(new Font("Roboto", Font.BOLD, 15));
		lbNome.getHorizontalAlignment();
		lbNome.setBounds(21, 144, 81, 20);
		panel.add(lbNome);
		
		lbEndereco = new JLabel("Tipo:");
		lbEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lbEndereco.setFont(new Font("Roboto", Font.BOLD, 15));
		lbEndereco.setBounds(21, 231, 81, 14);
		panel.add(lbEndereco);
		
		lbAtributo = new JLabel("Valor*:");
		lbAtributo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAtributo.setFont(new Font("Roboto", Font.BOLD, 15));
		lbAtributo.setBounds(0, 186, 102, 14);
		panel.add(lbAtributo);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfNome.setBounds(122, 144, 443, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		tfValor = new JTextField();
		tfValor.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfValor.setColumns(10);
		tfValor.setBounds(122, 183, 114, 20);
		panel.add(tfValor);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(304, 347, 120, 40);
//		panel.add(btExcluir);
		btExcluir.setIcon(new ImageIcon(TelaConta.class.getResource("/view/lixeira.png")));
		btExcluir.setForeground(new Color(178, 34, 34));
		btExcluir.setFont(new Font("Roboto", Font.BOLD, 18));
		btExcluir.setBackground(new Color(255, 255, 255));
		btExcluir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34)));
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(445, 347, 120, 40);
		panel.add(btSalvar);
		btSalvar.setIcon(new ImageIcon(TelaConta.class.getResource("/view/salvar.png")));
		btSalvar.setForeground(new Color(72, 61, 139));
		btSalvar.setFont(new Font("Roboto", Font.BOLD, 18));
		btSalvar.setBackground(new Color(255, 255, 255));
		btSalvar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		
		btBuscar = new JButton("Ver");
		btBuscar.setBounds(342, 75, 120, 40);
//		panel.add(btBuscar);
		btBuscar.setIcon(new ImageIcon(TelaConta.class.getResource("/view/Visualizar.png")));
		btBuscar.setForeground(new Color(47, 79, 79));
		btBuscar.setFont(new Font("Roboto", Font.BOLD, 18));
		btBuscar.setBackground(Color.WHITE);
		btBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79)));
		
		tfId = new JTextField();
		tfId.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfId.setColumns(10);
		tfId.setBounds(522, 86, 43, 20);
		panel.add(tfId);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Roboto", Font.BOLD, 15));
		lblId.setBounds(487, 90, 25, 14);
		panel.add(lblId);
		
		tfData = new JTextField();
		tfData.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfData.setColumns(10);
		tfData.setBounds(429, 184, 136, 20);
		panel.add(tfData);
		
		JLabel lbTelefone = new JLabel("Data pagamento:");
		lbTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTelefone.setFont(new Font("Roboto", Font.BOLD, 15));
		lbTelefone.setBounds(304, 183, 120, 20);
		panel.add(lbTelefone);
		
		lbStatus = new JLabel("Status:");
		lbStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lbStatus.setFont(new Font("Roboto", Font.BOLD, 15));
		lbStatus.setBounds(21, 269, 81, 20);
		panel.add(lbStatus);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Funcionario", "Manutencao", "Alimentacao", "Limpeza", "Imposto"}));
		cbTipo.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbTipo.setBounds(122, 228, 207, 20);
		panel.add(cbTipo);
		
		rbPago = new JRadioButton("Pago");
		rbPago.setBackground(new Color(102, 205, 170));
		rbPago.setFont(new Font("Roboto", Font.PLAIN, 15));
		rbPago.setBounds(122, 269, 109, 23);
		panel.add(rbPago);
		
		rbNaoPago = new JRadioButton("N\u00E3o Pago");
		rbNaoPago.setSelected(true);
		rbNaoPago.setFont(new Font("Roboto", Font.PLAIN, 15));
		rbNaoPago.setBackground(new Color(102, 205, 170));
		rbNaoPago.setBounds(233, 269, 109, 23);
		panel.add(rbNaoPago);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbPago);
		bg.add(rbNaoPago);
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		ContaController c = new ContaController(tfNome, tfValor, tfId, tfData, cbTipo, rbNaoPago, rbPago);
		/**
		 * Fechar o frame
		 */
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConta.this.setVisible(false);
			}

		});
		
		/**
		 * inserir um novo cargo
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.validarCampo() == true) {
					c.inserir();
					TelaConta.this.setVisible(false);
				}
			}

		});
		
		/**
		 * excluir um cargo
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().trim().equals("")) {
					TelaMensagemErro frame = new TelaMensagemErro();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lbCampo.setText("ID: Campo obrigatório");
				}else {
					c.excluir();
					TelaConta.this.setVisible(false);
				}
			}

		});
		
		/**
		 * excluir um conta
		 */
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().trim().equals("")) {
					TelaMensagemErro frame = new TelaMensagemErro();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lbCampo.setText("ID: Campo obrigatório");
				}else {
					c.visualizar();
				}
			}

		});
	}
}
