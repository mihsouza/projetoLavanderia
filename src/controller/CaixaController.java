package controller;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.BD;
import view.TelaCargo;
import view.TelaMensagemAviso;
import view.TelaMensagemErro;

public class CaixaController extends BD {

	protected JTextField tfDe, tfAte;
	private JTextField tfDia, tfLucro;
	private JLabel lbEntrada;
	private JLabel lbSaidaConta, lbSaida;
	private JLabel lbLucro;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTextField tfEntrada;
	private JTextField tfConta;
	private JTextField tfComissao;
	
	public CaixaController(JTextField tfDia, JLabel lbEntrada, JLabel lbSaida, JLabel lbLucro, JLabel lbSaidaConta,
			DefaultTableModel modelo) {
		super();
		this.tfDia = tfDia;
		this.lbEntrada = lbEntrada;
		this.lbSaida = lbSaida;
		this.lbLucro = lbLucro;
		this.lbSaidaConta = lbSaidaConta;
		this.modelo = modelo;
	}
	
	
	public boolean validarCampo() {
		boolean valido = true;
		if (tfDia.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Data: campo obrigatório");
			valido = false;
		}
		return valido;
	}

	public void fecharComissao() {
			if (getConnection() == true) {
				try {
					String sql = "EXEC FECHARCOMISSAO '" + tfDia.getText() + "'";
					st = con.createStatement();
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
//				TelaMensagemAviso frame = new TelaMensagemAviso();
//				frame.setUndecorated(true);
//				frame.setVisible(true);
//				frame.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
	}
	
	public void fecharCaixa() {
		if (getConnection() == true) {
			try {
				String sql = "EXEC FECHARCAIXA '" + tfDia.getText() + "'";
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
//			TelaMensagemAviso frame = new TelaMensagemAviso();
//			frame.setUndecorated(true);
//			frame.setVisible(true);
//			frame.setLocationRelativeTo(null);
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}
	
	public void visualizarCaixa() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT * FROM HISTCAIXATOTAL WHERE DATA = '" + tfDia.getText() + "'";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					lbEntrada.setText(rs.getString("VALORENTRADA") + "0");
					lbSaidaConta.setText(rs.getString("VALORCONTA") + "0");
					lbSaida.setText(rs.getString("VALORCOMISSAO") + "0");
					lbLucro.setText(rs.getString("LUCRO") + "0");
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}
	
	public void listarComissao() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT F.FUNCIONARIO, H.VALORTOTAL, H.VALORCOMISSAO "
						+ "FROM HISTCOMISSAO H, FUNCIONARIO F "
						+ "WHERE F.ID = H.FUNCIONARIO "
						+ "AND DATA = '" + tfDia.getText() + "'";
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("FUNCIONARIO"), rs.getDouble("VALORTOTAL") + "0",  rs.getDouble("VALORCOMISSAO") + "0"});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}


	public CaixaController(JTextField tfDe, JTextField tfAte, DefaultTableModel modelo, JTextField tfLucro, JTextField tfEntrada, JTextField tfConta, JTextField tfComissao) {
		super();
		this.tfDe = tfDe;
		this.tfAte = tfAte;
		this.modelo = modelo;
		this.tfLucro = tfLucro;
		this.tfEntrada = tfEntrada;
		this.tfConta = tfConta;
		this.tfComissao = tfComissao;
	}
	
	public void listarCaixa() {
		if (getConnection() == true) {
			try {
				String sql = "select * from histCaixaTotal "
						+ "where data between '" + tfDe.getText() + "' and '" + tfAte.getText() + "' order by data desc";
				//System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("DATA"), rs.getDouble("VALORENTRADA") + "0",  rs.getDouble("VALORCONTA") + "0", 
							rs.getDouble("VALORCOMISSAO") + "0", rs.getDouble("LUCRO") + "0"});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}
	
	public void receberValores() {
		if (getConnection() == true) {
			try {
				String sql = "declare @lucro money \n" + 
						"exec retornaValores '" + tfDe.getText() + "', '" + tfAte.getText() + "', @lucro output \n" + 
						"select @lucro";
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				//while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					tfLucro.setText(String.valueOf(rs.getDouble("")));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			try {
				String sql = "declare @entrada money \n" + 
						"exec retornaEntrada '" + tfDe.getText() + "', '" + tfAte.getText() + "', @entrada output \n" + 
						"select @entrada";
				//System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				//while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					tfEntrada.setText(String.valueOf(rs.getDouble("")));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			try {
				String sql = "declare @entrada money \n" + 
						"exec retornaConta '" + tfDe.getText() + "', '" + tfAte.getText() + "', @entrada output \n" + 
						"select @entrada";
				//System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				//while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					tfConta.setText(String.valueOf(rs.getDouble("")));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			try {
				String sql = "declare @entrada money \n" + 
						"exec retornaComissao '" + tfDe.getText() + "', '" + tfAte.getText() + "', @entrada output \n" + 
						"select @entrada";
				//System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				//while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					tfComissao.setText(String.valueOf(rs.getDouble("")));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}



	
	public CaixaController(JTextField tfDe, JTextField tfAte, JTextField tfLucro, DefaultTableModel modelo,
			JTextField tfEntrada) {
		super();
		this.tfDe = tfDe;
		this.tfAte = tfAte;
		this.tfLucro = tfLucro;
		this.modelo = modelo;
		this.tfEntrada = tfEntrada;
	}


	public void listarComissaoFunc() {
		if (getConnection() == true) {
			try {
				String sql = "select f.funcionario, h.data, h.valorTotal, h.ValorComissao "
						+ "from funcionario f, histComissao h "
						+ "where f.id = h.funcionario "
						+ "and data between '" + tfDe.getText() + "' and '" + tfAte.getText() + "' "
						+ "and f.funcionario like '" + tfEntrada.getText() + "%' "
						+ "order by h.data";
				//System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("FUNCIONARIO"), rs.getString("DATA"), 
							rs.getDouble("VALORTOTAL") + "0", rs.getDouble("VALORCOMISSAO") + "0"});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			try {
				String sql = null;
				if(tfEntrada.getText().trim().equals("")) {
					sql = "select sum(ValorComissao) "
							+ "from histComissao "
							+ "where data between '" + tfDe.getText() + "' and '" + tfAte.getText() + "'";
				}else {
					sql = "select sum(ValorComissao) "
							+ "from histComissao "
							+ "where funcionario = (select id from FUNCIONARIO where funcionario like '" + tfEntrada.getText() + "%') "
							+ "and data between '" + tfDe.getText() + "' and '" + tfAte.getText() + "'";
				}
				//System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfLucro.setText(String.valueOf(rs.getDouble("")));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

}
