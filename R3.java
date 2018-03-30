/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


@Path("r3/{id}")
public class R3 {
	
	@PathParam("id")
	private String id;
	
	@Context
	private HttpHeaders heads;
	
	@Context
	private UriInfo info;
	
	@Context
	private HttpServletRequest req;
	
	public R3() {
		System.out.println("Created R#...");
	}
	
	@GET
	public String m1() {
		return "Welcome to R3..." + heads.getHeaderString("user-agent");
	}
	
	@GET
	@Path("info")
	public String m2() {
		UriBuilder builder = info.getBaseUriBuilder();
		return builder.path("bubba").toString();
	}
	
	@GET
	@Path("req")
	public String m3() {
		return req.getParameter("bubba");
	}
	
	@Path("z")
	public TheSub m4() {
		return new TheSub();
	}
}