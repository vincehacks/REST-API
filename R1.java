/* Vince Chang
 * REST API Training
 * 3/28/18
 * 
 * @Consumes what I will accept
 * @Produces what I will give
 */

package com.macys.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("r1")
public class R1 {

	@GET
	public String M1() {
		return "YO REST!";
	}
	
	@GET
	@Path("x")
	public String M2(@DefaultValue("Bubba")@QueryParam("name")String name) {
		return "Get out of here -> " + name;
	}
	
	@DELETE
	@Path("x")
	public String M3() {
		return "Deleted...";
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("y")
	public String M4() {
		return "M4";
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_XML)
	@Path("y")
	public String M5() {
		return "M5";
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("y")
	public String M6() {
		return "M6";
	}
}