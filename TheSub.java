/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;

public class TheSub {

	@GET
	public String m1(@PathParam("id") String id) {
		return "This is a sub resource ..." + id;
	}
}