package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CargoController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class TelaCargo extends JFrame {
	
	/**
	 * Componentes da tela para inserir, visualizar e excluir dados no banco.
	 */
	protected JPanel contentPane;
	protected JTextField tfId;
	protected JTextField tfNome;
	protected JTextField tfRemuneracao;
	protected JButton btSalvar, btCancelar, btVisualizar;
	protected JTextArea textAreaDescricao;
	protected JButton btExcluir;
	protected JLabel lblTitulo;

	/**
	 * Visualizar a aplicação.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCargo frame = new TelaCargo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Criação do frame.
	 */
	public TelaCargo() {
		
		setTitle("Cargo"); //Rafa: Alterar nome da tela
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png"))); //Rafa: Alterar ícone da tela
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(SystemColor.textHighlight);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNome = new JLabel("Cargo:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNome.setBounds(105, 126, 59, 30);
		contentPane.add(lblNome);
		
		JLabel lblRemuneracao = new JLabel("Remunera\u00E7\u00E3o:");
		lblRemuneracao.setForeground(Color.WHITE);
		lblRemuneracao.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblRemuneracao.setBounds(42, 161, 122, 35);
		contentPane.add(lblRemuneracao);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setForeground(Color.WHITE);
		lblDescricao.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblDescricao.setBounds(74, 207, 90, 25);
		contentPane.add(lblDescricao);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblId.setBounds(138, 90, 26, 30);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfId.setEditable(false);
		tfId.setBounds(173, 94, 55, 25);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfNome.setForeground(new Color(0, 0, 0));
		tfNome.setBounds(173, 130, 395, 25);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfRemuneracao = new JTextField();
		tfRemuneracao.setFont(new Font("Roboto", Font.PLAIN, 15));
		tfRemuneracao.setBounds(173, 167, 86, 25);
		contentPane.add(tfRemuneracao);
		tfRemuneracao.setColumns(10);
		
		textAreaDescricao = new JTextArea();
		textAreaDescricao.setFont(new Font("Roboto", Font.PLAIN, 15));
		textAreaDescricao.setBounds(172, 206, 396, 80);
		contentPane.add(textAreaDescricao);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Salvar.png")));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btSalvar.setBackground(new Color(60, 179, 113));
		btSalvar.setBounds(443, 307, 125, 40);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/cancel-button-white.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.setBounds(308, 307, 125, 40);
		contentPane.add(btCancelar);
		
		btVisualizar = new JButton("Buscar");
		btVisualizar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(96,204,204));
		btVisualizar.setBounds(262, 79, 135, 40);
//		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Excluir.png")));
		btExcluir.setForeground(Color.WHITE);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btExcluir.setBackground(new Color(96,204,204));
		btExcluir.setBounds(173, 307, 125, 40);
//		contentPane.add(btExcluir);
		
		JLabel lblNovoCargo = new JLabel("");
		lblNovoCargo.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/novo_cargo.png")));
		lblNovoCargo.setForeground(Color.WHITE);
		lblNovoCargo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblNovoCargo.setBounds(42, 18, 60, 50);
		contentPane.add(lblNovoCargo);
		
		
		ImageIcon novoCargo = new ImageIcon(TelaCargo.class.getResource("/view/novo_cargo.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lblNovoCargo.getWidth(), lblNovoCargo.getHeight(), Image.SCALE_SMOOTH);	
		lblNovoCargo.setIcon(new ImageIcon(novoCa));
		
		lblTitulo = new JLabel("Novo Cargo");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTitulo.setBounds(112, 18, 232, 56);
		contentPane.add(lblTitulo);
		

		
		controlarEventos();
		
	}
	
	/**
	 * Método para definir as ações do botão da tela.
	 */
	public void controlarEventos() {
		CargoController c = new CargoController(tfRemuneracao, tfNome, tfId, textAreaDescricao);
		/**
		 * Ação do botão salvar.
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = c.validarCampo();
				if(c.validarCampo()) {
					c.verificarAcao();
					TelaCargo.this.setVisible(false);
				}
			}
		});
		
		/**
		 * Ação do botão visualizar.
		 */
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.visualizar();
				}
		});
		
		/**
		 * Ação do botão excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.excluir();
					TelaCargo.this.setVisible(false);
				}
		});
		
		/**
		 * Ação do botão cancelar.
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaCargo.this.setVisible(false);
				}
		});
	}
}
