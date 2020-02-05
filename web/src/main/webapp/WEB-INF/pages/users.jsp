<%--
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="util/head.jsp"/>
    <title>Users page</title>
</head>
<body>
<div class="container">
    <jsp:include page="util/logo.jsp"/>

    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/dispatcher?command=deleteUser" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/dispatcher?command=addUser" class="btn btn-primary" aria-pressed="true" role="button">ADD</a>
                        <button type="submit" class="btn btn-primary">DELETE</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Email</th>
                                <th scope="col">FirstName</th>
                                <th scope="col">LastName</th>
                                <th scope="col">Role</th>
                                <th scope="col">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <th scope="row"><input type="checkbox" name="ids" value="${user.id}"></th>
                                    <td>${user.email}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.role}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/dispatcher?command=updateUser&id=${user.id}" class="btn btn-primary" aria-pressed="true"
                                           role="button">UPDATE</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<jsp:include page="util/js.jsp"/>
</body>
</html>--%>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="securiry" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
&lt;%&ndash;    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>&ndash;%&gt;
    <title>Users page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-16">
            <form action="${pageContext.request.contextPath}/store" method="get">
                <div class="row">
                    <div class="col-md-16">
                        <a href="${pageContext.request.contextPath}/logout"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Log out</a>
                        <securiry:authorize access="hasAuthority('VIEW_STORE')">
                            <button type="submit" class="btn btn-primary">Store</button>
                        </securiry:authorize>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-16">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <securiry:authorize access="hasAuthority('SECURITY')">
                                    <th scope="col">id</th>
                                </securiry:authorize>
                                <th scope="col">Email</th>
                                <th scope="col">Name</th>
                                <th scope="col">Surname</th>
                                <securiry:authorize access="hasAuthority('SALE')">
                                    <th scope="col">Phone</th>
                                </securiry:authorize>
                                <securiry:authorize access="hasAuthority('CHANGE_USER_ROLE')">
                                    <th scope="col">Role</th>
                                </securiry:authorize>
                                <th scope="col">profile id</th>
                                <th scope="col">status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <jsp:useBean id="users" scope="request" type="java.util.List"/>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <th scope="row"><label>
                                        <input type="checkbox" name="ids" value="${user.id}">
                                    </label></th>
                                    <securiry:authorize access="hasAuthority('SECURITY')">
                                        <th>${user.id}</th>
                                    </securiry:authorize>
                                    <th>${user.email}</th>
                                    <th>${user.name}</th>
                                    <th>${user.surname}</th>
                                    <securiry:authorize access="hasAuthority('SALE')">
                                        <th>${user.profile.phone}</th>
                                    </securiry:authorize>
                                    <securiry:authorize access="hasAuthority('CHANGE_USER_ROLE')">
                                        <th>${user.role.name}</th>
                                    </securiry:authorize>
                                    <th>${user.profile.userId}</th>
                                    <th>${user.status}</th>
                                    <securiry:authorize access="hasAuthority('SHOW_USER_ORDERS')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/orders/user.orders/${user.id}"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">User orders</a>
                                        </td>
                                    </securiry:authorize>
                                    <securiry:authorize access="hasAuthority('CHANGE_USER_ROLE')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/users/user.role/${user.id}"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Change Role</a>
                                        </td>
                                    </securiry:authorize>
                                    <securiry:authorize access="hasAuthority('CUSTOMER')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/profile/${user.id}"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Profile</a>
                                        </td>
                                    </securiry:authorize>
                                    <securiry:authorize access="hasAuthority('CHANGE_USER_PASSWORD')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/users/user.password/${user.id}"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Change Password</a>
                                        </td>
                                    </securiry:authorize>
                                    <securiry:authorize access="hasAuthority('DISABLE_USER')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/users/${user.id}/enable"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Enable</a>
                                        </td>
                                    </securiry:authorize>
                                    <securiry:authorize access="hasAuthority('DISABLE_USER')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/users/${user.id}/disable"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Disable</a>
                                        </td>
                                    </securiry:authorize>
                                    <securiry:authorize access="hasAuthority('DELETE_USER')">
                                        <td>
                                            <a href="${pageContext.request.contextPath}/users/${user.id}/delete"
                                               class="btn btn-primary"
                                               aria-pressed="true" role="button">Delete</a>
                                        </td>
                                    </securiry:authorize>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Navigation">
                            <ul class="pagination">
                                <c:forEach var="page" begin="1" end="${pages}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/users?page=${page}" }>${page}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="securiy" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Users</title>
</head>
<body>
<div class="container">
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/logout"
                   class=" btn btn-primary" aria-pressed="true" role="button">Log Out</a>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Email</th>
                        <th scope="col">Name</th>
                        <th scope="col">Surname</th>
                        <th scope="col">Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                    <tr>
                       <%-- <th scope="row"><label>
                            <input type="checkbox" name="ids" value="${user.id}">
                        </label></th>--%>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.surname}</td>
                        <td>${user.role.name}</td>
                           <security:authorize access="hasAuthority('DISABLE_USER')">
                               <td>
                                   <a href="${pageContext.request.contextPath}/users/${user.id}/disable"
                                      class="btn btn-primary"
                                      aria-pressed="true" role="button">Disable</a>
                               </td>
                           </security:authorize>
                           <security:authorize access="hasAuthority('DISABLE_USER')">
                               <td>
                                   <a href="${pageContext.request.contextPath}/users/${user.id}/enable"
                                      class="btn btn-primary"
                                      aria-pressed="true" role="button">Enable</a>
                               </td>
                           </security:authorize>
                           <security:authorize access="hasAuthority('CHANGE_USER_ROLE')">
                               <td>
                                   <a href="${pageContext.request.contextPath}/users/${user.id}/role"
                                      class="btn btn-primary"
                                      aria-pressed="true" role="button">Change Role</a>
                               </td>
                           </security:authorize>
                           <security:authorize access="hasAuthority('CHANGE_USER_PASSWORD')">
                               <td>
                                   <a href="${pageContext.request.contextPath}/users/${user.id}/password"
                                      class="btn btn-primary"
                                      aria-pressed="true" role="button">Change Password</a>
                               </td>
                           </security:authorize>
                           <security:authorize access="hasAuthority('DELETE_USER')">
                               <td>
                                   <a href="${pageContext.request.contextPath}/users/${user.id}/delete"
                                      class="btn btn-primary"
                                      aria-pressed="true" role="button">Delete</a>
                               </td>
                           </security:authorize>
                           <security:authorize access="hasAuthority('SHOW_USER_ORDERS')">
                               <td>
                                   <a href="${pageContext.request.contextPath}/orders"
                                      class="btn btn-primary"
                                      aria-pressed="true" role="button">User orders</a>
                               </td>
                           </security:authorize>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>