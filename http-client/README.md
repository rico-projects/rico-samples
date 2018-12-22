<p align="right">
<a href="http://www.canoo.com"><img src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/canoo_support.png"/></a>
</p>

# Simple REST example

This sample shows the usage of the Dolphin Platform HTTP client.

The Dolphin Platform contains a HTTP client that can easily used to call endpoints on any server. The HTTP
client has some big benefits againts other HTTP clients:

* It provides an easy to use fluent API
* The same API is used for all client toolkits and programming languages
* The API takes care about the UI thread and provides functionallity to do HTTP calls without blocking the UI thread
* The HTTP clients supports raw, String and JSON data by default
* The HTTP client automatically generates object instances based on JSON data

The given sample provides a JavaEE and a Spring server that both provide the same REST endpoints and some
clients that makes use of the Dolphin Platform HTTP client to access those endpoints.

## Modules

The sample is seperated in several modules. The following image shows the dependecies of the modules:

![modules](readme/modules.png "modules")

For all Java modules the model of the application is defined in the `common` module. By doing so the module definition
can easily be shared between client and server modules.

## Start the sample

The sample provide a JavaEE and a Spring based server. Only one of the servers can be started since both use the
same port. The server provides the application at `http://localhost:8080/simple-rest`.

Once a server is running multiple instances of the clients can be started. All clients do HTTP calls to a server at
 `http://localhost:8080/simple-rest`.

All modules provide a Maven target to start the application.

### JavaEE server
To start the JavaEE server simply run `mvn wildfly:run` from the `server-javaee` folder. Next to this the server can
be deployed to any JavaEE 6 application server. To do so run `mvn verify` from the `server-javaee` folder. The created
`process-monitor.war` in the `target` folder can be deployed as JavaEE web application. The application must be reachable
under `http://localhost:8080/simple-rest`.

### Spring Boot server
To start the Spring Boot server simply run `mvn spring-boot:run` from the `server-spring` folder. Next to this the
server can be started by the `com.canoo.dolphin.samples.rest.ServerApplication` class.

### Java client
To start the Java client simply run `mvn jfx:run` from the `client-java` folder. Next to this the client can be
started by the `com.canoo.dolphin.samples.rest.RestClient` class.

### JavaFX client
To start the JavaFX client simply run `mvn jfx:run` from the `client-javafx` folder. Next to this the client can be
started by the `com.canoo.dolphin.samples.rest.RestClient` class.

## License
The project is released as open source under the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)

<br/><br/>
<p align="center">
<sub>About Canoo</sub>
</p>
<p align="center">
<a title="Canoo Website" href="http://www.canoo.com/"><img style="margin:12px !important;" src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/color-link-48-1.png"/></a>
<a title="Canoo at Twitter" href="https://twitter.com/canoo"><img style="margin:12px !important;" src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/color-twitter-48-1.png"/></a>
<a title="Canoo at LinkedIn" href="https://www.linkedin.com/company/canoo-engineering-ag"><img style="margin:12px !important;" src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/color-linkedin-48-1.png"/></a>
<a title="Canoo at Xing" href="https://www.xing.com/companies/canooengineeringag"><img style="margin:12px !important;" src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/xing-48-1.png"/></a>
<a title="Canoo at YouTube" href="https://www.youtube.com/user/canoovideo"><img style="margin:12px !important;" src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/color-youtube-48-1.png"/></a>
<a title="Canoo at GitHub" href="https://github.com/canoo"><img style="margin:12px !important;" src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/color-github-48-1.png"/></a>
<a title="Contact Canoo" href="mailto:info@canoo.com"><img style="margin:12px !important;" src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/color-forwardtofriend-48-1.png"/></a>
</p>
