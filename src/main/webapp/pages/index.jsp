<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
    <link href="<c:url value="/pages/css/bootstrap-grid.css" />" rel="stylesheet">
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
</head>

<body>

<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

        <sec:authorize access="!isAuthenticated()">
            <p><a class="btn btn-lg btn-success" href="<c:url value="/faces/pages/login.jsp" />" role="button">Войти</a>
            </p>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <p></p>
            <p>Ваш логин: <sec:authentication property="principal.username"/></p>
            <p></p>
            <p></p>
            <p></p>
            <p>Теперь вы можете посмотреть список доступных доваров и отредактировать его</p>
            <p><a class="btn btn-lg btn-success" href="<c:url value="/faces/front/pricesList.xhtml" />" role="button">Загрузить</a>
            </p>


            <c:url value="/j_spring_security_logout" var="logoutUrl"/>
            <form action="${logoutUrl}" method="post" id="logoutForm">
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
            <script>
                function formSubmit() {
                    document.getElementById("logoutForm").submit();
                }
            </script>

            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <h2>
                    User : ${pageContext.request.userPrincipal.name} | <a
                        href="javascript:formSubmit()"> </a>
                    <a href="<c:url value="/logout" />">Logout</a>
                </h2>
            </c:if>
        </sec:authorize>
    </div>
</div>
</body>
</html>
