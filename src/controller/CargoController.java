package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Cargo;
import services.BD;

/**
 * Classe de controle. Nela ficara os métodos para manipulação dos dados no
 * banco de dados. Teremos dois métodos construtores, pois a utilizaremos em
 * duas telas diferentes.
 * 
 * @author michele
 *
 */
public class CargoController extends BD {

	/**
	 * Atributos da classe Tela Cargo.
	 */
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField tfRemuneracao;
	private JTextArea textAreaDescricao;

	/**
	 * Metodo para instanciar os objetos na classe Telacargo.
	 * 
	 * @param tfRemuneracao     = campo para inserir a remuneração do cargo.
	 * @param tfNome            = nome do cargo.
	 * @param tfId              = id( não é editavel, pois o ususario não deve
	 *                          alterar o mesmo no banco).
	 * @param textAreaDescricao = Descrição e observações sobre o cargo inserido.
	 */
	public CargoController(JTextField tfRemuneracao, JTextField tfNome, JTextField tfId, JTextArea textAreaDescricao) {
		this.tfRemuneracao = tfRemuneracao;
		this.tfId = tfId;
		this.tfNome = tfNome;
		this.textAreaDescricao = textAreaDescricao;
	}

	/**
	 * Método para validar campo obrigatório.
	 * 
	 * @return 
	 */
	public boolean validarCampo() {
		boolean valido = true;
		if (tfRemuneracao.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo 'Remuneração(%)' não pode estar vazio");
			valido = false;
		}
		return valido;
	}

	/**
	 * Inserir um novo registro e traz o id do registro inserido.
	 */
	public void inserir() {
		Cargo c = new Cargo();
		c.setNome(tfNome.getText());
		c.setRemuneracao(tfRemuneracao.getText());
		c.setDescricao(textAreaDescricao.getText());
		if (getConnection() == true) {
			try {
				String sql = "INSERT INTO CARGO VALUES ('" + c.getNome() + "', 0." + c.getRemuneracao() + ", '"
						+ c.getDescricao() + "')";
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			/**
			 * Preencher com o id que foi gerado no banco.
			 */
			try {
				String sql = "SELECT TOP 1 CARGO.ID FROM CARGO ORDER BY ID DESC";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfId.setText(String.valueOf(rs.getInt("ID")));
					JOptionPane.showMessageDialog(null, "Cargo inserido com sucesso!");
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Pemite ao usuario visualizar o cargo escolhido.
	 */
	public void visualizar() {
		int cargo = Integer.parseInt(JOptionPane.showInputDialog("Codigo do cargo a ser visualizado:"));
		if (getConnection()) {
			try {
				String sql = "SELECT CARGO, REMUNERACAO, DESCRICAO FROM CARGO WHERE ID = " + cargo;
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfId.setText(String.valueOf(cargo));
					tfNome.setText(rs.getString("CARGO"));
					tfRemuneracao.setText(String.valueOf(rs.getDouble("REMUNERACAO")));
					textAreaDescricao.setText(rs.getString("DESCRICAO"));
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Permite ao usuario editar os dados de um cargo.
	 */
	public void editar() {
		Cargo c = new Cargo();
		c.setNome(tfNome.getText());
		try {
			c.setId(Integer.parseInt(tfId.getText()));
		} catch (NumberFormatException erro) {
			JOptionPane.showMessageDialog(null, "Id deve ser numérico", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		c.setRemuneracao(tfRemuneracao.getText());
		c.setDescricao(textAreaDescricao.getText());
		if (getConnection()) {
			try {
				String sql = "UPDATE CARGO SET CARGO = '" + c.getNome() + "', REMUNERACAO = 0." + c.getRemuneracao()
						+ ", DESCRICAO = '" + c.getDescricao() + "'" + "WHERE ID = " + c.getId();
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Cargo alterado com sucesso!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Verifca se a ação do botão salvar será de inserir um novo registro ou de
	 * alterar um existente.
	 */
	public void verificarAcao() {
		Cargo c = new Cargo();
		if (tfId.getText().trim().equals("")) {
			inserir();
		} else if (getConnection()) {
			c.setId(Integer.parseInt(tfId.getText()));
			try {
				String sql = "SELECT ID FROM CARGO WHERE ID = " + c.getId();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				if (rs.next()) {
					editar();
				} else {
					inserir();
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Excluir um registro do banco.
	 */
	public void excluir() {
		if (getConnection()) {
			String sql = "DELETE FROM CARGO WHERE ID = " + tfId.getText();
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, tfNome.getText() + " excluído com sucesso!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * atributos da tela ListaCargo.
	 */
	private JTextField tfPesquisa;
	private JComboBox cbOrdenar;

	/**
	 * Método construtor para a classe ListaCargo
	 * 
	 * @param tfPesquisa
	 * @param cbOrdenar
	 */
	public CargoController(JTextField tfPesquisa, JComboBox cbOrdenar) {
		this.tfPesquisa = tfPesquisa;
		this.cbOrdenar = cbOrdenar;
	}

	/**
	 * Método para listar o cargo de acordo com os filtros e ordenação.
	 */
	public void consulta() {
		String consulta = null;
		String ordenacao = null;
		/**
		 * Definir a ordenacao da listagem.
		 */
		if (cbOrdenar.getSelectedItem().toString() == "Nome") {
			ordenacao = "CARGO";
		} else if (cbOrdenar.getSelectedItem().toString() == "Código") {
			ordenacao = "ID";
		} else {
			ordenacao = "REMUNERACAO";
		}

		/**
		 * Realizar a consulta no banco.
		 */
		if (getConnection()) {
			try {
				String sql = "SELECT ID, CARGO, REMUNERACAO FROM CARGO WHERE CARGO LIKE '" + tfPesquisa.getText()
						+ "%' ORDER BY " + ordenacao;
				st = con.createStatement();
				rs = st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
					while (rs.next()) {
						sb.append(rs.getInt("ID"));
						sb.append(" ");
						sb.append(rs.getString("CARGO"));
						sb.append(" ");
						sb.append(rs.getDouble("REMUNERACAO"));
						sb.append("\n");
					}
					JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Cargos",
							JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}
	}
}
