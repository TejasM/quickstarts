<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Java EE 6 Starter Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
	</head>

	<body>
		<div id="container">
			<div id="content">
				<div id="sidebar">
					<h3>Find out more</h3>
					<p>Learn more about JBoss AS 7.</p>
					<ul>
						<li><a
							href="https://docs.jboss.org/author/display/AS7/Getting+Started+Developing+Applications+Guide">JBoss
                  			AS 7 Getting Started Developing Applications Guide</a></li>
                  		<li><a href="jboss.org/jbossas">JBoss AS 7 project
                  			site</a></li>
					</ul>
					<p>Learn about the Java EE 6 platform and the component
						model it provides.</p>
					<ul>
						<li><a
	    	              href="http://download.oracle.com/javaee/6/tutorial/doc">Java
    	                 EE 6 tutorial</a></li>
		               <li><a
		                  href="http://docs.jboss.org/cdi/spec/1.0/html">JSR-299:
		                     CDI specification</a></li>
		               <li><a
		                  href="https://sites.google.com/site/cdipojo/get-started">CDI
		                     Source</a></li>
					</ul>
					<p>Dive into Weld, the CDI reference implementation, and
						discover portable extensions Seam 3 offers.</p>
		            <ul>
		               <li><a
		                  href="http://docs.jboss.org/weld/reference/latest/en-US/html">Weld
		                     reference guide</a></li>
		               <li><a href="http://seamframework.org/Weld">Weld
		                     project</a></li>
		               <li><a href="http://seamframework.org/Seam3">Seam
		                     3 project</a></li>
		               <li><a
		                  href="http://seamframework.org/Community/Forums">User
		                     forums</a></li>
		               <li><a
		                  href="http://seamframework.org/Community/MailingLists">Mailing
		                     lists</a></li>
		               <li><a
		                  href="https://issues.jboss.org/browse/WELDRAD">Archetype
		                     issue tracker</a></li>
		            </ul>
		            <p>Explore the Spring Framework, the application
		            development framework for enterprise Java.</p>
		            <ul>
		            	<li><a href="http://www.springframework.org">Spring Framework
		            		site</a></li>
		            </ul>
		            <p>
		               If you have an add-on, please <a
		                  href="http://seamframework.org/Community/Forums">let
		                  us know</a> and consider <a
		                  href="http://seamframework.org/Seam3/GetInvolved">contributing</a>
		               it back to the community!
		            </p>
				</div>
				<div id="insertedContent">
					<h1>Welcome to JBoss!</h1>
				</div>

				<div>
					<p>You have successfully deployed a Java EE 6 web application.</p>
					<h3>Your application can run on:</h3>
					<img src="<c:url value="/static/resources/gfx/dualbrand_as7eap.png"/>">
				</div>

				<form:form commandName="newMember" id="reg">
					<h2>Member Registration</h2>
					<p>Enforces annotation-based constraints defined on the model class</p>
					<table>
						<tbody>
							<tr>
								<th><form:label path="name">Name:</form:label></th>
								<td><form:input path="name"/></td>
								<td/>
							</tr>
							<tr>
								<th><form:label path="email">Email:</form:label></th>
								<td><form:input path="email"/></td>
								<td/>
							</tr>
							<tr>
								<th><form:label path="phoneNumber">Phone #:</form:label>
								<td><form:input path="phoneNumber"/></td>
								<td/>
							</tr>
						</tbody>
					</table>
					<br/>
					<input type="submit" value="Register"/>
				</form:form>
		</div>

		<h2>Members</h2>
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Email</th>
					<th>Phone #</th>
					<th>REST URL</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${members}" var="member">
					<tr>
						<td>${member.id}</td>
						<td>${member.name}</td>
						<td>${member.email}</td>
						<td>${member.phoneNumber}</td>
						<td><a href="<c:url value="/members/${member.id}"/>">/members/${member.id}</a></td>
				</c:forEach>
			</tbody>
		</table>
		<div id="footer">
			REST URL for all members: <a href="<c:url value="/members"/>">/members</a>
		</div>
	</div>
	</body>
</html>
