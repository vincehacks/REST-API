/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package com.macys.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.macys.rest.providers.GenericExceptionMapper;
import com.macys.rest.providers.RequestFilter;
import com.macys.rest.providers.SQLExceptionMapper;

@ApplicationPath("/v1")
public class RestApp extends Application {

  private Set<Class<?>> resources = new HashSet<>();
  private Set<Object> singletons = new HashSet<>();

  public RestApp() {
    System.out.println("Created V1 App");
    resources.add(R1.class);
    resources.add(R3.class);
    resources.add(BoatResource.class);
    resources.add(SQLExceptionMapper.class);
    resources.add(GenericExceptionMapper.class);
    resources.add(RequestFilter.class);

    singletons.add(new R5());
  }
  @Override
  public Set<Class<?>> getClasses() {
    return resources;
  }
  @Override
  public Set<Object> getSingletons() {
    return singletons;
  }
}