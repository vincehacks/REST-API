/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	
	@Override
	public Response toResponse(Throwable exception) {
		return Response.status(500).entity(exception.getMessage() + "<--Fix this").build();
	}
}