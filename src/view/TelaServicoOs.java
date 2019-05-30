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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServicoOs frame = new TelaServicoOs();
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
	public TelaServicoOs() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Id servi\u00E7o:");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNome.setBounds(36, 90, 57, 14);
		contentPane.add(lblNome);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTipo.setBounds(64, 127, 28, 14);
		contentPane.add(lblTipo);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPreco.setBounds(261, 127, 36, 14);
		contentPane.add(lblPreco);
		
		JLabel lblId = new JLabel("OS:");
		lblId.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblId.setBounds(69, 47, 23, 14);
		contentPane.add(lblId);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(101, 44, 55, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfTipo = new JTextField();
		tfTipo.setEditable(false);
		tfTipo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfTipo.setForeground(new Color(0, 0, 0));
		tfTipo.setBounds(102, 124, 149, 20);
		contentPane.add(tfTipo);
		tfTipo.setColumns(10);
		
		btSalvar = new JButton("Inserir");
		btSalvar.setForeground(new Color(34, 139, 34));
		btSalvar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btSalvar.setBounds(422, 161, 89, 23);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setForeground(new Color(255, 0, 0));
		btCancelar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btCancelar.setBounds(422, 282, 89, 23);
		contentPane.add(btCancelar);
		
		cbServico = new JComboBox();
		cbServico.setBounds(102, 87, 57, 20);
		contentPane.add(cbServico);
		
		tfPreco = new JTextField();
		tfPreco.setEditable(false);
		tfPreco.setBounds(307, 124, 63, 20);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
		
		lblServico = new JLabel("Servi\u00E7o:");
		lblServico.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblServico.setBounds(169, 90, 45, 14);
		contentPane.add(lblServico);
		
		tfServico = new JTextField();
		tfServico.setForeground(Color.BLACK);
		tfServico.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfServico.setEditable(false);
		tfServico.setColumns(10);
		tfServico.setBounds(224, 87, 285, 20);
		contentPane.add(tfServico);
		
		btAtualizar = new JButton("@");
		btAtualizar.setBounds(171, 43, 45, 23);
		contentPane.add(btAtualizar);
		
		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblQuantidade.setBounds(380, 127, 66, 14);
		contentPane.add(lblQuantidade);
		
		tfQuantidade = new JTextField();
		tfQuantidade.setBounds(456, 124, 55, 20);
		contentPane.add(tfQuantidade);
		tfQuantidade.setColumns(10);
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setColumns(10);
		tfTotal.setBounds(448, 204, 63, 20);
		contentPane.add(tfTotal);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTotal.setBounds(402, 210, 36, 14);
		contentPane.add(lblTotal);

		controlarEventos();
		
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
