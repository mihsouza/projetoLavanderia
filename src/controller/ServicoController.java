package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Cargo;
import model.Servico;
import services.BD;

/**
 * Classe de controle. Nela ficara os métodos para manipulação dos dados no
 * banco de dados. Teremos dois métodos construtores, pois a utilizaremos em
 * duas telas diferentes.
 * 
 * @author michele
 *
 */
public class ServicoController extends BD {

	/**
	 * Atributos da classe TelaServico.
	 */
	private JTextField tfId;
	private JTextField tfNome;
	private JComboBox cbTipo;
	private JTextField tfPreco;

	/**
	 * Método pra instanciar os ojetos da classe TelaServico.
	 * 
	 * @param tfId    = id( não é editavel, pois o ususario não deve alterar o mesmo
	 *                no banco).
	 * @param tfNome  = nome do serviço.
	 * @param tfPreco = preço atribuida ao serviço.
	 * @param cbTipo
	 */
	public ServicoController(JTextField tfId, JTextField tfNome, JTextField tfPreco, JComboBox cbTipo) {
		this.cbTipo = cbTipo;
		this.tfId = tfId;
		this.tfNome = tfNome;
		this.tfPreco = tfPreco;
	}

	/**
	 * Método para validar campo obrigatório
	 * 
	 * @return
	 */
	public boolean validarCampo() {
		boolean valido = true;
		if (tfPreco.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo 'Remuneração(%)' não pode estar vazio");
			valido = false;
		}
		return valido;
	}

	/**
	 * Inserir um novo registro
	 */
	public void inserir() {
		Servico s = new Servico();
		s.setNome(tfNome.getText());
		s.setTipo(cbTipo.getSelectedItem().toString());
		s.setPreco(Double.parseDouble(tfPreco.getText()));
		if (getConnection()) {
			try {
				String sql = "INSERT INTO SERVICO VALUES ('" + s.getNome() + "', '" + s.getTipo() + "', " + s.getPreco()
						+ ")";
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			/**
			 * Preencher com o id que foi gerado no banco.
			 */
			try {
				String sql = "SELECT TOP 1 SERVICO.ID FROM SERVICO ORDER BY ID DESC";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfId.setText(String.valueOf(rs.getInt("ID")));
					JOptionPane.showMessageDialog(null, "Serviço inserido com sucesso!");
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Pemite ao usuario visualizar o servico escolhido.
	 */
	public void visualizar() {
		int servico = Integer.parseInt(JOptionPane.showInputDialog("Codigo do cargo a ser visualizado:"));
		if (getConnection()) {
			try {
				String sql = "SELECT SERVICO, TIPO, PRECO FROM SERVICO WHERE ID = " + servico;
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfId.setText(String.valueOf(servico));
					tfNome.setText(rs.getString("SERVICO"));
					cbTipo.setSelectedItem(rs.getString("TIPO"));// VERIFICAR
					tfPreco.setText(String.valueOf(rs.getDouble("PRECO")));
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Permite ao usuario editar os dados de um serviço.
	 */
	public void editar() {
		Servico s = new Servico();
		try {
			s.setId(Integer.parseInt(tfId.getText()));
		} catch (NumberFormatException erro) {
			JOptionPane.showMessageDialog(null, "Id deve ser numérico", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		s.setNome(tfNome.getText());
		s.setTipo(cbTipo.getSelectedItem().toString());
		try {
			s.setPreco(Double.parseDouble(tfPreco.getText()));
		} catch (NumberFormatException erro) {
			JOptionPane.showMessageDialog(null, "Preço deve ser numerico com duas casas decimai\n Exemplo: 2.10",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (getConnection()) {
			try {
				String sql = "UPDATE SERVICO SET SERVICO = '" + s.getNome() + "', TIPO = '" + s.getTipo() + "', PRECO = "
						+ s.getPreco() + "" + "WHERE ID = " + s.getId();
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Serviço alterado com sucesso!");
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
		Servico s = new Servico();
		if (tfId.getText().trim().equals("")) {
			inserir();
		} else if (getConnection()) {
			try {
				s.setId(Integer.parseInt(tfId.getText()));
				String sql = "SELECT ID FROM SERVICO WHERE ID = " + s.getId();
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
		Servico s = new Servico();
		try {
			s.setId(Integer.parseInt(tfId.getText()));
		} catch (NumberFormatException erro) {
			JOptionPane.showMessageDialog(null, "Id deve ser numérico", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (getConnection()) {
			String sql = "DELETE FROM SERVICO WHERE ID = " + s.getId();
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, s.getId() + " excluído com sucesso!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * atributos da tela ListaServico.
	 */
	private JTextField tfPesquisa;
	private JComboBox cbOrdenar;

	/**
	 * Método construtor para a classe ListaCargo
	 * 
	 * @param tfPesquisa
	 * @param cbOrdenar
	 */
	public ServicoController(JTextField tfPesquisa, JComboBox cbOrdenar) {
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
			ordenacao = "SERVICO";
		} else if (cbOrdenar.getSelectedItem().toString() == "Código") {
			ordenacao = "ID";
		} else if (cbOrdenar.getSelectedItem().toString() == "Tipo") {
			ordenacao = "TIPO";
		} else {
			ordenacao = "PRECO";
		}

		/**
		 * Realizar a consulta no banco.
		 */
		if (getConnection()) {
			try {
				String sql = "SELECT * FROM SERVICO WHERE SERVICO LIKE '" + tfPesquisa.getText() + "%' ORDER BY " + ordenacao;
				st = con.createStatement();
				rs = st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
					while (rs.next()) {
						sb.append("ID: " + rs.getInt("ID"));
						sb.append(" ");
						sb.append("NOME: " + rs.getString("SERVICO"));
						sb.append(" ");
						sb.append("TIPO: " + rs.getString("TIPO"));
						sb.append("    ");
						sb.append("PREÇO: R$" + rs.getDouble("PRECO") + "0");
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
