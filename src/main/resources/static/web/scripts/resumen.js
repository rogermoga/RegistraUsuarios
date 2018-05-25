var datos;


$(document).ready(function () {
    console.log("ready!");

    $('#loginbutton').click(function () {

        window.location.href="http://localhost:8080/web/login.html";
    });


    $.ajax("/api/resumen").done(function (result) {
        datos = result;

        result

        for ( var i=0; i< result.length; i++){

            var userrow = document.createElement("tr");

            var namecell = document.createElement("td");
            var emailcell = document.createElement("td");
            var businesscell = document.createElement("td");
            var registrationDate = document.createElement("td");
            var accesstimes = document.createElement("td");

            namecell.innerHTML=result[i].Nombre;
            emailcell.innerHTML=result[i].Email;
            businesscell.innerHTML=result[i].Business;
            registrationDate.innerHTML=new Date(result[i].FechaRegistro);
            accesstimes.innerHTML= MakeAccessList(result[i].AccessTimes);

            userrow.appendChild(namecell);
            userrow.appendChild(emailcell);
            userrow.appendChild(businesscell);
            userrow.appendChild(registrationDate);
            userrow.appendChild(accesstimes);

            document.getElementById("tabladatos").appendChild(userrow);
        }
    })
});

function  MakeAccessList(arraydates) {

    var accessList = "";
    for ( var i= 0; i< arraydates.length; i++){

        var access = "<li>" + new Date(arraydates[i]) + "</li>";
        accessList += access;
    }
    return "<ul>" + accessList + "</ul>";
}
