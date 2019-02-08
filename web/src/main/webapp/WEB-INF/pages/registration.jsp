<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Registration page</title>
</head>
<html lang="en">
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form:form action="${pageContext.request.contextPath}" modelAttribute="user" method="post">
                <form:errors path="*" cssClass="error" element="div"/>
                <div class="form-group">
                    <form:label path="name">Name</form:label>
                    <form:input path="name" class="form-control" placeholder="Name" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="surname">Surname</form:label>
                    <form:input path="surname" class="form-control" placeholder="Surname" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="email">Email</form:label>
                    <form:input path="email" class="form-control" placeholder="email" required="true"/>
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input path="password" class="form-control" placeholder="Password"
                                type="password" required="true"/>
                </div>
                <button type="submit" class="btn btn-primary">Sign In</button>
                <input type="button" class="btn btn--primary" value="Log In"
                       onclick='location.href="/login"'>
            </form:form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>