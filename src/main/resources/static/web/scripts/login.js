$(document).ready(function () {
    console.log("ready!");

    $('#registerbutton').click(function () {

        window.location.href="http://localhost:8080/web/register.html";
    });

    $('#summarybutton').click(function () {

        window.location.href="http://localhost:8080/web/resumen.html";
    });


    $('#loginbutton').click(function () {

        var data = {
            "user": $('#email').val(),
        };
        console.log(data.user);
        $.ajax({
            data: data,
            //timeout: 1000,
            type: 'POST',
            url: '/api/usuarios'

        }).done(function (  textStatus, data, jqXHR) {

            console.log("loggin successful")

            document.getElementById("userinfo").innerHTML = "Access granted";

            console.log(data);
            console.log(textStatus);
            console.log(jqXHR);

        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(' Wrong credentials');
            window.location.href="http://localhost:8080/web/register.html";
            console.log(textStatus);
            console.log(jqXHR);
            console.log(errorThrown);

        });
    });

});