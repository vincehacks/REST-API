# REST-API (Representational State Transfer)

Created by Vince Chang </br>

REST API exercises that demonstrate a servlet using Maven and Java onto a server
that runs on Tomcat.

# RESTFUL WEB SERVICES IN JERSEY & JAX-RS

#### WHY EVERYONE IS EXCITED:

- Client side: thick client
- NoSQL and REST go together
- NoSQL has no locking
- People can update at the same time and it won’t be a process issue
- Updates happen at the column level, not at the row level

#### XML RULES:

1. Root
2. Open/Close
3. No Overlap

#### SOAP (SIMPLE OBJECT ACCESS PROTOCOL)

- SOAP is one of the reasons why we have REST
- REST takes web services and lines it up with HTTP protocol
- IDEA: treat everything out there like a resource
  - A service can **get, post, delete, patch**
- Thick (web browser) -> Thin -> Rich -> Rich.next (angular, react) -> loop
- When designing REST, start with the nouns
- **JAX-WS** is for SOAP Services
- **JAX-RS** is for REST Services
  - this is the standard for REST services
  - This model has 3 implementations:
    1. Apache CXF
    2. Rest Easy (JBOSS)
    3. Jersey
- **Micro-services**:
  - have a bunch of services that utilize different services to make business
    logic which clients have access
- If you want to make something scalable, make it **stateless**
  - scale _out not vertical_
- QueryParams are not required in the URLs

#### SOAP V.S REST

**SOAP**

- Design: WSDL: Web services description language
- Disc: UDDI:

**REST**

- Design: WADL: Web application description language (but not really used)
- Disc:
- This whole concept is used with Swagger
  - can describe the service to you in a `.json`
- REST needs 2 components:
  1. application
  2. root resource

#### RULES

1. Don’t violate the HTTP Protocol
2. We use the verbs on an entity (job)

#### VERBS

1. **GET**

   - Retrieve an obj
   - Pass parameters in the URL
   - GET request gives you _caching_

2. **PUT**

   - update entire obj

3. **POST**

   - create data
   - pass parameters in the body, but is a violation
   - people are doing this because they don’t want to see the information
     on the URL

4. **DELETE**

   - delete obj

5. **PATCH**
   - update a part of the obj

#### DIAGRAM

- Initial Line (status code: headers)
  - 100 informational
  - 200 status OK
  - 300 redirects
  - 400 client side errors, can recover come, developers deal with this the
    most
  - 500 server side errors, cant recover come
- i.e nothing found in the DB, send back 404
- Don’t violate the HTTP Protocol,
- Send back the correct status codes for REST developers to understand how to
  fix the problems

#### MAVEN

- Maven is a build manager
- The builds will use spring dependencies
- everything is stored in `home/.m2/repository`
- To run build: `mvn test, build, package`

# RESTFUL WEB SERVICES IN SPRING

#### WHAT IS REST?

- An architecture
- It is a mass silently agreed to process
  - It is not actually a standard
- It is all about dynamically revealing data
- REST is about sending resources
- In Spring
  - Available with Spring 3.0
  - Not an implementation of JAX-RS

#### MODELING REST

- Determine what is being exposed
  - Not everything in your application should be available.
  - What should be?
- Design your URIs
  - Remember the `@RequestMapping`? That is how you make it available
  - Should not be verbs (we could but we don't)
- Add operations
  - What HTTP method should be available for each
- Change your controller to be `@RestController`
  - Opens up availability for REST specific annotations

#### EXPOSURE

- Rest can send different data types besides just Java objects or XML
  - Spring give us classes for translating to and from JSON
  - There are others out there `@RequestMapping` has
    - `consumes` – what data types can this method understand
    - `produces` – what data types is this method able to send as a response
    - `headers` – modify HTTP headers, which could also tell us what kind of
      data type we want to send
    - `method` – what specific HTTP method will we respond to

#### DEFINING APPLICATIONS USED

```java
// Tells what response is expected
@RequestMapping(consumes={"application/XML", "application/JSON"})
@RequestMapping(headers="Accept=application/JSON") @RequestMapping(headers="Content-type=application/JSON")
```

#### OPERATIONS

- These are the methods that define how we receive requests
- If method is used, then request must match expected method
- If no method defined, request can be any of them
- Can take multiple operations
  `@RequestMapping(value="/book/{id}", method=RequestMethod.GET)`

#### DEFINING URIs

- URIs are not supposed to tell you what method or class that they are calling
- Supposed to **dynamically reveal** data
- Mostly use attribute 'method' to determine what is happening - Often use one
  `@RequestMapping` on entire class that has the value
  `@RequestMapping(method=RequestMethod.GET)`

#### METHODS TELL US SOMETHING

- **GET** : Retrieves; performs a 'select'
- **POST** : Create new; No primary key
- **PUT** : Updates; Has primary key
- **DELETE** : Remove
- **HEAD & OPERATIONS** : 2 different methods to retrieve meta-data

#### HANDLING RESPONSE CODES

- General best practice
  - REST presumes no visual client
  - Client needs to know what to expect to determine what to do next when there
    isn't a human on the client side

```java
@RequestMapping(method=RequestMethod.GET)
@ResponseStatus(HttpStatus.OK)
```

#### METHODS HAVE SPECIFIC HTTP RESPONSE CODES

- You may want to catch HTTP status codes in order to know how to proceed
- **GET** : 200 - OK
- **POST** : 201 - Created
- **PUT** : 201- Created; 404 - Not Found; 406 - Not Acceptable (format)
- **DELETE** : 200 - OK; 404 - Not Found

#### VIEW RESOLVERS

- Determines what format responses are sent in
- When we are dealing with REST we typically don't have a view, think of it as
  the XML being spit out as our view
- RESTful services are more likely to return the object
  - We usually use a `ContentNegotiationConfigurer`
    - Create this to understand what types are trying to work with
    - Tells us what media types to associate with what type of extension
    - Goes in the `MvcConfig` file

#### MvcConfig.xml

```java
@Configuration
@ComponentScan(basePackages="com.di.phonebook")
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
  public void configureContentNegotiation
    (ContentNegotiationConfigurer configurer) {
      // If we want to ignore the headers
      configure.ignoreAcceptHeader(false).
      favorPathExtension(true).
      defaultContentType(MediaType.APPLICATION_XML).
      mediaTypes(new HashMap<String, MediaType>(){
        {
          put("xml", MediaType.APPLICATION_XML); // JAXB Converter
          put("json", MediaType.APPLICATION_JSON); // Jackson Converter
        }
      });
} }
```

#### STRING FORMATS

- Generally use either XML or JSON
- For XML, we need to make the entity class JAXB compatible by using
  `@XmlRootElement` on the class
- For JSON compatibility, we need the Jackson APIs (specifically the
  `jackson-databind api`) - By using `@RestController`, we just need to have the
  databind api available
  and it will take care of reading to and from JSON without anything else
  needed on our part

#### JAXB (JAVA API FOR XML BINDING)

- Purpose is to convert objects from Java to a textual formal or vice versa
- We have to make one annotation and it will know to convert to a XML file
- Use `@XMLRootElement` to do this
- **_This will be an error when using `@RequestBody` if you do not have
  `@XMLRootElement` in the class of what you are trying to use!_**
- API that allows Java objects to be marshalled (converted from Java to XML)
  and unmarshalled (converted from XML to Java)
- Can write your own marshalling/unmarshalling, but not necessary
  - We have annotations that tell the entity how to convert

```java
@XMLRootElement
public class Address { private String street; private String city; ...
}
```

#### @RequestBody AND @ResponseBody

- `@RequestBody` – defines that the body of the HTTP packet will contain the
  'object' being passed into the method
- `@ResponseBody` – defines that the response will be in the body of the HTTP
  packet
- Both of these are used primarily in REST, and use their mappings to define
  the format (XML,JSON, etc.)

```java
@RequestMapping(method=RequestMethod.GET)
@ResponseBody
public MapCoordinates getLocation(@RequestBody Address address) { ... }
```

# SPRING REST SECURITY

#### SECURITY

- Who gets access to what, and how do we ensure that
  - **Authentication** – Are you who you say you are? (you enter your info)
  - **Authorization** – Are you allowed here? (confirms you are allowed)
- Many different providers for each of these
- Many different technologies that can be mixed and matched

#### SPRING SECURITY

- We need spring security JAR
  - Security JAR: `spring-security-web` and `spring-security-config`
  - Technology's JAR
    - Spring has several packages of security classes, many of which are
      specific to particular technologies
- We configure:
  - Authentication connection information
    - This is just what is needed for Spring to find it
    - Says little to nothing about how the data is actually sent
  - Authorization information
  - Java is role based authorization
  - Most of configuration is which roles a resource is restricted to

#### WEB SECURITY CONFIGURATION

- Main things is to annotate with `@EnableWebSecurity` && extend
  `WebSecurityConfigurerAdapter`

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
  @Override
  // This is the code that will talk to the security system (i.e Data Base)
  public void configure(AuthenticationManagerBuilder amb)throws Exception{
  ... }

  @Override
  public void configure(HttpSecurity http)throws Exception{ ...
  }
}
```

#### HttpSecurity

```java
@Override
public void configure(HttpSecurity http)throws Exception{
  http.authorizeRequests().
  antMatchers("/", "/home").permitAll().
  anyRequest().authenticated().and().
  formLogin().loginPage("/login").permitAll().and().
  logout().permitAll();
}
```

- Each step is considered in order
  - The above says that we are using authorization
  - Access to / or /home is allowed to anyone
  - All other requests must be authenticated using the login page defined and
    they have the ability to logout

#### CONFIGURATION EXPLANATION

- `@EnableWebSecurity`
  - Turns on web security
- `WebSecurityConfigurerAdapter`
  - Contains methods to override if needed for security configuration
    - Authentication managers
    - Authentication configuration
    - Trust resolvers
    - User details

#### CONFIGURE HttpSecurity

- Method to configure authorization for web pages
  - Includes login pages, logout mechanism and pages for HttpStatus errors

```java
@Override
public void configure(HttpSecurity http)throws Exception{
  http.authorizeRequests().antMatchers("/", "/home").permitAll(). antMatchers("/admin/**").hasRole("ADMIN"). anyRequest().authenticated().and(). formLogin().loginPage("/login").permitAll().and(). logout().permitAll();
}
```

#### CONFIGURING AUTHENTICATION MANAGERS

- Method to override if you need to modify or add information to gain access to
  your authentication manager
- This code snippet is creating a person and administrator in memory

```java
@Override
public void configure(AuthenticationManagerBuilder auth) throws Exception{
  auth.inMemoryAuthentication(). withUser("person").password("pass").roles("USER").and(). withUser("administrator").password("aPass").roles("USER",
  "ADMIN");
}
```

#### MISCELLANEOUS SECURITY NOTES

- `@EnableGlobalMethodSecurity`
  - Generally added to a configuration file
    - Allows configuration using expression-based annotations
    - Turns on AOP based security
    - AOP is **Aspect Oriented Programming**
      - Going to build aspects rather than objects
      - Can write classes from a configuration standpoint know where it
        is getting intercepted
      - Can take pieces out and put them back in during runtime

# VALIDATION USING JSR-303 (JAVA SPECIFICATION REQUESTS)

#### VALIDATION

- Three ways to do validation
  1. Use the JSR-303 annotations
  2. Write your own
  3. A mix of the previous two
- Using JSR-303, you simply use one of the annotations provided
- For writing your own, you implement `ConstraintValidator` and you define a
  annotation for it
- Configure a `DataBinder` if you want more control and/or don't want to use
  annotations

#### JSR-303

- Spring 3.0 and above support JSR-303. This JSR is all about validation via
  annotation
- Primarily found in the
  - `javax.validation.constraints`
  - `@NotNull`
  - `@Min(<minimum value allowed>`
  - `@Max(<maximum value allowed>)`
  - `@Future`
  - `@Size`

#### JAR DEPENDENCIES

- Needs to be configured
  - One part is the validation engine
  - One part is the API to get the JSR-303 annotations

```xml
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-validator</artifactId>
  <version>5.0.1.Final</version>
</dependency>

<dependency>
  <groupId>javax.validation</groupId>
  <artifactId>validation-api</artifactId>
  <version>1.1.0.Final</version>
</dependency>
```

#### PREPARING FOR VALIDATION

- To use validation, add the following bean into your configuration file

```java
@Bean
public Validator setup303Validation(){
  return new LocalValidatorFactoryBean();
}
```

- This turns on validation
  - There is an expectation that you have a provider loaded
  - Comes from `org.springframework.validation.beanvalidation`
  - Implements what is needed for both Spring and Java's validation
    mechanisms

#### MODIFY YOUR MODEL CLASS

```java
public class Book {
  @NotNull
  @Pattern(regexp="^(97(8|9))?\d{9}(\d|X)$") private String ISBN;
  @NotNull
  @Size(min=5, max=24)
  private String author;
  @NotNull
  private String title;
  @Past
  private Date dueDate;
}
```

#### MODIFY YOUR SPRING BEAN

```java
public class LibraryServiceImpl implements LibraryService{
  private BookRepository repository;

  public LibraryServiceImpl(BookRepository repository){ this.repository = repository;
  }
  public void addBook(@Valid Book b) { return repository.addBook(b);
  }
}
```
