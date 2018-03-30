/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("r2")
public class R2 {
	
	@GET
	@Path("a")
	public String M1() {
		return "M1";
	}
	
	@GET
	@Path("a/{id: \\d+}") // This is a regex that is looking for a number
	public String M2(@PathParam("id") int x) {
		return "M2 = " + x;
	}
	
	@GET
	@Path("a/{id}")
	public String M3(@PathParam("id") String x) {
		return "M3 = " + x;
	}
}