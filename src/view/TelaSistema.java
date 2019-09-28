package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.PedidoController;
import services.GeneratorPDF;

public class TelaSistema extends JFrame {

	public JPanel contentPane;
	public JButton btSair, btBuscar, btPedido, btCliente, btFluxo, btConta, btReceber, btCaixa, btListaCargo, btCadastrar, btComissao;
	public JPanel panel, plBotao;
	public JLabel lbTitulo, lbAtributo, lbImagem, lbMenu;
	public JTextField tfSaida;
	private JTable table;
	public JButton btListaCliente;
	public JButton btListaFuncionario;
	public JButton btListaServico;
	public JButton btListaPedido;
	public JButton btListaConta;
	public JLabel lbUsuario;
	public JPanel pMenu;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaSistema frame = new TelaSistema();
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
	public TelaSistema() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1366, 745);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(572, 5, 773, 696);
		panel.setBackground(new Color(175, 238, 238));
		contentPane.add(panel);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Agenda");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(164, 47, 120, 45);
		panel.add(lbTitulo);
		
		btSair = new JButton("");
		btSair.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/desligar.png")));
		btSair.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btSair.setForeground(Color.WHITE);
		btSair.setBackground(Color.WHITE);
		btSair.setBounds(716, 11, 47, 45);
		btSair.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.RED, Color.RED, Color.RED, Color.RED));
		panel.add(btSair);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/agenda.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(24, 24, 130, 102);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/agenda.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		plBotao = new JPanel();
		plBotao.setBackground(new Color(95, 158, 160));
		plBotao.setBounds(24, 149, 739, 62);
		panel.add(plBotao);
		plBotao.setLayout(null);
		
		lbAtributo = new JLabel("Data:");
		lbAtributo.setBounds(10, 25, 56, 14);
		plBotao.add(lbAtributo);
		lbAtributo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAtributo.setFont(new Font("Roboto", Font.BOLD, 15));
		
		tfSaida = new JTextField();
		tfSaida.setBounds(76, 22, 120, 20);
		plBotao.add(tfSaida);
		tfSaida.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfSaida.setColumns(10);
		
		btBuscar = new JButton("Ver");
		btBuscar.setBounds(206, 11, 120, 40);
		plBotao.add(btBuscar);
		btBuscar.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/detalhes.png")));
		btBuscar.setForeground(new Color(0, 128, 128));
		btBuscar.setFont(new Font("Roboto", Font.BOLD, 18));
		btBuscar.setBackground(Color.WHITE);
		btBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128), new Color(0, 128, 128), new Color(0, 128, 128)));
		
		pMenu = new JPanel();
		pMenu.setBackground(new Color(32, 178, 170));
		pMenu.setBounds(10, 5, 552, 696);
		contentPane.add(pMenu);
		pMenu.setLayout(null);
		
		lbMenu = new JLabel("New label");
		lbMenu.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/agua.png")));
		lbMenu.setBounds(10, 42, 191, 158);
		lbMenu.setBackground(new Color(0, 102, 51));
		pMenu.add(lbMenu);
		
		ImageIcon novoMenu = new ImageIcon(TelaSistema.class.getResource("/view/agua.png"));
		Image novoMen = novoMenu.getImage().getScaledInstance(lbMenu.getWidth(), lbMenu.getHeight(), Image.SCALE_SMOOTH);	
		lbMenu.setIcon(new ImageIcon(novoMen));
		
		JLabel lblNewLabel = new JLabel("Lava");
		lblNewLabel.setForeground(new Color(199, 21, 133));
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setBounds(212, 51, 86, 44);
		pMenu.add(lblNewLabel);
		
		JLabel label = new JLabel("&");
		label.setForeground(new Color(199, 21, 133));
		label.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 35));
		label.setBounds(320, 81, 36, 44);
		pMenu.add(label);
		
		JLabel lblPassa = new JLabel("Passa");
		lblPassa.setForeground(new Color(199, 21, 133));
		lblPassa.setFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 35));
		lblPassa.setBounds(366, 114, 102, 44);
		pMenu.add(lblPassa);
		
		btPedido = new JButton("Pedido");
		btPedido.setBounds(10, 266, 120, 40);
		pMenu.add(btPedido);
		btPedido.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/ordem_botao.png")));
		btPedido.setForeground(new Color(0, 0, 0));
		btPedido.setFont(new Font("Roboto", Font.BOLD, 18));
		btPedido.setBackground(new Color(255, 255, 255));
		btPedido.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		
		btCliente = new JButton("Cliente");
		btCliente.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/clientes_botao.png")));
		btCliente.setBounds(271, 266, 120, 40);
		//pMenu.add(btCliente);
		btCliente.setForeground(new Color(0, 0, 0));
		btCliente.setBackground(new Color(255, 255, 255));
		btCliente.setFont(new Font("Roboto", Font.BOLD, 18));
		btCliente.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		
		JLabel lblAtalhos = new JLabel("Atalhos");
		lblAtalhos.setForeground(new Color(199, 21, 133));
		lblAtalhos.setFont(new Font("Lucida Bright", Font.PLAIN, 29));
		lblAtalhos.setBounds(10, 211, 156, 44);
		pMenu.add(lblAtalhos);
		
		btConta = new JButton("Conta");
		btConta.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/conta_botao.png")));
		btConta.setForeground(Color.BLACK);
		btConta.setFont(new Font("Roboto", Font.BOLD, 18));
		btConta.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btConta.setBackground(Color.WHITE);
		btConta.setBounds(401, 266, 120, 40);
		//pMenu.add(btConta);
		
		JLabel lblMenus = new JLabel("Menu");
		lblMenus.setForeground(new Color(199, 21, 133));
		lblMenus.setFont(new Font("Lucida Bright", Font.PLAIN, 29));
		lblMenus.setBounds(10, 331, 156, 44);
		pMenu.add(lblMenus);
		
		JLabel lblRelatrios = new JLabel("Relat\u00F3rio");
		lblRelatrios.setForeground(new Color(199, 21, 133));
		lblRelatrios.setFont(new Font("Lucida Bright", Font.PLAIN, 29));
		lblRelatrios.setBounds(10, 497, 156, 44);
		pMenu.add(lblRelatrios);
		
		btCaixa = new JButton("Caixa");
		btCaixa.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/caixa_botao.png")));
		btCaixa.setForeground(Color.BLACK);
		btCaixa.setFont(new Font("Roboto", Font.BOLD, 18));
		btCaixa.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btCaixa.setBackground(Color.WHITE);
		btCaixa.setBounds(10, 552, 120, 40);
		//pMenu.add(btCaixa);
		
		btFluxo = new JButton("Fluxo");
		btFluxo.setBounds(10, 603, 120, 40);
		//pMenu.add(btFluxo);
		btFluxo.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/PDF.png")));
		btFluxo.setForeground(Color.BLACK);
		btFluxo.setFont(new Font("Roboto", Font.BOLD, 18));
		btFluxo.setBackground(new Color(255, 255, 255));
		btFluxo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		
		btReceber = new JButton("Receber");
		btReceber.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/caixa_botao.png")));
		btReceber.setForeground(Color.BLACK);
		btReceber.setFont(new Font("Roboto", Font.BOLD, 18));
		btReceber.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btReceber.setBackground(Color.WHITE);
		btReceber.setBounds(140, 266, 120, 40);
		//pMenu.add(btReceber);
		
		JLabel lblUsario = new JLabel("Usu\u00E1rio:");
		lblUsario.setForeground(Color.BLACK);
		lblUsario.setFont(new Font("Lucida Bright", Font.BOLD, 21));
		lblUsario.setBounds(10, 0, 89, 34);
		pMenu.add(lblUsario);
		
		lbUsuario = new JLabel("Usu\u00E1rio");
		lbUsuario.setForeground(Color.BLACK);
		lbUsuario.setFont(new Font("Lucida Bright", Font.PLAIN, 21));
		lbUsuario.setBounds(107, 0, 200, 34);
		pMenu.add(lbUsuario);
		
		
		/**
		 * Botões menu Contabil
		 */
		btListaConta = new JButton("Conta");
		btListaConta.setForeground(Color.BLACK);
		btListaConta.setFont(new Font("Roboto", Font.BOLD, 18));
		btListaConta.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btListaConta.setBackground(new Color(175, 238, 238));
		btListaConta.setBounds(140, 383, 120, 40);
		//pMenu.add(btListaConta);
		
		/**
		 * Botões menu Servico
		 */
		btListaServico = new JButton("Servi\u00E7o");
		btListaServico.setForeground(Color.BLACK);
		btListaServico.setFont(new Font("Roboto", Font.BOLD, 18));
		btListaServico.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btListaServico.setBackground(new Color(175, 238, 238));
		btListaServico.setBounds(140, 434, 120, 40);
		//pMenu.add(btListaServico);
		
		btListaPedido = new JButton("Pedido");
		btListaPedido.setForeground(Color.BLACK);
		btListaPedido.setFont(new Font("Roboto", Font.BOLD, 18));
		btListaPedido.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btListaPedido.setBackground(new Color(175, 238, 238));
		btListaPedido.setBounds(10, 383, 120, 40);
		pMenu.add(btListaPedido);
		
		btComissao = new JButton("Comiss\u00F5es");
		btComissao.setForeground(Color.BLACK);
		btComissao.setFont(new Font("Roboto", Font.BOLD, 18));
		btComissao.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btComissao.setBackground(new Color(175, 238, 238));
		btComissao.setBounds(10, 656, 120, 40);
		pMenu.add(btComissao);
		
		/**
		 * Botões menu Pessoa
		 */
		btListaCargo = new JButton("Cargo");
		btListaCargo.setForeground(new Color(0, 0, 0));
		btListaCargo.setFont(new Font("Roboto", Font.BOLD, 18));
		btListaCargo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btListaCargo.setBackground(new Color(175, 238, 238));
		btListaCargo.setBounds(270, 434, 120, 40);
		//pMenu.add(btListaCargo);
		
		btListaCliente = new JButton("Cliente");
		btListaCliente.setForeground(Color.BLACK);
		btListaCliente.setFont(new Font("Roboto", Font.BOLD, 18));
		btListaCliente.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btListaCliente.setBackground(new Color(175, 238, 238));
		btListaCliente.setBounds(10, 434, 120, 40);
		//pMenu.add(btListaCliente);
		
		btListaFuncionario = new JButton("Funcion\u00E1rio");
		btListaFuncionario.setForeground(Color.BLACK);
		btListaFuncionario.setFont(new Font("Roboto", Font.BOLD, 18));
		btListaFuncionario.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btListaFuncionario.setBackground(new Color(175, 238, 238));
		btListaFuncionario.setBounds(270, 383, 120, 40);
		//pMenu.add(btListaFuncionario);
		
		btCadastrar = new JButton("Cadastrar um usu\u00E1rio");
		btCadastrar.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/novo.png")));
		btCadastrar.setForeground(new Color(199, 21, 133));
		btCadastrar.setFont(new Font("Roboto", Font.BOLD, 18));
		btCadastrar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(199,21,133), new Color(199,21,133), new Color(199,21,133), new Color(199,21,133)));
		btCadastrar.setBackground(Color.WHITE);
		btCadastrar.setBounds(283, 639, 238, 40);
		//pMenu.add(btCadastrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 222, 739, 463);
		panel.add(scrollPane);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		modelo.addColumn("ID");
		modelo.addColumn("Data de Entrada");
		modelo.addColumn("Cliente");
		modelo.addColumn("Funcionário responsável");
		modelo.addColumn("Status");
		modelo.addColumn("Observação");
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		
		/**
		 * Chamar uma tela para visualizar
		 */
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController p = new PedidoController(tfSaida, modelo);
				p.listarPedidos();
			}

		});
		
		btReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoController p = new PedidoController();
				p.receberPedido();
			}

		});
		
		btCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCaixa frame = new ListaCaixa();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btListaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCliente frame = new ListaCliente();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btListaConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaConta frame = new ListaConta();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btListaPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaPedido frame = new ListaPedido();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				if(lbUsuario.getText().equals("sandra")) {
					frame.tfUsuario.setText("");
				}else
					if(lbUsuario.getText().equals("adriana")) {
						frame.tfUsuario.setText("");
					}else 
						if(lbUsuario.getText().equals("sandra26")){
							frame.tfUsuario.setText("");
						}else {
							frame.tfUsuario.setText(lbUsuario.getText());
						}
			}

		});
		
		btListaFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaFuncionario frame = new ListaFuncionario();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btListaCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCargo frame = new ListaCargo();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btListaServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaServico frame = new ListaServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente frame = new TelaCliente();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrar frame = new TelaCadastrar();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPedido frame = new TelaPedido();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.panel.add(frame.btExcluir);
				frame.panel.add(frame.btBuscar);
				frame.lbTitulo.setText("");
			}

		});
		
		btConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConta frame = new TelaConta();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
		
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		
		btFluxo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GeneratorPDF g = new GeneratorPDF();
				g.pdfListaCaixa();
			}
		});
		
		btComissao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaComissao frame = new ListaComissao();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				if(lbUsuario.getText().equals("sandra")) {
					frame.tfEntrada.setText("");
				}else
					if(lbUsuario.getText().equals("adriana")) {
						frame.tfEntrada.setText("");
					}else {
						frame.tfEntrada.setText(lbUsuario.getText());
					}
			}

		});
	}
}
