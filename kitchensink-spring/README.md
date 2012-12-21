kitchensink-spring: Kitchensink Examples using Spring 3.2
===============================================================
Author: Marius Bogoevici
Level: Intermediate
Technologies: JSP, JPA, JSON, Spring, JUnit
Summary: An example that incorporates multiple technologies
Target Product: EAP
Source: <https://github.com/jboss-jdf/jboss-as-quickstart/>

What is it?
-----------

This is quickstart is actually 5 quickstarts in one.

All of them use the following setup:

* Sample, deployable Maven 3 project to help you get your foot in the door developing with Java EE 6 on JBoss Enterprise Application Platform 6 or JBoss AS 7.

* Setup to allow you to create a compliant Java EE 6 application using JSP, JPA 2.0 and Spring 3.2. A Persistence unit and some sample persistence and transaction code is included to introduce you to database access in enterprise Java.

The basic version show cases the above technologies without any extra features, while the other four versions showcase various Spring 3.2 features as follows:

_NOTE: Many of the following use cases may seem contrived, that is because they are. The sole purpose of these 4 quickstarts is to showcase Spring 3.2's outstanding new features._

* AsyncRequestMapping showcases Spring 3.2's Asynchronous Callable Function [More on Spring's Asynchronous Request Processing](<http://static.springsource.org/spring/docs/3.2.x/spring-framework-reference/html/mvc.html#mvc-ann-async>)
* ControllerAdvice showcases Spring 3.2's new `@ControllerAdvice` which allows the user to configure various aspects such as global exception handler, init binders, and adding global model atrributes
* MatrixVariables showcases Spring 3.2's added support for Matrix Variables in urls, e.g. http://localhost:8080/something;p=2;q=3. In this quickstart the matrix variables are used for filtering purposes.
* Spring MVC Test: Adds `MemberMockMVCTest.java` to showcase a use case of `MockMVC` and `RestTemplate` to test the mvc aspect of the application which was previously not possible without external tools such as Arquillian.

System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss Enterprise Application Platform 6 or JBoss AS 7.

Configure Maven
---------------

1. If you have not yet done so, you must Configure Maven before testing the quickstarts.
2. Start JBoss Enterprise Application Platform 6 or JBoss AS 7 with the Web Profile
3. Open a command line and navigate to the root of the JBoss server directory.
4. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

Build and Deploy the Quickstart
-------------------------------

_NOTE: The following applies to each of the modules in this project. {module} should be replaced by the respective module that you are working with._

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](../README.md#buildanddeploy) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

4. This will deploy module/target/jboss-as-kitchensink-spring-{module}.war to the running instance of the server.

If you don't have maven configured you can manually copy module/target/jboss-as-kitchensink-spring-{module}.war to JBOSS_HOME/standalone/deployments.

Access the application
----------------------

The application will be running at the following URL: http://localhost:8080/jboss-as-kitchensink-spring-{module}/.

Undeploy the Archive
----------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy

Or you can manually remove the application by removing jboss-as-kitchensink-spring-{module}.war from JBOSS_HOME/standalone/deployments

Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------

You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](../README.md#useeclipse)


Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc

