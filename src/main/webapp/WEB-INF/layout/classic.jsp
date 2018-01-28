<%@include file="../layout/taglib.jsp" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.css" >



    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div class="container">
    <tilesx:useAttribute name="current"/>

    <nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
        <a class="navbar-brand" href='<spring:url value="/" />'>JBA</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample09"
                aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarsExample09">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ${current == 'index' ? 'active' : ''}">
                    <a class="nav-link" href="<spring:url value="/" />">Home<span class="sr-only">(current)</span></a>
                </li>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item ${current == 'users' ? 'active' : ''}">
                        <a class="nav-link" href="<spring:url value="/users.html" />">Users</a>
                    </li>
                </security:authorize>

                <security:authorize access="isAuthenticated()">
                    <li class="nav-item ${current == 'account' ? 'active' : ''}">
                        <a class="nav-link" href="<spring:url value="/account.html" />">My account</a>
                    </li>
                </security:authorize>

                <security:authorize access="!isAuthenticated() or hasRole('ROLE_ADMIN')">
                    <li class="nav-item ${current == 'sign_up' ? 'active' : ''}">
                        <a class="nav-link" href="<spring:url value="/sign_up.html" />">SignUp</a>
                    </li>
                </security:authorize>
                <security:authorize access="!isAuthenticated()">
                    <li class="nav-item ${current == 'login' ? 'active' : ''}">
                        <a class="nav-link" href="<spring:url value="/login.html" />">Login</a>
                    </li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item active'}">
                        <a class="nav-link" href="<spring:url value="/logout" />">Logout</a>
                    </li>
                </security:authorize>
            </ul>
        </div>
    </nav>
    <tiles:insertAttribute name="body"/>

    <br><br>

    <center>
        <tiles:insertAttribute name="footer"/>
    </center>
</div>
</body>
</html>

