<p align="right">
<a href="http://www.canoo.com"><img src="http://www.guigarage.com/wordpress/wp-content/uploads/2016/08/canoo_support.png"/></a>
</p>

# Microservice Remoting sample

This sample shows how the remoting layer of the Dolphin Platform can easily be used and integrated
in a microservice architecture. This sample contains 2 microservices that provide different remoting controllers. The clients easily integrate view widgets for all this controllers in one view. By doing so a dynamic client view can easily be build based on several widgets that receive
data from different servers.

![microservices](readme/microservice.png "microservices")

## Modules

TODO

![modules](readme/modules.png "modules")

## Start the sample

The sample provides 2 Spring Boot based microservices.

### Spring Boot server 1
To start the Spring Boot server simply run `mvn spring-boot:run` from the `server-spring-data1` folder. Next to this the
server can be started by the `com.canoo.platform.samples.microservices.product.ProductServer` class.

The server is running at `http://localhost:8080`

### Spring Boot server 2
To start the Spring Boot server simply run `mvn spring-boot:run` from the `server-spring-data2` folder. Next to this the
server can be started by the `com.canoo.platform.samples.microservices.product.ProductServer` class.

The server is running at `http://localhost:8081`

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
