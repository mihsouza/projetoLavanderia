package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.ServicoController;

public class TelaServico extends JFrame {

	protected JPanel contentPane;
	protected JButton btVoltar, btBuscar, btSalvar, btExcluir;
	protected JPanel panel;
	protected JLabel lbTitulo, lbNome, lblOrdenar, lbAtributo, lbImagem;
	protected JTextField tfNome, tfLavar;
	protected JComboBox cbTipo;
	public JTextField tfId;
	public JTextField tfPassar;
	public String id;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaServico frame = new TelaServico();
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
	public TelaServico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 637, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(102, 205, 170));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lbTitulo = new JLabel("Novo servi\u00E7o");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(127, 21, 297, 28);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(TelaServico.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(507, 4, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/servicos.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(21, 11, 106, 104);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/servicos.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		lbNome = new JLabel("Nome*:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setFont(new Font("Roboto", Font.BOLD, 15));
		lbNome.getHorizontalAlignment();
		lbNome.setBounds(21, 147, 81, 14);
		panel.add(lbNome);
		
		lblOrdenar = new JLabel("Tipo*:");
		lblOrdenar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenar.setFont(new Font("Roboto", Font.BOLD, 15));
		lblOrdenar.setBounds(21, 189, 81, 14);
		panel.add(lblOrdenar);
		
		lbAtributo = new JLabel("Pre\u00E7os*:");
		lbAtributo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAtributo.setFont(new Font("Roboto", Font.BOLD, 15));
		lbAtributo.setBounds(21, 228, 81, 14);
		panel.add(lbAtributo);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfNome.setBounds(122, 144, 443, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		tfLavar = new JTextField();
		tfLavar.setText("0.00");
		tfLavar.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfLavar.setColumns(10);
		tfLavar.setBounds(122, 258, 121, 20);
		panel.add(tfLavar);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Lavar", "Passar", "Lavar e passar"}));
		cbTipo.setFont(new Font("Roboto", Font.PLAIN, 15));
		cbTipo.setBounds(122, 186, 203, 20);
		panel.add(cbTipo);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(304, 305, 120, 40);
		panel.add(btExcluir);
		btExcluir.setIcon(new ImageIcon(TelaServico.class.getResource("/view/lixeira.png")));
		btExcluir.setForeground(new Color(178, 34, 34));
		btExcluir.setFont(new Font("Roboto", Font.BOLD, 18));
		btExcluir.setBackground(new Color(255, 255, 255));
		btExcluir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34)));
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(445, 305, 120, 40);
		panel.add(btSalvar);
		btSalvar.setIcon(new ImageIcon(TelaServico.class.getResource("/view/salvar.png")));
		btSalvar.setForeground(new Color(72, 61, 139));
		btSalvar.setFont(new Font("Roboto", Font.BOLD, 18));
		btSalvar.setBackground(new Color(255, 255, 255));
		btSalvar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		
		btBuscar = new JButton("Ver");
		btBuscar.setBounds(342, 75, 120, 40);
//		panel.add(btBuscar);
		btBuscar.setIcon(new ImageIcon(TelaServico.class.getResource("/view/Visualizar.png")));
		btBuscar.setForeground(new Color(47, 79, 79));
		btBuscar.setFont(new Font("Roboto", Font.BOLD, 18));
		btBuscar.setBackground(Color.WHITE);
		btBuscar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79), new Color(47, 79, 79)));
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfId.setColumns(10);
		tfId.setBounds(522, 86, 43, 20);
		panel.add(tfId);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("Roboto", Font.BOLD, 15));
		lblId.setBounds(487, 90, 25, 14);
		panel.add(lblId);
		
		JLabel lblLavar = new JLabel("Lavar:");
		lblLavar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLavar.setFont(new Font("Roboto", Font.BOLD, 15));
		lblLavar.setBounds(21, 262, 81, 14);
		panel.add(lblLavar);
		
		JLabel lblPassar = new JLabel("Passar:");
		lblPassar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassar.setFont(new Font("Roboto", Font.BOLD, 15));
		lblPassar.setBounds(253, 262, 81, 14);
		panel.add(lblPassar);
		
		tfPassar = new JTextField();
		tfPassar.setText("0.00");
		tfPassar.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfPassar.setColumns(10);
		tfPassar.setBounds(353, 259, 121, 20);
		panel.add(tfPassar);
		
		controlarEvento();
	}
	
	public void controlarEvento() {
		ServicoController c = new ServicoController(tfNome, tfLavar, cbTipo, tfId, tfPassar);
		/**
		 * Fechar o frame
		 */
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaServico.this.setVisible(false);
			}

		});
		
		/**
		 * inserir um novo cargo
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.validarCampo() == true) {
					c.inserir();
					TelaServico.this.setVisible(false);
				}
			}

		});
		
		/**
		 * excluir um cargo
		 */
//		btExcluir.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if(tfId.getText().trim().equals("")) {
//					TelaMensagemErro frame = new TelaMensagemErro();
//					frame.setUndecorated(true);
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//					frame.lbCampo.setText("ID: Campo obrigatório");
//				}else {
//					c.excluir();
//					TelaServico.this.setVisible(false);
//				}
//			}
//
//		});
		
		/**
		 * excluir um cargo
		 */
		btBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().trim().equals("")) {
					TelaMensagemErro frame = new TelaMensagemErro();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lbCampo.setText("ID: Campo obrigatório");
				}else {
					//c.visualizar();
				}
			}

		});
	}
}
