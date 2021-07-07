package com.ramiro.microprofile.dto;

import com.ramiro.microprofile.form.ComponenteAtributoForm;

public class ComponenteAtributoDto {

	private String rotulo;
	private String conteudo;
	private Boolean visibilidade;
	private Integer ordenacao;
	
	public String getRotulo() {
		return rotulo;
	}
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Boolean isVisibilidade() {
		return visibilidade;
	}
	public void setVisibilidade(Boolean visibilidade) {
		this.visibilidade = visibilidade;
	}
	public Integer getOrdenacao() {
		return ordenacao;
	}
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}

	public static ComponenteAtributoDto of(ComponenteAtributoForm atributoForm) {
		
		ComponenteAtributoDto componenteAtributoDto = new ComponenteAtributoDto();
		componenteAtributoDto.setConteudo(atributoForm.getConteudo());
		componenteAtributoDto.setOrdenacao(atributoForm.getOrdenacao());
		componenteAtributoDto.setRotulo(atributoForm.getRotulo());
		componenteAtributoDto.setVisibilidade(atributoForm.getVisibilidade() != null ? atributoForm.getVisibilidade().equals("true") : false);
		return componenteAtributoDto;
	}
	
}