package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import controller.PedidoController;
import services.GeneratorPDF;
import java.awt.Window.Type;
import java.awt.SystemColor;

public class TelaPedido extends JFrame {

	protected JPanel contentPane;
	protected JTextField tfId;
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
	protected JButton btVisualizar, btOrdem;
	protected JButton btExcluir, btPdf;
	protected JLabel lblImagem;
	protected JLabel lblTitulo;

	/**
	 * Executar a aplicação.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaPedido frame = new TelaPedido();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Criando o frame.
	 */
	public TelaPedido() {
		
		setTitle("Novo Pedido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png"))); //Rafa: Alterar ícone da tela
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setBackground(SystemColor.textHighlight);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNome = new JLabel("Cliente* :");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNome.setBounds(46, 159, 53, 14);
		contentPane.add(lblNome);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		lblDataEntrada.setForeground(new Color(255, 255, 255));
		lblDataEntrada.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDataEntrada.setBounds(25, 196, 74, 14);
		contentPane.add(lblDataEntrada);
		
		JLabel lblDescricao = new JLabel("Observa\u00E7\u00F5es:");
		lblDescricao.setForeground(new Color(255, 255, 255));
		lblDescricao.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDescricao.setBounds(25, 311, 74, 14);
		contentPane.add(lblDescricao);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(new Color(255, 255, 255));
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblId.setBounds(85, 116, 17, 15);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(108, 113, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfDataEntrada = new JTextField();
		tfDataEntrada.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataEntrada.setBounds(109, 193, 139, 20);
		contentPane.add(tfDataEntrada);
		tfDataEntrada.setColumns(10);
		
		JLabel lblDataPrevista = new JLabel("Data Prevista* :");
		lblDataPrevista.setForeground(new Color(255, 255, 255));
		lblDataPrevista.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDataPrevista.setBounds(270, 196, 96, 14);
		contentPane.add(lblDataPrevista);
		
		textAreaObs = new JTextArea();
		textAreaObs.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textAreaObs.setBounds(108, 309, 396, 39);
		contentPane.add(textAreaObs);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBackground(new Color(60, 179, 113));
		btSalvar.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/Salvar.png")));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btSalvar.setBounds(401, 455, 120, 40);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/cancel-button-white.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btCancelar.setBounds(271, 455, 120, 40);
		contentPane.add(btCancelar);
		
		tfDataPrevista = new JTextField();
		tfDataPrevista.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataPrevista.setColumns(10);
		tfDataPrevista.setBounds(365, 193, 139, 20);
		contentPane.add(tfDataPrevista);
		
		lblDataSaida = new JLabel("Data de sa\u00EDda:");
		lblDataSaida.setForeground(new Color(255, 255, 255));
		lblDataSaida.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDataSaida.setBounds(23, 366, 76, 14);
		contentPane.add(lblDataSaida);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTotal.setBounds(369, 366, 42, 14);
		contentPane.add(lblTotal);
		
		lbAtendente = new JLabel("Atendente:");
		lbAtendente.setForeground(new Color(255, 255, 255));
		lbAtendente.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lbAtendente.setBounds(25, 272, 74, 14);
		contentPane.add(lbAtendente);
		
		cbAtendente = new JComboBox();
		cbAtendente.setBounds(108, 269, 396, 20);
		contentPane.add(cbAtendente);
		
		tfTotal = new JTextField();
		tfTotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTotal.setColumns(10);
		tfTotal.setBounds(421, 363, 83, 20);
		contentPane.add(tfTotal);
		
		tfDataSaida = new JTextField();
		tfDataSaida.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfDataSaida.setColumns(10);
		tfDataSaida.setBounds(108, 363, 139, 20);
		contentPane.add(tfDataSaida);
		
		cbCliente = new JComboBox();
		cbCliente.setBounds(109, 156, 395, 20);
		contentPane.add(cbCliente);
		
		JLabel label = new JLabel("Status:");
		label.setForeground(new Color(255, 255, 255));
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Segoe UI", Font.BOLD, 12));
		label.setBounds(55, 235, 44, 14);
		contentPane.add(label);
		
		rbFinalizado = new JRadioButton("Finalizado");
		rbFinalizado.setForeground(new Color(255, 255, 255));
		rbFinalizado.setBackground(SystemColor.textHighlight);
		rbFinalizado.setBounds(108, 231, 109, 23);
		contentPane.add(rbFinalizado);
		
		rbEspera = new JRadioButton("Em espera");
		rbEspera.setForeground(new Color(255, 255, 255));
		rbEspera.setSelected(true);
		rbEspera.setBackground(SystemColor.textHighlight);
		rbEspera.setBounds(252, 231, 109, 23);
		contentPane.add(rbEspera);
		
		rbAtraso = new JRadioButton("Em atraso");
		rbAtraso.setForeground(new Color(255, 255, 255));
		rbAtraso.setBackground(SystemColor.textHighlight);
		rbAtraso.setBounds(395, 231, 109, 23);
		contentPane.add(rbAtraso);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbFinalizado);
		bg.add(rbEspera);
		bg.add(rbAtraso);
		
		btVisualizar = new JButton("Buscar");
		btVisualizar.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(96, 204, 204));
		btVisualizar.setBounds(190, 91, 132, 40);
//		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setBackground(new Color(218, 165, 32));
		btExcluir.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/Excluir.png")));
		btExcluir.setForeground(Color.WHITE);
		btExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btExcluir.setBounds(21, 455, 115, 40);
//		contentPane.add(btExcluir);
		
		btOrdem = new JButton("Gerar OS");
		btOrdem.setBackground(new Color(218, 165, 32));
		btOrdem.setForeground(Color.WHITE);
		btOrdem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btOrdem.setBounds(415, 25, 89, 23);
		contentPane.add(btOrdem);
		
		btPdf = new JButton("PDF");
		btPdf.setBackground(new Color(255, 102, 102));
		btPdf.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/PDF.png")));
		btPdf.setForeground(Color.WHITE);
		btPdf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btPdf.setBounds(146, 455, 115, 40);
		contentPane.add(btPdf);
		
		lblImagem = new JLabel("");
		lblImagem.setForeground(Color.WHITE);
		lblImagem.setIcon(new ImageIcon(TelaPedido.class.getResource("/view/novo.png")));
		lblImagem.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblImagem.setBounds(25, 11, 34, 40);
		contentPane.add(lblImagem);
		
		JLabel lblCamposCom = new JLabel("Campos com (*) s\u00E3o obrig\u00E1torios!");
		lblCamposCom.setForeground(Color.WHITE);
		lblCamposCom.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		lblCamposCom.setBounds(205, 62, 161, 14);
		contentPane.add(lblCamposCom);
		
		lblTitulo = new JLabel("Novo Pedido");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setBounds(69, 11, 179, 33);
		contentPane.add(lblTitulo);
		
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
				if(tfId.getText() .trim().equals("")){
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("O campo 'ID' não pode estar vazio!");
				}else {
					p.visualizar();
				}
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
					if(tfId.getText().trim().equals("")) {
						TelaMensagem men = new TelaMensagem();
						men.setUndecorated(true);
						men.setVisible(true);
						men.setLocationRelativeTo(null);
						men.lblAviso.setText("O pedido deve estar salvo!");
					}else {
						frame.setUndecorated(true);
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						frame.receberPedido(tfId.getText());
						TelaPedido.this.setVisible(false);
					}
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
		
		/**
		 * Ações do botão PDF
		 */
		btPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GeneratorPDF g = new GeneratorPDF();
					if(tfId.getText().trim().equals("")) {
						TelaMensagem men = new TelaMensagem();
						men.setUndecorated(true);
						men.setVisible(true);
						men.setLocationRelativeTo(null);
						men.lblAviso.setText("O pedido deve estar salvo!");
					}else {
						g.pdfPedido(tfId.getText(), cbCliente.getSelectedItem().toString(), 
								tfDataEntrada.getText(), tfDataPrevista.getText(), cbAtendente.getSelectedItem().toString(), 
								textAreaObs.getText());
					}
				}
		});
	}
}
