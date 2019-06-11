package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ClienteController;
import model.Cliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class TelaCliente extends JFrame {

	protected JPanel contentPane;
	protected JTextField tfId;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextArea textAreaEndereco;
	private JTextField tfTelefone;
	private JLabel lblCep;
	protected JLabel lblCidade, lblTitulo;
	private JLabel lblEstado;
	private JTextField tfCep;
	private JTextField tfCidade;
	private JComboBox cbEstado;
	protected JButton btCancelar, btSalvar, btExcluir, btVisualizar;

	/**
	 * Iniciar a aplicação.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		
		setTitle("Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setBackground(SystemColor.textHighlight);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNome = new JLabel("Nome* :");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNome.setBounds(52, 120, 60, 39);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF* :");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCpf.setBounds(68, 159, 44, 39);
		contentPane.add(lblCpf);
		
		JLabel lblDescricao = new JLabel("Endere\u00E7o :");
		lblDescricao.setForeground(Color.WHITE);
		lblDescricao.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDescricao.setBounds(33, 209, 79, 21);
		contentPane.add(lblDescricao);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblId.setBounds(91, 90, 38, 29);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(127, 92, 55, 25);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfNome.setForeground(new Color(0, 0, 0));
		tfNome.setBounds(127, 130, 396, 25);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		tfCpf.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCpf.setBounds(127, 169, 139, 25);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone* :");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTelefone.setBounds(318, 165, 79, 27);
		contentPane.add(lblTelefone);
		
		textAreaEndereco = new JTextArea();
		textAreaEndereco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textAreaEndereco.setBounds(127, 209, 396, 50);
		contentPane.add(textAreaEndereco);
		
		tfTelefone = new JTextField();
		tfTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(407, 169, 116, 25);
		contentPane.add(tfTelefone);
		
		lblCep = new JLabel("CEP :");
		lblCep.setForeground(Color.WHITE);
		lblCep.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCep.setBounds(83, 268, 38, 29);
		contentPane.add(lblCep);
		
		lblCidade = new JLabel("Cidade :");
		lblCidade.setForeground(Color.WHITE);
		lblCidade.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCidade.setBounds(61, 304, 60, 25);
		contentPane.add(lblCidade);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblEstado.setBounds(318, 263, 98, 39);
		contentPane.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA ", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbEstado.setBounds(377, 272, 44, 25);
		contentPane.add(cbEstado);
		
		tfCep = new JTextField();
		tfCep.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCep.setColumns(10);
		tfCep.setBounds(127, 272, 98, 25);
		contentPane.add(tfCep);
		
		tfCidade = new JTextField();
		tfCidade.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCidade.setColumns(10);
		tfCidade.setBounds(127, 307, 150, 25);
		contentPane.add(tfCidade);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/cancel-button-white.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.setBounds(263, 367, 125, 40);
		contentPane.add(btCancelar);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/Salvar.png")));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btSalvar.setBackground(new Color(60, 179, 113));
		btSalvar.setBounds(398, 367, 125, 40);
		contentPane.add(btSalvar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/Excluir.png")));
		btExcluir.setForeground(Color.WHITE);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btExcluir.setBackground(new Color(96, 204, 204));
		btExcluir.setBounds(127, 369, 125, 38);
//		contentPane.add(btExcluir);
		
		btVisualizar = new JButton("Buscar");
		btVisualizar.setIcon(new ImageIcon(TelaCliente.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(96, 204, 204));
		btVisualizar.setBounds(214, 77, 135, 40);
//		contentPane.add(btVisualizar);
		
		lblTitulo = new JLabel("Novo cliente");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(127, 25, 171, 29);
		contentPane.add(lblTitulo);
		
		JLabel lblImgCliente = new JLabel("");
		lblImgCliente.setIcon(new ImageIcon(ListaCliente.class.getResource("/view/clientes.png")));
		lblImgCliente.setForeground(Color.WHITE);
		lblImgCliente.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblImgCliente.setBounds(37, 11, 75, 55);
		contentPane.add(lblImgCliente);
		
		ImageIcon Cliente = new ImageIcon(TelaCargo.class.getResource("/view/clientes.png"));
		Image Cli = Cliente.getImage().getScaledInstance(lblImgCliente.getWidth(), lblImgCliente.getHeight(), Image.SCALE_SMOOTH);	
		lblImgCliente.setIcon(new ImageIcon(Cli));
		
		controlarEventos();
	}
	
	public void controlarEventos() {
		ClienteController c = new ClienteController(tfId, tfNome, tfCpf, textAreaEndereco, tfTelefone, tfCep, tfCidade, cbEstado);
		/**
		 * Ações do botão salvar
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = c.validarCampo();
				if(valido) {
					c.verificarAcao();
					TelaCliente.this.setVisible(false);
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
					c.visualizar();
				}	
			}
		});
		
		/**
		 * Ação do botão excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.excluir();
					TelaCliente.this.setVisible(false);
				}
		});
		
		
		/**
		 * Ação do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaCliente.this.setVisible(false);
				}
		});
	}
}
