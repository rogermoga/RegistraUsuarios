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
            type: 'POST',
            url: '/api/usuarios'

        }).done(function (responseText, statusText , result) {

            console.log("loggin successful");
            console.log(result);
            document.getElementById("userinfo").innerHTML = "Access granted";

        }).fail(function (result) {

            console.log("the error is : " + result.status);
                if(result.status == 406){
                    document.getElementById("userinfo").innerHTML = "Please enter an email";
                }else{
                    alert(' Wrong credentials. Please register');
                    window.location.href="http://localhost:8080/web/register.html";
                }
        });
    });
});