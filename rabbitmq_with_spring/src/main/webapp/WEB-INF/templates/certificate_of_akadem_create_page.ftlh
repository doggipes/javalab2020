<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Akadem</title>
</head>
<body>
    <@spring.bind "AkademForm"/>
    <form method="post">
        Name: <br>
        <@spring.formInput "AkademForm.name"/>
        <br><br>
        Surname: <br>
        <@spring.formInput "AkademForm.surname"/>
        <br><br>
        Passport Number: <br>
        <@spring.formInput "AkademForm.pass_number"/>
        <br><br>
        Age: <br>
        <@spring.formInput "AkademForm.age"/>
        <br><br>
        Date: <br>
        <@spring.formInput "AkademForm.date"/>
        <br><br>
        Email: <br>
        <@spring.formInput "AkademForm.email"/>
        <input type="submit" value="Submit">
    </form>
</body>
</html>