package services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import view.TelaMensagemPdf;
import view.TelaSistema;

public class GeneratorPDF {
	
	public void mensagem() {
		TelaMensagemPdf frame = new TelaMensagemPdf();
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public GeneratorPDF() {
		// TODO Auto-generated constructor stub
	}
	
	private static Font fontetitulo = new Font(Font.FontFamily.COURIER, 26, Font.BOLD, BaseColor.BLACK);
	private static Font fontePadrao = new Font(Font.FontFamily.TIMES_ROMAN, 12);
	private static Font fonteVermelha = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.RED);
	private static Font negrito = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	
	/**
	 * Gerar PDF do Pedido
	 * @param id
	 * @param nomecliente
	 * @param entrada
	 * @param previsto
	 * @param nomeatendente
	 * @param obspedido
	 */
	public void pdfPedido(String id, String nomecliente, String entrada, String previsto, String nomeatendente, String obspedido){
        // criação do documento
       Document document = new Document();
       try {
           
    	// Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
           Font title = new Font(FontFamily.COURIER, 26, Font.BOLD+Font.ITALIC+Font.UNDERLINE);
           
           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\tatia\\Downloads\\PDF\\PDF_Pedido_" + id + ".pdf"));
           document.open();
           document.setPageSize(PageSize.LARGE_CROWN_QUARTO);
           
           Image figura = Image.getInstance((TelaSistema.class.getResource("/view/logotipo.png")));
           figura.setAlignment(figura.ALIGN_CENTER);
           document.add(figura);
           
           
           // adicionando um parágrafo no documento
           Paragraph espaco = new Paragraph(" ");
           document.add(espaco);
           
           //Adicionando título
           Paragraph titulo = new Paragraph("Pedido nº " + id, fontetitulo);
           titulo.setAlignment(titulo.ALIGN_CENTER);
           document.add(titulo);
           
           document.add(espaco);
           document.add(espaco);
           
           Paragraph cliente = new Paragraph("Cliente: ", negrito);
           Paragraph nomeCliente = new Paragraph(nomecliente, fontePadrao);
           document.add(cliente);
           document.add(nomeCliente);
           
           Paragraph data = new Paragraph("Data entrada: ", negrito);
           Paragraph dataEntrada = new Paragraph(entrada, fontePadrao);
           document.add(data);
           document.add(dataEntrada);
           
           Paragraph dataPre = new Paragraph("Data prevista: ", negrito);
           Paragraph dataPrevista = new Paragraph(previsto, fontePadrao);
           document.add(dataPre);
           document.add(dataPrevista);
           
           Paragraph atendente = new Paragraph("Atendente: ", negrito);
           Paragraph nomeAtendente = new Paragraph(nomeatendente, fontePadrao);
           document.add(atendente);
           document.add(nomeAtendente);
           
           Paragraph obs = new Paragraph("Observações: ", negrito);
           Paragraph observacao = new Paragraph(obspedido, fontePadrao);
           document.add(obs);
           document.add(observacao);
           
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           
           Paragraph saida = new Paragraph("Data saída: ____/____/_______", negrito);
           document.add(saida);
           
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           
           Paragraph recebido = new Paragraph("Entregue e recebido por:", negrito);
           recebido.setAlignment(recebido.ALIGN_CENTER);
           Paragraph assinatura = new Paragraph("_______________________________________", negrito);
           assinatura.setAlignment(assinatura.ALIGN_CENTER);
           document.add(recebido);
           document.add(espaco);
           document.add(assinatura);
           
           document.add(espaco);
           document.add(espaco);
           
           Paragraph aviso = new Paragraph("****Apresentar este documento para retirar as roupas****", fonteVermelha);
           aviso.setAlignment(aviso.ALIGN_CENTER);
           document.add(aviso);
           
           mensagem();
 }
       catch(DocumentException de) {
           System.err.println(de.getMessage());
       }
       catch(IOException ioe) {
           System.err.println(ioe.getMessage());
       }
       document.close();
   }
	
	/**
	 * Gerar PDF da Ordem de serviço
	 * @param id
	 * @param nomecliente
	 * @param entrada
	 * @param previsto
	 * @param nomefuncionario
	 * @param obsOs
	 * @param pedido
	 */
	public void pdfOs(String id, String nomecliente, String entrada, String previsto, String nomefuncionario, String obsOs, String pedido){
        // criação do documento
       Document document = new Document();
       try {
           
    	// Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
           Font title = new Font(FontFamily.COURIER, 26, Font.BOLD+Font.ITALIC+Font.UNDERLINE);
           
           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\tatia\\Downloads\\PDF\\PDF_Os_" + id + ".pdf"));
           document.open();
           document.setPageSize(PageSize.LARGE_CROWN_QUARTO);
           
           Image figura = Image.getInstance((TelaSistema.class.getResource("/view/logotipo.png")));
           figura.setAlignment(figura.ALIGN_CENTER);
           document.add(figura);
           
           
           // adicionando um parágrafo no documento
           Paragraph espaco = new Paragraph(" ");
           document.add(espaco);
           
           //Adicionando título
           Paragraph titulo = new Paragraph("Ordem de serviço nº " + id, fontetitulo);
           titulo.setAlignment(titulo.ALIGN_CENTER);
           document.add(titulo);
           
           document.add(espaco);
           document.add(espaco);
           
           Paragraph cliente = new Paragraph("Cliente: ", negrito);
           Paragraph nomeCliente = new Paragraph(nomecliente, fontePadrao);
           document.add(cliente);
           document.add(nomeCliente);
           
           Paragraph data = new Paragraph("Data entrada:                      Data prevista:", negrito);
           Paragraph dataEntrada = new Paragraph(entrada + "                                   " + previsto, fontePadrao);
           document.add(data);
           document.add(dataEntrada);
           
           String ped = null;
           int numpedido = Integer.parseInt(pedido);
           if(numpedido > 0 && numpedido <= 9) {
        	   ped = "000" + pedido;
           }else if(numpedido >= 10 && numpedido <= 99) {
        	   ped = "00" + pedido;
           }else if(numpedido >= 100 && numpedido <= 999) {
        	   ped = "0" + pedido;
           }else {
        	   ped = pedido;
           }


           Paragraph detalhepedido = new Paragraph("Pedido nº:                            Funcionário", negrito);
           Paragraph detalhes = new Paragraph(ped + "                                             " + nomefuncionario, fontePadrao);
           document.add(detalhepedido);
           document.add(detalhes);
           
           
           Paragraph obs = new Paragraph("Observações: ", negrito);
           Paragraph observacao = new Paragraph(obsOs, fontePadrao);
           document.add(obs);
           document.add(observacao);
           
           document.add(espaco);
           document.add(espaco);
           
           BD b = new BD();
           
   		if (b.getConnection()) {
			try {
				String sql = "SELECT S.ID, S.SERVICO, S.PRECO, S.TIPO, O.QUANTIDADE, O.TOTAL "
						+ "FROM SERVICO S, SERVICOORDEM O "
						+ "WHERE S.ID = O.IDSERVICO "
						+ "AND O.IDORDEM = " + id;
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
				Paragraph cabecalho = new Paragraph("ID:     PREÇO:     QTDADE:      DESCRIÇÃO", negrito);
				document.add(cabecalho);
					while (b.rs.next()) {
						String idservico = null;
						int serv = b.rs.getInt("ID");
						
						String numquant = null;
						int quantidade = b.rs.getInt("QUANTIDADE");
						if(serv> 0 && serv <= 9) {
							idservico = "00" + String.valueOf(serv);
						}else if(serv>= 10 && serv <= 99) {
							idservico = "0" + String.valueOf(serv);
						}else {
							idservico = String.valueOf(serv);
						}
						
						if(quantidade > 0 && quantidade <= 9) {
							numquant = "0" + String.valueOf(quantidade);
						}else if (quantidade >= 10 && quantidade <= 99){
							numquant = String.valueOf(quantidade);
						}
						
						if(quantidade >= 100){
							numquant = String.valueOf(quantidade);
							Paragraph lista = new Paragraph(idservico + "        R$ " 
									+ b.rs.getDouble("PRECO") + "0               " 
									+ numquant + "                 " 
									+ b.rs.getString("SERVICO") + " (" + b.rs.getString("TIPO") + ")" , fontePadrao);
										document.add(lista);
						}else {
							Paragraph lista = new Paragraph(idservico + "        R$ " 
									+ b.rs.getDouble("PRECO") + "0                 " 
									+ numquant + "                 " 
									+ b.rs.getString("SERVICO") + " (" + b.rs.getString("TIPO") + ")" , fontePadrao);
										document.add(lista);
						}

					}
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
			
			try {
				String sql = "SELECT PRECO FROM ORDEM WHERE ID = " + id;
				b.st = b.con.createStatement();
				b.rs = b.st.executeQuery(sql);
				StringBuffer sb = new StringBuffer();
					while (b.rs.next()) {
						Paragraph total = new Paragraph("Total : R$ " + b.rs.getDouble("PRECO") + "0", negrito);
						total.setAlignment(total.ALIGN_LEFT);
						document.add(total);
					}
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
			}
		}

           document.add(espaco);
           document.add(espaco);
           
           Paragraph saida = new Paragraph("Data execução: ____/____/_______", negrito);
           document.add(saida);
           
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           
           Paragraph recebido = new Paragraph("Visto funcionário:", negrito);
           recebido.setAlignment(recebido.ALIGN_CENTER);
           Paragraph assinatura = new Paragraph("_______________________________________", negrito);
           assinatura.setAlignment(assinatura.ALIGN_CENTER);
           document.add(recebido);
           document.add(espaco);
           document.add(assinatura);
           
           document.add(espaco);
           document.add(espaco);
           
           Paragraph aviso = new Paragraph("****Deixar esta comanda junto com a mercadoria****", fonteVermelha);
           aviso.setAlignment(aviso.ALIGN_CENTER);
           document.add(aviso);
           
           mensagem();
 }
       catch(DocumentException de) {
           System.err.println(de.getMessage());
       }
       catch(IOException ioe) {
           System.err.println(ioe.getMessage());
       }
       document.close();
   }
	
	/**
	 * Criar pdf para listar as contas filtradas
	 */
	
	private JTextField tfPesquisa;
	private JTextField tfInicio;
	private JTextField tfFim;
	private JRadioButton rbNaoPago, rbPago;

	public GeneratorPDF(JRadioButton rbNaoPago, JRadioButton rbPago, JTextField tfPesquisa, JTextField tfInicio,
			JTextField tfFim) {
		this.rbNaoPago = rbNaoPago;
		this.rbPago = rbPago;
		this.tfPesquisa = tfPesquisa;
		this.tfInicio = tfInicio;
		this.tfFim = tfFim;
	}
	
	public void pdfListaContas() {
		String nome = JOptionPane.showInputDialog("Como gostaria de nomear este relatorio?");
		// criação do documento
	       Document document = new Document();
	       try {
	           
	    	// Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
	           Font title = new Font(FontFamily.COURIER, 26, Font.BOLD+Font.ITALIC+Font.UNDERLINE);
	           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\tatia\\Downloads\\PDF\\Relatorio_" + nome + ".pdf"));
	           document.open();
	           document.setPageSize(PageSize.A4);
	           
	           Image figura = Image.getInstance((TelaSistema.class.getResource("/view/logotipo.png")));
	           figura.setAlignment(figura.ALIGN_CENTER);
	           document.add(figura);
	           
	           
	           // adicionando um parágrafo no documento
	           Paragraph espaco = new Paragraph(" ");
	           document.add(espaco);
	           
	           //Adicionando título
	           Paragraph titulo = new Paragraph("Relatório - Contas a pagar", fontetitulo);
	           titulo.setAlignment(titulo.ALIGN_CENTER);
	           document.add(titulo);
	           
	           document.add(espaco);
	           document.add(espaco);
	           
	           BD b = new BD();
	           
	           String sql = null;
	   		if(rbNaoPago.isSelected() && rbPago.isSelected()) {
	   			sql = "SELECT DATA, PAGOA, VALOR, STATUS "
	   					+ "FROM HISTCONTAS WHERE DATA BETWEEN '" + tfInicio.getText() + "' AND '" + tfFim.getText() + "' "
	   					+ "AND STATUS LIKE 'Pago' "
	   					+ "OR STATUS LIKE 'Não Pago' "
	   					+ "AND PAGOA LIKE '" + tfPesquisa.getText() + "%' ";
	   		}else if(rbNaoPago.isSelected()) {
	   			sql = "SELECT DATA, PAGOA, VALOR, STATUS "
	   					+ "FROM HISTCONTAS WHERE DATA BETWEEN '" + tfInicio.getText() + "' AND '" + tfFim.getText() + "' "
	   					+ "AND STATUS LIKE 'Não Pago' "
	   					+ "AND PAGOA LIKE '" + tfPesquisa.getText() + "%' ";
	   		}else {
	   			sql = "SELECT DATA, PAGOA, VALOR, STATUS "
	   					+ "FROM HISTCONTAS WHERE DATA BETWEEN '" + tfInicio.getText() + "' AND '" + tfFim.getText() + "' "
	   					+ "AND STATUS LIKE 'Pago' "
	   					+ "AND PAGOA LIKE '" + tfPesquisa.getText() + "%' ";
	   		}
	   		if (b.getConnection()) {
	   			try {
	   				b.st = b.con.createStatement();
	   				b.rs = b.st.executeQuery(sql);
					Paragraph cabecalho = new Paragraph("Data                   Valor                Status          Pago a", negrito);
					document.add(cabecalho);
						while (b.rs.next()) {
							String status = null;
							if(b.rs.getString("STATUS").equals("Pago")) {
								status = b.rs.getString("STATUS") + "       ";
							}else {
								status = b.rs.getString("STATUS");
							}
							
							double valor = b.rs.getDouble("Valor");
							String preco = null;
							if(valor > 0 && valor <= 9) {
								preco = "     " + String.valueOf(valor);
							}else if(valor >= 10 && valor <= 99) {
								preco = "    " + String.valueOf(valor);
							}else if(valor >= 101 && valor <= 999) {
								preco = "  " + String.valueOf(valor);
							}else{
								preco = "" + String.valueOf(valor);
							}
								Paragraph lista = new Paragraph(b.rs.getString("DATA") + "             R$" 
										+ preco + "0             "+ status + "        " 
										+ b.rs.getString("PAGOA"), fontePadrao);
											document.add(lista);
						}
						
				} catch (SQLException e) {
					System.out.println("ERRO" + e.toString());
				}

			}	           
	   		mensagem();
	 }
	       catch(DocumentException de) {
	           System.err.println(de.getMessage());
	       }
	       catch(IOException ioe) {
	           System.err.println(ioe.getMessage());
	       }
	       document.close();
	}
	
	public void pdfListaCaixa() {
		String nome = JOptionPane.showInputDialog("Como gostaria de nomear este relatorio?");
		String dataInicio = JOptionPane.showInputDialog("Data de incio do relatório: (dd/mm/aaaa)");
		String dataFim = JOptionPane.showInputDialog("Data de incio do relatório: (dd/mm/aaaa)");
		// criação do documento
	       Document document = new Document();
	       try {
	           
	    	// Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
	           Font title = new Font(FontFamily.COURIER, 26, Font.BOLD+Font.ITALIC+Font.UNDERLINE);
	           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Michele\\Downloads\\PDF\\Relatorio_" + nome + ".pdf"));
	           document.open();
	           document.setPageSize(PageSize.A4);
	           
//	           Image figura = Image.getInstance((TelaSistema.class.getResource("/view/logotipo.png")));
//	           figura.setAlignment(figura.ALIGN_CENTER);
//	           document.add(figura);
	           
	           
	           // adicionando um parágrafo no documento
	           Paragraph espaco = new Paragraph(" ");
	           document.add(espaco);
	           
	           //Adicionando título
	           Paragraph titulo = new Paragraph("Relatório - Fluxo de caixa", fontetitulo);
	           titulo.setAlignment(titulo.ALIGN_CENTER);
	           document.add(titulo);
	           
	           document.add(espaco);
	           document.add(espaco);
	           
	           BD b = new BD();
	   		if (b.getConnection()) {
	   			try {
	   				String sql = "select * from histCaixaTotal where data between '" + dataInicio + "' and '" + dataFim + "'";
	   				System.out.println(sql);
	   				b.st = b.con.createStatement();
	   				b.rs = b.st.executeQuery(sql);
					Paragraph cabecalho = new Paragraph("Data                   Entrada             Contas          Comissão          Lucro", negrito);
					document.add(cabecalho);
						while (b.rs.next()) {
							String data = (b.rs.getString("DATA") + "     ");
							double caixa = b.rs.getDouble("VALORENTRADA");
							String caixaTotal = null;
							if(caixa > 0 && caixa <= 9) {
								caixaTotal = "     " + String.valueOf(caixa);
							}else if(caixa >= 10 && caixa <= 99) {
								caixaTotal = "    " + String.valueOf(caixa);
							}else if(caixa >= 101 && caixa <= 999) {
								caixaTotal = "  " + String.valueOf(caixa);
							}else{
								caixaTotal = "" + String.valueOf(caixa);
							}
							
							double conta = b.rs.getDouble("VALORCONTA");
							String contas = null;
							if(conta > 0 && conta <= 9) {
								contas = "     " + String.valueOf(conta);
							}else if(conta >= 10 && conta <= 99) {
								contas = "    " + String.valueOf(conta);
							}else if(conta >= 101 && conta <= 999) {
								contas = "  " + String.valueOf(conta);
							}else{
								contas = "" + String.valueOf(conta);
							}
							
							double saida = b.rs.getDouble("VALORCOMISSAO");
							String contaTotal = null;
							if(saida > 0 && saida <= 9) {
								contaTotal = "     " + String.valueOf(saida);
							}else if(saida >= 10 && saida <= 99) {
								contaTotal = "    " + String.valueOf(saida);
							}else if(saida >= 101 && saida <= 999) {
								contaTotal = "  " + String.valueOf(saida);
							}else{
								contaTotal = "" + String.valueOf(saida);
							}
							
							double lucro = b.rs.getDouble("LUCRO");
							String lucroTotal = null;
							if(lucro > 0 && lucro <= 9) {
								lucroTotal = "     " + String.valueOf(saida);
							}else if(lucro >= 10 && lucro <= 99) {
								lucroTotal = "    " + String.valueOf(saida);
							}else if(lucro >= 101 && lucro <= 999) {
								lucroTotal = "  " + String.valueOf(saida);
							}else{
								lucroTotal = "" + String.valueOf(saida);
							}
								Paragraph lista = new Paragraph(b.rs.getString("DATA") + "             R$" 
										+ caixaTotal + "0             R$"+ contaTotal + "0          R$" 
										+ saida + "0             R$" + lucro + "0", fontePadrao);
											document.add(lista);
						}
						
				} catch (SQLException e) {
					System.out.println("ERRO" + e.toString());
				}

			}	           
	   		mensagem();
	 }
	       catch(DocumentException de) {
	           System.err.println(de.getMessage());
	       }
	       catch(IOException ioe) {
	           System.err.println(ioe.getMessage());
	       }
	       document.close();
	}
	
 }

