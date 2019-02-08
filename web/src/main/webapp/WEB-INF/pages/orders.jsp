<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="securiy" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Orders</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <form action="${pageContext.request.contextPath}/orders" method="get">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${pageContext.request.contextPath}/logout"
                           class=" btn btn-primary"
                           aria-pressed="true" role="button">Log out</a>
                        <security:authorize access="hasAuthority('VIEW_ITEMS')">
                            <td>
                                <a href="${pageContext.request.contextPath}/items"
                                   class="btn btn-primary"
                                   aria-pressed="true" role="button">Items</a>
                            </td>
                        </security:authorize>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">order id</th>
                                <th scope="col">user id</th>
                                <th scope="col">item id</th>
                                <th scope="col">item name</th>
                                <th scope="col">created</th>
                                <th scope="col">quantity</th>
                                <th scope="col">status</th>
                            </tr>
                            </thead>
                            <tbody>
<%--
                            <jsp:useBean id="orders" scope="request" type="java.util.List"/>
--%>
                            <c:forEach items="${orders}" var="order">
                                <tr>
                                    <th>${order.id}</th>
                                    <th>${order.userDTO.id}</th>
                                    <th>${order.itemDTO.id}</th>
                                    <th>${order.itemDTO.name}</th>
                                    <th>${order.created}</th>
                                    <th>${order.quantity}</th>
                                    <th>${order.status}</th>
                                    <security:authorize access="hasAuthority('CHANGE_ORDER_STATUS')">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/orders/user.orders.change.status/${order.id}"
                                           class="btn btn-primary"
                                           aria-pressed="true" role="button">Change status</a>
                                    </td>
                                    </security:authorize>
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
</body>
</html>
