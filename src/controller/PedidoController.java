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

import model.Funcionario;
import model.Pedido;

/**
 * Classe de controle. Nela ficara os métodos para manipulação dos dados no
 * banco de dados. Teremos dois métodos construtores, pois a utilizaremos em
 * duas telas diferentes.
 * 
 * @author michele
 *
 */
public class PedidoController {

	/**
	 * Componentes da classe TelaCliente
	 */
	private JTextField tfId;
	private JTextField tfDataEntrada;
	private JTextArea textAreaObs;
	private JTextField tfDataPrevista;
	private JTextField tfTotal;
	private JTextField tfDataSaida;
	private JComboBox cbAtendente;
	private JComboBox cbCliente;
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
	 * @param tfDataSaida
	 * @param cbAtendente
	 * @param cbCliente
	 * @param rbAtraso
	 * @param rbEspera
	 * @param rbFinalizado
	 */
	public PedidoController(JTextField tfId, JTextField tfDataEntrada, JTextArea textAreaObs, JTextField tfDataPrevista,
			JTextField tfTotal, JTextField tfDataSaida, JComboBox cbAtendente, JComboBox cbCliente,
			JRadioButton rbAtraso, JRadioButton rbEspera, JRadioButton rbFinalizado) {
		super();
		this.tfId = tfId;
		this.tfDataEntrada = tfDataEntrada;
		this.textAreaObs = textAreaObs;
		this.tfDataPrevista = tfDataPrevista;
		this.tfTotal = tfTotal;
		this.tfDataSaida = tfDataSaida;
		this.cbAtendente = cbAtendente;
		this.cbCliente = cbCliente;
		this.rbAtraso = rbAtraso;
		this.rbEspera = rbEspera;
		this.rbFinalizado = rbFinalizado;
	}

	/**
	 * Preenche o comboBox de clientes
	 */
	public void carregarCliente() {
		services.BD b = new services.BD();
		b.getConnection();
		try {
			String sql = "SELECT CLIENTE FROM CLIENTE";
			b.st = b.con.createStatement();
			b.rs = b.st.executeQuery(sql);
			while (b.rs.next()) {
				cbCliente.addItem(b.rs.getString("CLIENTE"));
			}
		} catch (SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}

	/**
	 * Preenche o comboBox de atendentes
	 */
	public void carregarAtendente() {
		services.BD b = new services.BD();
		b.getConnection();
		try {
			String sql = "SELECT F.FUNCIONARIO FROM FUNCIONARIO F, CARGO C " + "WHERE C.ID = F.CARGO "
					+ "AND C.CARGO LIKE 'Atendente'";
			b.st = b.con.createStatement();
			b.rs = b.st.executeQuery(sql);
			while (b.rs.next()) {
				cbAtendente.addItem(b.rs.getString("FUNCIONARIO"));
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
			JOptionPane.showMessageDialog(null, "O campo 'Data prevista' não pode estar vazio");
			valido = false;
		}
		return valido;
	}

	/**
	 * Inserir um novo registro
	 */
	public void inserir() {
		Pedido p = new Pedido();
		p.setDataPrevista(tfDataPrevista.getText());
		p.setObservacao(textAreaObs.getText());
		p.setDataSaida(tfDataSaida.getText());
		p.setCliente(cbCliente.getSelectedItem().toString());
		p.setAtendente(cbAtendente.getSelectedItem().toString());
		if (tfTotal.getText().trim().equals("")) {
			p.setPreco(0);
		} else {
			try {
				p.setPreco(Double.parseDouble(tfTotal.getText()));
			} catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(null,
						"Remuneração deve ser numerico com duas casas decimai\n Exemplo: 0.10", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (rbAtraso.isSelected()) {
			p.setStatus("Em atraso");
		} else if (rbEspera.isSelected()) {
			p.setStatus("Em espera");
		} else {
			p.setStatus("Finalizado");
		}
		services.BD b = new services.BD();
		if (b.getConnection()) {
			try {
				String sql = "INSERT INTO PEDIDO VALUES ((SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '" + p.getCliente()
						+ "'), (SELECT ID FROM FUNCIONARIO WHERE FUNCIONARIO LIKE '" + p.getAtendente()
						+ "'), GETDATE(), '" + p.getDataPrevista() + "', '" + p.getStatus() + "', " + p.getPreco()
						+ ", '" + p.getDataSaida() + "', '" + p.getObservacao() + "')";
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
			try {
				String sql = "SELECT TOP 1 PEDIDO.ID FROM PEDIDO ORDER BY ID DESC";
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				while (b.rs.next()) {
					tfId.setText(String.valueOf(b.rs.getInt("ID")));
					JOptionPane.showMessageDialog(null, "Pedido gerado com sucesso!");
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}

	/**
	 * Editar um registro existente
	 */
	public void editar() {
		Pedido p = new Pedido();
		p.setId(Integer.parseInt(tfId.getText()));
		p.setDataPrevista(tfDataPrevista.getText());
		p.setObservacao(textAreaObs.getText());
		p.setDataSaida(tfDataSaida.getText());
		p.setCliente(cbCliente.getSelectedItem().toString());
		p.setAtendente(cbAtendente.getSelectedItem().toString());
		if (tfTotal.getText().trim().equals("")) {
			p.setPreco(0);
		} else {
			try {
				p.setPreco(Double.parseDouble(tfTotal.getText()));
			} catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(null, "Preço deve ser numerico com duas casas decimais\n Exemplo: 1.10",
						"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (rbAtraso.isSelected()) {
			p.setStatus("Em atraso");
		} else if (rbEspera.isSelected()) {
			p.setStatus("Em espera");
		} else {
			p.setStatus("Finalizado");
		}
		services.BD b = new services.BD();
		if (b.getConnection()) {
			try {
				String sql = "UPDATE PEDIDO SET CLIENTE = (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '" + p.getCliente()
						+ "')," + " ATENDENTE = (SELECT ID FROM FUNCIONARIO WHERE FUNCIONARIO LIKE '" + p.getAtendente()
						+ "'), DATAPREVISTA = '" + p.getDataPrevista() + "'," + " STATUS = '" + p.getStatus()
						+ "', PRECO = " + p.getPreco() + ", DATASAIDA = '" + p.getDataSaida() + "', "
						+ "OBSERVACAO  = '" + p.getObservacao() + "' " + "WHERE ID = " + p.getId();
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso!");
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
				String sql = "SELECT * FROM PEDIDO WHERE ID = " + f.getId();
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
	 * Visualizar um pedido
	 */
	public void visualizar() {
		services.BD b = new services.BD();
		int id = Integer.parseInt(JOptionPane.showInputDialog("Qual o código do pedido que gostaria de visualizar"));
		if (b.getConnection()) {
			try {
				String sql = "SELECT P.ID, C.CLIENTE, F.FUNCIONARIO, P.DATAENTRADA, P.DATAPREVISTA, P.STATUS, P.PRECO, P.DATASAIDA, P.OBSERVACAO "
						+ "FROM PEDIDO P, CLIENTE C, FUNCIONARIO F " + "WHERE P.CLIENTE = C.ID "
						+ "AND P.ATENDENTE = F.ID " + "AND P.ID = " + id;
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				while (b.rs.next()) {
					tfId.setText(String.valueOf(b.rs.getInt("ID")));
					tfDataEntrada.setText(b.rs.getString("DATAENTRADA"));
					tfDataPrevista.setText(b.rs.getString("DATAPREVISTA"));
					tfDataSaida.setText(b.rs.getString("DATASAIDA"));
					tfTotal.setText(String.valueOf(b.rs.getDouble("PRECO")));
					if (b.rs.getString("STATUS").equals("Finalizado")) {
						rbFinalizado.setSelected(true);
					} else if (b.rs.getString("STATUS").equals("Em espera")) {
						rbEspera.setSelected(true);
					} else {
						rbAtraso.setSelected(true);
					}
					cbAtendente.setSelectedItem(b.rs.getString("FUNCIONARIO"));
					cbCliente.setSelectedItem(b.rs.getString("CLIENTE"));
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
		Pedido p = new Pedido();
		p.setId(Integer.parseInt(tfId.getText()));
		services.BD b = new services.BD();
		if (b.getConnection()) {
			String sql = "DELETE FROM PEDIDO WHERE ID = " + p.getId();
			try {
				b.st = b.con.createStatement();
				b.st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "Pedido id : "+ p.getId() + "\nexcluido com sucesso!");
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
	 * @param rbAtraso
	 * @param rbEspera
	 * @param rbFinalizado
	 * @param tfPesquisa
	 * @param cbOrdenar
	 */
	public PedidoController(JRadioButton rbAtraso, JRadioButton rbEspera, JRadioButton rbFinalizado,
			JTextField tfPesquisa, JComboBox cbOrdenar) {
		super();
		this.rbAtraso = rbAtraso;
		this.rbEspera = rbEspera;
		this.rbFinalizado = rbFinalizado;
		this.tfPesquisa = tfPesquisa;
		this.cbOrdenar = cbOrdenar;
	}

	public void consulta() {
		String ordenacao = null;
		/**
		 * Definir a ordenacao da listagem.
		 */
		if (cbOrdenar.getSelectedItem().toString() == "Cliente") {
			ordenacao = "CLIENTE";
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
			sql = "SELECT P.ID, C.CLIENTE, F.FUNCIONARIO, P.STATUS "
					+ "FROM PEDIDO P, CLIENTE C, FUNCIONARIO F " + "WHERE P.CLIENTE = C.ID "
					+ "AND P.ATENDENTE = F.ID " + "AND C.CLIENTE LIKE '" + tfPesquisa.getText() + "%' "
					+ "AND P.STATUS LIKE '" + status + "' "
					+ "ORDER BY " + ordenacao;
		} else if (rbEspera.isSelected()) {
			status = "Em espera";
			sql = "SELECT P.ID, C.CLIENTE, F.FUNCIONARIO, P.STATUS "
					+ "FROM PEDIDO P, CLIENTE C, FUNCIONARIO F " + "WHERE P.CLIENTE = C.ID "
					+ "AND P.ATENDENTE = F.ID " + "AND C.CLIENTE LIKE '" + tfPesquisa.getText() + "%' "
					+ "AND P.STATUS LIKE '" + status + "'"
					+ "ORDER BY " + ordenacao;
		} else if (rbFinalizado.isSelected()) {
			status = "Finalizado";
			sql = "SELECT P.ID, C.CLIENTE, F.FUNCIONARIO, P.STATUS "
					+ "FROM PEDIDO P, CLIENTE C, FUNCIONARIO F " + "WHERE P.CLIENTE = C.ID "
					+ "AND P.ATENDENTE = F.ID " + "AND C.CLIENTE LIKE '" + tfPesquisa.getText() + "%' "
					+ "AND P.STATUS LIKE '" + status + "' "
					+ "ORDER BY " + ordenacao;
		} else {
			sql = "SELECT P.ID, C.CLIENTE, F.FUNCIONARIO, P.STATUS "
					+ "FROM PEDIDO P, CLIENTE C, FUNCIONARIO F " + "WHERE P.CLIENTE = C.ID "
					+ "AND P.ATENDENTE = F.ID " + "AND C.CLIENTE LIKE '" + tfPesquisa.getText() + "%' "
					+ "ORDER BY " + ordenacao;
		}

		/**
		 * Realizar a consulta no banco.
		 */
		services.BD b = new services.BD();
		if (b.getConnection()) {
			try {
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
				while (b.rs.next()) {
					sb.append("ID: " + b.rs.getInt("ID"));
					sb.append(" ");
					sb.append("CLIENTE: " + b.rs.getString("CLIENTE"));
					sb.append(" ");
					sb.append("FUNCIONARIO: " + b.rs.getString("FUNCIONARIO"));
					sb.append(" ");
					sb.append("STATUS: " + b.rs.getString("STATUS"));
					sb.append("\n");
				}
				JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Pedidos", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Receber pedido
	 */

	private JTextField tfStatus;
	private JTextField tfPreco;
	private JTextField tfPago;
	private JTextField tfTroco;
	private JTextField tfPedido;
	
	private JTextField tfCliente;
	public PedidoController(JTextField tfCliente, JTextField tfStatus, JTextField tfPreco, JTextField tfPago,
			JTextField tfTroco, JTextField tfPedido) {
		super();
		this.tfCliente = tfCliente;
		this.tfStatus = tfStatus;
		this.tfPreco = tfPreco;
		this.tfPago = tfPago;
		this.tfTroco = tfTroco;
		this.tfPedido = tfPedido;
	}
	
	public void atualizar() {
		services.BD b = new services.BD();
		int id = Integer.parseInt(tfPedido.getText());
		if (b.getConnection()) {
			try {
				String sql = "SELECT C.CLIENTE, P.STATUS, P. PRECO "
						+ "FROM PEDIDO P, CLIENTE C "
						+ "WHERE P.CLIENTE = C.ID "
						+ "AND P.ID = " + id;
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				while (b.rs.next()) {
					tfCliente.setText(b.rs.getString("CLIENTE"));
					tfStatus.setText(b.rs.getString("STATUS"));
					tfPreco.setText(String.valueOf(b.rs.getDouble("PRECO")));
				}
			} catch (Exception erro) {
				System.out.println("ERRO" + erro.toString());
			}
		} else {
			System.out.println("Falha na conexão");
		}
	}
		
		public void receber() {
			services.BD b = new services.BD();
			if (b.getConnection()) {
				try {
					String sql = "UPDATE PEDIDO SET STATUS = 'Finalizado', DATASAIDA = GETDATE() WHERE ID = " + tfPedido.getText();
					b.st = b.con.createStatement();
					b.st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Recebido!");
				} catch (Exception erro) {
					System.out.println("ERRO" + erro.toString());
				}
			} else {
				System.out.println("Falha na conexão");
			}
		}
		
		public void calcularTroco() {
			float preco = Float.parseFloat(tfPreco.getText());
			float pago = Float.parseFloat(tfPago.getText());
			
			float troco = pago - preco;
			
			tfTroco.setText(String.valueOf(troco));
		}
		
	}
	