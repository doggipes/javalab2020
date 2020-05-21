<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Feed</title>
</head>
<body>
<form action="" method="post">
    <input type="text" name="text" placeholder="Write a post..."> <br><br>
    <input type="submit" class="send" value="Post">
</form>
<#list posts as post>
    ${post.user.email}
    ${post.text} <br><br>
</#list>
</body>
</html>