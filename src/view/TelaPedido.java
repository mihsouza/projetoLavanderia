package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.FuncionarioController;
import controller.PedidoController;

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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class TelaPedido extends JFrame {

	protected JPanel contentPane;
	protected JButton btVoltar, btBuscar, btSalvar, btExcluir, btReceber;
	protected JPanel panel;
	protected JLabel lbTitulo, lbNome, lbAtendente, lbImagem;
	protected JTextField tfSaida;
	public JTextField tfId;
	private JTextField tfPrevisto;
	private JLabel lbStatus;
	private JComboBox cbCliente;
	private JLabel lblFuncionrio;
	private JComboBox cbFuncionario, cbStatus, cbAtendente;
	private JLabel lblDataSada;
	private JTextField tfTotal;
	private JLabel lblTotal;
	private JButton btNovo;
	public JTextArea taObs;
	private JLabel lblSaldo;
	private JTextField tfSaldo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPedido frame = new TelaPedido();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPedido() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 637, 591);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(102, 205, 170));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Novo pedido");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(127, 21, 297, 36);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(507, 4, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/ordem_servico.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(21, 11, 96, 92);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/ordem_servico.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		lbNome = new JLabel("Cliente*:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setFont(new Font("Roboto", Font.BOLD, 15));
		lbNome.getHorizontalAlignment();
		lbNome.setBounds(21, 144, 81, 20);
		panel.add(lbNome);
		
		lbAtendente = new JLabel("Atendente*:");
		lbAtendente.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAtendente.setFont(new Font("Roboto", Font.BOLD, 15));
		lbAtendente.setBounds(0, 231, 102, 14);
		panel.add(lbAtendente);
		
		tfSaida = new JTextField();
		tfSaida.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfSaida.setBounds(122, 364, 114, 20);
		panel.add(tfSaida);
		tfSaida.setColumns(10);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(318, 491, 120, 40);
		//panel.add(btExcluir);
		btExcluir.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/lixeira.png")));
		btExcluir.setForeground(new Color(178, 34, 34));
		btExcluir.setFont(new Font("Roboto", Font.BOLD, 18));
		btExcluir.setBackground(new Color(255, 255, 255));
		btExcluir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34)));
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(448, 491, 120, 40);
		panel.add(btSalvar);
		btSalvar.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/salvar.png")));
		btSalvar.setForeground(new Color(72, 61, 139));
		btSalvar.setFont(new Font("Roboto", Font.BOLD, 18));
		btSalvar.setBackground(new Color(255, 255, 255));
		btSalvar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		
		btBuscar = new JButton("Ver");
		btBuscar.setBounds(342, 75, 120, 40);
//		panel.add(btBuscar);
		btBuscar.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/Visualizar.png")));
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
		
		tfPrevisto = new JTextField();
		tfPrevisto.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfPrevisto.setColumns(10);
		tfPrevisto.setBounds(122, 183, 114, 20);
		panel.add(tfPrevisto);
		
		JLabel lbTelefone = new JLabel("Entrega*:");
		lbTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTelefone.setFont(new Font("Roboto", Font.BOLD, 15));
		lbTelefone.setBounds(0, 183, 102, 20);
		panel.add(lbTelefone);
		
		lbStatus = new JLabel("Status:");
		lbStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lbStatus.setFont(new Font("Roboto", Font.BOLD, 15));
		lbStatus.setBounds(21, 269, 81, 20);
		panel.add(lbStatus);
		
		cbAtendente = new JComboBox();
		cbAtendente.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbAtendente.setBounds(122, 228, 443, 20);
		panel.add(cbAtendente);
		
		cbCliente = new JComboBox();
		cbCliente.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbCliente.setBounds(122, 144, 443, 20);
		panel.add(cbCliente);
		
		lblFuncionrio = new JLabel("Funcion\u00E1rio*:");
		lblFuncionrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncionrio.setFont(new Font("Roboto", Font.BOLD, 15));
		lblFuncionrio.setBounds(0, 321, 102, 14);
		panel.add(lblFuncionrio);
		
		cbFuncionario = new JComboBox();
		cbFuncionario.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbFuncionario.setBounds(122, 319, 443, 20);
		panel.add(cbFuncionario);
		
		lblDataSada = new JLabel("Data sa\u00EDda:");
		lblDataSada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataSada.setFont(new Font("Roboto", Font.BOLD, 15));
		lblDataSada.setBounds(10, 364, 92, 20);
		panel.add(lblDataSada);
		
		tfTotal = new JTextField();
		tfTotal.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfTotal.setColumns(10);
		tfTotal.setBounds(451, 430, 114, 20);
		panel.add(tfTotal);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Roboto", Font.BOLD, 15));
		lblTotal.setBounds(349, 430, 92, 20);
		panel.add(lblTotal);
		
//		JButton btPdf = new JButton("PDF");
//		btPdf.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/PDF.png")));
//		btPdf.setForeground(new Color(220, 20, 60));
//		btPdf.setFont(new Font("Roboto", Font.BOLD, 18));
//		btPdf.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(220, 20, 60), new Color(220, 20, 60), new Color(220, 20, 60), new Color(220, 20, 60)));
//		btPdf.setBackground(Color.WHITE);
//		btPdf.setBounds(318, 491, 120, 40);
//		panel.add(btPdf);
		
		btNovo = new JButton("Servi\u00E7o");
		btNovo.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/novo.png")));
		btNovo.setForeground(new Color(50, 205, 50));
		btNovo.setFont(new Font("Roboto", Font.BOLD, 18));
		btNovo.setBackground(new Color(255, 255, 255));
		btNovo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(50, 205, 50), new Color(50, 205, 50), new Color(50, 205, 50), new Color(50, 205, 50)));
		btNovo.setBounds(445, 364, 120, 40);
		panel.add(btNovo);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em fila", "Em execu\u00E7\u00E3o", "Pronto", "Entregue"}));
		cbStatus.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbStatus.setBounds(122, 270, 221, 20);
		panel.add(cbStatus);
		
		btReceber = new JButton("Receber");
		btReceber.setForeground(new Color(47, 79, 79));
		btReceber.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/caixa_botao.png")));
		btReceber.setFont(new Font("Roboto", Font.BOLD, 18));
		btReceber.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79)));
		btReceber.setBackground(Color.WHITE);
		btReceber.setBounds(21, 491, 120, 40);
		panel.add(btReceber);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		lblObservao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblObservao.setFont(new Font("Dialog", Font.BOLD, 15));
		lblObservao.setBounds(10, 414, 92, 20);
		panel.add(lblObservao);
		
		taObs = new JTextArea();
		taObs.setWrapStyleWord(true);
		taObs.setLineWrap(true);
		taObs.setBounds(122, 414, 264, 66);
		panel.add(taObs);
		
		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setFont(new Font("Roboto", Font.BOLD, 15));
		lblSaldo.setBounds(331, 183, 102, 20);
		panel.add(lblSaldo);
		
		tfSaldo = new JTextField();
		tfSaldo.setText("0.0");
		tfSaldo.setEditable(false);
		tfSaldo.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfSaldo.setColumns(10);
		tfSaldo.setBounds(451, 184, 114, 20);
		panel.add(tfSaldo);
		
		controlarEvento();
		
	}
	
	public void controlarEvento() {
		PedidoController c = new PedidoController(tfSaida, tfId, tfPrevisto, cbCliente, cbFuncionario, cbStatus, tfTotal, cbAtendente, taObs, tfSaldo);
		c.carregarAtendente();
		c.carregarCliente();
		c.carregarFuncionario();
		/**
		 * Fechar o frame
		 */
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPedido.this.setVisible(false);
			}

		});
		
		/**
		 * inserir um novo cargo
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.validarCampo() == true) {
					c.verificarSaldo();
					c.inserir();
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
					TelaPedido.this.setVisible(false);
				}
			}

		});
		
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
		
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaServicoOrdem frame = new TelaServicoOrdem();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.tfPedido.setText(tfId.getText());
				frame.tfSaldo.setText(tfSaldo.getText());
			}

		});
		
		btReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController p = new PedidoController(tfId);
				p.receberPelaTela();
			}

		});
	}
}
