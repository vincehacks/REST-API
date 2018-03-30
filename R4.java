/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("r4")
public class R4 {

	public R4() {
		System.out.println("R4 Created...");
	}
	
	@GET
	public String m1() {
		return "R4-M1";
	}
}