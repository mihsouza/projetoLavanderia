package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FuncionarioController;
import model.Cliente;
import model.Funcionario;
import model.Servico;

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
import java.awt.SystemColor;

public class TelaFuncionario extends JFrame {

	protected JPanel contentPane;
	protected JTextField tfId;
	private JTextField tfNome;
	private JTextField tfCpf;
	protected JButton btSalvar, btCancelar, btVisualizar, btExcluir, btFinanceiro;
	private JTextArea textAreaEndereco;
	private JTextField tfTelefone;
	private JLabel lblCep;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JTextField tfCep;
	private JTextField tfCidade;
	private JComboBox cbEstado;
	private JComboBox cbCargo;
	protected JLabel lblTitulo;
	private JLabel label;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaFuncionario frame = new TelaFuncionario();
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
	public TelaFuncionario() {
		
		setTitle("Funcionários");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 473);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		JLabel lblNome = new JLabel("Nome*:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNome.setBounds(62, 120, 55, 39);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF*:");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCpf.setBounds(79, 159, 38, 39);
		contentPane.add(lblCpf);
		
		JLabel lblDescricao = new JLabel("Endere\u00E7o:");
		lblDescricao.setForeground(Color.WHITE);
		lblDescricao.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDescricao.setBounds(47, 209, 70, 29);
		contentPane.add(lblDescricao);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblId.setBounds(91, 84, 38, 29);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(127, 86, 55, 25);
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
		
		JLabel lblTelefone = new JLabel("Telefone*:");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTelefone.setBounds(324, 166, 75, 25);
		contentPane.add(lblTelefone);
		
		textAreaEndereco = new JTextArea();
		textAreaEndereco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textAreaEndereco.setBounds(127, 209, 396, 50);
		contentPane.add(textAreaEndereco);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setIcon(new ImageIcon(TelaFuncionario.class.getResource("/view/Salvar.png")));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btSalvar.setBackground(new Color(60, 179, 113));
		btSalvar.setBounds(398, 361, 125, 40);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setIcon(new ImageIcon(TelaFuncionario.class.getResource("/view/cancel-button-white.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btCancelar.setBackground(new Color(205, 92, 92));
		btCancelar.setBounds(128, 361, 125, 40);
		contentPane.add(btCancelar);
		
		tfTelefone = new JTextField();
		tfTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(407, 169, 116, 25);
		contentPane.add(tfTelefone);
		
		lblCep = new JLabel("CEP:");
		lblCep.setForeground(Color.WHITE);
		lblCep.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCep.setBounds(78, 272, 39, 20);
		contentPane.add(lblCep);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setForeground(Color.WHITE);
		lblCidade.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCidade.setBounds(308, 270, 55, 25);
		contentPane.add(lblCidade);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblEstado.setBounds(407, 312, 55, 25);
		contentPane.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA ", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbEstado.setBounds(479, 312, 44, 25);
		contentPane.add(cbEstado);
		
		tfCep = new JTextField();
		tfCep.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCep.setColumns(10);
		tfCep.setBounds(127, 272, 98, 25);
		contentPane.add(tfCep);
		
		tfCidade = new JTextField();
		tfCidade.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCidade.setColumns(10);
		tfCidade.setBounds(373, 272, 150, 25);
		contentPane.add(tfCidade);
		
		cbCargo = new JComboBox();
		cbCargo.setBounds(127, 312, 219, 25);
		contentPane.add(cbCargo);
		
		JLabel lblCargo = new JLabel("Cargo*:");
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCargo.setBounds(62, 310, 55, 29);
		contentPane.add(lblCargo);
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setIcon(new ImageIcon(TelaFuncionario.class.getResource("/view/Visualizar.png")));
		btVisualizar.setForeground(Color.WHITE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btVisualizar.setBackground(new Color(96, 204, 204));
		btVisualizar.setBounds(211, 77, 135, 40);
//		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setIcon(new ImageIcon(TelaFuncionario.class.getResource("/view/Excluir.png")));
		btExcluir.setForeground(Color.WHITE);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btExcluir.setBackground(new Color(96, 204, 204));
		btExcluir.setBounds(10, 361, 125, 40);
//		contentPane.add(btExcluir);
		
		btFinanceiro = new JButton("Contas");
		btFinanceiro.setIcon(new ImageIcon(TelaFuncionario.class.getResource("/view/financeiro.png")));
		btFinanceiro.setForeground(Color.WHITE);
		btFinanceiro.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btFinanceiro.setBackground(new Color(107, 142, 35));
		btFinanceiro.setBounds(263, 361, 125, 40);
		contentPane.add(btFinanceiro);
		
		lblTitulo = new JLabel("Novo Funcion\u00E1rio");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTitulo.setBounds(116, 15, 309, 56);
		contentPane.add(lblTitulo);
		
		JLabel lblNovoFuncionario = new JLabel("");
		lblNovoFuncionario.setIcon(new ImageIcon(TelaFuncionario.class.getResource("/view/novo_funcionario.png")));
		lblNovoFuncionario.setForeground(Color.WHITE);
		lblNovoFuncionario.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblNovoFuncionario.setBounds(46, 15, 60, 50);
		contentPane.add(lblNovoFuncionario);
		
		ImageIcon novoFuncionario = new ImageIcon(TelaCargo.class.getResource("/view/novo_funcionario.png"));
		Image novoFun = novoFuncionario.getImage().getScaledInstance(lblNovoFuncionario.getWidth(), lblNovoFuncionario.getHeight(), Image.SCALE_SMOOTH);	
		lblNovoFuncionario.setIcon(new ImageIcon(novoFun));
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		FuncionarioController f = new FuncionarioController(tfId, tfNome, tfCpf, textAreaEndereco, tfTelefone, tfCep, tfCidade, cbEstado, cbCargo);
		f.carregarCargo();
		/**
		 * Ações do botão salvar
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = f.validarCampo();
				if(valido) {
					f.verificarAcao();
					TelaFuncionario.this.setVisible(false);
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
					f.visualizar();
				}
			}
		});
		
		/**
		 * Ação do botão excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					f.excluir();
					TelaFuncionario.this.setVisible(false);
				}
		});
		
		/**
		 * Ações do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaFuncionario.this.setVisible(false);
				}
		});
		
		/**
		 * Ações do botão Financeiro
		 */
		btFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CadastrarSalarioFinanceiro frame = new CadastrarSalarioFinanceiro();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.receberId(tfId.getText());
				}
		});
	}
}
