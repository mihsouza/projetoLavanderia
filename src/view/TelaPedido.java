package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PedidoController;
import model.Cliente;
import model.Pedido;
import model.Servico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class TelaPedido extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfDataEntrada;
	private JButton btSalvar, btCancelar;
	private JTextArea textAreaObs;
	private JTextField tfDataPrevista;
	private JLabel lblDataSaida;
	private JLabel lblTotal;
	private JLabel lbAtendente;
	private JTextField tfTotal;
	private JTextField tfDataSaida;
	private JComboBox cbAtendente;
	private JComboBox cbCliente;
	private JRadioButton rbAtraso;
	private JRadioButton rbEspera;
	private JRadioButton rbFinalizado;
	private JButton btVisualizar, btOrdem;
	private JButton btExcluir;

	/**
	 * Executar a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPedido frame = new TelaPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criando o frame.
	 */
	public TelaPedido() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Cliente*:");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNome.setBounds(39, 90, 53, 14);
		contentPane.add(lblNome);
		
		JLabel lblDataEntrada = new JLabel("Data entrada:");
		lblDataEntrada.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDataEntrada.setBounds(18, 127, 74, 14);
		contentPane.add(lblDataEntrada);
		
		JLabel lblDescricao = new JLabel("Observa\u00E7\u00F5es:");
		lblDescricao.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDescricao.setBounds(18, 242, 74, 14);
		contentPane.add(lblDescricao);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(78, 47, 14, 14);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(101, 44, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfDataEntrada = new JTextField();
		tfDataEntrada.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataEntrada.setBounds(102, 124, 139, 20);
		contentPane.add(tfDataEntrada);
		tfDataEntrada.setColumns(10);
		
		JLabel lblDataPrevista = new JLabel("Data prevista*:");
		lblDataPrevista.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDataPrevista.setBounds(263, 127, 96, 14);
		contentPane.add(lblDataPrevista);
		
		textAreaObs = new JTextArea();
		textAreaObs.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textAreaObs.setBounds(101, 240, 396, 39);
		contentPane.add(textAreaObs);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setForeground(new Color(34, 139, 34));
		btSalvar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btSalvar.setBounds(324, 352, 89, 23);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setForeground(new Color(255, 0, 0));
		btCancelar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btCancelar.setBounds(422, 352, 89, 23);
		contentPane.add(btCancelar);
		
		tfDataPrevista = new JTextField();
		tfDataPrevista.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataPrevista.setColumns(10);
		tfDataPrevista.setBounds(358, 124, 139, 20);
		contentPane.add(tfDataPrevista);
		
		lblDataSaida = new JLabel("Data de sa\u00EDda:");
		lblDataSaida.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDataSaida.setBounds(16, 297, 76, 14);
		contentPane.add(lblDataSaida);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTotal.setBounds(362, 297, 42, 14);
		contentPane.add(lblTotal);
		
		lbAtendente = new JLabel("Atendente:");
		lbAtendente.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbAtendente.setBounds(33, 203, 59, 14);
		contentPane.add(lbAtendente);
		
		cbAtendente = new JComboBox();
		cbAtendente.setBounds(101, 200, 396, 20);
		contentPane.add(cbAtendente);
		
		tfTotal = new JTextField();
		tfTotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTotal.setColumns(10);
		tfTotal.setBounds(414, 294, 83, 20);
		contentPane.add(tfTotal);
		
		tfDataSaida = new JTextField();
		tfDataSaida.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataSaida.setColumns(10);
		tfDataSaida.setBounds(101, 294, 139, 20);
		contentPane.add(tfDataSaida);
		
		cbCliente = new JComboBox();
		cbCliente.setBounds(102, 87, 395, 20);
		contentPane.add(cbCliente);
		
		JLabel label = new JLabel("Status:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Times New Roman", Font.BOLD, 13));
		label.setBounds(48, 166, 44, 14);
		contentPane.add(label);
		
		rbFinalizado = new JRadioButton("Finalizado");
		rbFinalizado.setBackground(SystemColor.menu);
		rbFinalizado.setBounds(101, 162, 109, 23);
		contentPane.add(rbFinalizado);
		
		rbEspera = new JRadioButton("Em espera");
		rbEspera.setSelected(true);
		rbEspera.setBackground(SystemColor.menu);
		rbEspera.setBounds(245, 162, 109, 23);
		contentPane.add(rbEspera);
		
		rbAtraso = new JRadioButton("Em atraso");
		rbAtraso.setBackground(SystemColor.menu);
		rbAtraso.setBounds(388, 162, 109, 23);
		contentPane.add(rbAtraso);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbFinalizado);
		bg.add(rbEspera);
		bg.add(rbAtraso);
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setForeground(Color.BLUE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btVisualizar.setBounds(125, 352, 89, 23);
		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setForeground(Color.BLACK);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btExcluir.setBounds(225, 352, 89, 23);
		contentPane.add(btExcluir);
		
		btOrdem = new JButton("Gerar OS");
		btOrdem.setForeground(Color.BLUE);
		btOrdem.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btOrdem.setBounds(408, 27, 89, 23);
		contentPane.add(btOrdem);
		
		controlarEventos();

	}
	
	
	public void controlarEventos() {
		PedidoController p = new PedidoController(tfId, tfDataEntrada, textAreaObs, tfDataPrevista, tfTotal, tfDataSaida, cbAtendente, cbCliente,
			rbAtraso, rbEspera, rbFinalizado);
		p.carregarAtendente();
		p.carregarCliente();
		
		/**
		 * Ações do botão salvar
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = p.validarCampo();
				if(valido) {
					p.verificarAcao();
				}
			}
		});
		
		/**
		 * Ação do botão visualizar.
		 */
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					p.visualizar();
				}
		});
		
		/**
		 * Ação do botão excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					p.excluir();
					TelaPedido.this.setVisible(false);
				}
		});
		
		/**
		 * Ações do botão Gerar OS
		 */
		btOrdem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaOrdemServico frame = new TelaOrdemServico();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.receberPedido(tfId.getText());
				}
		});
		
		/**
		 * Ações do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaPedido.this.setVisible(false);
				}
		});
	}
}
