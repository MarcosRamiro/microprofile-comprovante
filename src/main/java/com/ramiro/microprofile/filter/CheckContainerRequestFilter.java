package com.ramiro.microprofile.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class CheckContainerRequestFilter implements ContainerRequestFilter {

	private final String AUTHORIZATION = "Authorization";
	private final String SENHA = "123";
	private final String MSG_TOKEN_INCORRETO = "token incorreto";
	private final String MSG_AUTHORIZATION_AUSENTE = "Authorization header ausente";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		this.tratarHeaderAuthorization(requestContext);

	}

	private void tratarHeaderAuthorization(ContainerRequestContext requestContext) {

		if (requestContext.getUriInfo().getPath().contains("comprovante/")) {

			if (requestContext.getHeaders().containsKey(this.AUTHORIZATION)) {

				String authorization = requestContext.getHeaderString(AUTHORIZATION);

				if (authorization.startsWith("Basic ")) {
					authorization = authorization.replaceFirst("Basic ", "");
					byte[] byteUserPass = Base64.getDecoder().decode(authorization);
					String userPass = new String(byteUserPass);
					StringTokenizer token = new StringTokenizer(userPass, ":");

					if (token.countTokens() == 2 && token.nextToken().equals("marcos")
							&& token.nextToken().equals("123"))
						return;

				}

				requestContext
						.abortWith(Response.status(Response.Status.FORBIDDEN).entity(this.MSG_TOKEN_INCORRETO).build());

			} else {

				requestContext.abortWith(
						Response.status(Response.Status.FORBIDDEN).entity(this.MSG_AUTHORIZATION_AUSENTE).build());
			}
		}
	}

}
