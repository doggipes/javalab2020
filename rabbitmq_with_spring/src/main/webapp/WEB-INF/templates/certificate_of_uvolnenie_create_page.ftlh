<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring />
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Uvolnenie</title>
</head>
<body>
    <@spring.bind "CertificateForm"/>
    <form method="post">
        Name: <br>
        <@spring.formInput "CertificateForm.name"/>
        <br><br>
        Surname: <br>
        <@spring.formInput "CertificateForm.surname"/>
        <br><br>
        Passport Number: <br>
        <@spring.formInput "CertificateForm.pass_number"/>
        <br><br>
        Age: <br>
        <@spring.formInput "CertificateForm.age"/>
        <br><br>
        Date: <br>
        <@spring.formInput "CertificateForm.date"/>
        <br><br>
        Email: <br>
        <@spring.formInput "CertificateForm.email"/>
        <br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>