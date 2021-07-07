package com.ramiro.microprofile.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ramiro.microprofile.dto.TemplateDto;
import com.ramiro.microprofile.form.Form;
import com.ramiro.microprofile.service.ComprovanteBinder;


@Path("comprovante")
public class ComprovanteController {
	
	@Inject
	private ComprovanteBinder comprovanteBinder;

	@POST
	@Path("detalhe")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@Timed(name = "detalhe")
	//@Counted (name="detalheCount",description="The number of request to the comprovante")
	public Response obterComprovante(Form form) {

		TemplateDto template = comprovanteBinder.bind(form.getComprovante(), form.getTemplate());

		return Response.ok(template, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("detalhe")
	@Produces(MediaType.TEXT_PLAIN)
	public Response obterComprovanteGet() {

		return Response.ok("ola mundo!", MediaType.TEXT_PLAIN).build();
	}

}
