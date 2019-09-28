package view;

import java.awt.BorderLayout;
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

import controller.CaixaController;
import services.GeneratorPDF;

public class ListaCaixa extends JFrame {

	protected JPanel contentPane;
	protected JButton btVoltar, btNovo, btFiltrar;
	protected JPanel panel, plBotao;
	protected JLabel lbTitulo, lbAtributo, lbAtributo2, lbTotal, lbImagem;
	protected JTextField tfDe, tfAte;
	private JTable table;
	private JTextField tfLucro;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTextField tfEntrada;
	private JTextField tfConta;
	private JTextField tfComissao;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListaCaixa frame = new ListaCaixa();
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
	public ListaCaixa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1366, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Fluxo de caixa");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(177, 28, 297, 28);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(ListaCaixa.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(1258, 11, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/fechar_caixa.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(34, 4, 107, 89);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/fechar_caixa.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		plBotao = new JPanel();
		plBotao.setBackground(new Color(95, 158, 160));
		plBotao.setBounds(10, 216, 1320, 62);
		panel.add(plBotao);
		plBotao.setLayout(null);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.setIcon(new ImageIcon(ListaCaixa.class.getResource("/view/filtrar.png")));
		btFiltrar.setForeground(new Color(0, 0, 0));
		btFiltrar.setBackground(new Color(255, 255, 255));
		btFiltrar.setFont(new Font("Roboto", Font.BOLD, 18));
		btFiltrar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0), new Color(0, 0, 0)));
		btFiltrar.setBounds(10, 11, 120, 40);
		plBotao.add(btFiltrar);
		
		btNovo = new JButton("Fechar");
		btNovo.setIcon(new ImageIcon(ListaCaixa.class.getResource("/view/novo.png")));
		btNovo.setForeground(new Color(0, 0, 128));
		btNovo.setFont(new Font("Roboto", Font.BOLD, 18));
		btNovo.setBackground(new Color(255, 255, 255));
		btNovo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128), new Color(0, 0, 128)));
		btNovo.setBounds(150, 11, 120, 40);
		plBotao.add(btNovo);
		
		lbAtributo = new JLabel("De:");
		lbAtributo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAtributo.setFont(new Font("Roboto", Font.BOLD, 15));
		lbAtributo.setBounds(10, 129, 141, 14);
		panel.add(lbAtributo);
		
		tfDe = new JTextField();
		tfDe.setText("01/01/2019");
		tfDe.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfDe.setColumns(10);
		tfDe.setBounds(164, 127, 141, 20);
		panel.add(tfDe);
		
		lbAtributo2 = new JLabel("At\u00E9:");
		lbAtributo2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAtributo2.setFont(new Font("Roboto", Font.BOLD, 15));
		lbAtributo2.setBounds(315, 130, 141, 14);
		panel.add(lbAtributo2);
		
		tfAte = new JTextField();
		tfAte.setText("31/12/2019");
		tfAte.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfAte.setColumns(10);
		tfAte.setBounds(466, 127, 141, 20);
		panel.add(tfAte);
		
		lbTotal = new JLabel("Lucro do per\u00EDodo:");
		lbTotal.setFont(new Font("Roboto", Font.BOLD, 15));
		lbTotal.setBounds(710, 52, 141, 14);
		panel.add(lbTotal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 289, 1320, 396);
		panel.add(scrollPane);
		
		table = new JTable(modelo);
		table.setFont(new Font("Roboto", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		
		modelo.addColumn("Data");
		modelo.addColumn("Entrada (R$)");
		modelo.addColumn("Contas (R$)");
		modelo.addColumn("Comissão (R$)");
		modelo.addColumn("Lucro (R$)");
		
		tfLucro = new JTextField();
		tfLucro.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfLucro.setColumns(10);
		tfLucro.setBounds(710, 73, 141, 20);
		panel.add(tfLucro);
		
		JLabel lblDetalhes = new JLabel("Detalhes:");
		lblDetalhes.setFont(new Font("Roboto", Font.BOLD, 15));
		lblDetalhes.setBounds(710, 108, 141, 14);
		panel.add(lblDetalhes);
		
		tfEntrada = new JTextField();
		tfEntrada.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfEntrada.setColumns(10);
		tfEntrada.setBounds(710, 159, 141, 20);
		panel.add(tfEntrada);
		
		JLabel lblEntrada = new JLabel("Entrada:");
		lblEntrada.setFont(new Font("Roboto", Font.BOLD, 15));
		lblEntrada.setBounds(710, 138, 141, 14);
		panel.add(lblEntrada);
		
		tfConta = new JTextField();
		tfConta.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfConta.setColumns(10);
		tfConta.setBounds(861, 159, 141, 20);
		panel.add(tfConta);
		
		JLabel lblContas = new JLabel("Contas:");
		lblContas.setFont(new Font("Roboto", Font.BOLD, 15));
		lblContas.setBounds(861, 138, 141, 14);
		panel.add(lblContas);
		
		tfComissao = new JTextField();
		tfComissao.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfComissao.setColumns(10);
		tfComissao.setBounds(1012, 159, 141, 20);
		panel.add(tfComissao);
		
		JLabel lblComisso = new JLabel("Comiss\u00E3o:");
		lblComisso.setFont(new Font("Roboto", Font.BOLD, 15));
		lblComisso.setBounds(1012, 138, 141, 14);
		panel.add(lblComisso);
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		
		CaixaController c = new CaixaController(tfDe, tfAte, modelo, tfLucro, tfEntrada, tfConta, tfComissao);
		
		btFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.listarCaixa();
					c.receberValores();
			}

		});
		
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCaixa.this.setVisible(false);
			}

		});
		
		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFecharCaixa frame = new TelaFecharCaixa();
				//frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}

		});
	}
}
