/*****************************************************************************
	Vince Chang
	LAB 1
	Update your previous service:
	1. Respond to GET /Myservice/v1/Suppliers/001 with a text/plain description
	of Suppliers 001
	2. Respond to GET /MyService/v1/Customers/001/Suppliers/1 by returning a
	text/plain representation of the first supplier with a relationship with
	Customer 001
	3. Respond to GET /MyService/Suppliers by returning a text/html <ul> type
	list that contains <a href=...> elements that are links to each Supplier
	detail
	4. Arrange that the user must be logged in, and in an administrative role, to
	be able to perform the DELETE operation listed in Lab 1 item 4
******************************************************************************/

package rest;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import entity.Supplier;
import entity.SupplierTable;

@Path("suppliers")
public class RestSuppliers {

	@Context
	private UriInfo info;
	
	@GET
	@Path("/{key: \\d+}")
	public String m1(@PathParam("key") int pk) {
		Supplier s = SupplierTable.findByPrimaryKey(new UUID(0, pk));
		
		return s.toString();
	}
	
	@GET
	public String m2() {
		StringBuilder sb = new StringBuilder();
		UriBuilder builder;
		
		sb.append("<ul>\n");
		
		for (Supplier s : SupplierTable.getAll()) {
			builder = info.getBaseUriBuilder();
			
			//String sInfo = s.getId().toString();
			//sInfo = sInfo.substring(sInfo.length()-3, sInfo.length());
			
			sb.append("\t<a href=" + builder.path("/supplieruuid/"+s.getId()) + ">Supplier Id Click Here!</a>\n");
		}
		
		sb.append("</ul>");
		
		return sb.toString();
	}
	
	@GET
	@Path("supplieruuid/{skey:  \\d+}")
	public String m3(@PathParam("skey") int sk) {
		return SupplierTable.findByPrimaryKey(new UUID(0, sk)).getId().toString();
	}
}