package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CargoController;
import controller.ContasController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TelaMenu extends JFrame {

	private JPanel contentPane;
	private JButton btCargo, btServico, btPedido, btOs, btFuncionario, btCliente;
	private JButton btFinanceiro;
	private JButton btCaixa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu frame = new TelaMenu();
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
	public TelaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblMenu.setBounds(326, 11, 44, 22);
		contentPane.add(lblMenu);
		
		btCargo = new JButton("Cargos");
		btCargo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btCargo.setBounds(27, 95, 138, 59);
		contentPane.add(btCargo);
		
		btServico = new JButton("Servi\u00E7os");
		btServico.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btServico.setBounds(280, 95, 138, 59);
		contentPane.add(btServico);
		
		btCliente = new JButton("Clientes");
		btCliente.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btCliente.setBounds(540, 95, 138, 59);
		contentPane.add(btCliente);
		
		btFuncionario = new JButton("Funcionarios");
		btFuncionario.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btFuncionario.setBounds(27, 215, 138, 59);
		contentPane.add(btFuncionario);
		
		btPedido = new JButton("Pedidos");
		btPedido.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btPedido.setBounds(280, 215, 138, 59);
		contentPane.add(btPedido);
		
		btOs = new JButton("OS");
		btOs.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btOs.setBounds(540, 215, 138, 59);
		contentPane.add(btOs);
		
		btFinanceiro = new JButton("Financeiro");
		btFinanceiro.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btFinanceiro.setBounds(27, 317, 138, 59);
		contentPane.add(btFinanceiro);
		
		btCaixa = new JButton("Fechar Caixa");
		btCaixa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btCaixa.setBounds(280, 317, 138, 59);
		contentPane.add(btCaixa);
		
		controlarEventos();
	}
	
	/**
	 * Método para definir as ações do botão da tela.
	 */
	public void controlarEventos() {

		btPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPedido frame = new ListaPedido();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaFuncionario frame = new ListaFuncionario();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCliente frame = new ListaCliente();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});		

		btOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaOrdemServico frame = new ListaOrdemServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCargo frame = new ListaCargo();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaServico frame = new ListaServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaContas frame = new ListaContas();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		
		btCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContasController c = new ContasController();
				c.fechamento();
			}
		});
	}

}
