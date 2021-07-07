package com.ramiro.microprofile.form;

import java.util.List;

public class ComponenteForm {
	
	private String tipo;
	private String nome;
	private String titulo;
	private Integer ordenacao;
	private String visibilidade;
	
	List<ComponenteAtributoForm> dados;

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getOrdenacao() {
		return ordenacao;
	}
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}
	public String getVisibilidade() {
		return visibilidade;
	}
	public void setVisibilidade(String visibilidade) {
		this.visibilidade = visibilidade;
	}
	public List<ComponenteAtributoForm> getDados() {
		return dados;
	}
	public void setDados(List<ComponenteAtributoForm> dados) {
		this.dados = dados;
	}
	
}
