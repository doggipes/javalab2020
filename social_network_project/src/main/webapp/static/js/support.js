function sendMessage(pageId, text) {
    let body = {
        pageId: pageId,
        text: text
    };

    $.ajax({
        url: "/messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {

        }
    });
}

var body = $("#body");
var pageId = body.data("pageid");
var messages = $('#messages');
// LONG POLLING
(function receiveMessage() {
    $.ajax({
        url: "/messages?pageId=" + pageId,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
                messages.prepend('<li style=\'list-style-type: none\'>'+ response[0].user.name + ': ' + response[0].text + '</li>');
            receiveMessage();
        }
    })
})();