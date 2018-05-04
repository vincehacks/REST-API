/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("boats")
public class BoatResource {

  @GET
  @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  public Response M1() {
    List<Employee> emps = new ArrayList<>();
    emps.add(new Employee (1,"Bubba","Chef",50));
    emps.add(new Employee (1,"Bubba's Wife","Captain",100));

    // Create a new boat using the constructor
    Boat b = new Boat("The Big Boat",450,40,"Red","The Big Boat Maker",emps);

    // Return the response status with the boat that I just built
    return Response.status(200).entity(b).build();
  }

  @GET
  public String M2() {
    return "No Go on the Media Type ...";
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public String M3(Boat b) {
    return "You just created: " + b.toString();
  }
}