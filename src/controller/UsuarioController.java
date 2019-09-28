package controller;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.BD;
import view.TelaCadastrar;
import view.TelaCargo;
import view.TelaConta;
import view.TelaMensagemAviso;
import view.TelaMensagemErro;
import view.TelaSistema;

public class UsuarioController extends BD {

	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfCadastrar;
	public JComboBox cbPermissao;
	public String permissao;

	public UsuarioController(JTextField tfUsuario, JTextField tfSenha, JTextField tfCadastrar, JComboBox cbPermissao) {
		super();
		this.tfUsuario = tfUsuario;
		this.tfSenha = tfSenha;
		this.tfCadastrar = tfCadastrar;
		this.cbPermissao = cbPermissao;
	}

	public boolean validarCampo() {
		boolean valido = true;
		if (tfUsuario.getText().trim().equals("") && tfSenha.getText().trim().equals("")
				&& tfCadastrar.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Preencha os campos obrigatórios!");
			valido = false;
		} else if (tfUsuario.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Usuário: campo obrigatório");
			valido = false;
		} else if (tfSenha.getText().trim().equals("") || tfCadastrar.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Senha: campo obrigatório");
			valido = false;
		}
		return valido;
	}

	public boolean validarSenha() {
		if (tfSenha.getText().trim().equals(tfCadastrar.getText())) {
			return true;
		}
		TelaMensagemErro frame = new TelaMensagemErro();
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.lbCampo.setText("Senhas não conferem!");
		return false;
	}

	public void inserir() {
		if (validarSenha()) {
			if (getConnection() == true) {
				try {
					String sql = "INSERT INTO USUARIO VALUES ('" + tfUsuario.getText() + "', '" + tfSenha.getText()
							+ "' , '" + cbPermissao.getSelectedItem() + "')";
					st = con.createStatement();
					st.executeUpdate(sql);
					TelaMensagemAviso frame = new TelaMensagemAviso();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					TelaCadastrar t = new TelaCadastrar();
					t.setVisible(false);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
		}
	}

	public UsuarioController(JTextField tfUsuario, JTextField tfSenha) {
		super();
		this.tfUsuario = tfUsuario;
		this.tfSenha = tfSenha;
	}

	public boolean validarAcesso() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT * FROM USUARIO WHERE USUARIO LIKE '" + tfUsuario.getText() + "' AND SENHA LIKE '"
						+ tfSenha.getText() + "'";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				if (rs.next()) {
					permissao = rs.getString("PERMISSAO");
					return true;
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
		TelaMensagemAviso frame = new TelaMensagemAviso();
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.lbTitulo.setText("Usuário e/ou senha inválidos!");
		return false;
	}

	public void logar() {
		validarAcesso();
		if (validarAcesso()) {
			if (permissao.equals("Gerente")) {
				TelaSistema frame = new TelaSistema();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(
						frame.EXIT_ON_CLOSE);
				frame.pMenu.add(frame.btCaixa);
				frame.pMenu.add(frame.btCadastrar);
				frame.pMenu.add(frame.btFluxo);
				frame.pMenu.add(frame.btListaCargo);
				frame.pMenu.add(frame.btListaCliente);
				frame.pMenu.add(frame.btListaConta);
				frame.pMenu.add(frame.btListaFuncionario);
				frame.pMenu.add(frame.btListaServico);
				frame.pMenu.add(frame.btCliente);
				frame.pMenu.add(frame.btReceber);
				frame.pMenu.add(frame.btConta);
				frame.lbUsuario.setText(tfUsuario.getText());
			} else if (permissao.equals("Administrador")) {
				TelaSistema frame = new TelaSistema();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(
						frame.EXIT_ON_CLOSE);
				frame.pMenu.add(frame.btCaixa);
				frame.pMenu.add(frame.btFluxo);
				frame.pMenu.add(frame.btListaCliente);
				frame.pMenu.add(frame.btListaConta);
				frame.pMenu.add(frame.btListaServico);
				frame.pMenu.add(frame.btCliente);
				frame.pMenu.add(frame.btReceber);
				frame.pMenu.add(frame.btConta);
				frame.lbUsuario.setText(tfUsuario.getText());
			} else {
				TelaSistema frame = new TelaSistema();
				frame.setDefaultCloseOperation(
						frame.EXIT_ON_CLOSE);
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lbUsuario.setText(tfUsuario.getText());
			}
		}
	}

}
