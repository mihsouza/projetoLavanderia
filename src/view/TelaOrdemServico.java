package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.OrdemServicoController;
import model.Cliente;
import model.OrdemServico;
import model.Pedido;
import model.Servico;
import services.GeneratorPDF;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
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
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class TelaOrdemServico extends JFrame {

	protected JPanel contentPane;
	protected JTextField tfId;
	private JTextField tfDataEntrada;
	protected JButton btSalvar, btCancelar, btServico, btVisualizar, btExcluir, btPdf;
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
	protected JTextField tfPedido;
	private JTextField tfCliente;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaOrdemServico frame = new TelaOrdemServico();
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
	public TelaOrdemServico() {
		
		setTitle("Ordem de serviços");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaOrdemServico.class.getResource("/view/ordemServico.png")));
		setTitle("Nova Ordem de Servi\u00E7o");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(SystemColor.textHighlight);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNome = new JLabel("Nome do Cliente *");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNome.setBounds(93, 83, 139, 14);
		contentPane.add(lblNome);
		
		JLabel lblDataEntrada = new JLabel("Data de Entrada");
		lblDataEntrada.setForeground(Color.WHITE);
		lblDataEntrada.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDataEntrada.setBounds(93, 139, 118, 14);
		contentPane.add(lblDataEntrada);
		
		JLabel lblDescricao = new JLabel("Observa\u00E7\u00F5es");
		lblDescricao.setForeground(Color.WHITE);
		lblDescricao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDescricao.setBounds(93, 301, 104, 17);
		contentPane.add(lblDescricao);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblId.setBounds(93, 39, 14, 14);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(116, 38, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfDataEntrada = new JTextField();
		tfDataEntrada.setEditable(false);
		tfDataEntrada.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataEntrada.setBounds(93, 161, 104, 20);
		contentPane.add(tfDataEntrada);
		tfDataEntrada.setColumns(10);
		
		JLabel lblDataPrevista = new JLabel("Data Prevista *");
		lblDataPrevista.setForeground(Color.WHITE);
		lblDataPrevista.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDataPrevista.setBounds(221, 139, 111, 14);
		contentPane.add(lblDataPrevista);
		
		textAreaObs = new JTextArea();
		textAreaObs.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textAreaObs.setBounds(93, 326, 396, 39);
		contentPane.add(textAreaObs);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBackground(new Color(60, 179, 113));
		btSalvar.setIcon(new ImageIcon(TelaOrdemServico.class.getResource("/view/Salvar.png")));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btSalvar.setBounds(374, 514, 115, 40);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.setIcon(new ImageIcon(TelaOrdemServico.class.getResource("/view/cancel-button-white.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btCancelar.setBounds(96, 514, 115, 40);
		contentPane.add(btCancelar);
		
		tfDataPrevista = new JTextField();
		tfDataPrevista.setEditable(false);
		tfDataPrevista.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataPrevista.setColumns(10);
		tfDataPrevista.setBounds(221, 161, 111, 20);
		contentPane.add(tfDataPrevista);
		
		lblDataExecut = new JLabel("Realizado em");
		lblDataExecut.setForeground(Color.WHITE);
		lblDataExecut.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblDataExecut.setBounds(93, 386, 106, 14);
		contentPane.add(lblDataExecut);
		
		lblTotal = new JLabel("Total");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTotal.setBounds(361, 386, 35, 14);
		contentPane.add(lblTotal);
		
		lbFuncionario = new JLabel("Funcion\u00E1rio");
		lbFuncionario.setForeground(Color.WHITE);
		lbFuncionario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbFuncionario.setBounds(93, 245, 101, 14);
		contentPane.add(lbFuncionario);
		
		cbFuncionario = new JComboBox();
		cbFuncionario.setBounds(93, 270, 396, 20);
		contentPane.add(cbFuncionario);
		
		tfTotal = new JTextField();
		tfTotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTotal.setColumns(10);
		tfTotal.setBounds(406, 386, 83, 20);
		contentPane.add(tfTotal);
		
		tfDataExecut = new JTextField();
		tfDataExecut.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataExecut.setColumns(10);
		tfDataExecut.setBounds(193, 386, 139, 20);
		contentPane.add(tfDataExecut);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblStatus.setBounds(93, 192, 44, 14);
		contentPane.add(lblStatus);
		
		rbFinalizado = new JRadioButton("Finalizado");
		rbFinalizado.setForeground(Color.WHITE);
		rbFinalizado.setBackground(SystemColor.textHighlight);
		rbFinalizado.setBounds(94, 215, 109, 23);
		contentPane.add(rbFinalizado);
		
		rbEspera = new JRadioButton("Em espera");
		rbEspera.setForeground(Color.WHITE);
		rbEspera.setSelected(true);
		rbEspera.setBackground(SystemColor.textHighlight);
		rbEspera.setBounds(226, 215, 109, 23);
		contentPane.add(rbEspera);
		
		rbAtraso = new JRadioButton("Em atraso");
		rbAtraso.setForeground(Color.WHITE);
		rbAtraso.setBackground(SystemColor.textHighlight);
		rbAtraso.setBounds(367, 215, 109, 23);
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
		tfPedido.setBounds(418, 38, 55, 20);
		contentPane.add(tfPedido);
		
		JLabel lblPedido = new JLabel("N\u00BA Pedido");
		lblPedido.setForeground(Color.WHITE);
		lblPedido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPedido.setBounds(337, 39, 71, 14);
		contentPane.add(lblPedido);
		
		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCliente.setColumns(10);
		tfCliente.setBounds(93, 108, 377, 20);
		contentPane.add(tfCliente);
		
		btServico = new JButton("Adicionar");
		btServico.setBackground(new Color(218, 165, 32));
		btServico.setForeground(Color.WHITE);
		btServico.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		btServico.setBounds(394, 450, 95, 30);
		contentPane.add(btServico);
		
		btVisualizar = new JButton("Buscar");
		btVisualizar.setBackground(new Color(218, 165, 32));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btVisualizar.setBounds(196, 37, 89, 23);
		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setBackground(new Color(218, 165, 32));
		btExcluir.setIcon(new ImageIcon(TelaOrdemServico.class.getResource("/view/Excluir.png")));
		btExcluir.setForeground(Color.WHITE);
		btExcluir.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btExcluir.setBounds(93, 450, 115, 40);
		contentPane.add(btExcluir);
		
		btPdf = new JButton("PDF");
		btPdf.setForeground(Color.WHITE);
		btPdf.setBackground(new Color(255, 51, 0));
		btPdf.setIcon(new ImageIcon(TelaOrdemServico.class.getResource("/view/PDF.png")));
		btPdf.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btPdf.setBounds(221, 450, 115, 40);
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
				if(tfId.isEditable()) {
					if(tfId.getText() .trim().equals("")){
						TelaMensagem frame = new TelaMensagem();
						frame.setUndecorated(true);
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						frame.lblAviso.setText("O campo 'ID' não pode estar vazio!");
					}else {
						o.visualizar();
					}
				}else
					if(tfPedido.getText() .trim().equals("")){
						TelaMensagem frame = new TelaMensagem();
						frame.setUndecorated(true);
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						frame.lblAviso.setText("O campo 'Pedido' não pode estar vazio!");
					}else {
						receberPedido(tfPedido.getText());
					}
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
					if(tfId.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "O pedido deve estar salvo");
					}else {
						frame.setUndecorated(true);
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						frame.receberOrdem(tfId.getText());
					}
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
