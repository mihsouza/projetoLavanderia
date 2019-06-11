package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ServicoController;
import model.Cargo;
import model.Servico;

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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class TelaServico extends JFrame {

	protected JPanel contentPane;
	protected JTextField tfId;
	private JTextField tfNome;
	protected JComboBox cbTipo;
	protected JTextField tfPreco;
	protected JButton btSalvar, btCancelar, btVisualizar, btExcluir;
	protected JLabel lblTitulo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaServico frame = new TelaServico();
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
	public TelaServico() {
		
		setTitle("Serviço");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 409);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Servi\u00E7o:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNome.setBounds(56, 142, 55, 27);
		contentPane.add(lblNome);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTipo.setBounds(76, 192, 35, 27);
		contentPane.add(lblTipo);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setForeground(Color.WHITE);
		lblPreco.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblPreco.setBounds(372, 192, 45, 27);
		contentPane.add(lblPreco);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblId.setBounds(81, 91, 30, 29);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfId.setEditable(false);
		tfId.setBounds(121, 99, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfNome.setForeground(new Color(0, 0, 0));
		tfNome.setBounds(121, 148, 395, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Lavar", "Passar", "Lavar e Passar"}));
		cbTipo.setBounds(121, 198, 178, 20);
		contentPane.add(cbTipo);
		
		tfPreco = new JTextField();
		tfPreco.setFont(new Font("Roboto", Font.PLAIN, 14));
		tfPreco.setBounds(430, 198, 86, 20);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Salvar.png")));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btSalvar.setBackground(new Color(60, 179, 113));
		btSalvar.setBounds(391, 308, 125, 40);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setIcon(new ImageIcon(TelaServico.class.getResource("/view/cancel-button-white.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.setBounds(256, 308, 125, 40);
		contentPane.add(btCancelar);
		
		btVisualizar = new JButton("Buscar");
		btVisualizar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(96,204,204));
		btVisualizar.setBounds(233, 88, 135, 40);
//		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Excluir.png")));
		btExcluir.setForeground(Color.WHITE);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btExcluir.setBackground(new Color(96,204,204));
		btExcluir.setBounds(121, 310, 125, 38);
//		contentPane.add(btExcluir);
		
		JLabel lblNovoCargo = new JLabel("");
		lblNovoCargo.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/servicos.png")));
		lblNovoCargo.setForeground(Color.WHITE);
		lblNovoCargo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblNovoCargo.setBounds(42, 18, 60, 50);
		contentPane.add(lblNovoCargo);
		
		
		ImageIcon novoCargo = new ImageIcon(TelaCargo.class.getResource("/view/servicos.png"));
		Image novoCa = novoCargo.getImage().getScaledInstance(lblNovoCargo.getWidth(), lblNovoCargo.getHeight(), Image.SCALE_SMOOTH);	
		lblNovoCargo.setIcon(new ImageIcon(novoCa));
		
		lblTitulo = new JLabel("Novo Servi\u00E7o");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setBounds(112, 18, 232, 56);
		contentPane.add(lblTitulo);
	
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		
		ServicoController s = new ServicoController(tfId, tfNome, tfPreco, cbTipo);
		/**
		 * Ação do botão salvar
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = s.validarCampo();
				if(valido) {
					s.verificarAcao();
					TelaServico.this.setVisible(false);
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
					s.visualizar();
				}
			}
		});
		
		/**
		 * Ação do botão excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					s.excluir();
					TelaServico.this.setVisible(false);
				}
		});
		
		
		/**
		 * Ação do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaServico.this.setVisible(false);
				}
		});
	}
}
