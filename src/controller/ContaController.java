package controller;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.BD;
import view.TelaCargo;
import view.TelaMensagemAviso;
import view.TelaMensagemErro;

public class ContaController extends BD {

	protected JTextField tfNome, tfValor;
	private JTextField tfId;
	private JTextField tfData;
	public JRadioButton rbNaoPago, rbPago;
	public JComboBox cbTipo;
	
	

	public ContaController(JTextField tfNome, JTextField tfValor, JTextField tfId, JTextField tfData, JComboBox cbTipo, 
			JRadioButton rbNaoPago, JRadioButton rbPago) {
		super();
		this.tfNome = tfNome;
		this.tfValor = tfValor;
		this.tfId = tfId;
		this.tfData = tfData;
		this.cbTipo = cbTipo;
		this.rbNaoPago = rbNaoPago;
		this.rbPago = rbPago;
	}

	public boolean validarCampo() {
		boolean valido = true;
		if (tfNome.getText().trim().equals("") && tfValor.getText().trim().equals("") && tfData.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Nome, valor e data: campos obrigatórios");
			valido = false;
		} else if (tfNome.getText().trim().equals("") && tfValor.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Nome e valor: campos obrigatórios");
			valido = false;
		} else if (tfNome.getText().trim().equals("") && tfData.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Nome e data: campos obrigatórios");
			valido = false;
		}  else if (tfData.getText().trim().equals("") && tfValor.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Data e valor: campos obrigatórios");
			valido = false;
		}else if (tfNome.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Nome: campo obrigatório");
			valido = false;
		}else if (tfData.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Data: campo obrigatório");
			valido = false;
		}else if (tfValor.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Valor: campo obrigatório");
			valido = false;
		}
		return valido;
	}

	public boolean converter() {
		double valor = 0;
		try {
			valor = Double.parseDouble(tfValor.getText());
			return true;
		} catch (NumberFormatException erro) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Valor: Valor dever ser numérico (Ex.: 0.10)");
			return false;
		}
	}

	public void inserir() {
		if (converter()) {
			String status = null;
			if (rbNaoPago.isSelected()) {
				status = "Não pago";
			}else {
				status = "Pago";
			}
			if (getConnection() == true) {
				try {
					String sql = null;
					if(tfId.getText().trim().equals("")) {
						sql = "EXEC CONTAINSERIR null , '" + tfNome.getText() + "', "
							+ tfValor.getText() + ", '" + tfData.getText() + "', '" + cbTipo.getSelectedItem() + "', '" + status + "'";
					}else {
						sql = "EXEC CONTAINSERIR " + tfId.getText() + ", '" + tfNome.getText() + "', " + 
								tfValor.getText() + ", '" + tfData.getText() + "', '" + cbTipo.getSelectedItem() + "', '" + status + "'";
					}
					st = con.createStatement();
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
//				/**
//				 * Preencher com o id que foi gerado no banco.
//				 */
//				if (tfId.getText().trim().equals("")) {
//					try {
//						String sql = "SELECT TOP 1 CARGO.ID FROM CARGO ORDER BY ID DESC";
//						st = con.createStatement();
//						rs = st.executeQuery(sql);
//						while (rs.next()) {
//							tfId.setText(String.valueOf(rs.getInt("ID")));
//						}
//					} catch (Exception erro) {
//						JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
//					}
//				}
				TelaMensagemAviso frame = new TelaMensagemAviso();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
		}
	}

	public void excluir() {
		if (getConnection() == true) {
			try {
				String sql = "DELETE FROM CONTA WHERE ID = " + tfId.getText();
				st = con.createStatement();
				st.executeUpdate(sql);
				TelaMensagemAviso frame = new TelaMensagemAviso();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lbTitulo.setText("Excluído!");
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

	public void visualizar() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT * FROM CONTA WHERE ID = " + tfId.getText();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfNome.setText(rs.getString("CONTA"));
					tfValor.setText(String.valueOf(rs.getDouble("VALOR")));
					tfData.setText(rs.getString("DATA"));
					cbTipo.setSelectedItem(rs.getString("TIPO"));
					if (rs.getString("STATUS").equals("Pago")) {
						rbPago.setSelected(true);
					} else {
						rbNaoPago.setSelected(true);
					}
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}
	
	/*
	 * Listar os cargos
	 */
	
	protected JComboBox cbOrdenar, cbStatus;
	protected JTextField tfDe, tfAte;
	DefaultTableModel modelo = new DefaultTableModel();

	public ContaController(JTextField tfNome, JComboBox cbOrdenar, DefaultTableModel modelo, JComboBox cbStatus, JTextField tfDe, JTextField tfAte) {
		this.tfNome = tfNome;
		this.cbOrdenar = cbOrdenar;
		this.modelo = modelo;
		this.cbStatus = cbStatus;
		this.tfDe = tfDe;
		this.tfAte = tfAte;
	}
	
	public void listar() {
		String status = null;
		if(cbStatus.getSelectedItem().equals("Pago")) {
			status = "Pago";
		}else
			if(cbStatus.getSelectedItem().equals("Não pago")) {
				status = "Não pago";
			}else {
				status = "";
			}
		if (getConnection() == true) {
			try {
				String sql = "SELECT ID, CONTA, VALOR, DATA, STATUS FROM CONTA WHERE CONTA LIKE '" + tfNome.getText() + "%' "
						+ "AND STATUS LIKE '" + status + "%' "
								+ "AND DATA BETWEEN '"+ tfDe.getText() + "' AND '" + tfAte.getText() + "' "
										+ "ORDER BY " + cbOrdenar.getSelectedItem();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				//System.out.println(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("CONTA"),  "R$ " + rs.getDouble("VALOR") + "0", rs.getString("DATA"), rs.getString("STATUS")});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

}
