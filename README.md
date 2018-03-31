# REST-API (Representational State Transfer)

Created by Vince Chang </br>
REST API Training creating a servlet using Maven and Java onto a server that
runs on Tomcat. 


#### Why everyone is excited:
- Client side: thick client 
- NoSQL and REST go together
- NoSQL has no locking
- People can update at the same time and it won’t be a process issue
- Updates happen at the column level, not at the row level


#### XML RULES:
    1. Root
    2. Open/Close
    3. No Overlap


#### SOAP (Simple Object Access Protocol)
- Soap is one of the reasons why we have REST
- REST takes web services and lines it up with HTTP protocol
- Idea: treat everything out there like a resource
    - A service can get, post, delete, patch
- Thick (web browser) -> Thin -> Rich -> Rich.next (angular, react) -> loop
- When designing REST, start with the nouns 
- **JAX-WS** is for SOAP Services
- **JAX-RS** is for REST Services
    - this is the standard for REST services
    - This model has 3 implementations:
        1. Apache CXF
        2. Rest Easy (JBOSS)
        3. Jersey
- Micro-services:
    - have a bunch of services that utilize different services to make business logic which clients have access 
- If you want to make something scalable, make it **stateless**
    - scale *out not vertical*
- QueryParams are not required in the URLs

#### SOAP V.S REST
    SOAP
    - Design: WSDL: Web services description language
    - Disc: UDDI: 

    REST
    - Design: WADL:  Web application description language (but not really used)
    - Disc: 
    - This whole concept is used with Swagger
        - can describe the service to you in a .json
    - REST needs 2 components:
        1. application
        2. root resource 

#### Rules
    1. Don’t violate the HTTP Protocol
    2. We use the verbs on an entity (job)

#### Verbs
    1. GET 
        - Retrieve an obj
        - Pass parameters in the URL
        - GET request gives you *caching*

    2. PUT
        - update entire obj

    3. POST
       - create data
	   - pass parameters in the body, but is a violation
	   - people are doing this because they don’t want to see the information
       on the URL

    4. DELETE
        - delete obj
    
    5. PATCH
        - update a part of the obj


#### Diagram:
- Initial Line (status code: headers)
    - 100 informational
    - 200 status OK
    - 300 redirects
    - 400 client side errors, can recover come, developers deal with this the
    most
    - 500 server side errors, cant recover come
- i.e nothing found in the DB, send back 404 
- don’t violate the HTTP Protocol,
- send back the correct status codes for REST developers to understand how to
fix the problems


#### Maven
- Maven is a build manager 
- the builds will use spring dependencies 
- everything is stored in `home/.m2/repository`
- to run build: mvn test, build, package 
