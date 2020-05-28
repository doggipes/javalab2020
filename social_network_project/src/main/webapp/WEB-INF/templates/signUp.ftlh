<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
</head>
<body>
<h1>Sign Up Page</h1>

<@spring.bind "SignUpForm"/>
<form action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    Email: <br>
        <@spring.formInput "SignUpForm.email"/>
        <@spring.showErrors "<br>"/>
    <br><br>
    Name: <br>
        <@spring.formInput "SignUpForm.name"/>
        <@spring.showErrors "<br>"/>
    <br><br>
    Password: <br>
        <@spring.formInput "SignUpForm.password"/>
        <@spring.showErrors "<br>"/>
    <input type="submit" value="Submit">
</form>

<br>
<br>
<a href="/login">Sign Up</a>
</body>
</html>