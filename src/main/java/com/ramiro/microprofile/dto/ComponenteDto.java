package com.ramiro.microprofile.dto;

import java.util.ArrayList;
import java.util.List;

import com.ramiro.microprofile.form.ComponenteForm;

public class ComponenteDto {
	
	private String tipo;
	private String nome;
	private String titulo;
	private Integer ordenacao;
	private Boolean visibilidade;
	List<ComponenteAtributoDto> dados;
	
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
	public Boolean isVisibilidade() {
		return visibilidade;
	}
	public void setVisibilidade(Boolean visibilidade) {
		this.visibilidade = visibilidade;
	}
	public List<ComponenteAtributoDto> getDados() {
		return dados;
	}
	public void setDados(List<ComponenteAtributoDto> dados) {
		this.dados = dados;
	}

	public static ComponenteDto of(ComponenteForm componenteForm) {
		
		ComponenteDto componenteDto = new ComponenteDto();
		componenteDto.setNome(componenteForm.getNome());
		componenteDto.setOrdenacao(componenteForm.getOrdenacao());
		componenteDto.setTipo(componenteForm.getTipo());
		componenteDto.setVisibilidade(componenteForm.getVisibilidade() != null ? componenteForm.getVisibilidade().equals("true") : null);
		componenteDto.setTitulo(componenteForm.getTitulo());

		if (componenteForm.getDados() != null) {
			
			List<ComponenteAtributoDto> lista = new ArrayList<ComponenteAtributoDto>();
			
			componenteForm.getDados().stream()
				.forEach(atributoForm -> lista.add(ComponenteAtributoDto.of(atributoForm)));
		
			componenteDto.setDados(lista);
		}
		
		return componenteDto;
	}
	
	
	
}
