package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Cargo;
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
public class ServicoController extends BD {

	/**
	 * Atributos da classe TelaServico.
	 */
	private JTextField tfId;
	private JTextField tfNome;
	private JComboBox cbTipo;
	private JTextField tfPreco;

	/**
	 * M�todo pra instanciar os ojetos da classe TelaServico.
	 * 
	 * @param tfId    = id( n�o � editavel, pois o ususario n�o deve alterar o mesmo
	 *                no banco).
	 * @param tfNome  = nome do servi�o.
	 * @param tfPreco = pre�o atribuida ao servi�o.
	 * @param cbTipo
	 */
	public ServicoController(JTextField tfId, JTextField tfNome, JTextField tfPreco, JComboBox cbTipo) {
		this.cbTipo = cbTipo;
		this.tfId = tfId;
		this.tfNome = tfNome;
		this.tfPreco = tfPreco;
	}

	/**
	 * M�todo para validar campo obrigat�rio
	 * 
	 * @return
	 */
	public boolean validarCampo() {
		boolean valido = true;
		if (tfPreco.getText().trim().equals("")) {
			TelaMensagem frame = new TelaMensagem();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lblAviso.setText("O campo 'Remunera��o' n�o pode estar vazio!");
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
				String sql = "SELECT SERVICO, TIPO, PRECO FROM SERVICO WHERE ID = " + tfId.getText();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfNome.setText(rs.getString("SERVICO"));
					cbTipo.setSelectedItem(rs.getString("TIPO"));// VERIFICAR
					tfPreco.setText(String.valueOf(rs.getDouble("PRECO")));
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conex�o");
		}
	}

	/**
	 * Permite ao usuario editar os dados de um servi�o.
	 */
	public void editar() {
		Servico s = new Servico();
		try {
			s.setId(Integer.parseInt(tfId.getText()));
		} catch (NumberFormatException erro) {
			JOptionPane.showMessageDialog(null, "Id deve ser num�rico", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		s.setNome(tfNome.getText());
		s.setTipo(cbTipo.getSelectedItem().toString());
		try {
			s.setPreco(Double.parseDouble(tfPreco.getText()));
		} catch (NumberFormatException erro) {
			JOptionPane.showMessageDialog(null, "Pre�o deve ser numerico com duas casas decimai\n Exemplo: 2.10",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (getConnection()) {
			try {
				String sql = "UPDATE SERVICO SET SERVICO = '" + s.getNome() + "', TIPO = '" + s.getTipo() + "', PRECO = "
						+ s.getPreco() + "" + "WHERE ID = " + s.getId();
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
			System.out.println("Falha na conex�o");
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
			JOptionPane.showMessageDialog(null, "Id deve ser num�rico", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		if (getConnection()) {
			String sql = "DELETE FROM SERVICO WHERE ID = " + s.getId();
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
	 * @param cbOrdenar
	 */
	public ServicoController(JTextField tfPesquisa, JComboBox cbOrdenar, DefaultTableModel modelo) {
		this.tfPesquisa = tfPesquisa;
		this.cbOrdenar = cbOrdenar;
		this.modelo = modelo;
	}

	/**
	 * M�todo para listar o cargo de acordo com os filtros e ordena��o.
	 */
	public void consulta() {
		String consulta = null;
		String ordenacao = null;
		/**
		 * Definir a ordenacao da listagem.
		 */
		if (cbOrdenar.getSelectedItem().toString() == "Nome") {
			ordenacao = "SERVICO";
		} else if (cbOrdenar.getSelectedItem().toString() == "C�digo") {
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
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("SERVICO"), "R$ " + rs.getDouble("PRECO") + "0", rs.getString("TIPO")});
				}
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}
	}

}
