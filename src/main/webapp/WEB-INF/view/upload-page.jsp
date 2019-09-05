<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Upload page</title>
</head>
<body>
    <form method="POST" action="upload" enctype="multipart/form-data" id="uploadForm">
        <input type="file" name="myFile" accept="image/*" required /> <br/>
        <input type="submit" value="Upload"/>
    </form>
    <br/>

    <textarea name="uploadCommentary" form="uploadForm">Please leave your comment</textarea>

</body>
</html>
