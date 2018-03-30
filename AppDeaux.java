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