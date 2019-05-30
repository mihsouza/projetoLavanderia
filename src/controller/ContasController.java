package controller;

import javax.naming.ldap.Rdn;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Contas;
import services.BD;

/**
 * Classe de controle. Nela ficara os métodos para manipulação dos dados no
 * banco de dados. Teremos dois métodos construtores, pois a utilizaremos em
 * duas telas diferentes.
 * 
 * @author michele
 *
 */
public class ContasController extends BD {
	/**
	 * Propriedades da TelaCadastroSalarioFinanceiro
	 */
	private JPanel contentPane;
	private JTextField tfVale;
	private JTextField tfSalario;
	private JTextField tfDtVale;
	private JTextField tfDtSalario;
	private JTextField tfId;
	private JComboBox cbDivisao;

	/**
	 * Instanciar componentes
	 * 
	 * @param contentPane
	 * @param tfVale
	 * @param tfSalario
	 * @param tfDtVale
	 * @param tfDtSalario
	 * @param cbDivisao
	 */
	public ContasController(JTextField tfVale, JTextField tfSalario, JTextField tfDtVale, JTextField tfDtSalario,
			JComboBox cbDivisao, JTextField tfId) {
		this.tfId = tfId;
		this.tfVale = tfVale;
		this.tfSalario = tfSalario;
		this.tfDtVale = tfDtVale;
		this.tfDtSalario = tfDtSalario;
		this.cbDivisao = cbDivisao;
	}

	/**
	 * Buscar no banco qual o valor da remuneracao referente ao cargo em que a
	 * pessoa foi contratada para exercer Fazer a dvisão caso o usuario escolha
	 * pagar a remuneracao em vale e salario ou apenas salario
	 * 
	 * @param id
	 */
	public void calcular() {
		double salarioTotal = 0;
		double vale = 0;
		double salario = 0;
		if (getConnection()) {
			try {
				String sql = "SELECT C.REMUNERACAO " + "FROM CARGO C, FUNCIONARIO F " + "WHERE F.CARGO = C.ID "
						+ "AND F.ID = " + tfId.getText();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					salarioTotal = (rs.getDouble("REMUNERACAO"));
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}

		if (cbDivisao.getSelectedItem().toString().equals("Sim")) {
			vale = salarioTotal * 0.40;
			salario = salarioTotal - vale;

			tfVale.setText(String.valueOf(vale));
			tfSalario.setText(String.valueOf(salario));
		} else {
			tfSalario.setText(String.valueOf(salarioTotal));
			tfVale.setText(String.valueOf(""));
		}
	}

	/**
	 * Método para cadastrar o pagamento dos salarios no contas a pagar
	 */
	public void inserirSalario() {
		String nomeFunc = null;
		if (getConnection()) {
			try {
				String sql = "SELECT FUNCIONARIO FROM FUNCIONARIO WHERE ID = " + tfId.getText();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					nomeFunc = (rs.getString("FUNCIONARIO"));
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}

		if (cbDivisao.getSelectedItem().toString().equals("Sim")) {
			try {
				String sql = "EXEC INSERIRFINANCEIRO '" + nomeFunc + " - Vale', " + tfVale.getText() + ", '"
						+ tfDtVale.getText() + "', " + "'mensal', 'Todo mês', 'Funcionario', 'Não Pago', 12";
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			try {
				String sql = "EXEC INSERIRFINANCEIRO '" + nomeFunc + " - Salario', " + tfSalario.getText() + ", '"
						+ tfDtSalario.getText() + "', " + "'mensal', 'Todo mês', 'Funcionario', 'Não Pago', 12";
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Lançamento realizado no financeiro!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			try {
				String sql = "EXEC INSERIRFINANCEIRO '" + nomeFunc + " - Salario', " + tfSalario.getText() + ", '"
						+ tfDtSalario.getText() + "', " + "'mensal', 'Todo mês', 'Funcionario', 'Não Pago', 12";
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Lançamento realizado no financeiro!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}
	}

	/**
	 * Instancia para a telaCadastroContas
	 */
	private JTextField tfPagoa;
	private JTextField tfValor;
	private JTextField tfData;
	private JTextField tfParcela;
	private JRadioButton rbNaoPago, rbPago;
	private JComboBox cbTipo, cbRec, cbFrequencia;

	public ContasController(JTextField tfId, JTextField tfPagoa, JTextField tfValor, JTextField tfData,
			JTextField tfParcela, JRadioButton rbNaoPago, JRadioButton rbPago, JComboBox cbTipo, JComboBox cbRec,
			JComboBox cbFrequencia) {
		this.tfId = tfId;
		this.tfPagoa = tfPagoa;
		this.tfValor = tfValor;
		this.tfData = tfData;
		this.tfParcela = tfParcela;
		this.rbNaoPago = rbNaoPago;
		this.rbPago = rbPago;
		this.cbTipo = cbTipo;
		this.cbRec = cbRec;
		this.cbFrequencia = cbFrequencia;
	}

	public void inserirConta() {
		Contas c = new Contas();
		c.setData(tfData.getText());
		c.setFrequencia(cbFrequencia.getSelectedItem().toString());
		c.setPagoa(tfPagoa.getText());
		c.setRecorrencia(cbRec.getSelectedItem().toString());
		c.setTipo(cbTipo.getSelectedItem().toString());
		try {
			c.setValor(Double.parseDouble(tfValor.getText()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor deve ser numerico", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		if (rbNaoPago.isSelected()) {
			c.setStatus("Não pago");
		} else {
			c.setStatus("Pago");
		}

		if (getConnection()) {
			try {
				String sql = "EXEC INSERIRFINANCEIRO '" + c.getPagoa() + "', " + c.getValor() + ", '" + c.getData()
						+ "', '" + c.getRecorrencia() + "', '" + c.getFrequencia() + "', '" + c.getTipo() + "', '"
						+ c.getStatus() + "', " + tfParcela.getText() + "";
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Lançamento realizado no financeiro!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}
	}

	/**
	 * Visualizar o registro completo de uma conta
	 */
	public void visualizar() {
		String id = JOptionPane.showInputDialog("Digite o codigo:");
		if (getConnection()) {
			try {
				String sql = "SELECT * FROM HISTCONTAS WHERE ID = " + id;
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfParcela.setEditable(false);
					tfId.setText(id);
					tfPagoa.setText(rs.getString("PAGOA"));
					tfValor.setText(String.valueOf(rs.getDouble("VALOR")) + "0");
					tfData.setText(rs.getString("DATA"));
					cbFrequencia.setSelectedItem(rs.getString("FREQUENCIA"));
					cbRec.setSelectedItem(rs.getString("RECORRENCIA"));
					cbTipo.setSelectedItem(rs.getString("OBS"));
					if (rs.getString("STATUS").equals("Pago")) {
						rbPago.setSelected(true);
					} else {
						rbNaoPago.setSelected(true);
					}

				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}

	}

	/**
	 * Alterar uma conta cadastrada. Obs: O usuario não podera alterar a frequencia
	 * e a recorrencia
	 */
	public void alterar() {
		JOptionPane.showMessageDialog(null, "Esta ação não podera realizar alteraçoes nos seguintes parametros:\n"
				+ "*Recorrencia\n" + "*Frequencia", "MESSAGE", JOptionPane.YES_NO_OPTION);
		String status = null;
		if (rbNaoPago.isSelected()) {
			status = "Não pago";
		} else {
			status = "Pago";
		}
		if (getConnection()) {
			try {
				String sql = "UPDATE HISTCONTAS SET PAGOA = '" + tfPagoa.getText() + "', DATA = '" + tfData.getText()
						+ "', VALOR = " + tfValor.getText() + ", STATUS = '" + status + "', OBS = '"
						+ cbTipo.getSelectedItem().toString() + "' WHERE ID = " + tfId.getText();
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Lançamento realizado no financeiro!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}

	}

	public void verificarAcao() {
		if (tfId.getText().trim().equals("")) {
			inserirConta();
		} else {
			alterar();
		}
	}

	public void excluir() {
		if (getConnection()) {
			try {
				String sql = "DELETE FROM HISTCONTAS WHERE ID = " + tfId.getText();
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Lançamento excluido do financeiro!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}
	}

	/**
	 * Instanciar para listar as Contas Cadastradas
	 */
	private JTextField tfPesquisa;
	private JTextField tfInicio;
	private JTextField tfFim;

	public ContasController(JRadioButton rbNaoPago, JRadioButton rbPago, JTextField tfPesquisa, JTextField tfInicio,
			JTextField tfFim) {
		this.rbNaoPago = rbNaoPago;
		this.rbPago = rbPago;
		this.tfPesquisa = tfPesquisa;
		this.tfInicio = tfInicio;
		this.tfFim = tfFim;
	}

	/**
	 * Pesquisar as contas cadastradas
	 */
	public void listar() {
		String sql = null;
		if (rbNaoPago.isSelected() && rbPago.isSelected()) {
			sql = "SELECT DATA, PAGOA, VALOR, STATUS " + "FROM HISTCONTAS WHERE DATA BETWEEN '" + tfInicio.getText()
					+ "' AND '" + tfFim.getText() + "' " + "AND STATUS LIKE 'Pago' " + "OR STATUS LIKE 'Não Pago' "
					+ "AND PAGOA LIKE '" + tfPesquisa.getText() + "%' ";
		} else if (rbNaoPago.isSelected()) {
			sql = "SELECT DATA, PAGOA, VALOR, STATUS " + "FROM HISTCONTAS WHERE DATA BETWEEN '" + tfInicio.getText()
					+ "' AND '" + tfFim.getText() + "' " + "AND STATUS LIKE 'Não Pago' " + "AND PAGOA LIKE '"
					+ tfPesquisa.getText() + "%' ";
		} else {
			sql = "SELECT DATA, PAGOA, VALOR, STATUS " + "FROM HISTCONTAS WHERE DATA BETWEEN '" + tfInicio.getText()
					+ "' AND '" + tfFim.getText() + "' " + "AND STATUS LIKE 'Pago' " + "AND PAGOA LIKE '"
					+ tfPesquisa.getText() + "%' ";
		}
		if (getConnection()) {
			try {
				st = con.createStatement();
				rs = st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
				sb.append("DATA             VALOR           STATUS         PAGO A\n");
				while (rs.next()) {
					sb.append(rs.getString("DATA") + "     R$" + rs.getDouble("VALOR") + "0      "
							+ rs.getString("STATUS") + "      " + rs.getString("PAGOA") + "\n");
				}
				JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Contas", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Fazer fechamento do caixa
	 */
	public ContasController() {
		// TODO Auto-generated constructor stub
	}

	public void fechamento() {
		String data = JOptionPane.showInputDialog("Digite a data para fechamento (dd/mm/yyyy) :");
		if (getConnection()) {
			try {
				String sql = "EXEC FECHARCAIXADIARIO '" + data + "'";
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Lançamento realizado no financeiro!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			try {
				String sql = "SELECT CAIXATOTAL, CONTATOTAL, LUCROTOTAL FROM HISTCAIXADIA WHERE DATA = '" + data +"'";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
				while (rs.next()) {
					sb.append("Caixa total:");
					sb.append("\n");
					sb.append("R$ " + rs.getDouble("CAIXATOTAL") + "0");
					sb.append("\n");
					sb.append("Total de contas pagas:");
					sb.append("\n");
					sb.append("R$ " + rs.getDouble("CONTATOTAL") + "0");
					sb.append("\n");
					sb.append("Lucro:");
					sb.append("\n");
					sb.append("R$ " + rs.getDouble("LUCROTOTAL") + "0");
				}
				JOptionPane.showMessageDialog(null, sb.toString(), "Fechamento do caixa",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}

		}
	}
}
