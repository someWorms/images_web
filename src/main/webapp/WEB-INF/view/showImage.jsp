<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>full picture</title>


</head>
<body>
    <div>
        <img src="/resources/images/${nameImage}" style="width: 100%;"/>
    </div>
<hr>
    <h2>Comments:</h2><br/>
    <div>
        <c:forEach var="comments" items="${commentImage}">
            ${comments}
        </c:forEach>
    </div>

    <div>
        <textarea name="addCommentary" form="addComment"> Leave your comment </textarea>
        <form action="addComment?${nameImage}" method="get" id="addComment">
            <input type="submit" value="Add">
        </form>
    </div>
</body>
</html>
