package controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Usuario;
import services.BD;
import view.TelaMenu;

/**
 * Classe de controle. Nela ficara os métodos para manipulação dos dados no
 * banco de dados. Teremos dois métodos construtores, pois a utilizaremos em
 * duas telas diferentes.
 * 
 * @author michele
 *
 */
public class UsuarioController {

	/**
	 * Propriedades do cadastro de usuarios
	 */
	private JTextField tfUsuario;
	private JTextField tfSenha;
	private JTextField tfConfirmar;

	/**
	 * Método para a TelaCadastro
	 * 
	 * @param tfUsuario
	 * @param tfSenha
	 * @param tfConfirmar
	 */
	public UsuarioController(JTextField tfUsuario, JTextField tfSenha, JTextField tfConfirmar) {
		super();
		this.tfUsuario = tfUsuario;
		this.tfSenha = tfSenha;
		this.tfConfirmar = tfConfirmar;
	}

	BD b = new BD();

	/**
	 * Validar campos obrigatorios
	 * 
	 * @return
	 */
	public boolean validarCampo() {
		boolean valido = true;
		if (tfUsuario.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo 'Usuario' não pode estar vazio", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			valido = false;
		} else if (tfSenha.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo 'Senha' não pode estar vazio", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			valido = false;
		} else if (tfConfirmar.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo 'Confirmar' não pode estar vazio", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			valido = false;
		}
		return valido;
	}

	/**
	 * Método para cadastrar um novo usuario
	 */
	public void inserirUsuario() {
		String usuario = tfUsuario.getText();
		String senha = tfSenha.getText();
		String confirmar = tfConfirmar.getText();

		if (confirmar.equals(senha)) {
			if (b.getConnection() == true) {
				try {
					String sql = "INSERT INTO USUARIO VALUES ('" + usuario + "', '" + senha + "')";
					b.st = b.con.createStatement();
					b.st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!", "MESSAGE",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					System.out.println("ERRO" + erro.toString());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Campos não conferem", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	/**
	 * Construtor para o Login
	 * @param tfUsuario
	 * @param tfSenha
	 */
	public UsuarioController(JTextField tfUsuario, JTextField tfSenha) {
		super();
		this.tfUsuario = tfUsuario;
		this.tfSenha = tfSenha;
	}

	/**
	 * Logar no sistema
	 */
	public boolean acesso() {
		boolean valido = false;
		String usuario = tfUsuario.getText();
		String senha = tfSenha.getText();
		if (b.getConnection()) {
			try {
				String sql = "SELECT * FROM USUARIO WHERE USUARIO LIKE '" + usuario + "' AND SENHA LIKE '" + senha
						+ "'";
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				if (b.rs.next()) {
					valido = true;
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}
		return valido;

	}

}
