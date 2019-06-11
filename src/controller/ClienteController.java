package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.Servico;
import services.BD;
import view.TelaMensagem;

/**
 * Classe de controle. Nela ficara os m�todos para manipula��o dos dados no
 * banco de dados. Teremos dois m�todos construtores, pois a utilizaremos em
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
	 * M�todo para validar campo obrigat�rio
	 * 
	 * @return
	 */
	public boolean validarCampo() {
		boolean valido = true;
		if (tfCpf.getText().trim().equals("")) {
			TelaMensagem frame = new TelaMensagem();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lblAviso.setText("O campo 'CPF' n�o pode estar vazio!");
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
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("Salvo!");
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conex�o");
		}
	}

	/**
	 * Pemite ao usuario visualizar o servico escolhido.
	 */
	public void visualizar() {
		if (getConnection()) {
			try {
				String sql = "SELECT * FROM CLIENTE WHERE ID = " + tfId.getText() + "";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
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
			System.out.println("Falha na conex�o");
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
				TelaMensagem frame = new TelaMensagem();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lblAviso.setText("Salvo!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conex�o");
		}
	}

	/**
	 * Verifca se a a��o do bot�o salvar ser� de inserir um novo registro ou de
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
			System.out.println("Falha na conex�o");
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
				TelaMensagem frame = new TelaMensagem();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lblAviso.setText("Exclu�do!");
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conex�o");
		}
	}

	/**
	 * atributos da tela ListaServico.
	 */
	private JTextField tfPesquisa;
	private JComboBox cbOrdenar;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * M�todo construtor para a classe ListaCargo
	 * 
	 * @param tfPesquisa
	 * @param tfCpf
	 * @param cbOrdenar
	 */
	public ClienteController(JTextField tfPesquisa, JComboBox cbOrdenar, JTextField tfCpf, DefaultTableModel modelo) {
		this.tfPesquisa = tfPesquisa;
		this.cbOrdenar = cbOrdenar;
		this.tfCpf = tfCpf;
		this.modelo = modelo;
	}

	/**
	 * M�todo para listar os clientes de acordo com os filtros e ordena��o.
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
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("CLIENTE"), rs.getString("CPF"), rs.getString("TELEFONE")});
				}
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}
	}
}
