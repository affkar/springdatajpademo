# What this project has.

1. A configured local H2 Database.
2. Liquibase Setup to create tables in the database 
3. Spring Data JPA- Hibernate with a few entities and repository, 
4. Spring Web - A Rest controller to be able to save objects using the repository.
5. Open API Specs in the src/main/resources/openapispec folder - configured within the open-api-generator maven plugin in pom.xml to generate code in the target directory. maven-build-helper adds the generated sources as a source directory. For example - PlayerAPI, AccountAPI, which can be implemented by PlayerController in the src/main/java folder. Note: Unless the PlayerController is annotated with @RestController, it is not exposed by Spring as an endpoint. 
6. Swagger endpoint is available at `<server-path>/swagger-ui/index.html`. 
7. Open API Specs can be generated for any existing controllers by enabling a property springdoc.api-docs.path=/api-docs and the generated spec will be available at `<server-path>/api-docs.yaml`.

## How to run
Just pick the main class with the @SpringBootApplication annotation and start it. 

### Useful Links
1. H2 console will be available at http://localhost:8080/h2_console
2. Postman collection is available in the postman folder. Import it into postman and you should be able to
   * POST - create a player and see a player id in the response.  
   * GET - get a player by id. 
