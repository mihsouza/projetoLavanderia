package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Cliente;
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
public class ClienteController extends BD {

	/**
	 * Componentes da classe TelaCliente
	 */
	private JTextField tfId;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextArea textAreaEndereco;
	private JTextField tfTelefone;
	private JTextField tfCep;
	private JTextField tfCidade;
	private JComboBox cbEstado;

	/**
	 * Instanciando os componentes
	 * 
	 * @param tfId
	 * @param tfNome
	 * @param tfCpf
	 * @param textAreaEndereco
	 * @param tfTelefone
	 * @param tfCep
	 * @param tfCidade
	 * @param cbEstado
	 */
	public ClienteController(JTextField tfId, JTextField tfNome, JTextField tfCpf, JTextArea textAreaEndereco,
			JTextField tfTelefone, JTextField tfCep, JTextField tfCidade, JComboBox cbEstado) {
		this.tfId = tfId;
		this.tfNome = tfNome;
		this.tfCpf = tfCpf;
		this.textAreaEndereco = textAreaEndereco;
		this.tfTelefone = tfTelefone;
		this.tfCep = tfCep;
		this.tfCidade = tfCidade;
		this.cbEstado = cbEstado;
	}

	/**
	 * Método para validar campo obrigatório
	 * 
	 * @return
	 */
	public boolean validarCampo() {
		boolean valido = true;
		if (tfCpf.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O campo 'CPF' não pode estar vazio");
			valido = false;
		}
		return valido;
	}

	/**
	 * Inserir um novo registro
	 */
	public void inserir() {
		Cliente c = new Cliente();
		c.setNome(tfNome.getText());
		c.setCpf(tfCpf.getText());
		c.setEndereco(textAreaEndereco.getText());
		c.setCep(tfCep.getText());
		c.setCidade(tfCidade.getText());
		c.setEstado(cbEstado.getSelectedItem().toString());
		c.setTelefone(tfTelefone.getText());
		if (getConnection()) {
			try {
				String sql = "INSERT INTO CLIENTE VALUES ('" + c.getNome() + "', '" + c.getCpf() + "', '"
						+ c.getEndereco() + "', '" + c.getCep() + "'," + "'" + c.getCidade() + "', '" + c.getEstado()
						+ "', '" + c.getTelefone() + "')";
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			try {
				String sql = "SELECT TOP 1 CLIENTE.ID FROM CLIENTE ORDER BY ID DESC";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfId.setText(String.valueOf(rs.getInt("ID")));
					JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
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
		String cpf = JOptionPane
				.showInputDialog("Digite o cpf do cliente:\n OBS.:Coloque traços e números (Ex.: 999.999.999-99)");
		if (getConnection()) {
			try {
				String sql = "SELECT * FROM CLIENTE WHERE CPF = '" + cpf + "'";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfId.setText(String.valueOf(rs.getInt("ID")));
					tfNome.setText(rs.getString("CLIENTE"));
					tfCpf.setText(rs.getString("CPF"));
					textAreaEndereco.setText(rs.getString("ENDERECO"));
					tfTelefone.setText(rs.getString("TELEFONE"));
					tfCep.setText(rs.getString("CEP"));
					tfCidade.setText(rs.getString("CIDADE"));
					cbEstado.setSelectedItem(rs.getString("ESTADO"));

				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Permite ao usuario editar os dados de um cliente
	 */
	public void editar() {
		Cliente c = new Cliente();
		c.setId(Integer.parseInt(tfId.getText()));
		c.setNome(tfNome.getText());
		c.setCpf(tfCpf.getText());
		c.setEndereco(textAreaEndereco.getText());
		c.setCep(tfCep.getText());
		c.setCidade(tfCidade.getText());
		c.setEstado(cbEstado.getSelectedItem().toString());
		c.setTelefone(tfTelefone.getText());
		if (getConnection()) {
			try {
				String sql = "UPDATE CLIENTE SET CLIENTE = '" + c.getNome() + "'," + " CPF = '" + c.getCpf()
						+ "', ENDERECO = '" + c.getEndereco() + "'," + "CEP = '" + c.getCep() + "', CIDADE = '"
						+ c.getCidade() + "', ESTADO = '" + c.getEstado() + "'," + "TELEFONE = '" + c.getTelefone()
						+ "' " + "WHERE ID = " + c.getId();
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Salvo!");
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
		Cliente c = new Cliente();
		if (tfId.getText().trim().equals("")) {
			inserir();
		} else if (getConnection()) {
			try {
				c.setId(Integer.parseInt(tfId.getText()));
				String sql = "SELECT ID FROM CLIENTE WHERE ID = " + c.getId();
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
		Cliente c = new Cliente();
		c.setId(Integer.parseInt(tfId.getText()));
		if (getConnection()) {
			String sql = "DELETE FROM CLIENTE WHERE ID = " + c.getId();
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Salvo!");
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
	 * @param tfCpf
	 * @param cbOrdenar
	 */
	public ClienteController(JTextField tfPesquisa, JComboBox cbOrdenar, JTextField tfCpf) {
		this.tfPesquisa = tfPesquisa;
		this.cbOrdenar = cbOrdenar;
		this.tfCpf = tfCpf;
	}

	/**
	 * Método para listar os clientes de acordo com os filtros e ordenação.
	 */
	public void consulta() {
		String consulta = null;
		String ordenacao = null;
		/**
		 * Definir a ordenacao da listagem.
		 */
		if (cbOrdenar.getSelectedItem().toString() == "Nome") {
			ordenacao = "CLIENTE";
		} else {
			ordenacao = "ID";
		}
		/**
		 * Definir o tipo de consulta.
		 */
		if (tfPesquisa.getText().trim().equals("")) {
			if (tfCpf.getText().trim().equals("")) {
				consulta = "SELECT ID, CLIENTE, CPF, TELEFONE FROM CLIENTE ORDER BY " + ordenacao;
			} else {
				consulta = "SELECT ID, CLIENTE, CPF, TELEFONE FROM CLIENTE WHERE CPF LIKE '" + tfCpf.getText()
						+ "%' ORDER BY " + ordenacao;
			}
		} else if (tfCpf.getText().trim().equals("")) {
			consulta = "SELECT ID, CLIENTE, CPF, TELEFONE FROM CLIENTE WHERE CLIENTE LIKE '" + tfPesquisa.getText()
					+ "%' ORDER BY " + ordenacao;
		} else {
			consulta = "SELECT ID, CLIENTE, CPF, TELEFONE FROM CLIENTE WHERE CLIENTE LIKE '" + tfPesquisa.getText()
					+ "%' AND CPF LIKE '" + tfCpf.getText() + "%' ORDER BY " + ordenacao;
		}
		/**
		 * Realizar a consulta no banco.
		 */
		if (getConnection()) {
			try {
				String sql = (consulta);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
				while (rs.next()) {
					sb.append("ID: " + rs.getInt("ID"));
					sb.append(" ");
					sb.append("NOME: " + rs.getString("CLIENTE"));
					sb.append(" ");
					sb.append("CPF: " + rs.getString("CPF"));
					sb.append(" ");
					sb.append("TELEFONE: " + rs.getString("TELEFONE"));
					sb.append("\n");
				}
				JOptionPane.showMessageDialog(null, sb.toString(), "Lista de CLIENTES",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}
	}
}
