package controller;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.BD;
import view.TelaCargo;
import view.TelaMensagemAviso;
import view.TelaMensagemErro;

public class ServicoOrdemController extends BD {

	protected JTextField tfQuantidade;
	private JTextField tfPedido;
	private JTextField tfPreco;
	private JTextField tfAltura;
	private JTextField tfLargura;
	private JTextField tfId;
	public JTextField tfSaldo;
	DefaultTableModel modelo = new DefaultTableModel();

	
	public ServicoOrdemController(JTextField tfQuantidade, JTextField tfPedido, JTextField tfPreco, JTextField tfAltura,
			JTextField tfLargura, JTextField tfId, DefaultTableModel modelo, JTextField tfSaldo) {
		super();
		this.tfQuantidade = tfQuantidade;
		this.tfPedido = tfPedido;
		this.tfPreco = tfPreco;
		this.tfAltura = tfAltura;
		this.tfLargura = tfLargura;
		this.tfId = tfId;
		this.modelo = modelo;
		this.tfSaldo = tfSaldo;
	}

//
//	public boolean validarCampo() {
//		double lavar = 0;
//		double passar = 0;
//		try {
//			lavar = Double.parseDouble(tfLavar.getText());
//			passar = Double.parseDouble(tfPassar.getText());
//		} catch (NumberFormatException erro) {
//			TelaMensagemErro frame = new TelaMensagemErro();
//			frame.setUndecorated(true);
//			frame.setVisible(true);
//			frame.setLocationRelativeTo(null);
//			frame.lbCampo.setText("Preço deve ser numérico (Ex.: 3.10)");
//		}
//		boolean valido = true;
//		if (lavar <= 0 && passar <=0 && tfNome.getText().trim().equals("")) {
//			TelaMensagemErro frame = new TelaMensagemErro();
//			frame.setUndecorated(true);
//			frame.setVisible(true);
//			frame.setLocationRelativeTo(null);
//			frame.lbCampo.setText("Preencha os campos obrigatórios");
//			valido = false;
//		} else if (lavar <= 0 && passar <=0) {
//			TelaMensagemErro frame = new TelaMensagemErro();
//			frame.setUndecorated(true);
//			frame.setVisible(true);
//			frame.setLocationRelativeTo(null);
//			frame.lbCampo.setText("Atribua ao menos um valor para o serviço");
//			valido = false;
//		} else if (tfNome.getText().trim().equals("")) {
//			TelaMensagemErro frame = new TelaMensagemErro();
//			frame.setUndecorated(true);
//			frame.setVisible(true);
//			frame.setLocationRelativeTo(null);
//			frame.lbCampo.setText("Serviço: campo obrigatório");
//			valido = false;
//		}
//		return valido;
//	}
//
	public void inserir() {
		if (getConnection() == true) {
			try {
				String sql = "INSERT INTO SERVICOORDEM VALUES (" + tfId.getText() + ", " + tfPedido.getText() + ", "
						+ "(SELECT TOTAL FROM SERVICO WHERE ID = " + tfId.getText() + "), " + tfQuantidade.getText()
						+ ")";
				System.out.println(sql);
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			try {
				String sql = "update pedido set total = "
						+ "((select sum(total) from servicoordem where pedido = " + tfPedido.getText() + ") - (" + tfSaldo.getText() + ")), "
								+ "TOTALREAL = (select sum(total) from servicoordem where pedido = " + tfPedido.getText() + "), "
								+ "status = 'Pronto' where id =" + tfPedido.getText();
				System.out.println(sql);
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			TelaMensagemAviso frame = new TelaMensagemAviso();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}
	
	public void listarServicosNosPedidos() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT S.SERVICO, O.PRECO, O.QUANTIDADE, O.TOTAL "
						+ "FROM SERVICO S, SERVICOORDEM O "
						+ "WHERE S.ID = O.SERVICO "
						+ "AND O.PEDIDO = " + tfPedido.getText();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("SERVICO"), rs.getDouble("PRECO"), rs.getInt("QUANTIDADE"),
							rs.getDouble("TOTAL")});
					System.out.println(sql);
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

//
//	public void excluir() {
//		if (getConnection() == true) {
//			try {
//				String sql = "DELETE FROM SERVICO WHERE ID = " + tfId.getText();
//				st = con.createStatement();
//				st.executeUpdate(sql);
//				TelaMensagemAviso frame = new TelaMensagemAviso();
//				frame.setUndecorated(true);
//				frame.setVisible(true);
//				frame.setLocationRelativeTo(null);
//				frame.lbTitulo.setText("Excluído!");
//			} catch (Exception erro) {
//				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Falha na conexão");
//		}
//	}
//
//	public void visualizar() {
//		if (getConnection() == true) {
//			try {
//				String sql = "SELECT * FROM SERVICO WHERE ID = " + tfId.getText();
//				st = con.createStatement();
//				rs = st.executeQuery(sql);
//				while (rs.next()) {
//					tfNome.setText(rs.getString("SERVICO"));
//					cbTipo.setSelectedItem(rs.getString("TIPO"));
//					tfLavar.setText(String.valueOf(rs.getDouble("LAVAR")));
//					tfPassar.setText(String.valueOf(rs.getDouble("PASSAR")));
//				}
//			} catch (Exception erro) {
//				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Falha na conexão");
//		}
//	}
	
	/*
	 * Listar os serviços
	 */
	
//	public void listarServicos() {
//		if (getConnection() == true) {
//			try {
//				String sql = "SELECT * FROM SERVICO ORDER BY ID";
//				st = con.createStatement();
//				rs = st.executeQuery(sql);
//				while (modelo.getRowCount()>0) modelo.removeRow(0);
//				while (rs.next()) {
//					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("SERVICO"), rs.getString("TIPO"),
//							rs.getDouble("LAVAR") + "0", rs.getDouble("PASSAR") + "0"});
//				}
//			} catch (Exception erro) {
//				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Falha na conexão");
//		}
//	}
	
	public void inserirTapete() {
		double valor = 0;
		double preco = Double.parseDouble(tfPreco.getText());
		double altura = Double.parseDouble(tfAltura.getText());
		double largura = Double.parseDouble(tfLargura.getText());
		
		valor = (altura * largura) * preco;
		
		if (getConnection() == true) {
			try {
				String sql = "INSERT INTO SERVICOORDEM VALUES ( 1 , " + tfPedido.getText() + ", " + valor + ", 1)";
				System.out.println(sql);
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			try {
				String sql = "update pedido set total = "
						+ "((select sum(total) from servicoordem where pedido = " + tfPedido.getText() + ") - (" + tfSaldo.getText() + ")), "
								+ "TOTALREAL = (select sum(total) from servicoordem where pedido = " + tfPedido.getText() + "), "
								+ "status = 'Pronto' where id =" + tfPedido.getText();
				System.out.println(sql);
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			TelaMensagemAviso frame = new TelaMensagemAviso();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

}
