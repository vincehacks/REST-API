/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


// Everytime we are creating new classes, we need to add to the application
// class

@ApplicationPath("v2")
public class AppDeaux extends Application {

  // The ? will resolve to the correct type at runtime, you also do not need
  // to specify the types inside HashSet<> because it is defined when
  // Set<Class<?>> defines it! (This feature is new in Java 8+)
  private Set<Class<?>> resources = new HashSet<>();

  public AppDeaux() {
    System.out.println("Created V2 App");
    resources.add(R2.class);
    resources.add(R4.class);

  }
  @Override
  public Set<Class<?>> getClasses() {
    return resources;
  }
}