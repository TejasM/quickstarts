travel-spring: Travel Example using Spring 3.1
======================================================
Author: Marius Bogoevici
Level: Advanced
Technologies: JPA 2.0, Junit, JMX, Spring Security, Spring Webflow, Spring Test, and JSP
Summary: This example demonstrates the use of JPA 2.0, Junit, JMX, Spring Security, Spring Webflow, Spring Test, and JSP
Target Product: EAP
Source: <https://github.com/jboss-jdf/jboss-as-quickstart/>

What is this?
-------------

The application this project produces is designed to be run on JBoss Enterprise Application Platform 6 or JBoss AS 7.

Travel Spring showcases the use of Spring Webflow. It configures the flow of booking a hotel in `src/main/resources/META-INF/spring/travel/webflow.xml`.

Urls are mapped by `src/main/resources/META-INF/spring/travel/controllers.xml` and `HotelsController.java` and the various `tiles.xml` files configure which pages are rendered.

There are 4 users and 23 hotels populated in the database by `src/main/resources/import.sql`.

Security during the flow of booking a hotel is configured in `src/main/resources/META-INF/spring/security.xml` using Spring Security. It uses an embedded `<authenciation-provider>` and a custom login page.

System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

Configure Maven
---------------

If you have not yet done so, you must Configure Maven before testing the quickstarts.

Start JBoss Enterprise Application Platform 6 or JBoss AS 7 with the Web Profile
---------------
Open a command line and navigate to the root of the JBoss server directory.

The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

Start JBoss Enterprise Application Platform 6 or JBoss AS 7
-------------------------

Start JBoss Enterprise Application Platform 6 or JBoss AS 7 with the Web Profile


Build and Deploy the Quickstart
-------------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](../README.md#buildanddeploy) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

4. This will deploy target/jboss-as-travel-spring.war to the running instance of the server.

If you don't have maven configured you can manually copy target/jboss-as-travel-spring.war to JBOSS_HOME/standalone/deployments.

_NOTE: To deploy the project in EWS-Tomcat5/EWS-Tomcat6, you can use `-Pews-tomcat5` or `-Pews-tomcat6`_

Access the application
----------------------

The application will be running at the following URL: <http://localhost:8080/jboss-as-travel>.

Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy
    
Or you can manually remove the application by removing jboss-as-travel-spring.war from JBOSS_HOME/standalone/deployments

Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------------------------

You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](../README.md#useeclipse)

Debug the Application
--------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc

