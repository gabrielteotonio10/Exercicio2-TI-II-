package model;

public class Informacoes {
	private String descricao;
	private String data;
	private String hora_inicio;
	private String hora_termino;
	
	public Informacoes() {
		this.descricao = "";
		this.data = "";
		this.hora_inicio = "";
		this.hora_termino = "";
	}
	
	public Informacoes(String descricao, String data, String hora_inicio, String hora_termino) {
		this.descricao = descricao;
		this.data = data;
		this.hora_inicio = hora_inicio;
		this.hora_termino = hora_termino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_termino() {
		return hora_termino;
	}

	public void setHora_termino(String hora_termino) {
		this.hora_termino = hora_termino;
	}

	@Override
	public String toString() {
		return "Cronograma do dia [descrição=" + descricao + ", data=" + data + ", hora de início=" + hora_inicio + ", hora final=" + hora_termino + "]";
	}	
}
