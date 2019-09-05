
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Album</title>
    <style>
        *{box-sizing: border-box;}
        body{margin: 0;}
        main{display: grid;
            grid-template-columns: repeat(auto-fill,minmax(200px, 1fr))}
        img{display: block;
            width: 70%}
    </style>
</head>
<body>
<%
    /*For debugging. To see if any context is received.*/
    String contextPath = request.getContextPath();
    System.out.println(contextPath);
%>

<main role="main">
    <c:forEach var="listImage" items="${imgList}">

        <div>
            <%--I dont really know if it is a correct way to pass data to controller--%>
            <a href="full-info?nameImage=${listImage.fileName}&commentImage=<c:forEach var="listComment" items="${listImage.commentary}">${listComment},</c:forEach>">
                <img src="resources/images/${listImage.fileName}"  alt="please, update resources!"/>
            </a>
        </div>

    </c:forEach>
</main>
<%--

    <c:forEach var="cms" items="${imgList}">
        ${cms.commentary}
    </c:forEach>

--%>


</body>
</html>
