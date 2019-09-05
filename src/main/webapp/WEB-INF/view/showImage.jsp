<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>full picture</title>
    <style>
        .stylish{
            font-size: 22px;
        }

    </style>
</head>
<body>
    <div>
        <img src="/resources/images/${nameImage}" style="width: 100%;" alt=" "/>
    </div>
<hr>
    <h2>Comments:</h2><br/>
    <div class="stylish">
        <c:forEach var="comments" items="${commentImage}">
            <br/>${comments}
        </c:forEach>
    </div>
    <br>
    <div class="stylish">
        <br/>
        <textarea name="addCommentary" form="addComment"> Leave your comment </textarea><br/>

        <form action="addComment" method="post" id="addComment">
                <input type="hidden" name="nameImage" value="${nameImage}">
            <%-- <input type="text" name="addCommentary"> --%>
                <input type="submit" value="Add">
        </form>
        <a href="/album">To album</a>
        <br/>
    </div>
</body>
</html>
