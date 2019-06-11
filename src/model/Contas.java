package model;

public class Contas {
	/**
	 * propriedades das contas que serão cadastradas
	 */
	private int id;
	private String pagoa;
	private double valor;
	private String data;
	private String recorrencia;
	private String frequencia;
	private String tipo;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPagoa() {
		return pagoa;
	}
	public void setPagoa(String pagoa) {
		this.pagoa = pagoa;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getRecorrencia() {
		return recorrencia;
	}
	public void setRecorrencia(String recorrencia) {
		this.recorrencia = recorrencia;
	}
	public String getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
