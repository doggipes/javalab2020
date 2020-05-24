<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Support Service</title>
</head>
<body id="body" data-pageid="${pageId}">
<h1 id="h1">Ваш идентификатор - ${pageId}</h1>
<div>
    <input id="message" placeholder="Ваше сообщение">
    <button onclick="sendMessage('${pageId}',
            $('#message').val())">Отправить</button>
</div>
<div>
    <ul id="messages">
        <#if messages??>
        <#list messages as message>
            <li style="list-style-type: none">${message.user.name}: ${message.text}</li>
        </#list>
        </#if>
    </ul>
</div>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="/static/js/support.js"></script>
</body>
</html>