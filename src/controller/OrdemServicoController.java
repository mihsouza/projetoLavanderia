package controller;

import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Funcionario;
import model.OrdemServico;
import model.Pedido;
import model.Servico;
import model.ServicoOs;
import view.TelaMensagem;

/**
 * Classe de controle. Nela ficara os métodos para manipulação dos dados no
 * banco de dados. Teremos dois métodos construtores, pois a utilizaremos em
 * duas telas diferentes.
 * 
 * @author michele
 *
 */
public class OrdemServicoController {

	/**
	 * Componentes da classe TelaCliente
	 */
	private JTextField tfId;
	private JTextField tfDataEntrada;
	private JTextArea textAreaObs;
	private JTextField tfDataPrevista;
	private JTextField tfTotal;
	private JTextField tfDataExecut;
	private JComboBox cbFuncionario;
	private JTextField tfPedido;
	private JTextField tfCliente;
	private JRadioButton rbAtraso;
	private JRadioButton rbEspera;
	private JRadioButton rbFinalizado;

	/**
	 * Instanciando os componentes
	 * 
	 * @param tfId
	 * @param tfDataEntrada
	 * @param textAreaObs
	 * @param tfDataPrevista
	 * @param tfTotal
	 * @param tfDataExecut
	 * @param cbAtendente
	 * @param tfPedido
	 * @param tfCliente
	 * @param rbAtraso
	 * @param rbEspera
	 * @param rbFinalizado
	 * @param tfPesquisa
	 * @param cbOrdenar
	 */

	public OrdemServicoController(JTextField tfId, JTextField tfDataEntrada, JTextArea textAreaObs,
			JTextField tfDataPrevista, JTextField tfTotal, JTextField tfDataExecut, JComboBox cbFuncionario,
			JTextField tfPedido, JTextField tfCliente, JRadioButton rbAtraso, JRadioButton rbEspera,
			JRadioButton rbFinalizado) {
		super();
		this.tfId = tfId;
		this.tfDataEntrada = tfDataEntrada;
		this.textAreaObs = textAreaObs;
		this.tfDataPrevista = tfDataPrevista;
		this.tfTotal = tfTotal;
		this.tfDataExecut = tfDataExecut;
		this.cbFuncionario = cbFuncionario;
		this.tfPedido = tfPedido;
		this.tfCliente = tfCliente;
		this.rbAtraso = rbAtraso;
		this.rbEspera = rbEspera;
		this.rbFinalizado = rbFinalizado;
	}

	/**
	 * Preenche o comboBox de funcionarios
	 */
	public void carregarFuncionario() {
		services.BD b = new services.BD();
		b.getConnection();
		try {
			String sql = "SELECT FUNCIONARIO FROM FUNCIONARIO "
					+ "WHERE CARGO = (SELECT ID FROM CARGO WHERE CARGO LIKE 'PASSADEIRA') "
					+ "OR CARGO = (SELECT ID FROM CARGO WHERE CARGO LIKE 'LAVADEIRA')";
			b.st = b.con.createStatement();
			b.rs = b.st.executeQuery(sql);
			while (b.rs.next()) {
				cbFuncionario.addItem(b.rs.getString("FUNCIONARIO"));
			}
		} catch (SQLException e) {
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
		if (tfDataPrevista.getText().trim().equals("")) {
			TelaMensagem frame = new TelaMensagem();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lblAviso.setText("O campo 'Data prevista' não pode estar vazio!");
			valido = false;
		}
		return valido;
	}

	/**
	 * Inserir um novo registro
	 */
	public void inserir() {
		OrdemServico o = new OrdemServico();
		o.setPedido(Integer.parseInt(tfPedido.getText()));
		o.setDataEntrada(tfDataEntrada.getText());
		o.setDataPrevista(tfDataPrevista.getText());
		o.setObservacao(textAreaObs.getText());
		if (tfDataExecut.getText().trim().equals("")) {
			o.setDataExecut(null);
		} else {
			o.setDataExecut("'" + tfDataExecut.getText() + "'");
		}
		o.setFuncionario(cbFuncionario.getSelectedItem().toString());
		if (tfTotal.getText().trim().equals("")) {
			o.setPreco(0);
		} else {
			try {
				o.setPreco(Double.parseDouble(tfTotal.getText()));
			} catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(null, "Total deve ser numerico com duas casas decimai\n Exemplo: 2.10",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (rbAtraso.isSelected()) {
			o.setStatus("Em atraso");
		} else if (rbEspera.isSelected()) {
			o.setStatus("Em espera");
		} else {
			o.setStatus("Finalizado");
		}
		services.BD b = new services.BD();
		if (b.getConnection()) {
			try {
				String sql = "INSERT INTO ORDEM VALUES (" + o.getPedido()
						+ ", (SELECT ID FROM FUNCIONARIO WHERE FUNCIONARIO LIKE '"
						+ cbFuncionario.getSelectedItem().toString() + "') , '" + o.getDataEntrada() + "', '"
						+ o.getDataPrevista() + "'," + "'" + o.getStatus() + "', " + o.getPreco() + ", "
						+ o.getDataExecut() + ", '" + o.getObservacao() + "')";
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			try {
				String sql = "SELECT TOP 1 ORDEM.ID FROM ORDEM ORDER BY ID DESC";
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				while (b.rs.next()) {
					tfId.setText(String.valueOf(b.rs.getInt("ID")));
					TelaMensagem frame = new TelaMensagem();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.lblAviso.setText("Ordem de serviço gerada com sucesso!");
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Editar um ordem de serviço e ja alterar o status e preço no pedido
	 */
	public void editar() {
		OrdemServico o = new OrdemServico();
		o.setId(Integer.parseInt(tfId.getText()));
		o.setObservacao(textAreaObs.getText());
		if (tfDataExecut.getText().trim().equals("")) {
			o.setDataExecut(null);
		} else {
			o.setDataExecut("'" + tfDataExecut.getText() + "'");
		}
		o.setFuncionario(cbFuncionario.getSelectedItem().toString());
		if (tfTotal.getText().trim().equals("")) {
			o.setPreco(0);
		} else {
			try {
				o.setPreco(Double.parseDouble(tfTotal.getText()));
			} catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(null, "Total deve ser numerico com duas casas decimais\n Exemplo: 2.10",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (rbAtraso.isSelected()) {
			o.setStatus("Em atraso");
		} else if (rbEspera.isSelected()) {
			o.setStatus("Em espera");
		} else {
			o.setStatus("Finalizado");
		}
		services.BD b = new services.BD();
		if (b.getConnection()) {
			try {
				String sql = "UPDATE ORDEM SET FUNCIONARIO = (SELECT ID FROM FUNCIONARIO WHERE FUNCIONARIO LIKE '"
						+ cbFuncionario.getSelectedItem().toString() + "'), STATUS = '" + o.getStatus() + "', PRECO = "
						+ o.getPreco() + ", DATAEXECUT = " + o.getDataExecut() + ", " + "OBSERVACAO  = '"
						+ o.getObservacao() + "' " + "WHERE ID = " + o.getId();
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);

				String updatePedido = "UPDATE PEDIDO SET STATUS = '" + o.getStatus() + "', PRECO = " + o.getPreco()
						+ " , " + "OBSERVACAO  = '" + o.getObservacao() + "' " + "WHERE ID = (SELECT PEDIDO FROM ORDEM WHERE ID = " + o.getId() + ")";
				b.st = b.con.createStatement();
				b.st.executeUpdate(updatePedido);
				TelaMensagem frame = new TelaMensagem();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lblAviso.setText("Salvo!");
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
		services.BD b = new services.BD();
		Funcionario f = new Funcionario();
		if (tfId.getText().trim().equals("")) {
			inserir();
		} else if (b.getConnection()) {
			try {
				f.setId(Integer.parseInt(tfId.getText()));
				String sql = "SELECT * FROM ORDEM WHERE ID = " + f.getId();
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				if (b.rs.next()) {
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
	 * Visualizar uma OS
	 */
	public void visualizar() {
		services.BD b = new services.BD();
		if (b.getConnection()) {
			try {
				String sql = "SELECT O.ID, C.CLIENTE, O.PEDIDO, F.FUNCIONARIO, O.DATAENTRADA, O.DATAPREVISTA, O.STATUS, O.PRECO, O.DATAEXECUT, O.OBSERVACAO "
						+ "FROM ORDEM O, PEDIDO P, FUNCIONARIO F, CLIENTE C " + "WHERE O.FUNCIONARIO = F.ID "
						+ "AND P.ID = O.PEDIDO " + "AND P.CLIENTE = C.ID " + "AND O.ID = " + tfId.getText();
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				while (b.rs.next()) {
					tfId.setText(String.valueOf(b.rs.getInt("ID")));
					tfDataEntrada.setText(b.rs.getString("DATAENTRADA"));
					tfDataPrevista.setText(b.rs.getString("DATAPREVISTA"));
					tfDataExecut.setText(b.rs.getString("DATAEXECUT"));
					tfTotal.setText(String.valueOf(b.rs.getDouble("PRECO")));
					tfCliente.setText(b.rs.getString("CLIENTE"));
					if (b.rs.getString("STATUS").equals("Finalizado")) {
						rbFinalizado.setSelected(true);
					} else if (b.rs.getString("STATUS").equals("Em espera")) {
						rbEspera.setSelected(true);
					} else {
						rbAtraso.setSelected(true);
					}
					cbFuncionario.setSelectedItem(b.rs.getString("FUNCIONARIO"));
					tfPedido.setText(String.valueOf(b.rs.getInt("PEDIDO")));
					textAreaObs.setText(b.rs.getString("OBSERVACAO"));
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Excluir um registro
	 */
	public void excluir() {
		OrdemServico o = new OrdemServico();
		o.setId(Integer.parseInt(tfId.getText()));
		services.BD b = new services.BD();
		if (b.getConnection()) {
			String sql = "DELETE FROM ORDEM WHERE ID = " + o.getId();
			try {
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
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
	 * atributos da tela ListaOrdemServico.
	 */
	private JTextField tfPesquisa;
	private JComboBox cbOrdenar;
	DefaultTableModel modelo = new DefaultTableModel();

	/**
	 * Método construtor para a classe ListaCargo
	 * 
	 * @param rbAtraso
	 * @param rbEspera
	 * @param rbFinalizado
	 * @param tfPesquisa
	 * @param cbOrdenar
	 */
	public OrdemServicoController(JRadioButton rbAtraso, JRadioButton rbEspera, JRadioButton rbFinalizado,
			JTextField tfPesquisa, JComboBox cbOrdenar, DefaultTableModel modelo) {
		super();
		this.rbAtraso = rbAtraso;
		this.rbEspera = rbEspera;
		this.rbFinalizado = rbFinalizado;
		this.tfPesquisa = tfPesquisa;
		this.cbOrdenar = cbOrdenar;
		this.modelo = modelo;
	}

	public void consulta() {
		String ordenacao = null;
		/**
		 * Definir a ordenacao da listagem.
		 */
		if (cbOrdenar.getSelectedItem().toString() == "Funcionario") {
			ordenacao = "FUNCIONARIO";
		} else if (cbOrdenar.getSelectedItem().toString() == "Código") {
			ordenacao = "ID";
		} else if (cbOrdenar.getSelectedItem().toString() == "Status") {
			ordenacao = "STATUS";
		} else {
			ordenacao = "PRECO";
		}

		String sql = null;
		/**
		 * Filtro status
		 */
		String status = null;
		if (rbAtraso.isSelected()) {
			status = "Em atraso";
			sql = "SELECT O.ID, C.CLIENTE, O.PEDIDO, F.FUNCIONARIO, O.STATUS, O.PRECO, O.DATAEXECUT "
					+ "FROM ORDEM O, FUNCIONARIO F, CLIENTE C, PEDIDO P " + "WHERE O.FUNCIONARIO = F.ID "
					+ "AND P.ID = O.PEDIDO " + "AND P.CLIENTE = C.ID " + "AND P.STATUS LIKE '" + status + "' "
					+ "ORDER BY " + ordenacao;
		} else if (rbEspera.isSelected()) {
			status = "Em espera";
			sql = "SELECT O.ID, C.CLIENTE, O.PEDIDO, F.FUNCIONARIO, O.STATUS, O.PRECO, O.DATAEXECUT "
					+ "FROM ORDEM O, FUNCIONARIO F, CLIENTE C, PEDIDO P " + "WHERE O.FUNCIONARIO = F.ID "
					+ "AND P.ID = O.PEDIDO " + "AND P.CLIENTE = C.ID " + "AND P.STATUS LIKE '" + status + "' "
					+ "ORDER BY " + ordenacao;
		} else if (rbFinalizado.isSelected()) {
			status = "Finalizado";
			sql = "SELECT O.ID, C.CLIENTE, O.PEDIDO, F.FUNCIONARIO, O.STATUS, O.PRECO, O.DATAEXECUT "
					+ "FROM ORDEM O, FUNCIONARIO F, CLIENTE C, PEDIDO P " + "WHERE O.FUNCIONARIO = F.ID "
					+ "AND P.ID = O.PEDIDO " + "AND P.CLIENTE = C.ID " + "AND P.STATUS LIKE '" + status + "' "
					+ "ORDER BY " + ordenacao;
		} else {
			sql = "SELECT O.ID, C.CLIENTE, O.PEDIDO, F.FUNCIONARIO, O.STATUS, O.PRECO, O.DATAEXECUT "
					+ "FROM ORDEM O, FUNCIONARIO F, CLIENTE C, PEDIDO P " + "WHERE O.FUNCIONARIO = F.ID "
					+ "AND P.ID = O.PEDIDO " + "AND P.CLIENTE = C.ID " + "ORDER BY " + ordenacao;
		}

		/**
		 * Realizar a consulta no banco.
		 */
		services.BD b = new services.BD();
		if (b.getConnection()) {
			try {
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (b.rs.next()) {
					modelo.addRow(new Object[] {b.rs.getInt("ID"),
							b.rs.getInt("PEDIDO"),
							b.rs.getString("CLIENTE"), 
							b.rs.getString("FUNCIONARIO"),
							b.rs.getString("STATUS"), 
							"R$ " + b.rs.getDouble("PRECO") + "0",
							b.rs.getString("DATAEXECUT")});
				}
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}
	}
	
	/**
	 * Métodos para inserir serviços em uma OS
	 */
	
	private JTextField tfTipo;
	private JComboBox cbServico;
	private JTextField tfPreco;
	private JTextField tfServico;
	private JTextField tfQuantidade;
	
	/**
	 * Objetos da tela ServicoOs
	 * @param tfId
	 * @param tfTotal
	 * @param tfTipo
	 * @param cbServico
	 * @param tfPreco
	 * @param tfServico
	 * @param tfQuantidade
	 */
	public OrdemServicoController(JTextField tfId, JTextField tfTotal, JTextField tfTipo, JComboBox cbServico,
			JTextField tfPreco, JTextField tfServico, JTextField tfQuantidade) {
		super();
		this.tfId = tfId;
		this.tfTotal = tfTotal;
		this.tfTipo = tfTipo;
		this.cbServico = cbServico;
		this.tfPreco = tfPreco;
		this.tfServico = tfServico;
		this.tfQuantidade = tfQuantidade;
	}

	/**
	 * Método para validar campo obrigatório
	 * @return
	 */
	public boolean validarCampoServico() {
		boolean valido = true;
			if (tfQuantidade.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "O campo 'Quantidade' não pode estar vazio");
				valido = false;
		}
		return valido;
	}
	
	/**
	 * Preenche o comboBox de serviços
	 */
	public void carregarServico() {
		services.BD b = new services.BD();
		b.getConnection();
		try {
			String sql = "SELECT ID FROM SERVICO";
			b.st = b.con.createStatement();
			b.rs = b.st.executeQuery(sql);
			while(b.rs.next()) {
				cbServico.addItem(b.rs.getInt("id"));
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}
	
	/**
	 * Método para carregar informações do serviço atraves do codigo
	 */
	public void carregarInformacoes() {
		int servico = Integer.parseInt(cbServico.getSelectedItem().toString());
		services.BD b = new services.BD();
		b.getConnection();
		try {
			String consultacliente = "SELECT SERVICO,TIPO, PRECO FROM SERVICO WHERE ID = " + servico;
			b.st = b.con.createStatement();
			b.rs = b.st.executeQuery(consultacliente);
			while(b.rs.next()) {
				tfServico.setText((b.rs.getString("SERVICO")));
				tfPreco.setText(String.valueOf(b.rs.getDouble("PRECO")));
				tfTipo.setText((b.rs.getString("TIPO")));
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}
	
	/**
	 * Inserir um novo registro
	 */
	public void inserirServico() {
		ServicoOs s = new ServicoOs();
		s.setIdOrdemServico(Integer.parseInt(tfId.getText()));
		s.setIdServico(Integer.parseInt(cbServico.getSelectedItem().toString()));
		try {
			s.setPreco(Double.parseDouble(tfPreco.getText()));
			}catch(NumberFormatException erro){
				JOptionPane.showMessageDialog(null, "Preço deve ser numerico com duas casas decimai\n Exemplo: 2.10", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		s.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
		services.BD b = new services.BD();
		if(b.getConnection()) {
			try {
				String sql = "INSERT INTO SERVICOORDEM VALUES (" + s.getIdOrdemServico() +", " + s.getIdServico() + ", " + s.getPreco() + ", " + s.getQuantidade() + ")";
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
			}catch(Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			try {
				String pedido = "UPDATE PEDIDO SET PRECO = (select SUM(total) from servicoOrdem where idOrdem = " + s.getIdOrdemServico() + ") where id = (SELECT PEDIDO FROM ORDEM WHERE ID = "+ tfId.getText() +")";
				b.st = b.con.createStatement();
				b.st.executeUpdate(pedido);
				String sql = "UPDATE ORDEM SET PRECO = (select SUM(total) from servicoOrdem where idOrdem = " + s.getIdOrdemServico() + ") where id = " + s.getIdOrdemServico();
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
				String total = "select preco from ordem where id = " + s.getIdOrdemServico();
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(total);
				while(b.rs.next()) {
					tfTotal.setText(String.valueOf(b.rs.getDouble("preco")));
				}

			}catch(Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		}else {
			System.out.println("Falha na conexão");
		}
	}
	
	
//	public void editarOs() {
//		int servico = Integer.parseInt(JOptionPane.showInputDialog("Codigo do servico a ser excluido:"));
//		Servico s = new Servico();
//		s.setNome(tfNome.getText());
//		s.setTipo(cbTipo.getSelectedItem().toString());
//		try {
//			s.setPreco(Double.parseDouble(tfPreco.getText()));
//			}catch(NumberFormatException erro){
//				JOptionPane.showMessageDialog(null, "Preço deve ser numerico com duas casas decimai\n Exemplo: 2.10", "ERROR", JOptionPane.ERROR_MESSAGE);
//			}
//		BD.BD b = new BD.BD();
//		if(b.getConnection()) {
//			try {
//				String sql = "UPDATE SERVICO SET NOME = '" + s.getNome() +"', TIPO = " + s.getTipo() + ", PRECO = '" + s.getPreco() + "'"
//						+ "WHERE ID = " + servico;
//				b.st = b.con.createStatement();
//				b.st.executeUpdate(sql);
////				JOptionPane.showMessageDialog(null,"Salvo!");
//			}catch(Exception erro) {
//				System.out.println("ERRO" + erro.toString());
//			}
//		}else {
//			System.out.println("Falha na conexão");
//		}
//	}
	
//	public void excluirOs() {	
//		services.BD b = new services.BD();
//		int servico = Integer.parseInt(JOptionPane.showInputDialog("Codigo do servico na os a ser excluido:"));
//		if(b.getConnection()) {
//			String sql = "DELETE FROM servicoOrdemServico WHERE IDSERVICO = " + servico;
//			try {
//				b.st = b.con.createStatement();
//				b.st.executeUpdate(sql);
//				JOptionPane.showMessageDialog(null,"Salvo!");
//			}catch(Exception erro) {
//				System.out.println("ERRO" + erro.toString());
//			}
//		}else {
//			System.out.println("Falha na conexão");
//		}
//	}
}