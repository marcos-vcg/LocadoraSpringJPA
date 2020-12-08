package controller;

import java.util.Calendar;

import javax.validation.constraints.Size;

		

public class Tarefa {

	private Long id;
	
	@Size(min=5, message=" *Deve ter no mínimo 5 caracteres!")
	private String descricao;
	private boolean finalizado;
	private Calendar dataFinalizacao;
	
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public Calendar getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(Calendar dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
     
     
	
}
