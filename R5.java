/* Vince Chang
 * REST API Training
 * 3/28/18
 * 
 * It is up to the developer to know when to thrown an exception or not. If
 * the information is useful, you should thrown an exception
 */

package com.macys.rest;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("r5/{id}")
public class R5 {
	
	// You can not inject this into Singletons
	//	@PathParam("id")
	//	private String id;
	
	public R5() {
		System.out.println("created R5...");
	}

	@GET
	public String m1(){
		return "Hello from R5...";
	}

	@GET
	@Path("z")
	public Response m2(){
		return Response.status(200).entity("This is a response ..").
		cookie(new NewCookie("cook","oatmeal")).header("tx-macy","bubba").build();
	}

	@GET
	@Path("err1")
	public String M3() {

		// Generates the response that we want to return if there is an error
		ResponseBuilder builder = Response.status(506).entity
		("Something really bad happended...");

		// Business Logic, if something bad happen, want to inform
		throw new WebApplicationException(builder.build());
	}

	@GET
	@Path("err2")
	public String M4() throws SQLException {
		// This is an example if a SQL exception bubbles up, don't want the user
		// to see the stack trace!
		throw new SQLException("ORA-1506");
	}

	@GET
	@Path("err3") 
	public String M5(){
		throw new NullPointerException();
	}
}