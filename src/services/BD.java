package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

	/**
	 * Todos são interfaces.
	 */
	

	//Para realizar a conexão
	public Connection con = null;
	//Executar instruções do banco 
	public Statement st = null;
	//simula uma tabela em memória
	public ResultSet rs = null;
	
	private final String LOGIN = 
			"sa";
	private final String SENHA = 
			"123456";
	private final String DRIVER = 
			"com.microsoft.sqlserver.jdbc.SQLServerDriver";//aqui fica o caminho do driver
	private final String DATABASE = 
			"projetoLavanderia";
	private final String URL = 
			"jdbc:sqlserver://DESKTOP-2C8EAGK:1433;databaseName=" + DATABASE;
	
	public boolean getConnection() {
		try {
			Class.forName(DRIVER);
//			System.out.println("Sucesso");
			try {
				con = DriverManager.getConnection(URL, LOGIN, SENHA);
//				System.out.println("Sucesso");
				return true;
			} catch (SQLException e) {
				System.out.println("ERRO" + e.toString());
				return false;
			}
		}catch(ClassNotFoundException e) {
			System.out.println("Driver não encontrado" + e.toString());
			return false;
		}
	}
	
	public void close() {
		try {
			if( rs != null) rs.close();
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
		
		try {
			if( st != null) st.close();
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
		
		try {
			if( con != null) {
				con.close();
				System.out.println("Desconectado");
			}
		}catch(SQLException e) {
			System.out.println("ERRO" + e.toString());
		}
	}
	
	public static void main(String[] args) {
		BD b = new BD();
		b.getConnection();
		b.close();
	}

}
