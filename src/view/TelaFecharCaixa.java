package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.CaixaController;
import controller.CargoController;

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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFecharCaixa extends JFrame {

	public JPanel contentPane;
	public JPanel panel;
	public JLabel lbTitulo, lbImagem;
	public JButton btOk;
	public JLabel lblNewLabel;
	public JLabel lblAt;
	private JTextField tfDia;
	private JLabel lblSada;
	private JLabel lblLucro;
	private JLabel lbEntrada;
	private JLabel lbSaida, lbSaidaConta;
	private JLabel lbLucro;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaFecharCaixa frame = new TelaFecharCaixa();
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
	public TelaFecharCaixa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 637, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Fechar caixa do dia:");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 18));
		lbTitulo.setBounds(151, 25, 185, 36);
		panel.add(lbTitulo);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/fechar_caixa.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(21, 11, 106, 104);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/fechar_caixa.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		btOk = new JButton("OK");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btOk.setBackground(new Color(143, 188, 143));
		btOk.setFont(new Font("Roboto", Font.BOLD, 18));
		btOk.setBounds(278, 348, 71, 40);
		panel.add(btOk);
		
		lblNewLabel = new JLabel("Entrada: R$");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 15));
		lblNewLabel.setBounds(151, 72, 82, 14);
		panel.add(lblNewLabel);
		
		lblAt = new JLabel("Detalhes das comiss\u00F5es:");
		lblAt.setFont(new Font("Roboto", Font.BOLD, 15));
		lblAt.setBounds(49, 176, 175, 14);
		panel.add(lblAt);
		
		tfDia = new JTextField();
		tfDia.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfDia.setBounds(342, 34, 114, 20);
		panel.add(tfDia);
		tfDia.setColumns(10);
		
		lblSada = new JLabel("Comiss\u00E3o: R$");
		lblSada.setForeground(new Color(255, 0, 0));
		lblSada.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSada.setFont(new Font("Roboto", Font.BOLD, 15));
		lblSada.setBounds(137, 97, 96, 14);
		panel.add(lblSada);
		
		lblLucro = new JLabel("Lucro: R$");
		lblLucro.setForeground(new Color(0, 0, 128));
		lblLucro.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLucro.setFont(new Font("Roboto", Font.BOLD, 15));
		lblLucro.setBounds(151, 151, 82, 14);
		panel.add(lblLucro);
		
		lbEntrada = new JLabel("");
		lbEntrada.setForeground(new Color(0, 100, 0));
		lbEntrada.setFont(new Font("Roboto", Font.BOLD, 15));
		lbEntrada.setBounds(237, 73, 82, 14);
		panel.add(lbEntrada);
		
		lbSaida = new JLabel("");
		lbSaida.setForeground(Color.RED);
		lbSaida.setFont(new Font("Roboto", Font.BOLD, 15));
		lbSaida.setBounds(237, 98, 82, 14);
		panel.add(lbSaida);
		
		lbLucro = new JLabel("");
		lbLucro.setForeground(new Color(0, 0, 128));
		lbLucro.setFont(new Font("Roboto", Font.BOLD, 15));
		lbLucro.setBounds(237, 151, 82, 14);
		panel.add(lbLucro);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 201, 517, 136);
		panel.add(scrollPane);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		modelo.addColumn("Funcionário");
		modelo.addColumn("Valor Total (R$)");
		modelo.addColumn("Comissão (R$)");
		
		JLabel lblSaidaConta = new JLabel("Contas: R$");
		lblSaidaConta.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSaidaConta.setForeground(Color.RED);
		lblSaidaConta.setFont(new Font("Roboto", Font.BOLD, 15));
		lblSaidaConta.setBounds(151, 122, 82, 14);
		panel.add(lblSaidaConta);
		
		lbSaidaConta = new JLabel("");
		lbSaidaConta.setForeground(Color.RED);
		lbSaidaConta.setFont(new Font("Roboto", Font.BOLD, 15));
		lbSaidaConta.setBounds(237, 123, 82, 14);
		panel.add(lbSaidaConta);
		
		controlarEvento();
		
	}
	
	public void controlarEvento() {
		
		CaixaController c = new CaixaController(tfDia, lbEntrada, lbSaida, lbLucro, lbSaidaConta, modelo);
		/**
		 * Chamar uma tela para cadastrar
		 */
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.validarCampo() == true) {
					c.fecharComissao();
					c.fecharCaixa();
					c.visualizarCaixa();
					c.listarComissao();
				}
			}

		});
		
	}

}
