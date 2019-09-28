package controller;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.BD;
import view.TelaCargo;
import view.TelaMensagemAviso;
import view.TelaMensagemErro;

public class CargoController extends BD {

	public JTextField tfNome, tfRemuneracao;
	public JTextField tfId;
	public JTextArea taDescricao;

	public CargoController(JTextField tfNome, JTextField tfRemuneracao, JTextField tfId, JTextArea taDescricao) {
		super();
		this.tfNome = tfNome;
		this.tfRemuneracao = tfRemuneracao;
		this.tfId = tfId;
		this.taDescricao = taDescricao;
	}

	public boolean validarCampo() {
		boolean valido = true;
		if (tfRemuneracao.getText().trim().equals("") && tfNome.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Cargo e Remuneração: campos obrigatórios");
			valido = false;
		} else if (tfNome.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Cardo: campo obrigatório");
			valido = false;
		} else if (tfRemuneracao.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Remuneração: campo obrigatório");
			valido = false;
		}
		return valido;
	}

	public boolean converter() {
		double remuneracao = 0;
		try {
			remuneracao = Double.parseDouble(tfRemuneracao.getText());
			return true;
		} catch (NumberFormatException erro) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Remuneração: Valor dever ser numérico (Ex.: 0.10)");
			return false;
		}
	}

	public void inserir() {
		if (converter()) {
			if (getConnection() == true) {
				try {
					String sql = null;
					if(tfId.getText().trim().equals("")) {
						sql = "EXEC CARGOINSERIR null , '" + tfNome.getText() + "', "
							+ tfRemuneracao.getText() + ", '" + taDescricao.getText() + "'";
					}else {
						sql = "EXEC CARGOINSERIR " + tfId.getText() + ", '" + tfNome.getText() + "', "
								+ tfRemuneracao.getText() + ", '" + taDescricao.getText() + "'";
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
				String sql = "DELETE FROM CARGO WHERE ID = " + tfId.getText();
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
				String sql = "SELECT * FROM CARGO WHERE ID = " + tfId.getText();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfNome.setText(rs.getString("CARGO"));
					tfRemuneracao.setText(String.valueOf(rs.getDouble("REMUNERACAO")));
					taDescricao.setText(rs.getString("DESCRICAO"));
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

	public CargoController(JTextField tfNome, JComboBox cbOrdenar, DefaultTableModel modelo) {
		this.tfNome = tfNome;
		this.cbOrdenar = cbOrdenar;
		this.modelo = modelo;
	}
	
	public void listar() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT * FROM CARGO WHERE CARGO LIKE '" + tfNome.getText() + "%' ORDER BY " + cbOrdenar.getSelectedItem();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("CARGO"),  rs.getDouble("REMUNERACAO") + "0"});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

}
