package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CargoController;
import controller.ClienteController;
import controller.ContasController;
import controller.FuncionarioController;
import controller.OrdemServicoController;
import controller.PedidoController;
import controller.ServicoController;
import services.BD;
import services.GeneratorPDF;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class TelaMenu extends JFrame {

	private JPanel contentPane;
	private JButton btCargo, btServico, btPedido, btOs, btFuncionario, btCliente, btReceber;
	private JPanel panel_1;
	private JPanel panel_3;
	private JButton btContas, btFecharCaixa, btRelatorio;
	private JLabel label;
	private JLabel lblUsuarioMenu;
	protected JLabel lblUsuario;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaMenu frame = new TelaMenu();
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
	public TelaMenu() {
		
		UIManager.put("TabbedPane.selected", SystemColor.textHighlight);
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
		
		setTitle("Menu"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("fonts/roboto.tff", Font.BOLD | Font.ITALIC, 60));
		lblMenu.setBounds(412, 15, 157, 77);
		contentPane.add(lblMenu);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		//tabbedPane.setForeground(Color.DARK_GRAY);
		tabbedPane.setBackground(new Color(242,233,187));
		tabbedPane.setBounds(10, 103, 871, 387);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 20));
		contentPane.add(tabbedPane);
		
		/**
		 * Para que o usuario não selecione dois rdbtn ao mesmo tempo
		 */
		ButtonGroup bg = new ButtonGroup();
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Financeiro", null, panel_3, null);
		panel_3.setLayout(null);
		
		btContas = new JButton("  Contas");
		btContas.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/contas.png")));
		btContas.setBounds(129, 76, 250, 100);
		btContas.setForeground(Color.WHITE);
		btContas.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btContas.setBackground(new Color(0, 139, 139));
		panel_3.add(btContas);
		
		btFecharCaixa = new JButton("  Caixa");
		btFecharCaixa.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/fechar_caixa.png")));
		btFecharCaixa.setForeground(Color.WHITE);
		btFecharCaixa.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btFecharCaixa.setBackground(new Color(0, 139, 139));
		btFecharCaixa.setBounds(471, 217, 250, 100);
		panel_3.add(btFecharCaixa);
		
		btReceber = new JButton(" Receber");
		btReceber.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/relatorio_financeiro.png")));
		btReceber.setForeground(Color.WHITE);
		btReceber.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btReceber.setBackground(new Color(0, 139, 139));
		btReceber.setBounds(471, 76, 250, 100);
		panel_3.add(btReceber);
		
		btRelatorio = new JButton("Relat\u00F3rio");
		btRelatorio.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/relatorio_financeiro.png")));
		btRelatorio.setForeground(Color.WHITE);
		btRelatorio.setFont(new Font("Segoe UI", Font.BOLD, 22));
		btRelatorio.setBackground(new Color(0, 139, 139));
		btRelatorio.setBounds(129, 217, 250, 100);
		panel_3.add(btRelatorio);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Serviços", null, panel, null);
		panel.setLayout(null);
		
		btServico = new JButton("Servi\u00E7os");
		btServico.setBounds(50, 125, 250, 100);
		panel.add(btServico);
		btServico.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/servicos.png")));
		btServico.setForeground(Color.WHITE);
		btServico.setBackground(new Color(0, 139, 139));
		btServico.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		btPedido = new JButton("Pedidos");
		btPedido.setBounds(310, 125, 250, 100);
		panel.add(btPedido);
		btPedido.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/pedidos.png")));
		btPedido.setForeground(Color.WHITE);
		btPedido.setBackground(new Color(0, 139, 139));
		btPedido.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		btOs = new JButton("O.S.");
		btOs.setBounds(570, 125, 250, 100);
		panel.add(btOs);
		btOs.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/ordemServico.png")));
		btOs.setForeground(Color.WHITE);
		btOs.setBackground(new Color(0, 139, 139));
		btOs.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Pessoas", null, panel_1, null);
		panel_1.setLayout(null);
		
		btCargo = new JButton("Cargos");
		btCargo.setBounds(50, 125, 250, 100);
		panel_1.add(btCargo);
		btCargo.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/cargo.png")));
		btCargo.setForeground(Color.WHITE);
		btCargo.setBackground(new Color(0, 139, 139));
		btCargo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		btFuncionario = new JButton("Funcion\u00E1rios");
		btFuncionario.setBounds(570, 125, 250, 100);
		panel_1.add(btFuncionario);
		btFuncionario.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/funcionarios.png")));
		btFuncionario.setForeground(Color.WHITE);
		btFuncionario.setBackground(new Color(0, 139, 139));
		btFuncionario.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		btCliente = new JButton("Clientes");
		btCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btCliente.setBounds(310, 125, 250, 100);
		panel_1.add(btCliente);
		btCliente.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/clientes.png")));
		btCliente.setForeground(Color.WHITE);
		btCliente.setBackground(new Color(0, 139, 139));
		btCliente.setFont(new Font("Segoe UI", Font.BOLD, 22));
		
		JLabel lblImgLogo = new JLabel("");
		lblImgLogo.setIcon(new ImageIcon(TelaMenu.class.getResource("/view/icone_NB.png")));
		lblImgLogo.setForeground(Color.DARK_GRAY);
		lblImgLogo.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 40));
		lblImgLogo.setBounds(327, 17, 75, 75);
		contentPane.add(lblImgLogo);
		
		ImageIcon Logo = new ImageIcon(TelaMenu.class.getResource("/view/icone_NB.png"));
		Image Log = Logo.getImage().getScaledInstance(lblImgLogo.getWidth(), lblImgLogo.getHeight(), Image.SCALE_SMOOTH);	
		lblImgLogo.setIcon(new ImageIcon(Log));
		
		lblUsuarioMenu = new JLabel("Usu\u00E1rio:");
		lblUsuarioMenu.setForeground(Color.WHITE);
		lblUsuarioMenu.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblUsuarioMenu.setBounds(12, 0, 157, 30);
		contentPane.add(lblUsuarioMenu);
		
		lblUsuario = new JLabel("");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsuario.setBounds(72, 0, 169, 30);
		contentPane.add(lblUsuario);
		
		
		
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
				PedidoController p = new PedidoController(frame.rbAtraso, frame.rbEspera, frame.rbFinalizado, frame.tfPesquisa, frame.cbOrdenar, frame.modelo);
				p.consulta();
				TelaMenu.this.setVisible(false);
			}
		});
		
		btFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaFuncionario frame = new ListaFuncionario();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				FuncionarioController f = new FuncionarioController(frame.tfPesquisa, frame.cbOrdenar, frame.tfCpf, modelo);
				f.consulta();
				TelaMenu.this.setVisible(false);
			}
		});
		
		btCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCliente frame = new ListaCliente();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				ClienteController c = new ClienteController(frame.tfPesquisa, frame.cbOrdenar, frame.tfCpf, frame.modelo);
				c.consulta();
				TelaMenu.this.setVisible(false);
			}
		});	
		
		btReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receber frame = new Receber();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				TelaMenu.this.setVisible(false);
			}
		});
		
		btContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaContas frame = new ListaContas();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				ContasController c = new ContasController(frame.rbNaoPago, frame.rbPago, frame.tfPesquisa, frame.tfInicio, frame.tfFim, frame.modelo);
				c.listar();
				TelaMenu.this.setVisible(false);
			}
		});

		btOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaOrdemServico frame = new ListaOrdemServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				OrdemServicoController o = new OrdemServicoController(frame.rbAtraso, frame.rbEspera, frame.rbFinalizado, frame.tfPesquisa, frame.cbOrdenar, frame.modelo);
				o.consulta();
				TelaMenu.this.setVisible(false);
			}
		});
		
		btCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCargo frame = new ListaCargo();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				CargoController c = new CargoController(frame.tfPesquisa, frame.cbOrdenar,frame.modelo);
				c.consulta();
				TelaMenu.this.setVisible(false);
			}
		});
		
		btServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaServico frame = new ListaServico();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				ServicoController s = new ServicoController(frame.tfPesquisa, frame.cbOrdenar, frame.modelo);
				s.consulta();
				TelaMenu.this.setVisible(false);
			}
		});
		
		btFecharCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContasController c = new ContasController();
				c.fechamento();
			}
		});
		
		btRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeneratorPDF g = new GeneratorPDF();
				g.pdfListaCaixa();
			}
		});
		
	}
}
