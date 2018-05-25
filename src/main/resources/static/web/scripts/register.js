$(document).ready(function () {
    console.log("ready!");

    $('#gotologin').click(function () {
        window.location.href="http://localhost:8080/web/login.html";

    });

    $('#registerbutton').click(function () {

        var data = {
            "user": $('#email').val(),
            "name": $('#name').val(),
            "business": $('#business').val()
        };
        console.log(data.user);
        $.ajax({
            data: data,
            //timeout: 1000,
            type: 'POST',
            url: '/api/register'

        }).done(function (  textStatus, data, jqXHR) {

            console.log("register successful")
            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);
            alert(' User Registered. Go back to log in to access');

        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(' failed');

            console.log(textStatus);
            console.log(jqXHR);
            console.log(errorThrown);

        });
    });

});