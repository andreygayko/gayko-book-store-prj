<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Comments</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12">
                    <a href="${pageContext.request.contextPath}/logout" class=" btn btn-primary"
                       aria-pressed="true" role="button">Log Out</a>
                    <a href="${pageContext.request.contextPath}/news"
                       class=" btn btn-primary" aria-pressed="true" role="button">News</a>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form action="${pageContext.request.contextPath}/news/create.comments/${news.id}"
                          method="post">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">news id</th>
                                <th scope="col">news title</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>${news.id}</th>
                                <th>${news.title}</th>
                            </tr>
                            </tbody>
                        </table>
                        <label class="my-1 mr-2">Comment</label>
                        <input type="text" name="content">
                        <button type="submit" class="btn btn-primary my-1">Create comment</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>
