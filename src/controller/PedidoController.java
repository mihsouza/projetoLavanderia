package controller;

import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import services.BD;
import view.TelaCargo;
import view.TelaMensagemAviso;
import view.TelaMensagemErro;

public class PedidoController extends BD {

	protected JTextField tfSaida;
	private JTextField tfId;
	private JTextField tfPrevisto;
	private JComboBox cbCliente, cbAtendente;
	private JComboBox cbFuncionario, cbStatus;
	private JTextField tfTotal;
	public JTextArea taObs;
	private JTextField tfSaldo;

	
	public PedidoController(JTextField tfSaida, JTextField tfId, JTextField tfPrevisto, JComboBox cbCliente,
			JComboBox cbFuncionario, JComboBox cbStatus, JTextField tfTotal, JComboBox cbAtendente, JTextArea taObs, JTextField tfSaldo) {
		super();
		this.tfSaida = tfSaida;
		this.tfId = tfId;
		this.tfPrevisto = tfPrevisto;
		this.cbCliente = cbCliente;
		this.cbFuncionario = cbFuncionario;
		this.cbStatus = cbStatus;
		this.tfTotal = tfTotal;
		this.cbAtendente = cbAtendente;
		this.taObs = taObs;
		this.tfSaldo = tfSaldo;
	}

	/**
	 * Popular comboBox com os cargs cadastrados no banco
	 */
	public void carregarCliente() {
		getConnection();
		try {
			String sql = "SELECT CLIENTE FROM CLIENTE ORDER BY CLIENTE";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				cbCliente.addItem(rs.getString("CLIENTE"));
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}
	
	public void carregarAtendente() {
		getConnection();
		try {
			String sql = "SELECT FUNCIONARIO FROM FUNCIONARIO WHERE CARGO = 1";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				cbAtendente.addItem(rs.getString("FUNCIONARIO"));
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}
	
	public void carregarFuncionario() {
		getConnection();
		try {
			String sql = "SELECT FUNCIONARIO FROM FUNCIONARIO WHERE CARGO BETWEEN 2 AND 5 ORDER BY FUNCIONARIO";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				cbFuncionario.addItem(rs.getString("FUNCIONARIO"));
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}

	public boolean validarCampo() {
		boolean valido = true;
		if (tfPrevisto.getText().trim().equals("")) {
			TelaMensagemErro frame = new TelaMensagemErro();
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			frame.lbCampo.setText("Entrega: campo obrigatório");
			valido = false;
		}
		return valido;
	}


	public void inserir() {
			if (getConnection() == true) {
				try {
					String sql = null;
					if(tfId.getText().trim().equals("")) {
						sql = "EXEC PEDIDOINSERIR null , '" + cbCliente.getSelectedItem() + "', '" + tfPrevisto.getText() + "',  '"
							+ cbAtendente.getSelectedItem() + "', '" + cbStatus.getSelectedItem() + "', '" + cbFuncionario.getSelectedItem() + "',"
									+ " NULL , '" + taObs.getText() + "', " + tfSaldo.getText();
					}else {
						sql = "EXEC PEDIDOINSERIR " + tfId.getText() + " , '" + cbCliente.getSelectedItem() + "', '" + tfPrevisto.getText() + "',  '"
								+ cbAtendente.getSelectedItem() + "', '" + cbStatus.getSelectedItem() + "', '" + cbFuncionario.getSelectedItem() + "',"
								+ "NULL, '" + taObs.getText() + "', " + tfSaldo.getText();
					}
					System.out.println(sql);
					st = con.createStatement();
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
				/**
				 * Preencher com o id que foi gerado no banco.
				 */
				if (tfId.getText().trim().equals("")) {
					try {
						String sql = "SELECT TOP 1 PEDIDO.ID FROM PEDIDO ORDER BY ID DESC";
						st = con.createStatement();
						rs = st.executeQuery(sql);
						while (rs.next()) {
							tfId.setText(String.valueOf(rs.getInt("ID")));
						}
					} catch (Exception erro) {
						JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
					}
				}
				TelaMensagemAviso frame = new TelaMensagemAviso();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
	}

	public void excluir() {
		if (getConnection() == true) {
			try {
				String sql = "DELETE FROM PEDIDO WHERE ID = " + tfId.getText();
				st = con.createStatement();
				st.executeUpdate(sql);
				TelaMensagemAviso frame = new TelaMensagemAviso();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.lbTitulo.setText("Excluído!");
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

	public void visualizar() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT C.CLIENTE, F.FUNCIONARIO, P.ENTREGA, P.STATUS, P.FUNCIONARIO, P.DATASAIDA, P.TOTAL, P.OBSERVACAO, P.SALDO"
						+ " FROM CLIENTE C, FUNCIONARIO F, PEDIDO P"
						+ " WHERE F.ID = P.ATENDENTE"
						+ " AND C.ID = P.CLIENTE"
						+ " AND P.ID = " + tfId.getText();
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					tfSaida.setText(rs.getString("DATASAIDA"));
					tfPrevisto.setText(rs.getString("ENTREGA"));
					tfTotal.setText(String.valueOf(rs.getDouble("TOTAL")));
					cbAtendente.setSelectedItem(rs.getString("FUNCIONARIO"));
					cbCliente.setSelectedItem(rs.getString("CLIENTE"));
					cbStatus.setSelectedItem(rs.getString("STATUS"));
					taObs.setText(rs.getString("OBSERVACAO"));
					tfSaldo.setText(String.valueOf(rs.getDouble("SALDO")));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
			try {
				String sql = "SELECT F.FUNCIONARIO "
						+ "FROM FUNCIONARIO F, PEDIDO P "
						+ "WHERE F.ID = P.FUNCIONARIO "
						+ " AND P.ID = " + tfId.getText();
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					cbFuncionario.setSelectedItem(rs.getString("FUNCIONARIO"));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}
	
	/*
	 * Listar os pedidos
	 */
	protected JTextField tfNome, tfDe, tfAte;
	protected JComboBox cbOrdenar;
	DefaultTableModel modelo = new DefaultTableModel();
	public JTextField tfUsuario;


	
	public PedidoController(JComboBox cbStatus, JTextField tfNome, JTextField tfDe, JTextField tfAte,
			JComboBox cbOrdenar, DefaultTableModel modelo, JTextField tfUsuario) {
		super();
		this.cbStatus = cbStatus;
		this.tfNome = tfNome;
		this.tfDe = tfDe;
		this.tfAte = tfAte;
		this.cbOrdenar = cbOrdenar;
		this.modelo = modelo;
		this.tfUsuario = tfUsuario;
	}

	public void listar() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT P.ID, P.ENTRADA, C.CLIENTE, P.STATUS, F.FUNCIONARIO, P.OBSERVACAO "
						+ "FROM CLIENTE C, PEDIDO P, FUNCIONARIO F "
						+ "WHERE C.ID = P.CLIENTE "
						+ "AND F.ID = P.FUNCIONARIO "
						+ "AND ENTRADA BETWEEN '" + tfDe.getText() + "' AND '" + tfAte.getText() + "' "
						+ "AND C.CLIENTE LIKE '%" + tfNome.getText() + "%' AND P.STATUS LIKE '%" + cbStatus.getSelectedItem() + "%' "
						+ "AND F.FUNCIONARIO LIKE '" + tfUsuario.getText() + "%' "
						+ " ORDER BY P." + cbOrdenar.getSelectedItem();
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getInt("ID"), rs.getString("ENTRADA"),  rs.getString("CLIENTE"), rs.getString("FUNCIONARIO"), rs.getString("STATUS"), rs.getString("OBSERVACAO")});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

	public PedidoController(JTextField tfSaida, DefaultTableModel modelo) {
		super();
		this.tfSaida = tfSaida;
		this.modelo = modelo;
	}
	
	
	public void listarPedidos() {
		if (getConnection() == true) {
			try {
				String sql = "SELECT C.CLIENTE, F.FUNCIONARIO, P.ENTRADA, P.STATUS, P.OBSERVACAO, P.ID " + 
						"FROM CLIENTE C, FUNCIONARIO F, PEDIDO P " + 
						"WHERE C.ID = P.CLIENTE " + 
						"AND F.ID = P.FUNCIONARIO " + 
						"AND ENTREGA = '" + tfSaida.getText() + "'";
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (modelo.getRowCount()>0) modelo.removeRow(0);
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("ID"), rs.getString("ENTRADA"),rs.getString("CLIENTE"), rs.getString("FUNCIONARIO"), rs.getString("STATUS"), rs.getString("OBSERVACAO")});
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
	}

	public PedidoController() {
	}
	
	public void debitarSaldo(String pedido, String cliente) {
		double saldo = 0;
		double preco = 0;
		if (getConnection() == true) {
			try {
				String sql = "SELECT TOTALREAL, SALDO " + "FROM PEDIDO " + "WHERE ID = " + pedido;
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					saldo = (rs.getDouble("TOTALREAL"));
					preco = (rs.getDouble("SALDO"));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
		if (saldo == 0) {
			System.out.println("ok");
		} else if (saldo < 0) {
			if (getConnection() == true) {
				try {
					String sql = "INSERT INTO SALDOCLIENTE VALUES (GETDATE(), (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '"
							+ cliente + "'), " + "" + pedido + ", " + saldo + ")";
					System.out.println(sql);
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
		} else if (saldo >= preco) {
			if (getConnection() == true) {
				try {
					String sql = "INSERT INTO SALDOCLIENTE VALUES (GETDATE(), (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '"
							+ cliente + "'), " + "" + pedido + ", -" + preco + ")";
					System.out.println(sql);
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
		} else if (saldo < preco) {
			if (getConnection() == true) {
				try {
					String sql = "INSERT INTO SALDOCLIENTE VALUES (GETDATE(), (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '"
							+ cliente + "'), " + "" + pedido + ", -" + saldo + ")";
					System.out.println(sql);
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
		}
	}
	
	public void receberPedido() {
		String pedido = JOptionPane.showInputDialog("Digite o número do pedido:");
		double total = 0;
		String cliente = null;
		if (getConnection() == true) {
			try {
				String sql = "SELECT P.TOTAL, C.CLIENTE "
						+ "FROM PEDIDO P, CLIENTE C "
						+ "WHERE P.CLIENTE = C.ID "
						+ "AND P.ID = " + pedido;
				System.out.println(sql);
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					total = (rs.getDouble("TOTAL"));
					cliente = (rs.getString("CLIENTE"));
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Falha na conexão");
		}
		
		double pago = Double.parseDouble(JOptionPane.showInputDialog("Pedido nº: " + pedido + "\n"
				+ "Cliente: " + cliente + "\n"
						+ "Valor: " + total + "\n \n \n"
								+ "Valor pago:"));
		double troco = pago - total;
		if(troco == 0) {
			JOptionPane.showMessageDialog(null, "Pedido nº: " + pedido + "\n"
					+ "Cliente: " + cliente + "\n"
							+ "Valor: " + total + "\n"
									+ "Valor pago: " + pago + "\n"
											+ "Troco: " + troco);
			if (getConnection() == true) {
				try {
					String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + pedido;
					System.out.println(sql);
					st.executeUpdate(sql);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
		}else
			if(troco < 0) {
				JOptionPane.showMessageDialog(null, "Pedido nº: " + pedido + "\n"
						+ "Cliente: " + cliente + "\n"
								+ "Valor: " + total + "\n"
										+ "Valor pago: " + pago + "\n"
												+ "Deve: " + troco);
				if (getConnection() == true) {
					try {
						String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + pedido;
						System.out.println(sql);
						st.executeUpdate(sql);
					} catch (Exception erro) {
						JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
					}
					try {
						String sql = "INSERT INTO SALDOCLIENTE VALUES (GETDATE(), (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '" + cliente + "'), "
								+ "" + pedido + ", " + troco + ")";
						System.out.println(sql);
						st.executeUpdate(sql);
					} catch (Exception erro) {
						JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Falha na conexão");
				}
			}else {
				String opcao = JOptionPane.showInputDialog("Troco: " + troco + "\n"
						+ "Gostaria de deixar como saldo?\n\n"
						+ "1- Sim\n"
						+ "2- Não");
				if(opcao.equals("1")) {
					JOptionPane.showMessageDialog(null, "Pedido nº: " + pedido + "\n"
							+ "Cliente: " + cliente + "\n"
									+ "Valor: " + total + "\n"
											+ "Valor pago: " + pago + "\n"
													+ "Saldo: " + troco + "\n"
															+ "Troco: 0.0");
					if (getConnection() == true) {
						try {
							String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + pedido;
							System.out.println(sql);
							st.executeUpdate(sql);
						} catch (Exception erro) {
							JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
						}
						try {
							String sql = "INSERT INTO SALDOCLIENTE VALUES (GETDATE(), (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '" + cliente + "'), "
									+ "" + pedido + ", " + troco + ")";
							System.out.println(sql);
							st.executeUpdate(sql);
						} catch (Exception erro) {
							JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Falha na conexão");
					}
				}else
					if(opcao.equals("2")) {
						JOptionPane.showMessageDialog(null, "Pedido nº: " + pedido + "\n"
								+ "Cliente: " + cliente + "\n"
										+ "Valor: " + total + "\n"
												+ "Valor pago: " + pago + "\n"
														+ "Troco: " + troco);
						if (getConnection() == true) {
							try {
								String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + pedido;
								System.out.println(sql);
								st.executeUpdate(sql);
							} catch (Exception erro) {
								JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Falha na conexão");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Opção inválida!");
					}
			}
		debitarSaldo(tfId.getText(), cliente);
		JOptionPane.showMessageDialog(null, "Recebido!");
	}

	public PedidoController(JTextField tfId) {
		this.tfId = tfId;
	}
	
	public void receberPelaTela() {
		double total = 0;
		String cliente = null;
		if(tfId.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Pedido: Valor obrigatório");
		}else {
			if (getConnection() == true) {
				try {
					String sql = "SELECT P.TOTAL, C.CLIENTE "
							+ "FROM PEDIDO P, CLIENTE C "
							+ "WHERE P.CLIENTE = C.ID "
							+ "AND P.ID = " + tfId.getText();
					System.out.println(sql);
					st = con.createStatement();
					rs = st.executeQuery(sql);
					while (rs.next()) {
						total = (rs.getDouble("TOTAL"));
						cliente = (rs.getString("CLIENTE"));
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Falha na conexão");
			}
			
			double pago = Double.parseDouble(JOptionPane.showInputDialog("Pedido nº: " + tfId.getText() + "\n"
					+ "Cliente: " + cliente + "\n"
							+ "Valor: " + total + "\n \n \n"
									+ "Valor pago:"));
			double troco = pago - total;
			if(troco == 0) {
				JOptionPane.showMessageDialog(null, "Pedido nº: " + tfId.getText() + "\n"
						+ "Cliente: " + cliente + "\n"
								+ "Valor: " + total + "\n"
										+ "Valor pago: " + pago + "\n"
												+ "Troco: " + troco);
				if (getConnection() == true) {
					try {
						String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + tfId.getText();
						System.out.println(sql);
						st.executeUpdate(sql);
					} catch (Exception erro) {
						JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Falha na conexão");
				}
			}else
				if(troco < 0) {
					JOptionPane.showMessageDialog(null, "Pedido nº: " + tfId.getText() + "\n"
							+ "Cliente: " + cliente + "\n"
									+ "Valor: " + total + "\n"
											+ "Valor pago: " + pago + "\n"
													+ "Deve: " + troco);
					if (getConnection() == true) {
						try {
							String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + tfId.getText();
							System.out.println(sql);
							st.executeUpdate(sql);
						} catch (Exception erro) {
							JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
						}
						try {
							String sql = "INSERT INTO SALDOCLIENTE VALUES (GETDATE(), (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '" + cliente + "'), "
									+ "" + tfId.getText() + ", " + troco + ")";
							System.out.println(sql);
							st.executeUpdate(sql);
						} catch (Exception erro) {
							JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Falha na conexão");
					}
				}else {
					String opcao = JOptionPane.showInputDialog("Troco: " + troco + "\n"
							+ "Gostaria de deixar como saldo?\n\n"
							+ "1- Sim\n"
							+ "2- Não");
					if(opcao.equals("1")) {
						JOptionPane.showMessageDialog(null, "Pedido nº: " + tfId.getText() + "\n"
								+ "Cliente: " + cliente + "\n"
										+ "Valor: " + total + "\n"
												+ "Valor pago: " + pago + "\n"
														+ "Saldo: " + troco + "\n"
																+ "Troco: 0.0");
						if (getConnection() == true) {
							try {
								String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + tfId.getText();
								System.out.println(sql);
								st.executeUpdate(sql);
							} catch (Exception erro) {
								JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
							}
							try {
								String sql = "INSERT INTO SALDOCLIENTE VALUES (GETDATE(), (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '" + cliente + "'), "
										+ "" + tfId.getText() + ", " + troco + ")";
								System.out.println(sql);
								st.executeUpdate(sql);
							} catch (Exception erro) {
								JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
							}
						} else {
							JOptionPane.showMessageDialog(null, "Falha na conexão");
						}
					}else
						if(opcao.equals("2")) {
							JOptionPane.showMessageDialog(null, "Pedido nº: " + tfId.getText() + "\n"
									+ "Cliente: " + cliente + "\n"
											+ "Valor: " + total + "\n"
													+ "Valor pago: " + pago + "\n"
															+ "Troco: " + troco);
							if (getConnection() == true) {
								try {
									String sql = "update pedido set dataSaida = GETDATE(), status = 'Entregue' where id = " + tfId.getText();
									System.out.println(sql);
									st.executeUpdate(sql);
								} catch (Exception erro) {
									JOptionPane.showMessageDialog(null, "ERRO" + erro.toString());
								}
							} else {
								JOptionPane.showMessageDialog(null, "Falha na conexão");
							}
						}else {
							JOptionPane.showMessageDialog(null, "Opção inválida!");
						}
				}
			debitarSaldo(tfId.getText(), cliente);
			JOptionPane.showMessageDialog(null, "Recebido!");
		}
	}

	public void verificarSaldo() {
		double saldo = 0;
		getConnection();
		try {
			String sql = "SELECT SUM(VALOR) FROM SALDOCLIENTE WHERE CLIENTE = (SELECT ID FROM CLIENTE WHERE CLIENTE LIKE '" + cbCliente.getSelectedItem() + "')";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				saldo = (rs.getDouble(""));
			}
			if(saldo < 0) {
				String opcao = JOptionPane.showInputDialog(null, "Este cliente possui saldo devedor no valor de " + saldo + "!\n"
						+ "Gostaria de lançar neste pedido?\n\n"
						+ "1- Sim\n"
						+ "2- Não");
				if(opcao.equals("1")) {
					tfSaldo.setText(String.valueOf(saldo));
				}
			}if(saldo > 0) {
				String opcao = JOptionPane.showInputDialog(null, "Este cliente possui saldo no valor de " + saldo + "!\n"
						+ "Gostaria de lançar neste pedido?\n\n"
						+ "1- Sim\n"
						+ "2- Não");
				if(opcao.equals("1")) {
					tfSaldo.setText(String.valueOf(saldo));
				}
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}
		
}
