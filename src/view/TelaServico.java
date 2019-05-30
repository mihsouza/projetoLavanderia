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

public class TelaServico extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JComboBox cbTipo;
	private JTextField tfPreco;
	private JButton btSalvar, btCancelar, btVisualizar, btExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaServico frame = new TelaServico();
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
	public TelaServico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Servi\u00E7o:");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNome.setBounds(47, 90, 45, 14);
		contentPane.add(lblNome);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTipo.setBounds(64, 127, 28, 14);
		contentPane.add(lblTipo);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPreco.setBounds(56, 165, 36, 14);
		contentPane.add(lblPreco);
		
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
		
		btSalvar = new JButton("Salvar");
		btSalvar.setForeground(new Color(34, 139, 34));
		btSalvar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btSalvar.setBounds(323, 282, 89, 23);
		contentPane.add(btSalvar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setForeground(new Color(255, 0, 0));
		btCancelar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btCancelar.setBounds(422, 282, 89, 23);
		contentPane.add(btCancelar);
		
		cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"Lavar", "Passar", "Lavar e Passar"}));
		cbTipo.setBounds(102, 124, 178, 20);
		contentPane.add(cbTipo);
		
		tfPreco = new JTextField();
		tfPreco.setBounds(102, 162, 86, 20);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setForeground(Color.BLUE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btVisualizar.setBounds(124, 282, 89, 23);
		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setForeground(Color.BLACK);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btExcluir.setBounds(224, 282, 89, 23);
		contentPane.add(btExcluir);
		
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
					s.visualizar();
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
