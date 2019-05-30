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

public class GeneratorPDF {
	
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
        // cria��o do documento
       Document document = new Document();
       try {
           
    	// Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
           Font title = new Font(FontFamily.COURIER, 26, Font.BOLD+Font.ITALIC+Font.UNDERLINE);
           
           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\miche\\Downloads\\PDF\\PDF_Pedido_" + id + ".pdf"));
           document.open();
           document.setPageSize(PageSize.LARGE_CROWN_QUARTO);
           
           Image figura = Image.getInstance("C:\\Users\\miche\\OneDrive\\Imagens\\Pi\\logotipo.PNG");
           figura.setAlignment(figura.ALIGN_CENTER);
           document.add(figura);
           
           
           // adicionando um par�grafo no documento
           Paragraph espaco = new Paragraph(" ");
           document.add(espaco);
           
           //Adicionando t�tulo
           Paragraph titulo = new Paragraph("Pedido n� " + id, fontetitulo);
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
           
           Paragraph obs = new Paragraph("Observa��es: ", negrito);
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
           
           Paragraph saida = new Paragraph("Data sa�da: ____/____/_______", negrito);
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
           
           JOptionPane.showMessageDialog(null, "PDF gerado com sucesso", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
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
	 * Gerar PDF da Ordem de servi�o
	 * @param id
	 * @param nomecliente
	 * @param entrada
	 * @param previsto
	 * @param nomefuncionario
	 * @param obsOs
	 * @param pedido
	 */
	public void pdfOs(String id, String nomecliente, String entrada, String previsto, String nomefuncionario, String obsOs, String pedido){
        // cria��o do documento
       Document document = new Document();
       try {
           
    	// Definindo uma fonte, com tamanho 20 e negrito e Sublinhado em baixo
           Font title = new Font(FontFamily.COURIER, 26, Font.BOLD+Font.ITALIC+Font.UNDERLINE);
           
           PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\miche\\Downloads\\PDF\\PDF_Os_" + id + ".pdf"));
           document.open();
           document.setPageSize(PageSize.LARGE_CROWN_QUARTO);
           
           Image figura = Image.getInstance("C:\\Users\\miche\\OneDrive\\Imagens\\Pi\\logotipo.PNG");
           figura.setAlignment(figura.ALIGN_CENTER);
           document.add(figura);
           
           
           // adicionando um par�grafo no documento
           Paragraph espaco = new Paragraph(" ");
           document.add(espaco);
           
           //Adicionando t�tulo
           Paragraph titulo = new Paragraph("Ordem de servi�o n� " + id, fontetitulo);
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


           Paragraph detalhepedido = new Paragraph("Pedido n�:                            Funcion�rio", negrito);
           Paragraph detalhes = new Paragraph(ped + "                                             " + nomefuncionario, fontePadrao);
           document.add(detalhepedido);
           document.add(detalhes);
           
           
           Paragraph obs = new Paragraph("Observa��es: ", negrito);
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
				Paragraph cabecalho = new Paragraph("ID:     PRE�O:     QTDADE:      DESCRI��O", negrito);
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
           
           Paragraph saida = new Paragraph("Data execu��o: ____/____/_______", negrito);
           document.add(saida);
           
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           document.add(espaco);
           
           Paragraph recebido = new Paragraph("Visto funcion�rio:", negrito);
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
           
			JOptionPane.showMessageDialog(null, "PDF gerado com sucesso", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
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

