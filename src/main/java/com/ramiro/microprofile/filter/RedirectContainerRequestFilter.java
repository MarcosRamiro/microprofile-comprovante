package com.ramiro.microprofile.filter;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class RedirectContainerRequestFilter implements ContainerRequestFilter {

	private final String MSG_PARAMETROS_INVALIDOS = "Parametros invalidos";
	private final String PATH_ROUTER = "/webapi/router";

	@Context
	HttpHeaders headers;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		this.tratarDirecionamento(requestContext);
	}

	private void tratarDirecionamento(ContainerRequestContext requestContext) {

		if (requestContext.getUriInfo().getAbsolutePath().getPath().contains(this.PATH_ROUTER)) {

			String operacao = headers.getHeaderString("op");

			if (operacao != null) {

				byte[] byteUserPass = Base64.getDecoder().decode(operacao);
				String actionToRedirect = new String(byteUserPass);

				requestContext.setRequestUri(URI.create(actionToRedirect));

			} else {
				requestContext.abortWith(
						Response.status(Response.Status.FORBIDDEN).entity(this.MSG_PARAMETROS_INVALIDOS).build());
			}

		}

	}

}
