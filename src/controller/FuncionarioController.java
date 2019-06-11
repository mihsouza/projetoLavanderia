package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Cliente;
import model.Funcionario;
import model.Servico;
import services.BD;
import view.TelaMensagem;

/**
 * Classe de controle. Nela ficara os métodos para manipulação dos dados no
 * banco de dados. Teremos dois métodos construtores, pois a utilizaremos em
 * duas telas diferentes.
 * 
 * @author michele
 *
 */
public class FuncionarioController extends BD {

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
	private JComboBox cbCargo;

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
	public FuncionarioController(JTextField tfId, JTextField tfNome, JTextField tfCpf, JTextArea textAreaEndereco,
			JTextField tfTelefone, JTextField tfCep, JTextField tfCidade, JComboBox cbEstado, JComboBox cbCargo) {
		this.tfId = tfId;
		this.tfNome = tfNome;
		this.tfCpf = tfCpf;
		this.textAreaEndereco = textAreaEndereco;
		this.tfTelefone = tfTelefone;
		this.tfCep = tfCep;
		this.tfCidade = tfCidade;
		this.cbEstado = cbEstado;
		this.cbCargo = cbCargo;
	}
	
	/**
	 * Popular comboBox com os cargs cadastrados no banco
	 */
	public void carregarCargo() {
		services.BD b = new services.BD();
		b.getConnection();
		try {
			String sql = "SELECT CARGO FROM CARGO";
			b.st = b.con.createStatement();
			b.rs = b.st.executeQuery(sql);
			while(b.rs.next()) {
				cbCargo.addItem(b.rs.getString("CARGO"));
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}

	/**
	 * Método para validar campo obrigatório
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
			frame.lblAviso.setText("O campo 'CPF' não pode estar vazio!");
			valido = false;
		}
		return valido;
	}

	/**
	 * Inserir um novo registro
	 */
	public void inserir() {
		Funcionario f = new Funcionario();
		f.setNome(tfNome.getText());
		f.setCpf(tfCpf.getText());
		f.setEndereco(textAreaEndereco.getText());
		f.setCep(tfCep.getText());
		f.setCidade(tfCidade.getText());
		f.setEstado(cbEstado.getSelectedItem().toString());
		f.setTelefone(tfTelefone.getText());
		f.setCargo(cbCargo.getSelectedItem().toString());
		services.BD b = new services.BD();
		if(b.getConnection()) {
			try {
				String sql = "INSERT INTO FUNCIONARIO VALUES ('" + f.getNome() +"', '" + f.getCpf() +"', '" + f.getEndereco() +"', '" + f.getCep() +"',"
						+ "'" + f.getCidade() +"', '" + f.getEstado() +"', '" + f.getTelefone() +"',(SELECT ID FROM CARGO WHERE CARGO LIKE '" + f.getCargo() + "'))";
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
			}catch(Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			try {
				String sql = "SELECT TOP 1 FUNCIONARIO.ID FROM FUNCIONARIO ORDER BY ID DESC";
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				while (b.rs.next()) {
					tfId.setText(String.valueOf(b.rs.getInt("ID")));
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("Salvo!");
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Pemite ao usuario visualizar o servico escolhido.
	 */
	public void visualizar() {
		if (getConnection()) {
			try {
				String sql = "SELECT F.ID, F.FUNCIONARIO, F.CPF, F.ENDERECO, F.CEP, F.CIDADE, F.ESTADO, F.TELEFONE, C.CARGO "
						+ "FROM FUNCIONARIO F, CARGO C "
						+ "WHERE C.ID = F.CARGO "
						+ "AND F.ID = " + tfId.getText() + "";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfId.setText(String.valueOf(rs.getInt("ID")));
					tfNome.setText(rs.getString("FUNCIONARIO"));
					tfCpf.setText(rs.getString("CPF"));
					textAreaEndereco.setText(rs.getString("ENDERECO"));
					tfTelefone.setText(rs.getString("TELEFONE"));
					tfCep.setText(rs.getString("CEP"));
					tfCidade.setText(rs.getString("CIDADE"));
					cbEstado.setSelectedItem(rs.getString("ESTADO"));
					cbCargo.setSelectedItem(rs.getString("CARGO"));
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
		Funcionario f = new Funcionario();
		f.setId(Integer.parseInt(tfId.getText()));
		f.setNome(tfNome.getText());
		f.setCpf(tfCpf.getText());
		f.setEndereco(textAreaEndereco.getText());
		f.setCep(tfCep.getText());
		f.setCidade(tfCidade.getText());
		f.setEstado(cbEstado.getSelectedItem().toString());
		f.setTelefone(tfTelefone.getText());
		f.setCargo(cbCargo.getSelectedItem().toString());
		services.BD b = new services.BD();
		if(b.getConnection()) {
			try {
				String consulta = "SELECT ID FROM CARGO WHERE CARGO = '" + f.getCargo() + "'";
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(consulta);
				int cargo = 0;
				while(b.rs.next()) {
					cargo = b.rs.getInt("id");
				}
				String sql = "UPDATE FUNCIONARIO SET FUNCIONARIO = '" + f.getNome() +"',"
						+ " CPF = '" + f.getCpf() + "', ENDERECO = '" + f.getEndereco() + "',"
								+ "CEP = '" + f.getCep() + "', CIDADE = '" + f.getCidade() + "', ESTADO = '" + f.getEstado() + "',"
										+ "TELEFONE = '" + f.getTelefone() + "', CARGO = " + cargo +" "
						+ "WHERE ID = " + f.getId();
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
				TelaMensagem frame = new TelaMensagem();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lblAviso.setText("Salvo!");
			}catch(Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Verifca se a ação do botão salvar será de inserir um novo registro ou de
	 * alterar um existente.
	 */
	public void verificarAcao() {
		Funcionario f = new Funcionario();
		if (tfId.getText().trim().equals("")) {
			inserir();
		} else 
			if (getConnection()) {
			try {
				f.setId(Integer.parseInt(tfId.getText()));
				String sql = "SELECT ID FROM FUNCIONARIO WHERE ID = " + tfId.getText();
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
			String sql = "DELETE FROM FUNCIONARIO WHERE ID = " + c.getId();
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
				TelaMensagem frame = new TelaMensagem();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lblAviso.setText("Excluído!");
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
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Método construtor para a classe ListaCargo
	 * 
	 * @param tfPesquisa
	 * @param tfCpf
	 * @param cbOrdenar
	 */
	public FuncionarioController(JTextField tfPesquisa, JComboBox cbOrdenar, JTextField tfCpf, DefaultTableModel modelo) {
		this.tfPesquisa = tfPesquisa;
		this.cbOrdenar = cbOrdenar;
		this.tfCpf = tfCpf;
		this.modelo = modelo;
	}

	/**
	 * Método para listar os clientes de acordo com os filtros e ordenação.
	 */
	public void consulta() {
		String ordenacao = null;
		/**
		 * Definir a ordenacao da listagem.
		 */
		if (cbOrdenar.getSelectedItem().toString() == "Nome") {
			ordenacao = "FUNCIONARIO";
		} else {
			ordenacao = "ID";
		}
	
		/**
		 * Realizar a consulta no banco.
		 */
		if (getConnection()) {
			try {
				String sql = "SELECT ID, FUNCIONARIO, CPF, TELEFONE FROM FUNCIONARIO WHERE FUNCIONARIO LIKE '" + tfPesquisa.getText()
				+ "%' AND CPF LIKE '" + tfCpf.getText() + "%' ORDER BY " + ordenacao;
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("FUNCIONARIO"), rs.getString("CPF"), rs.getString("TELEFONE")});
				}

			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}
	}
}
