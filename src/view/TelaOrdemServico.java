package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrdemServicoController;
import model.Cliente;
import model.OrdemServico;
import model.Pedido;
import model.Servico;
import services.GeneratorPDF;

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
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class TelaOrdemServico extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfDataEntrada;
	private JButton btSalvar, btCancelar, btServico, btVisualizar, btExcluir, btPdf;
	private JTextArea textAreaObs;
	private JTextField tfDataPrevista;
	private JLabel lblDataExecut;
	private JLabel lblTotal;
	private JLabel lbFuncionario;
	private JTextField tfTotal;
	private JTextField tfDataExecut;
	private JComboBox cbFuncionario;
	private JRadioButton rbAtraso;
	private JRadioButton rbEspera;
	private JRadioButton rbFinalizado;
	private JTextField tfPedido;
	private JTextField tfCliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOrdemServico frame = new TelaOrdemServico();
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
	public TelaOrdemServico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 457);
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
		tfDataEntrada.setEditable(false);
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
		btSalvar.setBounds(323, 384, 89, 23);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setForeground(new Color(255, 0, 0));
		btCancelar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btCancelar.setBounds(422, 384, 89, 23);
		contentPane.add(btCancelar);
		
		tfDataPrevista = new JTextField();
		tfDataPrevista.setEditable(false);
		tfDataPrevista.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataPrevista.setColumns(10);
		tfDataPrevista.setBounds(358, 124, 139, 20);
		contentPane.add(tfDataPrevista);
		
		lblDataExecut = new JLabel("Realizado em:");
		lblDataExecut.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDataExecut.setBounds(18, 297, 76, 14);
		contentPane.add(lblDataExecut);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTotal.setBounds(362, 297, 42, 14);
		contentPane.add(lblTotal);
		
		lbFuncionario = new JLabel("Funcion\u00E1rio:");
		lbFuncionario.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lbFuncionario.setBounds(21, 203, 71, 14);
		contentPane.add(lbFuncionario);
		
		cbFuncionario = new JComboBox();
		cbFuncionario.setBounds(101, 200, 396, 20);
		contentPane.add(cbFuncionario);
		
		tfTotal = new JTextField();
		tfTotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTotal.setColumns(10);
		tfTotal.setBounds(414, 294, 83, 20);
		contentPane.add(tfTotal);
		
		tfDataExecut = new JTextField();
		tfDataExecut.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataExecut.setColumns(10);
		tfDataExecut.setBounds(101, 294, 139, 20);
		contentPane.add(tfDataExecut);
		
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
		
		tfPedido = new JTextField();
		tfPedido.setEditable(false);
		tfPedido.setColumns(10);
		tfPedido.setBounds(442, 44, 55, 20);
		contentPane.add(tfPedido);
		
		JLabel lblPedido = new JLabel("Pedido:");
		lblPedido.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPedido.setBounds(388, 47, 44, 14);
		contentPane.add(lblPedido);
		
		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCliente.setColumns(10);
		tfCliente.setBounds(102, 87, 395, 20);
		contentPane.add(tfCliente);
		
		btServico = new JButton("+");
		btServico.setForeground(new Color(0, 0, 139));
		btServico.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		btServico.setBounds(442, 325, 55, 39);
		contentPane.add(btServico);
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setForeground(Color.BLUE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btVisualizar.setBounds(124, 384, 89, 23);
		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setForeground(Color.BLACK);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btExcluir.setBounds(224, 384, 89, 23);
		contentPane.add(btExcluir);
		
		btPdf = new JButton("PDF");
		btPdf.setForeground(Color.BLUE);
		btPdf.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btPdf.setBounds(25, 384, 89, 23);
		contentPane.add(btPdf);
		
		controlarEventos();
	}

	/**
	 * Carregar informaçõepara gerar uma ordem de serviço.
	 * Estas informações não podem ser editadas com excessão da observação.
	 * @param pedido
	 */
	public void receberPedido(String pedido) {
		tfPedido.setText(pedido);
		int id = Integer.parseInt(tfPedido.getText());
		services.BD b = new services.BD();
		b.getConnection();
		try {
			String sql = "SELECT C.CLIENTE, P.DATAENTRADA, P.DATAPREVISTA, P.OBSERVACAO , P.STATUS "
					+ "FROM CLIENTE C, PEDIDO P "
					+ "WHERE C.ID = P.CLIENTE "
					+ "AND P.ID = " + id;
			b.st = b.con.createStatement();
			b.rs = b.st.executeQuery(sql);
			while(b.rs.next()) {
				tfCliente.setText((b.rs.getString("CLIENTE")));
				tfDataEntrada.setText((b.rs.getString("DATAENTRADA")));
				tfDataPrevista.setText((b.rs.getString("DATAPREVISTA")));
				textAreaObs.setText((b.rs.getString("OBSERVACAO")));
				if (b.rs.getString("STATUS").equals("Finalizado")) {
					rbFinalizado.setSelected(true);
				} else if (b.rs.getString("STATUS").equals("Em espera")) {
					rbEspera.setSelected(true);
				} else {
					rbAtraso.setSelected(true);
				}
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}
	

	public void controlarEventos() {
		OrdemServicoController o = new OrdemServicoController(tfId, tfDataEntrada, textAreaObs,
			tfDataPrevista, tfTotal, tfDataExecut, cbFuncionario, 
			tfPedido, tfCliente, rbAtraso, rbEspera,
			rbFinalizado);

		o.carregarFuncionario();
		
		/**
		 * Ações do botão salvar
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = o.validarCampo();
				if(valido) {
					o.verificarAcao();
				}
			}
		});
		
		/**
		 * Ação do botão visualizar.
		 */
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					o.visualizar();
				}
		});
		
		/**
		 * Ação do botão excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					o.excluir();
					TelaOrdemServico.this.setVisible(false);
				}
		});
		
		/**
		 * Ação do botão "+".
		 */
		btServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaServicoOs frame = new TelaServicoOs();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.receberOrdem(tfId.getText());
				}
		});
		
		/**
		 * Ações do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaOrdemServico.this.setVisible(false);
				}
		});
		
		/**
		 * Ações do botão PDF
		 */
		btPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GeneratorPDF g = new GeneratorPDF();
					g.pdfOs(tfId.getText(), tfCliente.getText(), tfDataEntrada.getText(), tfDataPrevista.getText(), cbFuncionario.getSelectedItem().toString(), 
							textAreaObs.getText(), tfPedido.getText());
				}
		});
	}
}
