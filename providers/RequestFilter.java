/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest.providers;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

// This is how to make everything apply to all REST services in one place
// This is one layer before everything that we build
@Provider
public class RequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String auth = requestContext.getHeaderString("macys-auth");
		// If the header is null, we won't get to the rest of our other code!
		if(auth == null) {
			requestContext.abortWith(Response.status(401).entity("No soup for you!").build());
		}
	}
}