package com.ramiro.microprofile.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.ramiro.binder.ServiceBind;
import com.ramiro.binder.ServiceBindException;
import com.ramiro.microprofile.dto.ComponenteAtributoDto;
import com.ramiro.microprofile.dto.ComponenteDto;
import com.ramiro.microprofile.dto.TemplateDto;
import com.ramiro.microprofile.form.TemplateForm;

@RequestScoped
public class ComprovanteBinder {
	
	@Inject
	private ServiceBind serviceBind;

	public TemplateDto bind(Object comprovante, TemplateForm templateForm) {

		templateForm.getComponentes().stream()
		
				.forEach(componente -> {
					
					if (componente.getTitulo() != null && !componente.getTitulo().isEmpty())
						componente.setTitulo(this.encapsulaTry(serviceBind, comprovante,componente.getTitulo()));
					
					if (!componente.getTipo().equals("header") && !componente.getTipo().equals("footer")) {

						if (componente.getVisibilidade() != null && !componente.getVisibilidade().isEmpty())
							componente.setVisibilidade( this.encapsulaTry(serviceBind, comprovante,  componente.getVisibilidade() ));

						if (componente.getDados() != null) {
							componente.getDados().stream().forEach(compAtributo -> {

								if (compAtributo.getRotulo() != null && !compAtributo.getRotulo().isEmpty())
									compAtributo.setRotulo(this.encapsulaTry(serviceBind, comprovante,  compAtributo.getRotulo()));

								if (compAtributo.getVisibilidade() != null && !compAtributo.getVisibilidade().isEmpty())
									compAtributo.setVisibilidade(this.encapsulaTry(serviceBind, comprovante,compAtributo.getVisibilidade()));

								if (compAtributo.getConteudo() != null && !compAtributo.getConteudo().isEmpty())
									compAtributo.setConteudo(this.encapsulaTry(serviceBind, comprovante,compAtributo.getConteudo()));
							});
						}
					}
						

				});

		TemplateDto templateDto = TemplateDto.of(templateForm);
		
		this.removeComponentesVisualizacaoFalso(templateDto);

		return templateDto;
	}

	private String encapsulaTry(ServiceBind serviceBind, Object comprovante, String padrao)  {
		try {
			return serviceBind.bind(padrao, comprovante);
		}catch (ServiceBindException e ) {
			e.printStackTrace();
			return padrao;
		}
	
	}
	
	private void removeComponentesVisualizacaoFalso(TemplateDto templateDto) {
		
		List<ComponenteDto> componentesVisiveis = templateDto.getComponentes().stream()
		.filter(componente -> componente.isVisibilidade() == null || componente.isVisibilidade())
		.collect(Collectors.toList());
		
		for (ComponenteDto componenteDto : componentesVisiveis) {
			this.removeDadosVisualizacaoFalso(componenteDto);
		}
		
		templateDto.setComponentes(componentesVisiveis);
		
	}
	
	private void removeDadosVisualizacaoFalso(ComponenteDto componenteDto) {
		
		if (componenteDto.getDados() != null) {
			List<ComponenteAtributoDto> dados = componenteDto.getDados().stream()
			.filter(dado -> dado.isVisibilidade() == null || dado.isVisibilidade())
			.collect(Collectors.toList());
			componenteDto.setDados(dados);
		}
		
	}

}
