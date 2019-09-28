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

public class ServicoController extends BD {

	protected JTextField tfNome, tfLavar, tfServico;
	protected JComboBox cbTipo;
	private JTextField tfId;
	private JTextField tfPassar;
	//public String id;

	

	public ServicoController(JTextField tfServico, JTextField tfLavar, JComboBox cbTipo, JTextField tfId,
			JTextField tfPassar) {
		this.tfServico = tfServico;
		this.tfLavar = tfLavar;
		this.cbTipo = cbTipo;
		this.tfId = tfId;
		this.tfPassar = tfPassar;
	}

	public boolean validarCampo() {
		double lavar = 0;
		double passar = 0;
		try {
			lavar = Double.parseDouble(tfLavar.getText());
			passar = Double.parseDouble(tfPassar.getText());
		} catch (NumberFormatException erro) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Preço deve ser numérico (Ex.: 3.10)");
		}
		boolean valido = true;
		if (lavar <= 0 && passar <=0 && tfServico.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Preencha os campos obrigatórios");
			valido = false;
		} else if (lavar <= 0 && passar <=0) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Atribua ao menos um valor para o serviço");
			valido = false;
		} else if (tfServico.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Serviço: campo obrigatório");
			valido = false;
		}
		return valido;
	}

	public void inserir() {
			if (getConnection() == true) {
				try {
					String sql = "EXEC SERVICOINSERIR null , '" + tfServico.getText() + "', '"
							+ cbTipo.getSelectedItem() + "', " + tfLavar.getText() + ", " + tfPassar.getText();
					System.out.println(sql);
					st = con.createStatement();
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
//				/**
//				 * Preencher com o id que foi gerado no banco.
//				 */
				if (tfId.getText().trim().equals("")) {
					try {
						String sql = "SELECT TOP 1 SERVICO.ID FROM SERVICO ORDER BY ID DESC";
						st = con.createStatement();
						rs = st.executeQuery(sql);
						while (rs.next()) {
							tfId.setText(String.valueOf(rs.getInt("ID")));
						}
					} catch (Exception erro) {
						JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
					}
				}
				TelaMensagemAviso frame = new TelaMensagemAviso();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
	}
	
	public void alterar() {
		if (getConnection() == true) {
			try {
				String sql = "EXEC SERVICOINSERIR " + tfId.getText() + ", '" + tfServico.getText() + "', '"
							+ cbTipo.getSelectedItem() + "', " + tfLavar.getText() + ", " + tfPassar.getText();
				System.out.println(sql);
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
//			/**
//			 * Preencher com o id que foi gerado no banco.
//			 */
//			if (tfId.getText().trim().equals("")) {
//				try {
//					String sql = "SELECT TOP 1 CARGO.ID FROM CARGO ORDER BY ID DESC";
//					st = con.createStatement();
//					rs = st.executeQuery(sql);
//					while (rs.next()) {
//						tfId.setText(String.valueOf(rs.getInt("ID")));
//					}
//				} catch (Exception erro) {
//					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
//				}
//			}
			TelaMensagemAviso frame = new TelaMensagemAviso();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
}
	
	public ServicoController() {
	}
	
	public void excluir(String id) {
		if (getConnection() == true) {
			try {
				String sql = "DELETE FROM SERVICO WHERE ID = " + id;
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
	

	public void visualizar(String id) {
		System.out.println("Metodo" + id);
		if (getConnection() == true) {
			try {
				String sql = "SELECT * FROM SERVICO WHERE ID = " + id;
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfServico.setText(rs.getString("SERVICO"));
					cbTipo.setSelectedItem(rs.getString("TIPO"));
					tfLavar.setText(String.valueOf(rs.getDouble("LAVAR")));
					tfPassar.setText(String.valueOf(rs.getDouble("PASSAR")));
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

	public ServicoController(JTextField tfNome, JComboBox cbOrdenar, DefaultTableModel modelo) {
		this.tfNome = tfNome;
		this.cbOrdenar = cbOrdenar;
		this.modelo = modelo;
	}
	
	public void listar() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT * FROM SERVICO WHERE SERVICO LIKE '" + tfNome.getText() + "%' ORDER BY " + cbOrdenar.getSelectedItem();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("SERVICO"), rs.getString("TIPO")});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

}
