package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrdemServicoController;
import model.Cargo;
import model.Servico;
import model.ServicoOs;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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

public class TelaServicoOs extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfTipo;
	private JButton btSalvar, btCancelar;
	private JComboBox cbServico;
	private JTextField tfPreco;
	private JLabel lblServico;
	private JTextField tfServico;
	private JButton btAtualizar;
	private JLabel lblQuantidade;
	private JTextField tfQuantidade;
	private JTextField tfTotal;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaServicoOs frame = new TelaServicoOs();
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
	public TelaServicoOs() {
		
		setTitle("Cadastrar serviços na OS"); //Rafa: Alterar nome da tela
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png"))); //Rafa: Alterar ícone da tela
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 404);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 153));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Id servi\u00E7o:");
		lblNome.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblNome.setBounds(24, 64, 100, 32);
		contentPane.add(lblNome);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblTipo.setBounds(74, 145, 50, 32);
		contentPane.add(lblTipo);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblPreco.setBounds(322, 145, 66, 32);
		contentPane.add(lblPreco);
		
		JLabel lblId = new JLabel("OS:");
		lblId.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblId.setBounds(381, 11, 45, 32);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(430, 21, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfTipo = new JTextField();
		tfTipo.setEditable(false);
		tfTipo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTipo.setForeground(new Color(0, 0, 0));
		tfTipo.setBounds(134, 155, 149, 20);
		contentPane.add(tfTipo);
		tfTipo.setColumns(10);
		
		btSalvar = new JButton("Inserir");
		btSalvar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Salvar.png")));
		btSalvar.setForeground(Color.WHITE);
		btSalvar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btSalvar.setBackground(new Color(96,204,204));
		btSalvar.setBounds(372, 201, 132, 40);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Cancelar.png")));
		btCancelar.setForeground(Color.WHITE);
		btCancelar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btCancelar.setBackground(new Color(96,204,204));
		btCancelar.setBounds(371, 314, 132, 40);
		contentPane.add(btCancelar);
		
		cbServico = new JComboBox();
		cbServico.setBounds(134, 74, 89, 20);
		contentPane.add(cbServico);
		
		tfPreco = new JTextField();
		tfPreco.setEditable(false);
		tfPreco.setBounds(398, 155, 63, 20);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
		
		lblServico = new JLabel("Servi\u00E7o:");
		lblServico.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblServico.setBounds(43, 115, 81, 24);
		contentPane.add(lblServico);
		
		tfServico = new JTextField();
		tfServico.setForeground(Color.BLACK);
		tfServico.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfServico.setEditable(false);
		tfServico.setColumns(10);
		tfServico.setBounds(134, 121, 327, 20);
		contentPane.add(tfServico);
		
		btAtualizar = new JButton("Buscar");
//		btAtualizar.setBounds(257, 42, 73, 40);
		btAtualizar.setIcon(new ImageIcon(TelaCargo.class.getResource("/view/Visualizar.png")));
		btAtualizar.setForeground(Color.WHITE);
		btAtualizar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		btAtualizar.setBounds(252, 54, 132, 40);
		contentPane.add(btAtualizar);
		
		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblQuantidade.setBounds(5, 188, 119, 32);
		contentPane.add(lblQuantidade);
		
		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(131, 198, 55, 20);
		contentPane.add(tfQuantidade);
		tfQuantidade.setColumns(10);
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setColumns(10);
		tfTotal.setBounds(422, 278, 81, 20);
		contentPane.add(tfTotal);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblTotal.setBounds(355, 268, 57, 32);
		contentPane.add(lblTotal);

		//controlarEventos();
		
	}

	
	public void receberOrdem(String ordem) {
		tfId.setText(ordem);
	}
	
	public void controlarEventos() {
		OrdemServicoController o = new OrdemServicoController(tfId, tfTotal, tfTipo, cbServico,
			tfPreco, tfServico, tfQuantidade);
		
		o.carregarServico();
		/**
		 * Ações do botão salvar
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = o.validarCampoServico();
				if(valido) {
					o.inserirServico();
					tfPreco.setText("");
					tfPreco.setText("");
					tfQuantidade.setText("");
					tfServico.setText("");
					tfTipo.setText("");
				}
			}
		});
		
		/**
		 * Ações do botão atualizar
		 */
		btAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				o.carregarInformacoes();
			}
		});
		
		/**
		 * Ações do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					TelaServicoOs.this.setVisible(false);
				}
		});
	}
}
