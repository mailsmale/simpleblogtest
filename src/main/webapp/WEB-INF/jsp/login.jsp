<%@include file="../layout/taglib.jsp" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<style>
    body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
    }

    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }

    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }

    .form-signin .checkbox {
        font-weight: 400;
    }

    .form-signin .form-control {
        position: relative;
        box-sizing: border-box;
        height: auto;
        padding: 10px;
        font-size: 16px;
    }

    .form-signin .form-control:focus {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<form class="form-signin" name="f" action="<spring:url value='/j_spring_security_check'/>" method="POST">
    <h2 class="form-signin-heading">Please sign in</h2>
    <label for="name" class="sr-only">User</label>
    <input type="text" id="name" name="j_username" class="form-control" placeholder="Email address" required="" autofocus="">
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required="">
    <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit" value="Login">Sign in</button>
</form>
</body>
</html>
