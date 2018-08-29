<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spring Security</title>

    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">

    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
</head>

<body>

<div class="container" style="width: 300px;">
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">

        <h2 class="form-signin-heading">Please sign in</h2>

        <input type="text" class="form-control" name="j_username" placeholder="Username" required autofocus
               value="">

        <input type="password" class="form-control" name="j_password" placeholder="Password" required value="admin">

        <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>

    </form>
</div>

</body>
</html>
