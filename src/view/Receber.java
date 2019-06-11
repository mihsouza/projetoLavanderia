package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PedidoController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class Receber extends JFrame {

	private JPanel contentPane;
	private JTextField tfCliente;
	private JTextField tfStatus;
	private JTextField tfPreco;
	private JTextField tfPago;
	private JTextField tfTroco;
	private JTextField tfPedido;
	private JButton btAtualizar, btReceber;
	private JLabel lblTitulo;
	private JButton btVoltar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Receber frame = new Receber();
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
	public Receber() {
		setTitle("Receber"); //Rafa: Alterar nome da tela
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png"))); //Rafa: Alterar ícone da tela
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 435);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPedidoN = new JLabel("N\u00BA Pedido:");
		lblPedidoN.setForeground(Color.WHITE);
		lblPedidoN.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblPedidoN.setBounds(23, 95, 76, 27);
		contentPane.add(lblPedidoN);
		
		tfPedido = new JTextField();
		tfPedido.setBounds(105, 97, 40, 25);
		contentPane.add(tfPedido);
		tfPedido.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCliente.setBounds(47, 160, 52, 27);
		contentPane.add(lblCliente);
		
		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setColumns(10);
		tfCliente.setBounds(105, 164, 333, 25);
		contentPane.add(tfCliente);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblStatus.setBounds(47, 200, 52, 27);
		contentPane.add(lblStatus);
		
		tfStatus = new JTextField();
		tfStatus.setEditable(false);
		tfStatus.setColumns(10);
		tfStatus.setBounds(105, 204, 168, 25);
		contentPane.add(tfStatus);
		
		JLabel lblStatusR = new JLabel("Pre\u00E7o: R$");
		lblStatusR.setForeground(Color.WHITE);
		lblStatusR.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblStatusR.setBounds(33, 254, 66, 27);
		contentPane.add(lblStatusR);
		
		tfPreco = new JTextField();
		tfPreco.setEditable(false);
		tfPreco.setColumns(10);
		tfPreco.setBounds(105, 258, 137, 25);
		contentPane.add(tfPreco);
		
		JLabel lblPagoR = new JLabel("Pago: R$");
		lblPagoR.setForeground(Color.WHITE);
		lblPagoR.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblPagoR.setBounds(284, 257, 62, 27);
		contentPane.add(lblPagoR);
		
		tfPago = new JTextField();
		tfPago.setColumns(10);
		tfPago.setBounds(350, 261, 120, 25);
		contentPane.add(tfPago);
		
		btReceber = new JButton("Receber");
		btReceber.setBounds(24, 345, 135, 40);
		btReceber.setIcon(new ImageIcon(Receber.class.getResource("/view/contas-icon-white.png")));
		btReceber.setForeground(Color.WHITE);
		btReceber.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btReceber.setBackground(new Color(60, 179, 113));
		contentPane.add(btReceber);
		
		JLabel lblTrocoR = new JLabel("Troco: R$");
		lblTrocoR.setForeground(Color.WHITE);
		lblTrocoR.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTrocoR.setBounds(278, 295, 68, 27);
		contentPane.add(lblTrocoR);
		
		tfTroco = new JTextField();
		tfTroco.setEditable(false);
		tfTroco.setColumns(10);
		tfTroco.setBounds(350, 299, 120, 25);
		contentPane.add(tfTroco);
		
		btAtualizar = new JButton("Buscar");
		btAtualizar.setBounds(153, 90, 120, 40);
		btAtualizar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Visualizar.png")));
		btAtualizar.setForeground(Color.WHITE);
		btAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btAtualizar.setBackground(new Color(218, 165, 32));
		contentPane.add(btAtualizar);
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/fechar_caixa.png")));
		lblImagem.setForeground(Color.WHITE);
		lblImagem.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblImagem.setBounds(39, 18, 60, 50);
		contentPane.add(lblImagem);
		
		
		ImageIcon novoCargo = new ImageIcon(TelaCargo.class.getResource("/view/fechar_caixa.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lblImagem.getWidth(), lblImagem.getHeight(), Image.SCALE_SMOOTH);	
		lblImagem.setIcon(new ImageIcon(novoCa));
		
		lblTitulo = new JLabel("Receber");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setBounds(112, 18, 232, 56);
		contentPane.add(lblTitulo);
		
		btVoltar = new JButton("Voltar");
		btVoltar.setIcon(new ImageIcon(ListaFuncionario.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(0, 139, 139));
		btVoltar.setBounds(440, 18, 120, 40);
		contentPane.add(btVoltar);
		
		controlarEventos();
	}
	
	public void controlarEventos() {
		PedidoController p = new PedidoController(tfCliente, tfStatus, tfPreco, tfPago, tfTroco, tfPedido);
		
		/**
		 * Ações do botão atualizar
		 */
		btAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfPedido.getText() .trim().equals("")){
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("O campo 'Pedido' não pode estar vazio!");
				}else {
					p.atualizar();
				}
			}
		});
		
		
		/**
		 * Ação do botão receber.
		 */
		btReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfCliente.getText() .trim().equals("")){
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("Selecione ao menos um pedido!");
				}else {
					p.receber();
					p.calcularTroco();
				}
				}
		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receber.this.setVisible(false);
				TelaMenu t = new TelaMenu();
				t.setVisible(true);
			}
		});
		
	}

}
