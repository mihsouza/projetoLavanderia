package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.ContasController;
import java.awt.Color;

public class CadastrarSalarioFinanceiro extends JFrame {

	private JPanel contentPane;
	private JTextField tfVale;
	private JTextField tfSalario;
	private JTextField tfDtVale;
	private JTextField tfDtSalario;
	private JComboBox cbDivisao;
	private JButton btSalvar, btCalcular;
	private JTextField tfId;
	private JButton btCancelar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CadastrarSalarioFinanceiro frame = new CadastrarSalarioFinanceiro();
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
	public CadastrarSalarioFinanceiro() {
		
		setTitle("Cadastrar salário no financeiro");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaInicial.class.getResource("/view/icone_NB.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 319);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbDivisao = new JLabel("O montante ser\u00E1 pago em vale e sal\u00E1rio?");
		lbDivisao.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbDivisao.setBounds(10, 40, 251, 17);
		contentPane.add(lbDivisao);
		
		cbDivisao = new JComboBox();
		cbDivisao.setFont(new Font("Times New Roman", Font.BOLD, 14));
		cbDivisao.setModel(new DefaultComboBoxModel(new String[] {"Sim", "N\u00E3o"}));
		cbDivisao.setBounds(269, 38, 75, 20);
		contentPane.add(cbDivisao);
		
		JLabel lblValeR = new JLabel("Vale:  R$");
		lblValeR.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblValeR.setBounds(26, 103, 58, 17);
		contentPane.add(lblValeR);
		
		JLabel lblSalrioR = new JLabel("Sal\u00E1rio:  R$");
		lblSalrioR.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblSalrioR.setBounds(10, 138, 74, 17);
		contentPane.add(lblSalrioR);
		
		tfVale = new JTextField();
		tfVale.setEditable(false);
		tfVale.setBounds(94, 102, 86, 20);
		contentPane.add(tfVale);
		tfVale.setColumns(10);
		
		tfSalario = new JTextField();
		tfSalario.setEditable(false);
		tfSalario.setColumns(10);
		tfSalario.setBounds(94, 137, 86, 20);
		contentPane.add(tfSalario);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblData.setBounds(203, 103, 34, 17);
		contentPane.add(lblData);
		
		JLabel label = new JLabel("Data:");
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(203, 140, 34, 17);
		contentPane.add(label);
		
		tfDtVale = new JTextField();
		tfDtVale.setColumns(10);
		tfDtVale.setBounds(247, 102, 97, 20);
		contentPane.add(tfDtVale);
		
		tfDtSalario = new JTextField();
		tfDtSalario.setColumns(10);
		tfDtSalario.setBounds(247, 137, 97, 20);
		contentPane.add(tfDtSalario);
		
		JLabel lblAviso = new JLabel("*Os sal\u00E1rios ser\u00E3o cadastrados com um numero de 12 parcelas. ");
		lblAviso.setHorizontalAlignment(SwingConstants.LEFT);
		lblAviso.setFont(new Font("Times New Roman", Font.BOLD, 10));
		lblAviso.setBounds(10, 217, 287, 17);
		contentPane.add(lblAviso);
		
		JLabel lblParaExcluilosOu = new JLabel("Para exclui-los ou altera-los, favor acessar o menu funcionarios.");
		lblParaExcluilosOu.setHorizontalAlignment(SwingConstants.LEFT);
		lblParaExcluilosOu.setFont(new Font("Times New Roman", Font.BOLD, 10));
		lblParaExcluilosOu.setBounds(10, 232, 287, 17);
		contentPane.add(lblParaExcluilosOu);
		
		btCalcular = new JButton("Calcular");
		btCalcular.setBackground(new Color(210, 105, 30));
		btCalcular.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btCalcular.setBounds(133, 68, 89, 23);
		contentPane.add(btCalcular);
		
		btSalvar = new JButton("Cadastrar");
		btSalvar.setBackground(new Color(255, 127, 80));
		btSalvar.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btSalvar.setBounds(133, 183, 89, 23);
		contentPane.add(btSalvar);
		
		tfId = new JTextField();
		tfId.setBounds(299, 0, 45, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblIdFuncionario = new JLabel("Id funcionario");
		lblIdFuncionario.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIdFuncionario.setBounds(207, 3, 90, 17);
		contentPane.add(lblIdFuncionario);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btCancelar.setBackground(new Color(233, 150, 122));
		btCancelar.setBounds(275, 257, 89, 23);
		contentPane.add(btCancelar);
		
		controlarEventos();
	}
	
	/**
	 * O campo id do funcionario precisa receber o mesmo id da tela do cadastro de funcionario
	 */
	public void receberId(String id) {
		tfId.setText(id);
	}
	
	/**
	 * Método para definir as ações do botão da tela.
	 */
	public void controlarEventos() {
		ContasController c = new ContasController(tfVale, tfSalario, tfDtVale, tfDtSalario, cbDivisao, tfId);
		
		/**
		 * Ação do botão salvar.
		 */
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.inserirSalario();
			}
		});
		
		/**
		 * Ação do botão visualizar.
		 */
		btCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					c.calcular();
				}
		});
		
		/**
		 * Ações do botão cancelar
		 */
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CadastrarSalarioFinanceiro.this.setVisible(false);
				}
		});
	}
}
