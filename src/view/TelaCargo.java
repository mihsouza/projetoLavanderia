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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.CargoController;

public class TelaCargo extends JFrame {

	public JPanel contentPane;
	public JButton btVoltar, btBuscar, btSalvar, btExcluir;
	public JPanel panel;
	public JLabel lbTitulo, lbNome, lblOrdenar, lbAtributo, lbImagem;
	public JTextField tfNome, tfRemuneracao;
	public JTextField tfId;
	public JTextArea taDescricao;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCargo frame = new TelaCargo();
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
	public TelaCargo() {
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
		
		lbTitulo = new JLabel("Novo Cargo");
		lbTitulo.setFont(new Font("Roboto Lt", Font.BOLD, 30));
		lbTitulo.setBounds(127, 21, 297, 36);
		panel.add(lbTitulo);
		
		btVoltar = new JButton("");
		btVoltar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/voltar.png")));
		btVoltar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btVoltar.setForeground(Color.WHITE);
		btVoltar.setBackground(new Color(51, 51, 102));
		btVoltar.setBounds(507, 4, 58, 45);
		btVoltar.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(btVoltar);
		
		
		lbImagem = new JLabel("New label");
		lbImagem.setIcon(new ImageIcon(TelaSistema.class.getResource("/view/maleta.png")));
		lbImagem.setBackground(new Color(0, 102, 51));
		lbImagem.setBounds(21, 11, 90, 92);
		panel.add(lbImagem);
		
		/**
		 * Fazer com q a imagem fique dentro do label
		 */
		ImageIcon novoCargo = new ImageIcon(TelaSistema.class.getResource("/view/maleta.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lbImagem.getWidth(), lbImagem.getHeight(), Image.SCALE_SMOOTH);	
		lbImagem.setIcon(new ImageIcon(novoCa));
		
		lbNome = new JLabel("Cargo*:");
		lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbNome.setFont(new Font("Roboto", Font.BOLD, 15));
		lbNome.getHorizontalAlignment();
		lbNome.setBounds(21, 144, 81, 20);
		panel.add(lbNome);
		
		lblOrdenar = new JLabel("Descri\u00E7\u00E3o:");
		lblOrdenar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrdenar.setFont(new Font("Roboto", Font.BOLD, 15));
		lblOrdenar.setBounds(21, 231, 81, 14);
		panel.add(lblOrdenar);
		
		lbAtributo = new JLabel("Remunera\u00E7\u00E3o*:");
		lbAtributo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbAtributo.setFont(new Font("Roboto", Font.BOLD, 15));
		lbAtributo.setBounds(0, 186, 102, 14);
		panel.add(lbAtributo);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfNome.setBounds(122, 144, 443, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		tfRemuneracao = new JTextField();
		tfRemuneracao.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfRemuneracao.setColumns(10);
		tfRemuneracao.setBounds(122, 183, 121, 20);
		panel.add(tfRemuneracao);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(304, 305, 120, 40);
//		panel.add(btExcluir);
		btExcluir.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/lixeira.png")));
		btExcluir.setForeground(new Color(178, 34, 34));
		btExcluir.setFont(new Font("Roboto", Font.BOLD, 18));
		btExcluir.setBackground(new Color(255, 255, 255));
		btExcluir.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34), new Color(178, 34, 34)));
		
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(445, 305, 120, 40);
		panel.add(btSalvar);
		btSalvar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/salvar.png")));
		btSalvar.setForeground(new Color(72, 61, 139));
		btSalvar.setFont(new Font("Roboto", Font.BOLD, 18));
		btSalvar.setBackground(new Color(255, 255, 255));
		btSalvar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139), new Color(72, 61, 139)));
		
		btBuscar = new JButton("Ver");
		btBuscar.setBounds(342, 75, 120, 40);
//		panel.add(btBuscar);
		btBuscar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Visualizar.png")));
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
		
		taDescricao = new JTextArea();
		taDescricao.setFont(new Font("Roboto", Font.PLAIN, 15));
		taDescricao.setLineWrap(true);
		taDescricao.setBounds(122, 227, 443, 45);
		panel.add(taDescricao);

		controlarEvento();
	}
	
	public void controlarEvento() {
		CargoController c = new CargoController(tfNome, tfRemuneracao, tfId, taDescricao);
		/**
		 * Fechar o frame
		 */
		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCargo.this.setVisible(false);
			}

		});
		
		/**
		 * inserir um novo cargo
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.validarCampo() == true) {
					c.inserir();
					TelaCargo.this.setVisible(false);
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
					TelaCargo.this.setVisible(false);
				}
			}

		});
		
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
					c.visualizar();
				}
			}

		});
	}
		
}
