package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PedidoController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Receber extends JFrame {

	private JPanel contentPane;
	private JTextField tfCliente;
	private JTextField tfStatus;
	private JTextField tfPreco;
	private JTextField tfPago;
	private JTextField tfTroco;
	private JTextField tfPedido;
	private JButton btAtualizar, btReceber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receber frame = new Receber();
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
	public Receber() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPedidoN = new JLabel("Pedido n\u00BA:");
		lblPedidoN.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPedidoN.setBounds(10, 27, 53, 14);
		contentPane.add(lblPedidoN);
		
		tfPedido = new JTextField();
		tfPedido.setBounds(73, 24, 39, 20);
		contentPane.add(tfPedido);
		tfPedido.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCliente.setBounds(19, 81, 44, 14);
		contentPane.add(lblCliente);
		
		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setColumns(10);
		tfCliente.setBounds(73, 78, 333, 20);
		contentPane.add(tfCliente);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblStatus.setBounds(24, 121, 39, 14);
		contentPane.add(lblStatus);
		
		tfStatus = new JTextField();
		tfStatus.setEditable(false);
		tfStatus.setColumns(10);
		tfStatus.setBounds(73, 118, 168, 20);
		contentPane.add(tfStatus);
		
		JLabel lblStatusR = new JLabel("Pre\u00E7o:      R$");
		lblStatusR.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblStatusR.setBounds(26, 161, 68, 14);
		contentPane.add(lblStatusR);
		
		tfPreco = new JTextField();
		tfPreco.setEditable(false);
		tfPreco.setColumns(10);
		tfPreco.setBounds(104, 158, 137, 20);
		contentPane.add(tfPreco);
		
		JLabel lblPagoR = new JLabel("Pago:      R$");
		lblPagoR.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPagoR.setBounds(33, 217, 61, 14);
		contentPane.add(lblPagoR);
		
		tfPago = new JTextField();
		tfPago.setColumns(10);
		tfPago.setBounds(104, 214, 137, 20);
		contentPane.add(tfPago);
		
		btReceber = new JButton("Receber");
		btReceber.setBounds(292, 161, 95, 45);
		contentPane.add(btReceber);
		
		JLabel lblTrocoR = new JLabel("Troco:      R$");
		lblTrocoR.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTrocoR.setBounds(26, 257, 68, 14);
		contentPane.add(lblTrocoR);
		
		tfTroco = new JTextField();
		tfTroco.setEditable(false);
		tfTroco.setColumns(10);
		tfTroco.setBounds(104, 254, 137, 20);
		contentPane.add(tfTroco);
		
		btAtualizar = new JButton("Atualizar");
		btAtualizar.setBounds(133, 23, 89, 23);
		contentPane.add(btAtualizar);
		
		controlarEventos();
	}
	
	public void controlarEventos() {
		PedidoController p = new PedidoController(tfCliente, tfStatus, tfPreco, tfPago, tfTroco, tfPedido);
		
		/**
		 * Ações do botão atualizar
		 */
		btAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.atualizar();
			}
		});
		
		
		/**
		 * Ação do botão receber.
		 */
		btReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					p.receber();
					p.calcularTroco();
				}
		});
		
	}

}
