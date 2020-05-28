<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>

<form action="/login" method="post">
    <input type="text" name="email" placeholder="Email"> <br><br>
    <input type="password" name="password" placeholder="Password"> <br><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <label>
        <input type="checkbox" name="remember-me">Запомни меня
    </label>
    <input type="submit" class="send" value="Login">
</form>
<br>
<br>
<a href="/signUp">Login</a>
</body>
</html>