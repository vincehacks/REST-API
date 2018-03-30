/*****************************************************************************
	Vince Chang
	LAB 1
	Take the sample Customers and Suppliers code and use it to create a RESTful
	service that:
	1) Responds to GET /MyService/v1/Customers with a text/plain listing of all
	Customers, one per line
	2) Responds to GET /MyService/v1/Customers/001 with a text/plain listing of
	the single customer with id 001
	3) Ensure that GET /MyService/v1/Customers/Fred does not invoke any method;
	use a regular expression to achieve this
	4) Responds to DELETE /MyService/v1/Customers/001 by deleting the single
	customer with id 001
	5) Responds to GET /MyService/v1/Customers?query=name.eq.Fred+Jones with a
	text/plain representation of the customer with the name Fred Jones
	6) In each case, test your service by using POSTMAN in Chrome


	LAB 3
	Continue to update your service:
	1. Respond to GET /MyService/v1/Customers/999 (or any out of range ID
	value) with a status code of 404 Not Found
	2. Modify the method further so that it returns XML or JSON according to the
	requested Accept type from the client. Modify the @Produces annotation of
	this method to indicate that both XML and JSON are offered, and that the
	method no longer offers text/plain.
******************************************************************************/

package rest;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import entity.Customer;
import entity.CustomerTable;
import entity.RelationshipTable;
import entity.Supplier;

@Path("customers")
public class RestCustomers {

	@Context
	private UriInfo info;
	
	@GET
	@Path("/{cKey: \\d+}/suppliers/{sKey: \\d+}")
	public String relationship(@PathParam("cKey") int ck) {
		
		StringBuilder sb = new StringBuilder();
		
		for (Supplier s : RelationshipTable.findSuppliersOfCustomer(new UUID(0, ck))) {
			sb.append(s.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	

	// Lab 3
	@GET
	@Path("/{ckey: \\d+}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response outOfRange(@PathParam("ckey") int ck) {
		
		Customer c = CustomerTable.findByPrimaryKey(new UUID(0, ck));
		Response r = null;
		
		if (ck < 0 || ck >= 999) {
			r = Response.status(404).entity(c).build();
		} else {
			r = Response.status(200).entity(c).build();
		}
		
		return r;
	}
	
	@GET
	@Path("/{customerIdString}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response m3(@PathParam("customerIdString") UUID uuid) {
		
		Customer c = CustomerTable.findByPrimaryKey(uuid);
		ResponseBuilder builder = Response.status(200).entity(c.toString());
		
		return builder.build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response createNewCust() {
		
		Customer newC = new Customer(UUID.randomUUID(), "Vince",
		"123 Bulbasaur Lane", 0);
		
		CustomerTable.update(newC);
		System.out.println(newC.getId().toString());
		
		UriBuilder uriBuilder = info.getBaseUriBuilder();
		ResponseBuilder builder = Response.status(201).entity(
		"<a href=" + uriBuilder.path("/customers/" + newC.getId().toString()) + ">Click this to see new customer</a>");
		
		return builder.build();
	}
}