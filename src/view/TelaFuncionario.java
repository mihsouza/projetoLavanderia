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

public class TelaFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JButton btSalvar, btCancelar, btVisualizar, btExcluir, btFinanceiro;
	private JTextArea textAreaEndereco;
	private JTextField tfTelefone;
	private JLabel lblCep;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JTextField tfCep;
	private JTextField tfCidade;
	private JComboBox cbEstado;
	private JComboBox cbCargo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFuncionario frame = new TelaFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome*:");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNome.setBounds(50, 90, 42, 14);
		contentPane.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF*:");
		lblCpf.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCpf.setBounds(54, 127, 38, 14);
		contentPane.add(lblCpf);
		
		JLabel lblDescricao = new JLabel("Endere\u00E7o:");
		lblDescricao.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDescricao.setBounds(37, 165, 55, 14);
		contentPane.add(lblDescricao);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(78, 47, 14, 14);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(101, 44, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfNome.setForeground(new Color(0, 0, 0));
		tfNome.setBounds(102, 87, 395, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		tfCpf.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCpf.setBounds(102, 124, 139, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone*:");
		lblTelefone.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTelefone.setBounds(309, 127, 72, 14);
		contentPane.add(lblTelefone);
		
		textAreaEndereco = new JTextArea();
		textAreaEndereco.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textAreaEndereco.setBounds(101, 163, 396, 39);
		contentPane.add(textAreaEndereco);
		
		btSalvar = new JButton("Salvar");
		btSalvar.setForeground(new Color(34, 139, 34));
		btSalvar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btSalvar.setBounds(323, 299, 89, 23);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setForeground(new Color(255, 0, 0));
		btCancelar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btCancelar.setBounds(422, 299, 89, 23);
		contentPane.add(btCancelar);
		
		tfTelefone = new JTextField();
		tfTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(378, 124, 119, 20);
		contentPane.add(tfTelefone);
		
		lblCep = new JLabel("CEP:");
		lblCep.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCep.setBounds(64, 223, 28, 14);
		contentPane.add(lblCep);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCidade.setBounds(199, 223, 42, 14);
		contentPane.add(lblCidade);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblEstado.setBounds(411, 223, 42, 14);
		contentPane.add(lblEstado);
		
		cbEstado = new JComboBox();
		cbEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA ", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbEstado.setBounds(453, 220, 44, 20);
		contentPane.add(cbEstado);
		
		tfCep = new JTextField();
		tfCep.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCep.setColumns(10);
		tfCep.setBounds(101, 220, 83, 20);
		contentPane.add(tfCep);
		
		tfCidade = new JTextField();
		tfCidade.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfCidade.setColumns(10);
		tfCidade.setBounds(251, 220, 150, 20);
		contentPane.add(tfCidade);
		
		cbCargo = new JComboBox();
		cbCargo.setBounds(101, 256, 219, 20);
		contentPane.add(cbCargo);
		
		JLabel lblCargo = new JLabel("Cargo*:");
		lblCargo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCargo.setBounds(47, 259, 45, 14);
		contentPane.add(lblCargo);
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setForeground(Color.BLUE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btVisualizar.setBounds(124, 299, 89, 23);
		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setForeground(Color.BLACK);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btExcluir.setBounds(224, 299, 89, 23);
		contentPane.add(btExcluir);
		
		btFinanceiro = new JButton("Financeiro");
		btFinanceiro.setForeground(Color.BLUE);
		btFinanceiro.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btFinanceiro.setBounds(25, 299, 89, 23);
		contentPane.add(btFinanceiro);
		
		controlarEventos();

	}
	
	public void controlarEventos() {
		FuncionarioController f = new FuncionarioController(tfId, tfNome, tfCpf, textAreaEndereco, tfTelefone, tfCep, tfCidade, cbEstado, cbCargo);
		f.carregarCargo();
		/**
		 * A��es do bot�o salvar
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
		 * A��o do bot�o visualizar.
		 */
		btVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					f.visualizar();
				}
		});
		
		/**
		 * A��o do bot�o excluir.
		 */
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					f.excluir();
					TelaFuncionario.this.setVisible(false);
				}
		});
		
		/**
		 * A��es do bot�o cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaFuncionario.this.setVisible(false);
				}
		});
		
		/**
		 * A��es do bot�o Financeiro
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
