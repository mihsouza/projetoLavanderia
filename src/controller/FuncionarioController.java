package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.BD;
import view.TelaCargo;
import view.TelaMensagemAviso;
import view.TelaMensagemErro;

public class FuncionarioController extends BD {

	public JTextField tfNome, tfTelefone, tfCpf;
	public JTextField tfId;
	public JTextArea taEndereco;
	public JComboBox cbCargo;

	public FuncionarioController(JTextField tfNome, JTextField tfTelefone, JTextField tfId, JTextArea taEndereco, JTextField tfCpf, JComboBox cbCargo) {
		super();
		this.tfNome = tfNome;
		this.tfTelefone = tfTelefone;
		this.tfId = tfId;
		this.taEndereco = taEndereco;
		this.tfCpf = tfCpf;
		this.cbCargo = cbCargo;
	}
	
	/**
	 * Popular comboBox com os cargs cadastrados no banco
	 */
	public void carregarCargo() {
		getConnection();
		try {
			String sql = "SELECT CARGO FROM CARGO";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				cbCargo.addItem(rs.getString("CARGO"));
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}

	public boolean validarCampo() {
		boolean valido = true;
		if (tfNome.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Nome: campo obrigatório");
			valido = false;
		}
		return valido;
	}


	public void inserir() {
			if (getConnection() == true) {
				String cargo = null;
				try {
					String sql = "SELECT ID FROM CARGO WHERE CARGO LIKE '" + cbCargo.getSelectedItem() + "'";
					st = con.createStatement();
					rs = st.executeQuery(sql);
					while(rs.next()) {
						cargo = String.valueOf(rs.getInt("ID"));
					}
				}catch(SQLException e) {
					System.out.println("ERRO" + e.toString());
				}
				try {
					String sql = null;
					if(tfId.getText().trim().equals("")) {
						sql = "EXEC FUNCIONARIOINSERIR null , '" + tfNome.getText() + "', '" + tfCpf.getText() + "',  '"
							+ tfTelefone.getText() + "', '" + taEndereco.getText() + "', " + cargo;
					}else {
						sql = "EXEC FUNCIONARIOINSERIR " + tfId.getText() + " , '" + tfNome.getText() + "', '" + tfCpf.getText() + "',  '"
								+ tfTelefone.getText() + "', '" + taEndereco.getText() + "', " + cargo;
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

	public void excluir() {
		if (getConnection() == true) {
			try {
				String sql = "DELETE FROM FUNCIONARIO WHERE ID = " + tfId.getText();
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
				String sql = "SELECT C.CARGO, F.FUNCIONARIO, F.CPF, F.TELEFONE, F.ENDERECO"
						+ " FROM FUNCIONARIO F, CARGO C WHERE "
						+ "C.ID = F.CARGO AND "
						+ "F.ID = " + tfId.getText();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfNome.setText(rs.getString("FUNCIONARIO"));
					tfCpf.setText(rs.getString("CPF"));
					tfTelefone.setText(rs.getString("TELEFONE"));
					taEndereco.setText(rs.getString("ENDERECO"));
					cbCargo.setSelectedItem(rs.getString("CARGO"));
					
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
	
	protected JComboBox cbOrdenar;
	DefaultTableModel modelo = new DefaultTableModel();

	public FuncionarioController(JTextField tfNome, JTextField tfCpf, JComboBox cbOrdenar, DefaultTableModel modelo) {
		this.tfNome = tfNome;
		this.tfCpf = tfCpf;
		this.cbOrdenar = cbOrdenar;
		this.modelo = modelo;
	}
	
	public void listar() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT C.CARGO, F.ID, F.FUNCIONARIO, F.TELEFONE "
						+ "FROM FUNCIONARIO F, CARGO C "
						+ "WHERE C.ID = F.CARGO AND "
						+ "F.FUNCIONARIO LIKE '" + tfNome.getText() + "%' AND F.CPF LIKE '" + tfCpf.getText() + "%' ORDER BY F." + cbOrdenar.getSelectedItem();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("FUNCIONARIO"),  rs.getString("TELEFONE"), rs.getString("CARGO")});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

}
