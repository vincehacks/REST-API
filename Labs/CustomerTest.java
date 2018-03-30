/* Vince Chang
 * REST API Training
 * 3/28/18
 */

package restlab1;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class CustomerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		Response r = RestAssured.
				given().request().contentType(ContentType.JSON).response().contentType(ContentType.JSON).
				when().get("http://localhost:8080/restlab1/v1/customers/001");
				
			r.then().statusCode(200);
			r.then().contentType(ContentType.JSON);
	}
	
	@Test
	public void test2() {
		Response r = RestAssured.
				given().request().contentType(ContentType.JSON).response().contentType(ContentType.HTML).
				when().get("http://localhost:8080/restlab1/v1/customers/999");
				
			r.then().statusCode(406);
			r.then().contentType(ContentType.HTML);
	}
}