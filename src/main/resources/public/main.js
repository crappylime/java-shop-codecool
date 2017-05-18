/**
 * Created by michal on 12/05/2017.
 */


function checkLength(mes) {

    var textbox = document.getElementById("searchbox").value.length;
    if (textbox >= 3) {
        document.location.href = mes.href
    }
    document.getElementById("length_error").style.display = "inline";
}