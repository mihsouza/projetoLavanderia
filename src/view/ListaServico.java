package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import controller.CargoController;
import controller.ServicoController;

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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ListaServico extends JFrame {

	protected JPanel contentPane;
	protected JButton btVoltar, btBuscar, btNovo, btFiltrar;
	protected JPanel panel, plBotao;
	protected JLabel lbTitulo, lbNome, lblOrdenar, lbImagem;
	protected JTextField tfNome;
	protected JComboBox cbOrdenar;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	public JTextField tfServico;
	public JTextField tfLavar;
	public JTextField tfPassar;
	public JTextField tfId;
	private JButton btExcluir, btSalvar;
	public JComboBox cbTipo;
	public String id;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListaServico frame = new ListaServico();
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
	public ListaServico() {
		ListaServico.this.setUndecorated(true);
		UIManager.put("TabbedPane.selected", new Color(0, 0, 98));
		UIManager.put("TabbedPane.selectedForeground", Color.WHITE);
		
		setTitle("Serviços"); 
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaServico.class.getResource("/view/servicos.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1366, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setForeground(SystemColor.control);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Servi\u00E7os");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(177, 18, 297, 36);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(ListaServico.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(1258, 11, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/servicos.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(60, 4, 107, 89);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/servicos.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		plBotao = new JPanel();
		plBotao.setBackground(new Color(112, 128, 144));
		plBotao.setBounds(10, 86, 1320, 192);
		panel.add(plBotao);
		plBotao.setLayout(null);
		
		lblOrdenar = new JLabel("Ordenar por:");
		lblOrdenar.setBounds(10, 80, 141, 14);
		plBotao.add(lblOrdenar);
		lblOrdenar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenar.setFont(new Font("Roboto", Font.BOLD, 15));
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setBounds(164, 77, 203, 20);
		plBotao.add(cbOrdenar);
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"ID", "Nome", "Preco", "Tipo"}));
		cbOrdenar.setFont(new Font("Roboto", Font.PLAIN, 15));
		
		lbNome = new JLabel("Servi\u00E7o:");
		lbNome.setBounds(10, 43, 141, 14);
		plBotao.add(lbNome);
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setFont(new Font("Roboto", Font.BOLD, 15));
		
		tfNome = new JTextField();
		tfNome.setBounds(164, 40, 443, 20);
		plBotao.add(tfNome);
		tfNome.setBackground(UIManager.getColor("Button.light"));
		tfNome.setForeground(Color.BLACK);
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfNome.setColumns(10);
		
		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setForeground(new Color(0, 0, 128));
		lblFiltros.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblFiltros.setBounds(10, 11, 46, 14);
		plBotao.add(lblFiltros);
		
		btBuscar = new JButton("Ver");
		btBuscar.setBounds(152, 141, 120, 40);
		plBotao.add(btBuscar);
		btBuscar.setIcon(new ImageIcon(ListaServico.class.getResource("/view/detalhes.png")));
		btBuscar.setForeground(new Color(0, 128, 128));
		btBuscar.setFont(new Font("Roboto", Font.BOLD, 18));
		btBuscar.setBackground(Color.WHITE);
		btBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128), new Color(0, 128, 128), new Color(0, 128, 128)));
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setBounds(20, 141, 120, 40);
		plBotao.add(btFiltrar);
		btFiltrar.setIcon(new ImageIcon(ListaServico.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(new Color(0, 0, 0));
		btFiltrar.setBackground(new Color(255, 255, 255));
		btFiltrar.setFont(new Font("Roboto", Font.BOLD, 18));
		btFiltrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		lbNome.getHorizontalAlignment();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 289, 774, 396);
		panel.add(scrollPane);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("Nome*:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Roboto", Font.BOLD, 15));
		label.setBounds(794, 383, 81, 14);
		panel.add(label);
		
		tfServico = new JTextField();
		tfServico.setForeground(Color.BLACK);
		tfServico.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfServico.setColumns(10);
		tfServico.setBounds(885, 380, 410, 20);
		panel.add(tfServico);
		
		JLabel label_1 = new JLabel("Tipo*:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Roboto", Font.BOLD, 15));
		label_1.setBounds(794, 427, 81, 14);
		panel.add(label_1);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Lavar", "Passar", "Lavar e Passar"}));
		cbTipo.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbTipo.setBounds(885, 424, 213, 20);
		panel.add(cbTipo);
		
		JLabel label_2 = new JLabel("Pre\u00E7os*:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Roboto", Font.BOLD, 15));
		label_2.setBounds(794, 467, 81, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Lavar:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Roboto", Font.BOLD, 15));
		label_3.setBounds(794, 505, 81, 14);
		panel.add(label_3);
		
		tfLavar = new JTextField();
		tfLavar.setForeground(Color.BLACK);
		tfLavar.setText("0.00");
		tfLavar.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfLavar.setColumns(10);
		tfLavar.setBounds(885, 502, 121, 20);
		panel.add(tfLavar);
		
		tfPassar = new JTextField();
		tfPassar.setText("0.00");
		tfPassar.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfPassar.setColumns(10);
		tfPassar.setBounds(1115, 502, 121, 20);
		panel.add(tfPassar);
		
		JLabel label_4 = new JLabel("Passar:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Roboto", Font.BOLD, 15));
		label_4.setBounds(1024, 503, 81, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("ID:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("Roboto", Font.BOLD, 15));
		label_5.setBounds(1217, 314, 25, 14);
		panel.add(label_5);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfId.setEditable(false);
		tfId.setColumns(10);
		tfId.setBounds(1252, 310, 43, 20);
		panel.add(tfId);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setForeground(new Color(72, 61, 139));
		btSalvar.setIcon(new ImageIcon(TelaServico.class.getResource("/view/salvar.png")));
		btSalvar.setFont(new Font("Roboto", Font.BOLD, 18));
		btSalvar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		btSalvar.setBackground(Color.WHITE);
		btSalvar.setBounds(804, 620, 120, 40);
		panel.add(btSalvar);
		
		JLabel lblDetalhes = new JLabel("Servi\u00E7o");
		lblDetalhes.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lblDetalhes.setBounds(804, 310, 297, 28);
		panel.add(lblDetalhes);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setForeground(new Color(178, 34, 34));
		btExcluir.setIcon(new ImageIcon(TelaServico.class.getResource("/view/lixeira.png")));
		btExcluir.setFont(new Font("Roboto", Font.BOLD, 18));
		btExcluir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34)));
		btExcluir.setBackground(Color.WHITE);
		btExcluir.setBounds(1045, 620, 120, 40);
		panel.add(btExcluir);
		
		btNovo = new JButton("Novo");
		btNovo.setBounds(1175, 620, 120, 40);
		panel.add(btNovo);
		btNovo.setIcon(new ImageIcon(ListaServico.class.getResource("/view/novo.png")));
		btNovo.setForeground(new Color(0, 0, 128));
		btNovo.setFont(new Font("Roboto", Font.BOLD, 18));
		btNovo.setBackground(new Color(255, 255, 255));
		btNovo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128)));
		
		modelo.addColumn("ID");
		modelo.addColumn("Serviço");
		modelo.addColumn("Tipo");
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		
		/**
		 * Passa o valor dos filtros e listaa todos os serviços
		 */
		btFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServicoController l = new ServicoController(tfNome, cbOrdenar,modelo);
				l.listar();
			}
		});
		
		/**
		 * Volta para a tela principal
		 */
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaServico.this.setVisible(false);
			}

		});
		
		ServicoController c = new ServicoController(tfServico, tfLavar, cbTipo, tfId, tfPassar);
		/**
		 * Ação do mouse, pega o numero da linha clicada e busca no banco as informações
		 */
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        	int y = table.getSelectedColumn();
					int x = table.getSelectedRow();
					int obj = (int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
					System.out.println("Lista" + obj);
					id = String.valueOf(obj);
					System.out.println(id);
					tfId.setText(id);
					c.visualizar(id);
					System.out.println("modal" + tfId.getText());
		        }
		    }
		});
		
		/**
		 * Adiciona um novo registro
		 */
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.validarCampo() == true) {
					c.inserir();
				}
			}

		});
		
		/**
		 * Ação para excluir um registro
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMensagemExcluir frame = new TelaMensagemExcluir();
				//frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lbItem.setText(tfServico.getText());
				frame.id = tfId.getText();
				frame.modal = "servico";
				
			}

		});
		
		/**
		 * Salva as alterações
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.validarCampo() == true) {
					c.alterar();
				}
			}

		});
	}
}
