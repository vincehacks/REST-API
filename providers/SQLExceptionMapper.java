/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest.providers;

import java.sql.SQLException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

// Creating a centralized location that could handle SQL Exceptions (this is a callback)
// By adding Provider, we are telling Jersey to load this provider instead
@Provider
public class SQLExceptionMapper implements ExceptionMapper<SQLException> {
	
	@Override
	public Response toResponse(SQLException exception) {
		// We are extending the REST Platform
		ResponseBuilder builder = Response.status(500).entity(exception.getMessage());
		return builder.build();
	}
}