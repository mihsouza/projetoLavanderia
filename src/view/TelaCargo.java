package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CargoController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;

public class TelaCargo extends JFrame {
	
	/**
	 * Componentes da tela para inserir, visualizar e excluir dados no banco.
	 */
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField tfRemuneracao;
	private JButton btSalvar, btCancelar, btVisualizar;
	private JTextArea textAreaDescricao;
	private JButton btExcluir;

	/**
	 * Visualizar a aplicação.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCargo frame = new TelaCargo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criação do frame.
	 */
	public TelaCargo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Cargo:");
		lblNome.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNome.setBounds(54, 90, 38, 14);
		contentPane.add(lblNome);
		
		JLabel lblRemuneracao = new JLabel("Remunera\u00E7\u00E3o:");
		lblRemuneracao.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblRemuneracao.setBounds(10, 127, 82, 14);
		contentPane.add(lblRemuneracao);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDescricao.setBounds(28, 165, 64, 14);
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
		
		tfRemuneracao = new JTextField();
		tfRemuneracao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tfRemuneracao.setBounds(102, 124, 86, 20);
		contentPane.add(tfRemuneracao);
		tfRemuneracao.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Times New Roman", Font.BOLD, 13));
		label.setBounds(191, 127, 82, 14);
		contentPane.add(label);
		
		textAreaDescricao = new JTextArea();
		textAreaDescricao.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textAreaDescricao.setBounds(101, 163, 396, 72);
		contentPane.add(textAreaDescricao);
		
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
		
		btVisualizar = new JButton("Visualizar");
		btVisualizar.setForeground(Color.BLUE);
		btVisualizar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btVisualizar.setBounds(124, 282, 89, 23);
		contentPane.add(btVisualizar);
		
		btExcluir = new JButton("Excluir");
		btExcluir.setForeground(new Color(0, 0, 0));
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btExcluir.setBounds(224, 282, 89, 23);
		contentPane.add(btExcluir);
		
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
