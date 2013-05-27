<!DOCTYPE html>
<!--
JBoss, Home of Professional Open Source
Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
contributors by the @authors tag. See the copyright.txt in the
distribution for a full listing of individual contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/headTag.jsp"/>
<body>

<script>
    $(function () {
        $("#birthDate").datepicker({ dateFormat: 'yy/mm/dd'});
    });
</script>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${pet['new']}">
            <c:set var="method" value="post"/>
        </c:when>
        <c:otherwise>
            <c:set var="method" value="put"/>
        </c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${pet['new']}">New </c:if>
        Pet
    </h2>

    <form:form modelAttribute="pet" method="${method}"
               class="form-horizontal">
        <div class="control-group" id="owner">
            <label class="control-label">Owner </label>

            <c:out value="${pet.owner.firstName} ${pet.owner.lastName}"/>
        </div>
        <petclinic:inputField label="Name" name="name"/>
        <petclinic:inputField label="Birth Date" name="birthDate"/>
        <div class="control-group">
            <petclinic:selectField name="type" label="Type " names="${types}" size="5"/>
                <%--            <label class="control-label">Type </label>
                            <form:select path="type" items="${types}" size="5"/>--%>
        </div>
        <div class="form-actions">
            <c:choose>
                <c:when test="${pet['new']}">
                    <button type="submit">Add Pet</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update Pet</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
    <c:if test="${!pet['new']}">
    </c:if>
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
